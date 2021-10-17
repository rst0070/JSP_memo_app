package com.rst.jsp_memo.data;

import java.util.*;
/**
This is static factory class about meta data.

*/
public class DataCenter {
	
	private static final String DATA_FOLDER_PATH;
	private static final String LOGIN_FILE_PATH = "login_pw.txt";
	private static final String TAGS_FILE_PATH = "tags.csv";
	static{
		DATA_FOLDER_PATH = "/workspace/JSP_memo_app/data";
	}
	
	/**
	 * returns real file path.
	 */
	private static String getRealPath(String path){
		return DATA_FOLDER_PATH+'/'+path;
	}
	
	public static String getLoginPassword() throws ReadWriteException{
		String password = RawData.readFile( getRealPath( LOGIN_FILE_PATH ) );
		return password;
	}
	
	/**
	 * 현재존재하는 마지막 메모의 아이디.
	 */
	public static long getLastMemoId() throws ReadWriteException {
		long id;
		String last_num = RawData.readFile( getRealPath( "last_memo_num" ) );
		id = Long.parseLong(last_num);
		return id;
	}

	/**
	 * 모든 태그들을 리스트로 만들어 반환
	 * 이때 모든 태그를 담고있는 파일은 탭으로 태그들을 구분한다.
	 */
	public static LinkedList<String> getAllTags() throws ReadWriteException {
		String tags_file = RawData.readFile( getRealPath(TAGS_FILE_PATH) );
		
		StringTokenizer tags = new StringTokenizer(tags_file);

		LinkedList<String> tag_list = new LinkedList<String>();

		while(tags.hasMoreTokens()){
			tag_list.add( tags.nextToken() );
		}

		return tag_list;
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
	public static Memo getMemo(long memoId) throws ReadWriteException{
		Memo memo;
		
		String memoText = RawData.readFile(getRealPath("memo/"+memoId));

		Scanner scan = new Scanner(memoText);
	
		//태그들 저장
		StringTokenizer st = new StringTokenizer(scan.nextLine());
		LinkedList<String> tags = new LinkedList<String>();
		st.nextToken();
		while(st.hasMoreTokens()){
			tags.add(st.nextToken());
		}

		//제목 저장
		String[] titleLine = scan.nextLine().split("\t", 2);
		String title = titleLine[1];
		
		String contents = "";

		while(scan.hasNextLine()){
			contents += scan.nextLine()+'\n';
		}

		//마지막 \n제거
		contents = contents.substring(0, contents.length() - 1);
		memo = Memo.createMemo(memoId, tags, title, contents);

		scan.close();
		
		return memo;
	}


	/**
	 * 특정 tag를 같는 메모들의 아이디를 리스트로 반환한다.
	 * @param tag 찾을 메모들의 공통아이디
	 * @return LinkedList<Long> 메모들의 아이디가 Long값으로 들어있는 리스트
	 */
	private static LinkedList<Long> getMemoListByTag(String tag) throws ReadWriteException {
		String file = RawData.readFile( getRealPath( "tags/"+tag ) );
		StringTokenizer memos = new StringTokenizer(file);

		LinkedList<Long> memo_list = new LinkedList<Long>();

		int i = memos.countTokens();
		while(i-- > 0){
			memo_list.add( Long.parseLong(memos.nextToken()) );
		}

		return memo_list;
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
	public static Memo createMemo(long memoId, LinkedList<String> tags, String title, String contents) throws ReadWriteException{
		Memo m = Memo.createMemo(memoId, tags, title, contents);
		if(m == null) return null;
			
		long last_num = getLastMemoId();
		
		if( m.getMemoId() > last_num ){
			RawData.writeFile(getRealPath("last_memo_num"), ""+m.getMemoId());
			
		}
		
		//1. 메모파일에 작성하기
		writeMemoOnFile(m);

		//2. 태그파일에 메모작성하기
		LinkedList<String> allTags = getAllTags();
		Iterator<String> tagit = tags.iterator();
		while(tagit.hasNext()){
			String tag = tagit.next();

			//해당 하는 태그가 이미 존재할 경우
			if(allTags.contains(tag)){

				String memosInTag = RawData.readFile(getRealPath("tags/"+tag));
				if( !memosInTag.contains(tag) )
					RawData.writeFile(getRealPath("tags/"+tag), memosInTag + '\t' + m.getMemoId());
			}else{// 해당하는 태그가 존재하지 않을때
				allTags.add(tag);
				RawData.writeFile(getRealPath("tags/"+tag), m.getMemoId()+"");
			}
		}

		//태그목록이 변경되었을 것 이므로 태그목록 파일에 변경사항대로 기록.
		tagit = tags.iterator();
		String textForTags = "";
		while(tagit.hasNext()) textForTags += '\t' + tagit.next();
		RawData.writeFile(getRealPath(TAGS_FILE_PATH), textForTags);

		return m;
	}


	/**
	 * memo_id를 가지는 메모를 로컬상에서 삭제한다.
	 * @param long memo_id
	 */
	public static void deleteMemo(long memo_id) throws ReadWriteException{

		String path = getRealPath("memo/"+memo_id);
		RawData.deleteFile(path);
	}


	/**
	 * 파라미터로 주어진 메모 m을 로컬파일로 저장한다.
	 * @param Memo m
	 */
	protected static void writeMemoOnFile(Memo m) throws ReadWriteException{
		LinkedList<String> memo_tags = m.getTagList();
		String file_path = getRealPath("memo/"+m.getMemoId());
		
		String file_tags = "tags:";
		while( !memo_tags.isEmpty() ){
			String tag = memo_tags.pop();
			file_tags += '\t' + tag;
		}
		
		String file_title = "title:\t"+m.getTitle();
		String file_contents = m.getContents();

		RawData.writeFile( file_path, file_tags+'\n'+file_title+'\n'+file_contents);
	}
}
