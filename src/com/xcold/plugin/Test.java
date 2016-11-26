package com.xcold.plugin;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.xcold.config.Config;
import com.xcold.main.Function;
public class Test {
	public static void main(String [] args){
		Test test=new Test();
		
	}
	
	public Map<String, List<String>> map=new HashMap<String,List<String>>();
	public ArrayList<String> port1=null;
	public FileWriter fp=null;
	public Test(){
		if(Function.getDomain("http://sss.www.com")){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
	/*	//ss();
		//outPut();
		//System.out.println(Integer.parseInt("22"));
		try {
			fp=new FileWriter("output"+"\\"+Config.Banner+"_"+Config.domain+"_"+(new Date().getTime())+".md");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outPut();
		try {
			fp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public Map<String, List<String>> tree_1=new HashMap<String,List<String>>();
	public Map<String, List<String>> tree_2=new HashMap<String,List<String>>();
	public Map<String, List<String>> tree_3=new HashMap<String,List<String>>();
	
	public ArrayList<String> arrTree_1=new ArrayList<String>();
	public ArrayList<String> arrTree_2=new ArrayList<String>();
	public ArrayList<String> arrTree_3=new ArrayList<String>();
	//Êä³ö±¨±í
	public void outPut(){

		arrTree_1.add("pluginA");
		arrTree_1.add("pluginB");
		arrTree_1.add("pluginC");
		arrTree_1.add("pluginD");
		
		arrTree_2.add("serviceA");
		arrTree_2.add("serviceB");
		arrTree_2.add("serviceC");
		
		arrTree_3.add("serviceA_1");
		arrTree_3.add("serviceA_2");
		arrTree_3.add("serviceA_3");
		
		tree_1.put("top", arrTree_1);
		tree_2.put("pluginA", arrTree_2);
		tree_3.put("serviceA", arrTree_3);
		
		
		//System.out.println(tree_1);
		diedai(tree_1,0,"top");
		//System.out.println(tree_1.get("top"));

	}
	
	public void diedai(Map<String, List<String>> map,int type,String se){
		if(type<=3){
			if(type==0){
				System.out.println(Config.outputString.replace("{topTitle}",se));
				outputFile(Config.outputString.replace("{topTitle}",se), 1);
			}
			else if(type==1){
				System.out.println(Config.topTitle.replace("{topTitle}",se));
				outputFile(Config.topTitle.replace("{topTitle}",se),1);
			}	
			else if(type==2){
				outputFile(Config.minTopTitle.replace("{minTopTitle}",se),0);
				System.out.println(Config.minTopTitle.replace("{minTopTitle}",se));
			}else if(type==3){
				outputFile(Config.threeTitle.replace("{threeTitle}",se),0);
				System.out.println(Config.threeTitle.replace("{threeTitle}",se));
			}
			
			try {
				ArrayList<String> s=(ArrayList)map.get(se);
				if(!s.isEmpty()){
					System.out.println(s);
					
					type++;
					System.out.println(type);
					for (int i = 0; i < s.size(); i++) {
						switch (type) {
						case 1:
							diedai(tree_2,1,s.get(i));
							break;
						case 2:
							diedai(tree_3,2,s.get(i));
							break;
						case 3:
							diedai(tree_3,3,s.get(i));
							break;
						default:
							break;
						}
						
					}
				}else{
					outputFile(Config.minTopTitle.replace("{minTopTitle}",se),0);
				}


			} catch (Exception e) {
				// TODO: handle exception
			}

		}
				
		}

	public void outputFile(String str,int type){
//		File dir=new File("output1");
//		if(!dir.exists()||!dir.isDirectory()){
//			System.out.println("this is no exist");
//			dir.mkdir();
//		}
//		
//		File path=new File(dir.getAbsoluteFile()+"\\"+Config.Banner+"_"+Config.domain+"_"+(new Date().getTime())+".md");
//		System.out.println(path.getAbsolutePath());
		
		try {
			//FileWriter fp=new FileWriter(dir.getAbsoluteFile()+"\\"+Config.Banner+"_"+Config.domain+"_"+(new Date().getTime())+".md");
			fp.write(str);
			fp.write("\r\n");
			if(type==1){
				fp.write("---------");
				fp.write("\r\n");
			}

			System.out.println("thissss "+str);
			//fp.write(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void socketTest(){
		try {
			InetAddress addr=InetAddress.getByName("221.6.6.41");
			InetSocketAddress sockAddr=new InetSocketAddress(addr, 80);
			Socket socket=new Socket();

			socket.connect(sockAddr,3000);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),
			          "UTF-8"));
			String rs="";
			socket.close();
			while((rs=rd.readLine())!=null){
				System.out.println("222");
				
			}
			rd.close();

			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void  ss(){
		ArrayList<String> port1=new ArrayList<String>();
		port1.add("sss");
		port1.add("222");
		ArrayList<String> port2=new ArrayList<String>();
		port2.add("sss");
		port2.add("222");
		map.put("127.0.0.1", port2);
		map.put("222", port1);
		System.out.println(map);
		map.get("222").add("sdfdf");
		System.out.println(map);
		
		Vector<String> vc=new Vector<String>();
		vc.add("22");
		vc.add("111");
//		for(int i=0;i<vc.size();i++){
//			vc.pu
//		}
		
	}
	
	
}
