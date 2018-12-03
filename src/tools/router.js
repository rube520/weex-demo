/**
 * 页面跳转路由
 */
let navigator = weex.requireModule('navigator');

const routes = [
    {path:'index', component:'index',name:"首页"},
    {path:'error', component:'error',name:"错误页面"},
    {path:'modules/goods-detail', component:'goods-detail',name:"商品详情"},
    {path:'modules/goods-list', component:'goods-list',name:"商品列表"}
];

function getRoute(component) {
    let name = "";
    let targets = routes.filter(function (route) {
        return route.component === component;
    });
    if(targets.length > 0){
        name = targets.pop().path;
    } else {
        name = "error";
    }
    let arr = weex.config.bundleUrl.split("/");
    arr.pop();
    if(arr.includes("modules")){
        arr.pop();
    }
    if (weex.config.env.platform === "Web"){
        arr.push(name + ".html");
        return arr.join("/");
    } else {
        arr.push(name + ".js");
        return arr.join("/");
    }
}

export default function navigateToNextPage(component) {
    navigator.push({
        url:getRoute(component),
        animated:"true"
    })
}
