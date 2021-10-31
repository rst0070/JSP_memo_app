/**
 * This class used to do network things
 * after end of ajax communication, reload page.
 * 
 * 
 */
class Ajax{
    constructor(){
        this.url.memo.create = "/memo/create";
        this.url.memo.modify = "/memo/modify";
        this.url.memo.delete = "/memo/delete";

        this.url.tag.create = "/tag/create";
        this.url.tag.delete = "/tag/delete";
    }

    createTag(tagName){
        if(!tagName) return;

        $.ajax({
            url : this.url.tag.create,
            type: 'POST',
            dataType: 'json',
            data : {
                name : tagName
            },
            success: () => {
                this.reload("");
            }
        });
    }
    deleteTag(tagName){
        if(!tagName) return;

        $.ajax({
            url : this.url.tag.delete,
            type: 'POST',
            dataType: 'json',
            data : {
                name : tagName
            },
            success: () => {
                this.reload("");
            }
        });
    }

    createMemo(title, content, tagList){
        if(!title) title = "";
        if(!content) content = "";
        if(!tagList) tagList = [""];

        $.ajax({
            url : this.url.memo.create,
            type: 'POST',
            dataType: 'json',
            data : {
                title : title,
                content : content,
                tagList : tagList
            },
            success: () => {
                this.reload(tagList[0]);
            }
        });
    }

    modifyMemo(memoID, title, content, tagList){
        if(!memoID) return;
        if(!title) title = "";
        if(!content) content = "";
        if(!tagList) tagList = [""];

        $.ajax({
            url : this.url.memo.modify,
            type: 'POST',
            dataType: 'json',
            data : {
                memoID: memoID,
                title : title,
                content : content,
                tagList : tagList
            },
            success: () => {
                this.reload(tagList[0]);
            }
        });
    }

    deleteMemo(memoID){
        if(!memoID) return;

        $.ajax({
            url : this.url.memo.delete,
            type: 'POST',
            dataType: 'json',
            data : {
                memoID: memoID
            },
            success: () => {
                this.reload("");
            }
        });
    }

    reload(tag_name){
        if(!tag_name) tag_name = "";
        window.location.replace("/memolist/"+tag_name);
    }
}


export const ajax;
ajax = new Ajax(); 
