class TagEditPannel{
    constructor(containerID, tagList){
        this.$pannel = $('#'+containerID);
        
        this.$pannel.css({
            content : " ",
            position: "fixed",
            top:"0",
            left: "0",
            zIndex:"6",
            width: "100%",
            height: "100%",
            background:"rgba(150, 150, 150, 0.5)",
            display: "none"
        });
        this.$pannel.append('<div id="tagEditContainer"></div>');
        this.$container = $('#tagEditContainer');
        this.$container.css({
            width:"20em",
            marginLeft: "auto",
            marginRight: "auto",
            marginTop: "5em",
            padding:"1em",
            borderRadius:"0.2em",
            boxShadow:"0 0 0 1px gray"
        });

        if(tagList)
            this.allTagList = tagList;

        this.setList();
        this.setDeleteButton();
        this.setCreateButton();
        this.setCloseButton();
    }

    /**
     * this method sets function that get list of tag selection from this object
     * @param {function} returnFunction(action : "create" or "delete", tagList: arr of tag name)
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
        this.$container.append("<div id='tagListDiv'></div>");
        this.$tagListDiv = $('#tagListDiv');
        this.$tagListDiv.css({
            height:"20em",
            overflow:"auto"
        });
        if(this.allTagList)
            this.allTagList.forEach(element => {
                if(element){
                    this.$tagListDiv.append("<input id='tagCheckbox"+element+"' type='checkbox' class='tagCheckbox' value='" + element + "'/>");
                    this.$tagListDiv.append("<label for='tagCheckbox"+element+"' >" + element + "</label><br/>");    
                }
            });
        this.$tagListDiv.append("<input id='newTagText' style='width: 90%;' type='text' placeholder='type new tag name and press create button'/><br/>");
    }

    setDeleteButton(){
        let buttonId = 'deleteTagButton';
        this.$container.append("<button id='" + buttonId + "'>delete</button>");
        this.$deleteButton = $('#' + buttonId);
        this.$deleteButton.on('click', ()=>{
            this.selectedTagList = [];
            let list = this.selectedTagList;
            $('input[class="tagCheckbox"]:checked').each(function() {
                list.push(this.value);
             });
            this.returnFunction("delete",this.copyArray(this.selectedTagList));
            this.close();
        });
    }
    setCreateButton(){
        let buttonId = 'createTagButton';
        this.$container.append("<button id='" + buttonId + "'>create</button>");
        this.$createButton = $('#' + buttonId);
        this.$createButton.on('click', ()=>{
            let name = $('#newTagText').val();
            this.returnFunction("create", [name]);
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
        $('#newTagText').val("");
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

export const tagEditPannel = TagEditPannel;