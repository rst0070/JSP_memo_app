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
        this.setSaveButton();
        this.setCloseButton();
    }

    /**
     * this method sets function that get list of tag selection from this object
     * @param {function} returnFunction 
     */
    setReturnFunction(returnFunction){
        this.returnFunction = returnFunction;
    }

    setVisible(visible){
        let value = 'none';
        if(visible) value = 'block';
        this.$pannel.css({
            display : value
        });
    }

    setList(){
        this.$container.append("<div id='selectTagDiv'></div>");
        this.$selectTagDiv = $('#selectTagDiv');
        this.$selectTagDiv.css({
            height:"10em",
            overflow:"auto"
        });
        this.allTagList.forEach(element => {
            this.$selectTagDiv.append("<input id='tagCheckbox"+element+"' type='checkbox' class='tagCheckbox' value='" + element + "'/>");
            this.$selectTagDiv.append("<label for='tagCheckbox"+element+"' >" + element + "</label><br/>");
        });
    }
    setSaveButton(){
        let buttonId = 'saveTagButton';
        this.$container.append("<button id='" + buttonId + "'>save</button>");
        this.$saveButton = $('#' + buttonId);
        this.$saveButton.on('click', ()=>{
            this.selectedTagList = [];
            let list = this.selectedTagList;
            $('input[class="tagCheckbox"]:checked').each(function() {
                list.push(this.value);
                console.log(this.value);
             });
            this.returnFunction(this.copyArray(this.selectedTagList));
            this.close();
        });
    }

    setCloseButton(){
        let buttonId = 'closeTagButton';
        this.$container.append("<button id='" + buttonId + "'>close</button>");
        this.$closeButton = $('#' + buttonId);
        this.$closeButton.on('click', ()=>{
            this.close();
        });
    }

    close(){
        $('input[class="tagCheckbox"]:checked').each(function() {
            this.checked = false;
        });
        this.setVisible(false);
    }
    copyArray(arr){
        let newArray = [];
        arr.forEach(element => {
            newArray.push(element);
        });
        return newArray;
    }
}