/**
 * This class used to do network things
 * after end of ajax communication, reload page.
 * 
 * 
 */
 class Ajax{
    constructor(){
        this.url = {
            tag : '/tag',
            memo : '/memo'
        }
    }

    createTag(tagName){
        if(!tagName) return;

        $.ajax({
            url : this.url.tag,
            type: 'POST',
            dataType: 'json',
            data : JSON.stringify({
                action: "create",
                name : tagName
            }),
            success: () => {
                this.reload(TAG_NAME);
            }
        });


    }
    deleteTag(tagName){
        if(!tagName) return;

        $.ajax({
            url : this.url.tag,
            type: 'POST',
            dataType: 'json',
            data : JSON.stringify({
                action: "delete",
                name : tagName
            }),
            success: () => {
                this.reload("");
            }
        });

    }

    createMemo(memoTitle, memoContent, memoTagList){
        if(!memoTitle) memoTitle = "";
        if(!memoContent) memoContent = "";
        if(!memoTagList) memoTagList = [""];

        let dataObj = {
            action: "create",
            title : memoTitle,
            content : memoContent,
            tagList : memoTagList
        }

        $.ajax({
            url : this.url.memo,
            async: true,
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            data : JSON.stringify(dataObj)
        }).done((data)=>{
            console.log(data.message);
            this.reload(TAG_NAME);
        });

        setTimeout(()=>{
            this.reload(TAG_NAME);
        }, 2000);
    }

    modifyMemo(memoID, title, content, tagList){
        if(!memoID) return;
        if(!title) title = "";
        if(!content) content = "";
        if(!tagList) tagList = [""];

        let dataObj = {
            action : "modify",
            memoID: memoID,
            title : title,
            content : content,
            tagList : tagList
        };

        $.ajax({
            url : this.url.memo,
            type: 'POST',
            dataType: 'json',
            data : JSON.stringify(dataObj),
            success: () => {
                this.reload(tagList[0]);
            }
        });
        setTimeout(()=>{
            this.reload(TAG_NAME);
        }, 2000);
    }

    deleteMemo(memoID){
        if(!memoID) return;

        $.ajax({
            url : this.url.memo,
            type: 'POST',
            dataType: 'json',
            data : JSON.stringify({
                action: "delete",
                memoID: memoID
            }),
            success: () => {
                this.reload("");
            }
        });
        setTimeout(()=>{
            this.reload(TAG_NAME);
        }, 2000);
    }

    reload(tag_name){
        if(!tag_name) tag_name = "";
        console.log("going reload");
        window.location.replace("/memolist/"+tag_name);
    }
}


export const ajax = new Ajax();