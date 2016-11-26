package com.xcold.config;

public class Domains {
	public String ip="127.0.0.1";
	public String host="localhost";
	public String protocol="http" ;
	public StringBuffer posts;
	
	
	public void setPosts(StringBuffer posts) {
		this.posts = posts;
	}
	public StringBuffer getPosts() {
		return posts;
	}

	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


}
