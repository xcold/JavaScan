package com.xcold.config;

public class plugin {
	public  String getName() {
		return name;
	}
	public  void setName(String name) {
		this.name = name;
	}
	public  String getPackageName() {
		return packageName;
	}
	public  void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public  String getJarPath() {
		return jarPath;
	}
	public  void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}
	public  String name="";
	public  String packageName="com.xcold.plugin";
	public  String jarPath="";
	
}
