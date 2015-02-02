package com.almondtools.regexbench;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class Samples {

	private Queue<String> samples;

	@Setup
	public synchronized void setup() {
		this.samples = new LinkedList<String>(findSampleFiles());
	}

	public List<String> findSampleFiles() {
		try {
			List<String> files = new ArrayList<String>();
			URL url = Samples.class.getClassLoader().getResource("samples");
			URLConnection connection = url.openConnection();
			if (connection instanceof JarURLConnection) {
				JarURLConnection jarconnection = (JarURLConnection) connection;
				JarFile jarFile = jarconnection.getJarFile();
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (!entry.isDirectory() && entry.getName().endsWith(".sample")) {
						files.add(entry.getName());
					}
				}
				return files;
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		}
	}

	public String next() {
		return samples.poll();
	}

}
