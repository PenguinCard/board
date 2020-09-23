function login(path){
	var idVal=$("#id").val();
	var pwVal=$("#pw").val();
	if(idVal==""||idVal==null){
		alert("id를 입력해주세요");
	} else {
		if(pwVal==""||pwVal==null){
			alert("pw를 입력해주세요");
		} else {
			$.ajax({
				url: "login.do",
				type: "POST",
				data: { m_id: idVal, m_pw: pwVal},
				success: function(data) {
					if(data=="true"){
						location.href=path+"/board/";
					} else {
						alert("아이디 또는 비밀번호가 잘못되었습니다");
						location.href=path+"/member/loginForm";
					}					
				}
			});
		}
	}
}