package com.xcold.config;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextArea;

public class Config {
	public static String getDomain() {
		return domain;
	}
	public static void setDomain(String domain) {
		Config.domain = domain;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		Config.ip = ip;
	}
	public Vector<Domains> getDomains() {
		return domains;
	}
	public void setDomains(Vector<Domains> domains) {
		this.domains = domains;
	}
	
	public void addDomains(Domains domain) {
		this.domains.add(domain);
	}
	
	public static String domain="www.baidu.com";
	public static String ip="";
	public static String getPackageName() {
		return packageName;
	}
	public static void setPackageName(String packageName) {
		Config.packageName = packageName;
	}
	public Vector<plugin> getPlugins() {
		return plugins;
	}
	public void setPlugins(Vector<plugin> plugins) {
		this.plugins = plugins;
	}
	public static String getPlugindir() {
		return plugindir;
	}
	public static void setPlugindir(String plugindir) {
		Config.plugindir = plugindir;
	}
	public static Vector<Domains> domains=new Vector<Domains>();
	private static String packageName="com.xcold.plugin";
	public static Vector<plugin> plugins=new Vector<plugin>();
	public static URLClassLoader pluginLoads;
	public static String plugindir="plugin";
	public static String regex="(?<=(href=\"(http|https)://))[.0-9a-zA-Z-]*?("+domain+")";
	public static String [] scanPort={"21","22","23","25","53","80","110","139","143","389","443","445","465","873","993","995","1080","1723","1433","1521","3306","3389","3690","5432","5800","5900","6379","7001","8000","8001","8080","8081","8888","9200","9300","9080","9999","11211","27017"};
	public static int scanPortType=1;//扫描端口的类型，如果为1，则端口为常见端口的 数组，为0则是从0-65525扫描
	public static int scanIpType=0;//扫描ip的类型，如果为0，为指定的ip，为1则是扫描c段ip
	public static int threadNum=5;//制定线程数
	
	
	public static String outputString="#javaScan Report#";
	public static String topTitle="*{topTitle}*";
	public static String minTopTitle="*	{minTopTitle}";
	public static String threeTitle="	*	{threeTitle}";
	public static String fourTitle="		*	{fourTitle}";
	
	public static Map<String, List<String>> map=new HashMap<String,List<String>>();
	public static String Banner="JavaScan V1.0   Powered by Xcold";
	public static Map<String, List<String>> tree_1=new HashMap<String,List<String>>();
	public static Map<String, List<String>> getTree_1() {
		return tree_1;
	}
	public static void setTree_1(Map<String, List<String>> tree_1) {
		Config.tree_1 = tree_1;
	}
	public static Map<String, List<String>> getTree_2() {
		return tree_2;
	}
	public static void setTree_2(Map<String, List<String>> tree_2) {
		Config.tree_2 = tree_2;
	}
	public static Map<String, List<String>> getTree_3() {
		return tree_3;
	}
	public static void setTree_3(Map<String, List<String>> tree_3) {
		Config.tree_3 = tree_3;
	}

	public static Map<String, List<String>> tree_2=new HashMap<String,List<String>>();
	public static Map<String, List<String>> tree_3=new HashMap<String,List<String>>();
	public static Map<String, List<String>> tree_4=new HashMap<String,List<String>>();

	public static ArrayList<String> arrTree_1=new ArrayList<String>();
	public static ArrayList<String> arrTree_2=new ArrayList<String>();
	public static ArrayList<String> arrTree_3=new ArrayList<String>();
	public static String topString="top";
	public static ArrayList<String> activePlugin=new ArrayList<String>();
	public static JTextArea jta=new JTextArea(25,60);
	public static JTextArea jta1=new JTextArea(25,13);
	
	public static String target="";
	
	public static ArrayList<Thread> threadActive=new ArrayList<Thread>();
}
