package com.rst.jsp_memo.data;

import java.util.StringTokenizer;
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
	private static LinkedList<String> getTagsFromText(String tags_text){
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
	public static LinkedList<String> getAllTags(){
		String tags_file = RawData.readFile( getRealPath(TAGS_FILE_PATH) );
		LinkedList<String> list = getTagsFromText(tags_file);

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
	
			LinkedList<String> tags = getTagsFromText(data[0]);
			memo = Memo.createMemo(tags, title, data[2]);
		}
		
		return memo;
	}


	private static LinkedList<Long> getMemoListByTag(String tag){
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
	public static LinkedList<Memo> getMemosByTag(String tag){
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
	 * 애플리케이션에서 관리될 메모를 만든다.
	 * 따라서 MemoRepository에도 등록되어 관리된다.
	 * 
	 * @param tags
	 * @param title
	 * @param contents
	 * @return 생성된 메모객체 오류 발생시 null
	 */
	public static Memo createMemo(LinkedList<String> tags, String title, String contents){
		Memo m = Memo.createMemo(tags, title, contents);
		if(m != null){ 
			MemoRepository.putMemo(m);
		}
		return m;
	}


	/**
	 * memo_id를 가지는 메모를 메모 레퍼지토리, 로컬상에서 삭제한다.
	 * @param long memo_id
	 */
	public static void deleteMemo(long memo_id){

		MemoRepository.removeMemo(memo_id);
		String path = getRealPath("memo/"+memo_id);
		RawData.deleteFile(path);
	}


	/**
	 * 파라미터로 주어진 메모 m을 로컬파일로 저장한다.
	 * memo repository에서 변경사항을 저장할때 이용할 예정.(saveChanges메소드에서)
	 * @param Memo m
	 */
	protected static void writeMemoOnFile(Memo m){
		LinkedList<String> memo_tags = m.getTagList();
		String file_path = getRealPath("memo/"+m.getMemoId());
		
		String file_tags = "tags:\t";
		while( !memo_tags.isEmpty() ){
			file_tags += '\t'+memo_tags.pop();
		}

		String file_title = "title:\t"+m.getTitle();
		String file_contents = m.getContents();

		RawData.writeFile( file_path, file_tags+'\n'+file_title+'\n'+file_contents);
	}
}
