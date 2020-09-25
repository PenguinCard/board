function move(path, index, item, value){
	if(item!=null&&item!=""&&value!=null&&value!=""){
		location.href=path+"/board/?count="+index+"&"+item+"="+value;
	} else {
		location.href=path+"/board/?count="+index;
	}
}


function search(path){
	var item=$("select[name=item]").val();
	var keyword=$("#keyword").val();
	if(keyword!=null&&keyword!=""){
		location.href=path+"/board/?count=1&"+item+"="+keyword;
	} else {
		alert("검색어를 입력해주세요.");
	}
}