package com.xcold.main;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.ws.soap.Addressing;

import com.xcold.config.Config;
import com.xcold.config.Domains;

public class Function {
	
	public static void main(String [] args){
		Function f=new Function();
		
	}
	public static boolean getDomain(String str){
		String re="^[https|http]://[a-zA-Z0-9.]+";
		Pattern p=Pattern.compile(re);
		
		if(str==null||str==""||str.equals("")) return false;
		if(p.matcher(str)==null){
			return false;
		}
		return true;
	}
	public Function(){
		getHost("qq.cn");
	}
	public void getHost(String domain){
		String bing="http://cn.bing.com/search?FORM=PERE1&q=site:"+domain;
		String regex="(?<=(href=\"))((http|https)://)([.0-9a-zA-Z-]*?"+domain+")";
		ArrayList<String> al=new ArrayList<String>();
		Vector<String> vl=new Vector<String>();
		try {
			int i=0;
			int length=0;
			String domain_=domain;
			URL url=new URL(bing);
			while(true){
				String bing1=bing+"&first="+(5+10*i);
				i++;
				HttpURLConnection conn=(HttpURLConnection)url.openConnection();
				conn.connect();
				
				BufferedReader bf=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
				String s;
				StringBuffer sf=new StringBuffer();
				while((s=bf.readLine())!=null){
					sf.append(s);
				}
				
				s=sf.toString();
				
				if(i==50){
					System.out.println(i);
					break;
				}
				length=sf.length();
				
				Pattern p=Pattern.compile(regex);
				Matcher math=p.matcher(s);
				
				while(math.find()){

					if(al.contains(math.group(4))){
						continue;
					}
					
					domain_=math.group(4);
					//System.out.println(domain_+" "+vl);
					al.add(domain_);
					
					Domains d=new Domains();
					d.setHost(math.group(4));
					d.setProtocol(math.group(2));
					d.setIp(getIp(d.getHost()));
					Config.domains.add(d);
					System.out.println(domain_+" "+d.getHost()+" "+d.getProtocol()+" "+d.getIp()+"  "+getBaner(d));
				
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getIp(String host){
		InetAddress ip=null;
		
		try {
			ip=InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return "unknown";
		}
		
		return ip.getHostAddress().toString();
	}
	public String getBaner(Domains domain){
		
		String Baner[]={"X-Powered-By","Server"};
		String jiaoben[]={"php","jsp","asp"};
		
		String url=domain.getProtocol()+domain.getHost();
		try {
			URL u=new URL(url);
			HttpURLConnection conn=(HttpURLConnection)u.openConnection();
			conn.connect();
			Map<String, List<String>> map=conn.getHeaderFields();
			
			//System.out.println(map.entrySet());
			
			if(map.get(Baner[0])!=null){
				return map.get(Baner[0]).toString();
			}else if(map.get(Baner[1])!=null){
				return map.get(Baner[1]).toString();
			}else{
				return "Unkown";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Unkown";
	}
	public void getPost(){
		
	}
	
	public void getUrl(){
		
	}
}
