package com.xcold.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.xcold.config.Config;
import com.xcold.config.Tool;
import com.xcold.config.plugin;
import com.xcold.plugin.NAScan;

public class View extends JFrame implements KeyListener,ActionListener,ItemListener{
	
	public JPanel p1,p2,p3;
	public JPanel p4,p5,p6;
	public JButton jb1,jb2,jb3;
	
	public TextField tf1,tf2,tf3,tf4,tf5;
	public JLabel jl1,jl2,jl3,jl4,jf5;
	public ArrayList<JCheckBox> plugins=new ArrayList<JCheckBox>();
	public static JTextArea jta;
	public JScrollPane jsp;
	
	public static JTextArea jta1;
	public JScrollPane jsp1;
	
//	public static void main(String [] args){
//		View v=new View();
//	}
	
	public View(){
		
		this.setTitle(Config.Banner);
		this.setSize(800, 600);
		this.setLayout(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		
		jb1=new JButton("Start");
		jb1.addActionListener(this);
		jb1.setActionCommand("start");
		//p1处理
		//p1.setBackground(Color.BLACK);
		p1.setSize(450,150);
		p1.setLocation(0,0);
		jl1=new JLabel("Target	:");
		tf1=new TextField(20);
		jl2=new JLabel("Thread	:");
		tf2=new TextField(5);
		jl2.setFont(Tool.f3);
		jl1.setFont(Tool.f3);
		
		p1.add(jl1);
		p1.add(tf1);
		p1.add(jl2);
		p1.add(tf2);
		p1.setBorder(new TitledBorder(null, "基本参数设置", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//p3.setBackground(Color.blue);
		
		p4.setSize(150,150);
		p4.add(jb1);
		p4.setLocation(650,0);
		p4.setBorder(new TitledBorder(null, "开始", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//左边插件执行流程
		p2.setSize(150,450);
		p2.setLocation(0,150);
		jta1=Config.jta1;
		jsp1=new JScrollPane(Config.jta1);
		jta1.setFont(Tool.f5);
		jta1.setLineWrap(true);
		p2.add(jsp1);
		//		
		jta=Config.jta;
		jta.setFont(Tool.f5);
		jta.setLineWrap(true);
		jsp=new JScrollPane(Config.jta);
		
		p3.setSize(650,450);
		p3.setLocation(150,150);
		p3.add(jsp);
		//插件
		p5.setLocation(450, 0);
		p5.setSize(200, 150);
		p5.setBorder(new TitledBorder(null, "插件", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//
		//Map<String, List<JCheckBox>> plugins=new HashMap<String,List<JCheckBox>>();
		//生成插件选项
		PluginManage pm=new PluginManage();
		pm.getPlugin();
		//System.out.println(Config.plugins.size());
		for(int i=0;i<Config.plugins.size();i++){
			JCheckBox j=new JCheckBox(Config.plugins.get(i).getName());
			//System.out.println(Config.plugins.get(i).getName());
			p5.add(j);
			j.setSelected(false);
			j.addItemListener(this);
			plugins.add(j);
		}
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.add(p5);
		this.setVisible(true);
		this.setLocation(200, 100);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="start"){
				System.out.println(tf1.getText());
				if(!Function.getDomain(tf1.getText())){
					JOptionPane.showMessageDialog(null, "目标不能为空");
				}else{
					
					if(tf2.getText()!=null&&!tf2.getText().equals("")&&tf2.getText()!=""){
						try {
							System.out.println(Integer.parseInt(tf2.getText()));
							if(Integer.parseInt(tf2.getText())>0)
								Config.threadNum=Integer.parseInt(tf2.getText());
						} catch (Exception e2) {
						}
					}
					Config.target=tf1.getText();
					jta.append("目标 ："+tf1.getText()+" , 线程数 "+Config.threadNum+"\r\n");
					Main main=new Main();
				}

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<plugins.size();i++){
				if(plugins.get(i)==e.getItemSelectable()){
					
					if(plugins.get(i).isSelected()==true){
						System.out.println("选中");
						System.out.println(plugins.get(i).getActionCommand());
						Config.activePlugin.add(plugins.get(i).getActionCommand());
					}
				}
		}
		
	}
	
	
	
}
