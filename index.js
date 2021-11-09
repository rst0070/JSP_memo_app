import express from "express";
import bodyParser from "body-parser";
const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(bodyParser.raw());
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

app.post("/hi", (req, res)=>{
    console.log(req.body.name);
    res.status(200).json({ data: 'message' })
})
app.listen(3000, ()=>{
    console.log('server started');
});