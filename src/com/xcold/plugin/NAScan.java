package com.xcold.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.rowset.Joinable;

import com.xcold.config.Config;
import com.xcold.config.OutPut;
import com.xcold.interfaces.PluginService;


/**
 * 网络资产扫描
 * @author xcold
 *
 */
public class NAScan implements PluginService{
	
	public String ip ="127.0.0.1";
	public int scanIpType=1;
	public String domain="localhost";
	public String port[]=null;
	public int scanPortType=1;
	public int scanPortStart=0;
	public int scanPortEnd=65525;
	public int timeout=3000;
	public ArrayList<String> activeIp=new ArrayList<String>();
	public Map<String, List<String>> ipPort=new HashMap<String, List<String>>();
	public String PluginName="";
	public Queue<String> queue = new LinkedList<String>();  
	public Queue<String> queuePort = new LinkedList<String>();  
	public void runService(){
		if((this.scanIpType=Config.scanIpType)==1){
			this.ip=Config.ip;
		}
		this.domain=Config.domain;
		this.scanPortType=Config.scanPortType;
		if(this.scanPortType==1){
			this.port=Config.scanPort;
		}
		if(Config.scanIpType==1)
			ipActive(Config.domain);
		else{
			activeIp.add(Config.ip);
		}
		
		for(int a=0;a<activeIp.size();a++){
			queue.add(activeIp.get(a));
			//System.out.println("---");
		}
		//扫描端口
		//System.out.println(queue.poll()+"队列");
		portScan();
		outPut();

		
	}
	public void getHostIp(){
		try {
			InetAddress addr=InetAddress.getByName(Config.domain);
			String ip=addr.getHostAddress();
			activeIp.add(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void outScreen(String str){
		//Config.jta.append("\r\n");
		Config.jta.append("\r\b"+str+"\r\n");
		//Config.jta.append("\r\n");
	}
	//输出报告
	public void  outPut(){

		this.PluginName="PortScan";
		
		String service="activeIp";
		ArrayList<String> services=new ArrayList<String>();
		services.add(service);		
		Config.tree_1.get(Config.topString).add(PluginName);
		Config.tree_2.put(PluginName, services);
		
		Config.tree_3.put(service, activeIp);
		for(String str_ip:activeIp){
			System.out.println(ipPort.get(str_ip));
		}
	
		
	}

	public void getPort(){
		int scanPortType=Config.scanPortType;
		if(scanPortType==1){
			for(String port:Config.scanPort){
				queuePort.add(port);
			}
		}else{
			for(int i=0;i<65525;i++){
				queuePort.add(String.valueOf(i));
			}
		}
		
	}

	public void ipActive(String ip ){
		try {
			InetAddress addr=InetAddress.getByName(ip);
			String ipd=addr.getHostAddress();
			System.out.println("  "+ipd);
			String ips[]=ipd.split("\\.");
			//List<String> IPS=new  <String>();//=new List<String>();
			ArrayList<String> IPS=new ArrayList<String>();
			for(int i=0;i<=255;i++){
				ips[3]=String.valueOf(i);
				String ip_=ips[0]+"."+ips[1]+"."+ips[2]+"."+ips[3];
				queue.add(ip_);
			}

			//ExecutorService threadPool = Executors.newCachedThreadPool();
			getIp gi=new getIp(IPS);
			for(int threadnum=0;threadnum<Config.threadNum;threadnum++){
				Thread t=new Thread(gi);
				t.start();
				Config.threadActive.add(t);
				//threadPool.execute(gi);
				t.sleep(300);
				t.stop();
			}
			//threadPool.shutdown();
				
		}catch (UnknownHostException e){
			System.out.println("unknown host");
			//e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void portScan(){
		getPortThread gp=new getPortThread();
		
		for(int threadnum=0;threadnum<Config.threadNum;threadnum++){
			Thread t1=new Thread(gp);
			t1.start();
			Config.threadActive.add(t1);
			
			
			//System.out.println(1);
			//threadPool.execute(gi);
		}
		
	}
	
	class getIp implements Runnable{

		//public String ip;
		//public String ip;
		public ArrayList<String> ip=new ArrayList<String>();
		public int t=0;
		public getIp(ArrayList<String> ip){
			this.ip=ip;
		}
		public void run() {
			// TODO Auto-generated method stub

			while(true){

				//System.out.println(ip.get(t));
				try {
					String i=queue.poll();
					if(i==null){
						break;
					}else{
						InetAddress addr=null;
						addr = InetAddress.getByName(i);
						activeIp.add(i);
					}

				} catch (UnknownHostException e) {
				}
				

	
			}
			
			
		}


		
	}

	class getPortThread implements Runnable {
		int scanPortType=Config.scanPortType;
		public void run() {
			String i="";
			while(true){
				try{
					i=queue.poll();
					if(i==null){
						break;
					}else{
						
						runScan(scanPortType,i);
					}

				}catch(Exception e){
					
				}

			
			}	
		}
		
		
		
		
		public void runScan(int scanPortType,String t){
			ArrayList<String> arr=new ArrayList<String>();
			
			
			if(scanPortType==1){
				
				for(String port :Config.scanPort){
					try {
						//System.out.println("正在扫描 "+t+" 端口 "+port+"\r\n");
						
						outScreen("正在扫描 "+t+" 端口 "+port+"\r\n");
						
						InetAddress addr=InetAddress.getByName(t);
						SocketAddress so=new InetSocketAddress(addr, Integer.parseInt(port));
						Socket socket=new Socket();
						
						socket.connect(so, 3000);
						socket.close();
						//socket=new Socket();
						outScreen(t+": 开放 "+port+"端口"+"\r\n");
						arr.add("开放 "+port);
							
					
						
				} catch (UnknownHostException e) {
					
				}catch (IOException e) {
						outScreen(""+t+" 端口 "+port+" 不存在"+"\r\n");
						//arr.add(port+" 不开放");
					}
					
				}
			}else{
				
				for(int i=0;i<65525;i++){
					
					try {
						Socket socket=new Socket();
						InetAddress addr=InetAddress.getByName(t);
						SocketAddress so=new InetSocketAddress(addr, i);
						
						try {
							socket.connect(so, 3000);
							Config.jta.append(t+": 开放 "+port+"端口"+"\r\n" );
							//ipPort.get(t).add(String.valueOf(i));
							arr.add(String.valueOf(i));
						} catch (IOException e) {
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
					
				}
				
			}

			ipPort.put(t, arr);
			Config.tree_4=ipPort;//.put(t, ipPort.get(t));
		}
		
	}
	
}
