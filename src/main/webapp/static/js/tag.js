var content = $('.memo.memoContent');
console.log(content.innerWidth);

function modifyMemo(memoId){
    
    let title = $('#MEMO'+memoId+" .memoTitle").text();
    let content = $('#MEMO'+memoId+" .memoContent").text();


    goToTag(TAG_NAME);
}


/**
 * 지정된 태그 페이지로 이동.
 * @param {*} tag 
 */
function goToTag(tag){
    location.replace("/tag/"+tag);
}