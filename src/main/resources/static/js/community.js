function post() {
    var questionId = $("#question_id").val();
    var textarea = $("#textarea").val();

    if (!textarea.trim()) {
        alert("回复内容不能为空");
        return;
    }


    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": textarea,
            "type": 1
        }),
        success: function (response) {
            if(response.code == 100) {
                window.location.reload();
            } else {
                if (response.code == 203) {
                    var isAccept = confirm(response.message);
                    if (isAccept) {
                        window.open("https://github.com/login/oauth/authorize?client_id=60b9e232c55203724978&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");

                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        }

    });
}