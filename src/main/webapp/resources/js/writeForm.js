function submitBoard() {
	var titleValue = $("#title").val();
	if (titleValue == null || titleValue == "") {
		alert("제목을 입력해주세요");
	} else {
		board.submit();
	}
}

function backToList() {
	location.href = "/board/board/";
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview").css("display", "block");
			$("#preview").attr("src", e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}	


