package com.rst.jsp_memo.data;

import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;
/**
This is static factory class about meta data.

*/
public class DataCenter {
	
	private static final String DATA_FOLDER_PATH;
	private static final String LOGIN_FILE_PATH = "login_pw.txt";
	private static final String TAGS_FILE_PATH = "tags.csv";
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
		String password = RawData.readFile( getRealPath( LOGIN_FILE_PATH ) );
		return password;
	}
	
	public static long getNewMemoId(){
		long id;
		String last_num = RawData.readFile( getRealPath( "last_memo_num" ) );
		id = Long.parseLong(last_num)+1;
		return id;
	}

	/**
	 * 
	 * @param tags_text: 탭으로 태그들이 구분된 태그목록
	 * @return 각 태그를 하나의 원소로 하는 리스트를 반환
	 */
	private static List<String> getTagsFromText(String tags_text){
		StringTokenizer tags = new StringTokenizer(tags_text);

		LinkedList<String> tag_list = new LinkedList<String>();

		int i = tags.countTokens();
		while(i-- > 0){
			tag_list.add( tags.nextToken() );
		}

		return tag_list;
	}

	/**
	 * 모든 태그들을 리스트로 만들어 반환
	 * 이때 모든 태그를 담고있는 파일은 탭으로 태그들을 구분한다.
	 */
	public static List<String> getAllTags(){
		String tags_file = RawData.readFile( getRealPath(TAGS_FILE_PATH) );
		List<String> list = getTagsFromText(tags_file);

		return list;
	}

	/**
	 * 각 메모는 아래와 같은 형식을 갖는다.
	 * 이때 태그들은 탭으로 구분
	 * 타이틀 키워드와 실제 타이틀도 탭으로 구분
	 * contents는 3번째 줄에 위치
	 * tags:	test	memo
	 * title:	this is test memo
	 * contents......
	 * 
	 */
	public static Memo getMemo(long memoId){
		Memo memo;
		if(MemoRepository.isContain(memoId)){
			memo = MemoRepository.getMemo(memoId);
		}else{
			String memoText = RawData.readFile(getRealPath("memo/"+memoId));

			String[] data = memoText.split("\n", 3);
			String title = (data[1].split("\t",2))[1];
	
			List<String> tags = getTagsFromText(data[0]);
			memo = Memo.createMemo(tags, title, data[2]);
		}
		
		return memo;
	}


	private static List<Long> getMemoListByTag(String tag){
		String file = RawData.readFile( getRealPath( "tags/"+tag ) );
		StringTokenizer memos = new StringTokenizer(file);

		LinkedList<Long> memo_list = new LinkedList<Long>();

		int i = memos.countTokens();
		while(i-- > 0){
			memo_list.add( Long.parseLong(memos.nextToken()) );
		}

		return memo_list;
	}

	public static List<Memo> getMemosByTag(String tag){
		LinkedList<Long> ids = (LinkedList)getMemoListByTag(tag);
		List<Memo> memo_list = new LinkedList<Memo>();
		while(!ids.isEmpty()){
			Memo m = getMemo(((Long)( ids.pop() )).longValue());
			memo_list.add(m);
		}
		return memo_list;
	}

	/**
	 * 애플리케이션 관점에서 새로운 메모를 만든다. 즉 객체만 생성하는 것이 아닌
	 * 로컬 파일로 저장되는 개념
	 * 
	 * 따라서 MemoRepository에도 등록되어 관리된다.
	 * 
	 * @param tags
	 * @param title
	 * @param contents
	 * @return 생성된 메모객체 오류 발생시 null
	 */
	public static Memo createMemo(List<String> tags, String title, String contents){
		Memo m = Memo.createMemo(tags, title, contents);
		if(m != null){ 
			MemoRepository.putMemo(m);
		}
		return m;
	}



	public static void writeMemoOnFile(Memo m){

	}
}
