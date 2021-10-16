const UTIL = {};

function modifyMemo(memoId){
    
    let title = $('#MEMO'+memoId+" .memoTitle").text();
    let content = $('#MEMO'+memoId+" .memoContent").text();

    if( !UTIL.validateMemo(title, content) ){
        alert("you requested wrong type of memo");
        return;
    }
    let tagText = $('#MEMO'+memoId+" .memoTags").text();
    tagText = UTIL.deleteSpace( tagText );
    if( tagText.match("#memo") == null ) tagText += "#memo";

    let tags = tagText.split('#');

    $.ajax({
        type: "POST",
        url: "/tag/modify",
        data: {
            memoId: memoId,
            tags: tags,
            title: title,
            contents:content
        },
        success: ()=>{
            console.log("suc");
        },
        dataType: "json"
      });
    goToTag(TAG_NAME);
}

$('#NewMemoSave').on('click', createMemo);
function createMemo(){
    console.log("hi");
    let title = $("#NewMemoTitle").text();
    let content = $("#NewMemoContent").text();
    if( !UTIL.validateMemo(title, content) ){
        alert("you requested wrong type of memo");
        return;
    }

    let tagText = $("#NewMemoTags").text();
    console.log(tagText);
    tagText = UTIL.deleteSpace( tagText );
    if( tagText.match("#memo") == null ) tagText += "#memo";
    let tags = tagText.split('#');

    $.ajax({
        type: "POST",
        url: "/tag/create",
        data: {
            tags: tags,
            title: title,
            contents:content
        },
        success: ()=>{
            console.log("suc");
        },
        dataType: "json"
      });
    goToTag(TAG_NAME);
}

/**
 * 지정된 태그 페이지로 이동.
 * @param {*} tag 
 */
function goToTag(tag){
    location.replace("/tag/"+tag);
}


/**
 * returns spacebar deleted string
 */
UTIL.deleteSpace = (str)=>{
    let result = "";
    for(let i = 0; i < str.length; i++)
        if(str.charAt(i) != ' ') result+=str.charAt(i);
    return result;
}

UTIL.validateMemo = (title, content) => {
    if(title.lenght == 0 && content.lenght == 0) return false;
    return true;
}