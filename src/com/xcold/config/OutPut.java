package com.xcold.config;

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
import com.xcold.plugin.Test;

public class OutPut {

	
	public Map<String, List<String>> map=new HashMap<String,List<String>>();
	public ArrayList<String> port1=null;
	public FileWriter fp=null;
	
	public void getDir(){
		File dir=new File("outPut");
		if(!dir.exists()||!dir.isDirectory()){
			dir.mkdir();
		}
		
		
	}
	public OutPut(){
		
		getDir();
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
		}
	}
	public Map<String, List<String>> tree_1=Config.tree_1;
	public Map<String, List<String>> tree_2=Config.tree_2;
	public Map<String, List<String>> tree_3=Config.tree_3;
	public Map<String, List<String>> tree_4=Config.tree_4;
	public ArrayList<String> arrTree_1=Config.arrTree_1;
	public ArrayList<String> arrTree_2=Config.arrTree_2;
	public ArrayList<String> arrTree_3=Config.arrTree_3;
	//输出报表
	public void outPut(){

		System.out.println(Config.tree_4.entrySet());
		
		diedai(tree_1,0,"top");


	}
	
	public void diedai(Map<String, List<String>> map,int type,String se){
		if(type<=4){
			if(type==0){
				//System.out.println(Config.outputString.replace("{topTitle}",se));
				outputFile(Config.outputString.replace("{topTitle}",se), 1);
				outputFile("基本设置 host : "+Config.domain,0);
				outputFile("ip : "+Config.ip,0);
				outputFile("目标连接："+Config.target,0);
				outputFile("Report",1);
				
			}
			else if(type==1){
				//System.out.println(Config.topTitle.replace("{topTitle}",se));
				outputFile(Config.topTitle.replace("{topTitle}",se),1);
			}	
			else if(type==2){
				outputFile(Config.minTopTitle.replace("{minTopTitle}",se),0);
				//System.out.println(Config.minTopTitle.replace("{minTopTitle}",se));
			}else if(type==3){
				outputFile(Config.threeTitle.replace("{threeTitle}",se),0);
				//System.out.println(Config.threeTitle.replace("{threeTitle}",se));
			}else if(type==4){
				outputFile(Config.fourTitle.replace("{fourTitle}",se),0);
				//System.out.println(Config.fourTitle.replace("{fourTitle}",se));
			}
			
			try {
				ArrayList<String> s=(ArrayList)map.get(se);
				if(!s.isEmpty()){
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
							diedai(tree_4,3,s.get(i));
							break;
						case 4:
							diedai(tree_4,4,s.get(i));
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
		try {
			fp.write(str);
			fp.write("\r\n");
			if(type==1){
				fp.write("---------");
				fp.write("\r\n");
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
