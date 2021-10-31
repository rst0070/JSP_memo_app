import { MemoOBJ } from "./Memo.js";
import { memoEditPannel } from "./EditPannel.js";
import { tagSelectPannel } from "./TagSelectPannel.js";
//rest api??
function main(){
    const allTagList = ['java', 'flutter', 'swift', 'linear-algebra', 'react'];

    const TSP = new tagSelectPannel("selectTagContainer", allTagList);
    const MEP = new memoEditPannel("memoEditContainer", TSP);

    function createNewMemo(title, content, tag_list){
        console.log(title);
        console.log(content);
        tag_list.forEach(element => {
            console.log(element);
        })
    }
    $('#NewMemoButton').on('click', ()=>{
        MEP.setReturnFunction(createNewMemo);
        MEP.setText("title", "type content..", []);
        MEP.setVisible(true);
    })
}
$(main);

