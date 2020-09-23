function backToList(path){
	location.href=path+"/board/";
}

function checkPassword(path, pw){
	var pwchk=$("#pw").val();
	if(pwchk==pw){
		location.href=path+"/member/changeForm";
	} else {
		alert("비밀번호가 일치하지 않습니다");
		location.href=path+"/member/checkForm";
	}
}