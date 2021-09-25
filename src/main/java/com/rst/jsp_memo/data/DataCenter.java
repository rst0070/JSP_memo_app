package com.rst.jsp_memo.data;


/**
This is static factory class about meta data.

*/
public class DataCenter {
	
	private static final String DATA_FOLDER_PATH;
	private static final String LOGIN_FILE_PATH = "login_pw.txt";
	
	static{
		DATA_FOLDER_PATH = RawData.readFile("data_position.txt");
	}
	
	/**
	 * returns real file path.
	 */
	private static String getRealPath(String path){
		return DATA_FOLDER_PATH+'/'+path;
	}
	
	public static String getLoginPassword(){
		String password = "";
		password = RawData.readFile( getRealPath( LOGIN_FILE_PATH ) );
		return password;
	}
	
	public static long getNewMemoId(){
		long id = 1;
		
		return long;
	}
}
