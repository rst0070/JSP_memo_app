$(()=>{
    let box1 = $('#login-box');
    let box2 = $('#changepw-box');
    let changeboxVisible = false;
    $('#changeform').on('click',()=>{
        if(changeboxVisible){
            box2.css({
                display:"none"
            })
            box1.css({
                display:"block"
            })
        }else{
            box1.css({
                display:"none"
            })
            box2.css({
                display:"block"
            })
        }
        changeboxVisible = !changeboxVisible;
    });
})