package com.rst.jsp_memo.data;

import java.util.*;
/**
 * 한번 열었던 메모들을 모두 관리하는 레퍼지토리.
 * 여기에서는 메모와 메모아이디에 대한 관리만 한다.
 * 즉 메모객체를 저장하는 스태틱팩토리이자
 * 마지막 메모아이디를 가지고 있으며
 * 메모들의 변경사항을 저장하는 기능을 담당한다.
 * 
 * data 패키지 외부의 클래스에서 접근 불가.
 */
class MemoRepository {
    
    private static HashMap<Long, Memo> MEMO_MAP = new HashMap<Long, Memo>(10);

    /**
     * memo_id의 메모 아이디를 가진 메모가 레퍼지토리에서 관리되고 있는지 여부를 반환
     * @param memo_id
     * @return 관리되면 true 아니면 false
     */
    protected static boolean isContain(long memo_id){
        boolean result = MEMO_MAP.containsKey(memo_id);
        return result;
    }

    protected static void putMemo(Memo m){
        MEMO_MAP.put( m.getMemoId(), m);
    }

    protected static Memo getMemo(long memo_id){
        return MEMO_MAP.get(memo_id);
    }

    /**
     * MemoRepository상에서 해당 id를 가진 메모를 지운다.
     * 로컬에 저장된 파일을 삭제하는것은 다른 문제!
     * @param long memo_id repository에서 제거하고자 하는 메모의 아이디.
     */
    protected static void removeMemo(long memo_id){
        MEMO_MAP.remove(memo_id);
    }
    /**
     * 메모레퍼지토리의 모든 내용들을 로컬파일에 입력한다.
     * 이때 DataCenter.writeMemoOnFile() 을 이용한다.
     */
    protected static void saveChanged(){
        Collection<Memo> col = MEMO_MAP.values();
        Iterator<Memo> it = col.iterator();
        Memo m;
        while(it.hasNext()){
            m = it.next();
            DataCenter.writeMemoOnFile(m);
        }
    }
}
