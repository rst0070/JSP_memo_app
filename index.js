import express from "express";
const app = express();

app.use(express.json());
app.use(express.static('src'));
app.get("/", (req, res)=>{
    console.log("root get")
    res.redirect("/index.html");
});

app.post("/memo/create", (req, res) => {
    console.log(req.body);
    console.log(req.body.title);
    console.log(req.body.content);
    console.log(req.body.tagList);
})
app.listen(3000, ()=>{
    console.log('server started');
});