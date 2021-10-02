package com.rst.jsp_memo.data;


import java.io.*;
/**
This is static factory class
aim to read text files.
data 패키지 내부의 클래스에서만 접근가능
*/ 
class RawData {
	
	
	/**
	 * 파일의 절대경로를 입력하면 파일의 전체 내용을 반환한다.
	 * @param path
	 * @return
	 */
	protected static String readFile(String path) throws ReadWriteException{
	
		String result = "";
		
		try{
			File file = new File(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String line;
			while((line = br.readLine())!= null){
				result += line;
			}
			
			br.close();
		}catch(FileNotFoundException e){
			throw new ReadWriteException( ReadWriteException.FILE_NOT_FOUND, e.getMessage() );
		}catch(IOException e){
			throw new ReadWriteException(ReadWriteException.IO_DENIED, e.getMessage() );
		}
		
		return result;
	}
	
	/**
	 * 지정한 경로에 파일작성
	 * @param path - 작성할 파일의 절대경로
	 * @param contetns - 작성할 파일의 내용
	 * @throws 
	 */
	protected static void writeFile(String path, String contents) throws ReadWriteException{
	
		try{
			File file = new File(path);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(contents, 0, contents.length());
			
			bw.flush();
			bw.close();
		}catch(SecurityException e){
			throw new ReadWriteException( ReadWriteException.SECURITY_BLOCKED, e.getMessage() );
		}catch(FileNotFoundException e){
			throw new ReadWriteException( ReadWriteException.FILE_NOT_FOUND, e.getMessage() );
		}catch(IOException e){
			throw new ReadWriteException( ReadWriteException.IO_DENIED, e.getMessage() );
		}
	}

	/**
	 * 주어진 path의 파일 삭제.
	 * 파일이 존재하는지 확인하고 삭제함!
	 * @param path
	 */
	protected static void deleteFile(String path) throws ReadWriteException{
		try{
			File file = new File(path);
			if( file.exists() ) file.delete();
		}catch(SecurityException e){
			throw new ReadWriteException( ReadWriteException.SECURITY_BLOCKED, e.getMessage() );
		}
	} 
}
