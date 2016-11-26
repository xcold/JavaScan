package com.xcold.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.xcold.config.Config;
import com.xcold.config.OutPut;
import com.xcold.config.plugin;
import com.xcold.interfaces.PluginService;

public class Main {
	public PluginManage pm;
	public boolean trueThread=false;
	public Main(){
		pm=new PluginManage();
		init();
		getHostIp();
		initConfig();
		if(trueThread){
			initPlugin();
			System.out.println("this is plugin loading");
		}
			
		else{
			JOptionPane.showMessageDialog(null, "参数有错");
		}

		Boolean trueOut=true;
		while(true){
	 		if(Config.threadActive.size()<=0){
	 			break;
	 		}
	 		trueOut=true;
			for(Thread t:Config.threadActive){
				if (t.isAlive()){
					trueOut=false;
				}
			}
	 		if(trueOut){
	 			OutPut out=new OutPut();
	 			break;
	 		}
		}
	}
	//初始化 host等参数
	public void initConfig(){
		Config.tree_1.put("top", (new ArrayList<String>()));
	}
	public void init(){
		 //
		  
		  String host = Config.target;
		  
		  Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		  Matcher m=p.matcher(Config.target);
		  
		  while(m.find()){
			  trueThread=true;
			  System.out.println(m.group(0));
			  //Config.setDomain(m.group(0));
			  Config.domain=m.group(0);
		  }
		  
	}
	public void getHostIp(){
		try {
			//System.out.println(Config.domain);
			InetAddress addr=InetAddress.getByName(Config.domain);
			String ip=addr.getHostAddress();
			Config.ip=ip;
			System.out.println(Config.ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			trueThread=true;
		}
		
	}
	public void initPlugin(){
		pm.init();
		for(String plugin:Config.activePlugin){
			PluginService ps=pm.getInstance(plugin);
			Config.jta1.append(plugin+" 正在执行"+"!-->\r\n");
			ps.runService();
			boolean trueOut;
			while(true){
		 		
		 		if(Config.threadActive.size()<=0){
		 			break;
		 		}
		 		trueOut=true;
				for(Thread t:Config.threadActive){
					if (t.isAlive()){
						trueOut=false;
					}
				}
		 		if(trueOut){
		 			//OutPut out=new OutPut();
		 			break;
		 		}
			}
			
		}
	}
	public void loadPlugin(){
		//while(Config.activePlugin)
		for(String plugin:Config.activePlugin){
			System.out.println(plugin);
			
		}
		for(plugin a:Config.plugins){
			System.out.println(a.getJarPath());
		}
	
	}
}
