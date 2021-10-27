class EditPannel{

    /**
     * @param title
     */
    constructor(containerID, title, content, tagList){
        let $pannel = $('#'+containerID);
        $pannel.append('<div id="editContainer"></div>');
        $pannel.css({
            content : " ",
            position: "absolute",
            width: "100%",
            height: "100%",
            background:"rgba(150, 150, 150, 0.5)"
        });
        this.$container = $('#editContainer');
        this.$container.css({
            width:"20em",
            marginLeft: "auto",
            marginRight: "auto",
            marginTop: "5em",
            background:"white",
            padding:"1em",
            borderRadius:"0.2em",
            boxShadow:"0 0 0 1px gray"
        });

        this.setTitle(title);
        this.setContent(content);
        this.setTagList(tagList);
        this.setTagButton();
        this.setSaveButton();
    }

    setTitle(titleStr){
        this.$container.append('<div contenteditable="true" id="editTitle"></div>');
        this.$title = $('#editTitle');
        this.$title.text(titleStr);

        this.$title.css({
            fontWeight: "bold"
        });
    }

    setContent(contentStr){
        this.$container.append('<div contenteditable="true" id="editContent"></div>');
        
        this.$content = $('#editContent');
        this.$content.text(contentStr);
    }

    setTagList(tagListArr){
        this.$container.append('<div id="editTagList"></div>');
        this.$tagList = $('#editTagList');

        this.tagList = tagListArr;
        let tagListStr = "";
        tagListArr.forEach((str) => {
            tagListStr += '#' + str + '\t';
        });

        this.$tagList.text(tagListStr);
        this.$tagList.css({
            fontStyle: "italic"
        });
    }

    setTagButton(){
        this.$container.append('<button id="modifyTagButton">modify tag</button>');
        this.$tagButton = $('#modifyTagButton');

    }

    setSaveButton(){
        this.$container.append('<button id="saveMemoButton">save</button>');
        this.$saveButton = $('#saveMemoButton');
    }
}