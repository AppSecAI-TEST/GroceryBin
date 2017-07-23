package yiji.util.config;
import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args)throws Exception
	{
		System.out.println("Hello World!");
		Test t=new Test();
		ConfigUtil cu=new ConfigUtil();
		cu.addClass(t.getClass(),null).load(new FileInputStream("/sdcard/test.prop")).go();
		System.out.println(t.tv);
	}
	
}
