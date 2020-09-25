var idOverlapChk = false;
var id="";
var chk=false;
var pwchk=false;

function backToList(path) {
	location.href = path + "/board/";
}

function pwAlgorithm() {
	var regex=/^\w{8,20}$/;
	var pw=$("#pw").val();
	if(regex.exec(pw)==null){
		$("#pwchkalert").text("형식이 일치하지 않습니다").css('color', 'red').css('font-weight', 'bold');
		pwchk=false;
	} else {
		$("#pwchkalert").text("비밀번호로 사용가능합니다").css('color', 'green').css('font-weight', 'bold');
		pwchk=true;
	}
}

function chkPw(){
	var pw = $("#pw").val();
	var pwchk=$("#pwchk").val();
	if(pw==pwchk){
		$("#pwtext").text("비밀번호가 일치합니다").css('color', 'green').css('font-weight', 'bold');
		chk=true;
	} else {
		$("#pwtext").text("비밀번호가 다릅니다").css('color', 'red').css('font-weight', 'bold');
		chk=false;
	}
}

function insertMember(path) {
	var idchknull = $("#id").val();
	var pwchknull = $("#pw").val();
	var namechknull = $("#name").val();
	if (idchknull != null && idchknull != "") {
		if (pwchknull != null && pwchknull != "") {
			if (namechknull != null && namechknull != "") {
				if(idOverlapChk){
					if(idchknull==id){
						if(chk){
							if(pwchk){
								memberInfo.method = "post";
								memberInfo.action = path + "/member/addMember.do";
								memberInfo.submit();
							} else {
								alert("비밀번호 형식이 일치하지 않습니다");
							}
						} else {
							alert("비밀번호가 일치하지 않습니다");
						}
					} else {
						alert("아이디가 중복확인 값과 다릅니다");
					}
				} else {
					alert("아이디 중복되었습니다");
				}
			} else {
				alert("이름을 입력해주세요");
			}
		} else {
			alert("비밀번호를 입력해주세요");
		}
	} else {
		alert("아이디를 입력해주세요");
	}
}

function idOverlap() {
	var idVal = $("#id").val();
	if (idVal != null && idVal != "") {
		$.ajax({
			url: "idOverlapCheck",
			type: "POST",
			data: { idVal: idVal },
			success: function(data) {
				if (data == "사용가능한 아이디입니다") {
					idOverlapChk = true;
					$("#text").text(data).css('color', 'green').css('font-weight', 'bold');
					id=idVal;
				} else {
					idOverlapChk = false;
					$("#text").text(data).css('color', 'red').css('font-weight', 'bold');
				}
			}
		});
	} else {
		alert("id를 입력해주세요");
	}
}