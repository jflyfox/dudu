//定义的空函数，移动到这里是为了避免有的地方，单独只加载这个js，而不加在基础jscommon，如此确保已加载此方法
var ymPrompt_reload = true;

function EmptyFunc(){
	return;
}
//普通alert
function Alert(message,handler){
	handler = handler || null;
	ymPrompt.alert({title:'信息提示',message:message,handler:handler});
}
//成功信息SucceedInfo
function SucceedInfo(message,handler){
	handler = handler || null;
	ymPrompt.succeedInfo({message:message,width:null,height:null,handler:handler});
}
//失败信息ErrorInfo
function ErrorInfo(message,handler){
	handler = handler || null;
	ymPrompt.errorInfo({message:message,handler:handler});
}
//询问信息 
function Confirm (message,ok_fun,cancel_fun){ 
	ConfirmTitle("询问信息",message,ok_fun,cancel_fun);
}

//询问信息带标题 
function ConfirmTitle(title,message,ok_fun,cancel_fun){ 
	ymPrompt.confirmInfo({title:title,message:message,handler:function(tp){
		function f(fn) {
		   fn(); 
		}
		if(tp=='ok'){
			f(ok_fun); 
		}
		if(tp=='cancel'){
			if(cancel_fun!=null){
				f(cancel_fun);
			} 
		} 
	}});
}

var size_cha = 50;

//自定义iframe
function Iframe(url,width,height,title,maxBtn,minBtn,isParent,handler){ 
	if(ymPrompt_reload) handler = handler || WinClose;
	else handler = handler || null;
	if(isParent){
		var clientWidth = top.getWindowWidth();
		var clientHeight = top.getWindowHeight();
		if(width > clientWidth || height > clientHeight){
			width = clientWidth - size_cha;
			height = clientHeight - size_cha;
		}
		window.top.ymPrompt.win({message:url,width:width,height:height,title:title,handler:handler,maxBtn:maxBtn,minBtn:minBtn,iframe:true});
	}else{ 
		var clientWidth = getWindowWidth();
		var clientHeight = getWindowHeight();
		if(width > clientWidth || height > clientHeight){
			width = clientWidth - size_cha;
			height = clientHeight - size_cha;
		}
		ymPrompt.win({message:url,width:width,height:height,title:title,handler:handler,maxBtn:maxBtn,minBtn:minBtn,iframe:true});
	} 
} 

//自定义iframe
function IframeMax(url,title,isParent,handler){ 
	Iframe(url,10000,10000,title,true,false,isParent,handler);
}  

function IframeFull(url,title,handler){ 
	var clientWidth = getWindowWidth();
	var clientHeight = getWindowHeight();
	ymPrompt.win({message:url,width:clientWidth,height:clientHeight,title:title,
		handler:handler,maxBtn:false,minBtn:false,iframe:true});
}  

function WinClose(data,form){
	data = data||null;
	form = form||form1;
	var flag = (typeof console != 'undefined');
	var str = window.location.href;
	if(data=="close"){
		if(form.search){//存在search才出现这个情况
			if(typeof form.search.length==='undefined'){//页面只有一个的情况下
				form.search.click();
			}else{//存在多个的情况下
				if(form.search[0].getAttribute("onclick")===null){//第一个search不是查询，判断条件为是不是绑定对了onclick方法
					Alert("该页面存在多个search,并且第一个search不是查询，请查看---->"+str);
				}else{
					form.search[0].click();
				}
				if(flag)
			 			console.log('-------------------------------------->该页面存在多个search，请查看---->'+str);
			}
		}else{
			Alert("关闭，无search，请查看--->"+str);
			if(flag)
			  console.log('-------------------------------------->关闭，无search，请查看----->'+str);
		}
	}
}

//关闭Iframe
function closeIframe(obj) {
	if(obj){
		//可以考虑使用window.top.ymPrompt.doHandler("close",true);
		window.parent.parent.ymPrompt.doHandler("close",true);
	}else{
		window.parent.ymPrompt.doHandler("close",true);
	}
}

//关闭Iframe 刷新父页面
function closeIframeReload() { 
	//刷新父页面
	window.parent.location.reload();//这个方法会发生页面重载，IE6下面会出现刷新弹窗。
	window.parent.ymPrompt.doHandler("close",true);  
}


/**
// 自定义弹出框
$("#map_index").on("click",function(){
ymPrompt.win({
	message:$("#menu").html()
	,title:'页面索引',height:230,width:240,
	//btn:[['关闭','cancel']], //自定义按钮
	titleBar:false,winPos:"rt",showMask:false,fixPosition:true,dragOut:false});
});
**/