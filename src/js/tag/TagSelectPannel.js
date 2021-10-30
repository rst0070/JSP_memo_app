/**
 * !!!PREMISE!!!
 * -> all tag list doesn't change until reload page.
 * -> it means add new tag or remove tag actions should reload page in whole app.
 * because this TagSelectPannel object will be created once at page,
 * and constructor requires all tag list. => it doesn't listen changes about tags until reload page.
 */
class TagSelectPannel{
    constructor(containerID, tagList){
        this.$pannel = $('#'+containerID);
        this.$pannel.append('<div id="tagContainer"></div>');
        this.$pannel.css({
            content : " ",
            position: "absolute",
            width: "100%",
            height: "100%",
            background:"rgba(150, 150, 150, 0.5)",
            display: "none"
        });
        this.$container = $('#tagContainer');
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

        this.allTagList = tagList;
        this.setList();
    }

    setReturnFunction(returnFunction){
        this.returnFunction = returnFunction;
    }

    setList(){
        this.$container.append("<div></div>")
    }
    setSaveButton(){
        let buttonId = 'saveTagButton';
        this.$container.append("<button id='" + buttonId + "'>save</button>");
        this.$saveButton = $('#' + buttonId);
        this.$saveButton.on('click', ()=>{
            this.returnFunction(this.selectedTagList);
        });
    }

    setCloseButton(){

    }
}