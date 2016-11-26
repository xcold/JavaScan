package com.xcold.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.xcold.config.Config;
import com.xcold.interfaces.plugin;

public class Index {
	public static void main(String[] args) {
		// Index index=new Index();
		View view = new View();
	}

	public void loadPlugin() {

		String path = "plugin/pluginB.jar";
		File paths = new File(path);

		System.out.println(paths.getAbsolutePath());
		try {
			URL[] urls = new URL[1];
			urls[0] = new URL("file:" + paths.getAbsolutePath());
			URLClassLoader plugins = new URLClassLoader(urls);

			Class<?> clazz;
			try {
				clazz = plugins.loadClass("com.xcold.plugin.pluginB");
				try {
					Object instance = clazz.newInstance();
					plugin A = (plugin) instance;
					A.run();

					System.out.println(Config.getIp());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
