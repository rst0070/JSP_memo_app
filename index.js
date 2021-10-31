import express from "express";
const app = express();

app.use(express.static('src'));
app.get("/", (req, res)=>{
    res.redirect("/index.html");
});
app.listen(3000, ()=>{
    console.log('server started');
});