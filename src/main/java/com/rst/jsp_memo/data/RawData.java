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
data 패키지 내부의 클래스에서만 접근가능
*/ 
class RawData {
	
	private static ClassLoader classLoader;
	
	static{
		classLoader = RawData.class.getClassLoader();
	}
	
	
	
	protected static String readFile(String path){
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
	
	protected static boolean writeFile(String path, String contents){
		
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

	/**
	 * 주어진 path의 파일 삭제.
	 * 파일이 존재하는지 확인하고 삭제함!
	 * @param path
	 */
	protected static void deleteFile(String path){
		try{
			URI uri = classLoader.getResource(path).toURI();
			
			File file = new File(uri);
			if( file.exists() ) file.delete();
		}catch(URISyntaxException e){
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
	} 
}
