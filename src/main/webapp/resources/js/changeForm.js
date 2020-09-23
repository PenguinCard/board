function backToList(path){
	location.href=path+"/board/";
}

function updateMember(path){
	var pw=$("#pw").val();
	if(pw!=null&&pw!=""){
		memberInfo.method="post";
		memberInfo.action=path+"/member/update.do";
		memberInfo.submit();
	} else {
		alert("비밀번호를 입력해주세요");
	}
}

function deleteMember(path, num) {
	var chk=confirm("탈퇴하시겠습니까?");
	if(chk){
		location.href=path+"/member/delete.do?num="+num;
	} else {
		location.href=path+"/member/changeForm";
	}
}