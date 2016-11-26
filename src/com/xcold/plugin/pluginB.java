package com.xcold.plugin;

import com.xcold.config.Config;
import com.xcold.interfaces.plugin;

public class pluginB implements plugin{
	public void run(){
		Config.setIp("127.0.0.1");
		System.out.println("Plugin B is run");
	}
}
