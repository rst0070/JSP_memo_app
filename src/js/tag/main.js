import { MemoOBJ } from "./Memo.js";
import { MemoEditPannelOBJ } from "./EditPannel.js";
import { tagSelectPannel } from "./TagSelectPannel.js";
//rest api??
function main(){
    const allTagList = ['java', 'flutter', 'swift', 'linear-algebra', 'react'];
    const TSP = new tagSelectPannel("selectTagContainer", allTagList);

    function getNewMemo(tag_list){
        tag_list.forEach(element => {
            console.log(element);
        })
    }

    TSP.setReturnFunction(getNewMemo);
    $('#NewMemoButton').on('click', ()=>{
        TSP.setVisible(true);
    })
}
$(main);

