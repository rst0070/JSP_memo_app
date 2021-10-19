package com.rst.jsp_memo.data;

import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.*;
/**
This is static factory class about meta data.

*/
public class DataCenter {
	

	public static String getLoginPassword() throws ReadWriteException{
		DataAccess.MetaData md = DataAccess.getMetaData();
		return md.LOGIN_PW;
	}
	
	/**
	 * 현재존재하는 마지막 메모의 아이디.
	 */
	public static long getLastMemoId() throws ReadWriteException {
		DataAccess.MetaData md = DataAccess.getMetaData();
		return md.LAST_MEMO_NUM;
	}

	/**
	 * 모든 태그들을 리스트로 만들어 반환
	 * 이때 모든 태그를 담고있는 파일은 탭으로 태그들을 구분한다.
	 */
	public static LinkedList<String> getAllTags(){
		LinkedList<String> tags = new LinkedList<String>();
		ResultSet rs = DataAccess.execute("select * from TAG_INFO");
		try{
			while(rs.next()){
				tags.add(rs.getString("name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tags;
	}

	/**
	 * 
	 * 
	 * 각 메모는 아래와 같은 형식을 갖는다.
	 * 이때 태그들은 탭으로 구분
	 * 타이틀 키워드와 실제 타이틀도 탭으로 구분
	 * contents는 3번째 줄에 위치
	 * tags:	test	memo
	 * title:	this is test memo
	 * contents......
	 * 
	 * https://stackoverflow.com/questions/1096621/read-string-line-by-line
	 */
	public static Memo getMemo(long memoId){
		Memo memo;
		
		ResultSet rs = DataAccess.execute("select * from MEMO where id = "+memoId);

		String title = "";
		LinkedList<String> tags = new LinkedList<String>();
		String content = "";
		try{
			rs.next();
			title = rs.getString("title");
			content = rs.getString("content");

			String tagText = rs.getString("tags");
			StringTokenizer st = new StringTokenizer(tagText, "#");
			while(st.hasMoreTokens())	tags.add(st.nextToken());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		memo = Memo.createMemo(memoId, tags, title, content);

		return memo;
	}


	/**
	 * 특정 tag를 같는 메모들의 아이디를 리스트로 반환한다.
	 * @param tag 찾을 메모들의 공통아이디
	 * @return LinkedList<Long> 메모들의 아이디가 Long값으로 들어있는 리스트
	 */
	private static LinkedList<Long> getMemoListByTag(String tag) throws ReadWriteException {

		LinkedList<Long> list = new LinkedList<Long>();
		ResultSet rs = DataAccess.execute("select memos from TAG_INFO where name = '"+tag+"'");
		try{
			rs.next();
			String memosText = rs.getString("memos");
			StringTokenizer st = new StringTokenizer(memosText, "#");
			while(st.hasMoreTokens())	list.add(Long.parseLong(st.nextToken()));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 해당 tag를 가지는 모든 메모들을 LinkedList<Memo>형식으로 모아 반환.
	 * @param tag 태그명
	 * @return LinkedList<Memo> 해당 메모들
	 */
	public static LinkedList<Memo> getMemosByTag(String tag) throws ReadWriteException {
		LinkedList<Long> ids = getMemoListByTag(tag);
		LinkedList<Memo> memo_list = new LinkedList<Memo>();
		while(!ids.isEmpty()){
			Memo m = getMemo(((Long)( ids.pop() )).longValue());
			memo_list.add(m);
		}
		return memo_list;
	}

	/**
	 * 
	 * 메모를 파일에 기록한다.
	 * 
	 * 1. 메모파일 생성하기: (data/memo/<memoId>)
	 * 2. 해당되는 태그에 메모 아이디 입력하기: (data/tags/<tagName>)
	 * @param tags
	 * @param title
	 * @param contents
	 * @return 생성된 메모객체 오류 발생시 null
	 */
	public static void createMemo(long memoId, LinkedList<String> tags, String title, String contents) throws ReadWriteException{
		

	}


	/**
	 * memo_id를 가지는 메모를 로컬상에서 삭제한다.
	 * @param long memo_id
	 */
	public static void deleteMemo(long memo_id) throws ReadWriteException{

		String path = getRealPath("memo/"+memo_id);
		DataAccess.deleteFile(path);
	}


}
