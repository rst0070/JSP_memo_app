package com.rst.jsp_memo.data;

/**
 * 파일을 읽고 쓸때 발생하는 예외처리
 * 
 * 어떤 종류의 예외가있는가?
 * 1. 지정한 경로의 파일을 찾을 수 없을때
 * 2. 파일이 아닌 디렉토리를 선택했을때(이에대한 입출력진행하려할때)
 * 3. 파일에 대한 I/O 오류발생시
 * 4. security로 인해 io가 불가능할때
 */
public class ReadWriteException extends Exception{

    /**
     * 오류코드 상수들
     * 1. 지정한 경로의 파일을 찾을 수 없을때
     * 2. 파일이 아닌 디렉토리를 선택했을때(이에대한 입출력진행하려할때)
     * 3. 파일에 대한 I/O 오류발생시
     * 4. security로 인해 io가 불가능할때
     */
    public static final int FILE_NOT_FOUND = 1;
    public static final int NOT_FILE = 2;
    public static final int IO_DENIED = 3;
    public static final int SECURITY_BLOCKED = 4;

    public final int error_code;

    /**
     * 예외를 만든다.
     * @param error_code - 오류코드, 클래스에 정의된 상수들로 적용.
     * @param msg - 오류메세지. Exception(msg)로 전달됨.
     */
    public ReadWriteException(int error_code, String msg){
        super(msg);
        this.error_code = error_code;
    }

    @Override
    public String getMessage(){
        String result = "ReadWriteException: ";
        switch(error_code){
            case FILE_NOT_FOUND:
            result += "file not found";break;
            case NOT_FILE:
            result += "It is not file";break;
            case IO_DENIED:
            result += "cant io";break;
            case SECURITY_BLOCKED:
            result += "security problem";break;
            default:
            result += "What the ..";break;
        }
        return result;
    }
}
