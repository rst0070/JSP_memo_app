/**
 * HOW TO USE <br/>
 * Constructor sets innerHtml and references for them.(set methods)
 * by `setText(title: string, content: string, tagList: array of string)`, you can set inner text of EditPannel.
 * by `setVisible(boolean)`, you can set visiblity.
 * by `clean()`, you can remove all inner texts constructed by setText.
 */
class EditPannel{

    /**
     * @param {string} containerID container that EditPannel will be in.
     */
    constructor(containerID){
        this.$pannel = $('#'+containerID);
        this.$pannel.append('<div id="editContainer"></div>');
        this.$pannel.css({
            content : " ",
            position: "absolute",
            width: "100%",
            height: "100%",
            background:"rgba(150, 150, 150, 0.5)",
            display: "none"
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

        this.setTitle();
        this.setContent();
        this.setTagList();
        this.setTagButton();
        this.setSaveButton();
        this.setCloseButton();
    }

    /**
     * when save button pressed, returnFunction gets title, content, tagList
     * @param {function} returnFunction function(title, content, tagList)
     */
    setReturnFunction(returnFunction){
        this.returnFunction = returnFunction;
    }
    /**
     * @param {boolean} visible 
     */
    setVisible(visible){
        let value = "block";
        if(!visible) value = "none";

        this.$pannel.css({
            display: value
        })
    }

    setText(title, content, tagList){
        this.$title.text(title);
        this.$content.text(content);

        this.tagList = tagList;
        let tagListStr = "";
        this.tagList.forEach((str) => {
            tagListStr += '#' + str + '\t';
        });

        this.$tagList.text(tagListStr);
    }

    clean(){
        this.$title.text("");
        this.$content.text("");
        this.tagList = [];
        this.$tagList.text("");
    }
    setTitle(){
        this.$container.append('<div contenteditable="true" id="editTitle"></div>');
        this.$title = $('#editTitle');
        this.$title.css({
            fontWeight: "bold"
        });
    }

    setContent(){
        this.$container.append('<div contenteditable="true" id="editContent"></div>');
        this.$content = $('#editContent');
    }

    setTagList(){
        this.$container.append('<div id="editTagList"></div>');
        this.$tagList = $('#editTagList');
        this.$tagList.css({
            fontStyle: "italic"
        });

        let self = this;
        
        tagSelectPannel.setReturnFunction((list)=>{
            self.tagList = list;
            let tagListStr = "";
            self.tagList.forEach((str) => {
                tagListStr += '#' + str + '\t';
            });

            self.$tagList.text(tagListStr);
        });
    }

    setTagButton(){
        this.$container.append('<button id="modifyTagButton">modify tag</button>');
        this.$tagButton = $('#modifyTagButton');
        this.$tagButton.on('click', ()=>{
            tagSelectPannel.setVisible(true);
        })
    }

    setSaveButton(){
        this.$container.append('<button id="saveMemoButton">save</button>');
        this.$saveButton = $('#saveMemoButton');
        this.$saveButton.on('click', ()=>{
            let title = this.$title.text();
            let content = this.$content.text();
            let tagList = [];
            this.tagList.forEach(element=>{
                tagList.push(element);
            })

            this.returnFunction(title, content, tagList);
            this.clean();
            this.setVisible(false);
        })
    }

    setCloseButton(){
        this.$container.append('<button id="closeMemoButton">close</button>');
        this.$closeButton = $('#closeMemoButton');
        this.$closeButton.on('click', (()=>{
            this.clean();
            this.setVisible(false);
        }).bind(this));
    }
}