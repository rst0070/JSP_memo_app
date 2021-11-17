import { ajax } from "./Ajax.js";
import { memoEditPannel } from "./EditPannel.js";
import { tagSelectPannel } from "./TagSelectPannel.js";
import { tagEditPannel } from "./TagEditPannel.js";

//rest api??
function main(){
    

    function getAllTagsFromPage(){
        let list = [];
        let tags = $('.navBar').find('.tagName');
        tags.each((index, item)=>{
            let name = $(item).attr('value');
            list.push(name);
        });
        return list;
    }

    const allTagList = getAllTagsFromPage();
    const TEP = new tagEditPannel("tagEditPannel", allTagList)
    const TSP = new tagSelectPannel("selectTagContainer", allTagList);
    const MEP = new memoEditPannel("memoEditContainer", TSP);

    function tagAction(action, list){
        if(action == "create"){
            list.forEach(element => {
                ajax.createTag(element);
            })
        }else{
            list.forEach(element => {
                ajax.deleteTag(element);
            })
        }
        setTimeout(()=>{
            ajax.reload("");
        }, 2000);
    }
    TEP.setReturnFunction(tagAction);

    $('#editTagButton').on('click', ()=>{
        TEP.setVisible(true);
    })


    function createNewMemo(title, content, tag_list){
        ajax.createMemo(title, content, tag_list);
    }

    /**
     * execute AJAX actions to modify memo already exists.
     */
    function modifyMemo(memoID, title, content, tag_list){
        ajax.modifyMemo(memoID, title, content, tag_list);
    }

    /**
     * execute AJAX actions to delete memo already exists.
     */
    function deleteMemo(memoID){
        ajax.deleteMemo(memoID);
    }
    /**
     * when user want to create new memo.
     */
    $('#NewMemoButton').on('click', ()=>{
        MEP.setReturnFunction(createNewMemo);
        MEP.setText("title", "type\ncontent..", []);
        MEP.setVisible(true);
    })

    /**
     * when user want to modify memo already exists.
     */
    $('.memoModify').on('click', (event)=>{
        let $element = $(event.target).parent();
        let memoID = $element.attr("memoid");
        let title = $element.find('.memoTitle').text();
        let content = $element.find('.memoContent').text();
        let tagEs = $element.find('.memoTags').find('.tag');
        let tagList = [];

        tagEs.each((index, item)=>{
            console.log(item);
            let tag = $(item).attr('value');
            tagList.push(tag); 
        });
        
        MEP.setReturnFunction((title, content, tag_list)=>{
            modifyMemo(memoID, title, content, tag_list);
        });
        MEP.setText(title, content, tagList);
        MEP.setVisible(true);
    })

    $('.memoDelete').on('click', (event)=>{
        console.log("delete");
        let $element = $(event.target).parent();
        let memoID = $element.attr("memoid");
        deleteMemo(memoID);
    });
}
$(main);
