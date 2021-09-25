package com.rst.jsp_memo.data;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;



import java.net.URI;

import java.io.IOException;
import java.net.URISyntaxException;
/**
This is static factory class
aim to read text files.
*/ 
public class RawData {
	
	private static ClassLoader classLoader;
	
	static{
		classLoader = RawData.class.getClassLoader();
	}
	
	
	
	public static String readFile(String path){
		String result = "";
		
		try{
			URI uri = classLoader.getResource(path).toURI();
			
			File file = new File(uri);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String line;
			while((line = br.readLine())!= null){
				result += line;
			}
			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(URISyntaxException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean writeFile(String path, String contents){
		
		boolean isError = false;
		
		try{
			URI uri = classLoader.getResource(path).toURI();
			
			File file = new File(uri);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(contents, 0, contents.length());
			
			bw.flush();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
			isError = true;
		}catch(URISyntaxException e){
			e.printStackTrace();
			isError = true;
		}
		
		return !isError;
	}
}
