package com.xcold.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;
import com.xcold.config.*;
import com.xcold.interfaces.PluginService;
//import com.xcold.interfaces.plugin;

public class PluginManage {
	

	
	public void  init(){
		getPlugin();
		loadPlugin();
	}
public void loadPlugin(){
	
		String path="";
		//File paths=new File(path);
		int size=Config.plugins.size();
		
		try {
			URL[] urls=new URL[size];
			for(int i=0;i<size;i++){
				
				path=Config.getPlugindir()+"/"+Config.plugins.get(i).getName()+".jar";
				
				File paths=new File(path);
				
				urls[i]=new URL("file:"+paths.getAbsolutePath());
			}
			Config.pluginLoads=new URLClassLoader(urls);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PluginService getInstance(String pluginName){
		//URLClassLoader plugins=new URLClassLoader(pluginName);
		
		Class<?> clazz;
		try {
			clazz = Config.pluginLoads.loadClass("com.xcold.plugin."+pluginName);
			try {
				Object instance = clazz.newInstance();
				PluginService A=(PluginService)instance;
				//A.runService();
				return A;
				//System.out.println(Config.getIp());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
		
	}
	
	
	
	public void getPlugin(){
		Vector<plugin> plugins=new Vector<plugin>();
		File dir=new File(Config.getPlugindir());
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			//System.out.println(files[0]);
			for(int i =0 ;i<files.length;i++){
				File file=files[i];
				if(file.getName().substring(file.getName().lastIndexOf(".")).equals(".jar")){
					//System.out.println(file.getName());
					plugin p=new plugin();
					p.setJarPath(file.getAbsolutePath());
					p.setName(file.getName().substring(0,file.getName().lastIndexOf(".")));
					p.setPackageName(p.getPackageName()+"."+p.getName());
					plugins.add(p);
					//System.out.println(plugins.get(i).getName());
				}
				
			}
		}
		
		Config.plugins=plugins;
		//System.out.println(plugins.get(0).getName()+" "+plugins.get(1).getName()+"  --");
	}
}
