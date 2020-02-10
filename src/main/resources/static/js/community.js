/**
 * 评论一个问题
 */
function commentQuestion() {
    var questionId = $("#question_id").val();
    var content = $("#textarea").val();

    if (!content.trim()) {
        alert("回复内容不能为空");
        return;
    }

    sendComment(questionId, 1, content);
}

/**
 * 评论一个评论
 */
function commentComment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();

    if (!content.trim()) {
        alert("回复内容不能为空");
        return;
    }

    sendComment(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function onCommentIconClick(e) {
    var id = e.getAttribute("data-id");

    var subCommentContainer = $("#comment-" + id);
    if (!$(e).hasClass("icon-active")) {

        if(subCommentContainer.children().length == 2) {
            $.getJSON( "/comment/" + id, function(data) {
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "text": comment.user.name
                    })).append($("<div/>", {
                        "text": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "text": moment(comment.gmtCreate).format("YYYY-MM-DD")
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "comment-item"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
            });
        }
        //展开
        $(e).addClass("icon-active");
        subCommentContainer.addClass("in");
    } else {
        //折叠
        $(e).removeClass("icon-active");
        subCommentContainer.removeClass("in");
    }
}

/**
 * 将评论发送到后台
 * @param id
 * @param type
 * @param content
 */
function sendComment(id, type, content) {

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": type
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

/**
 * 加载tag标签
 */
function loadTags(arr, tagsPane) {
    for (var i = 0; i < arr.length; i++) {

        var tag = $("<span/>", {
            "class": "glyphicon glyphicon-tag",
            "text": arr[i],
            "onclick": "onTagClicked(this)"
        });

        var label = $("<span/>", {
            "class": "label label-info question-tag"
        }).append(tag);

        tagsPane.append(label);
    }
    console.log("load ok");
}

/**
 * 加载所有tag标签
 */
function loadAllTags() {

    var langArr = ["Java", "Python", "Php", "C", "Cpp", "Kotlin", "JavaScript"];
    var langTagPane = $("#language");
    loadTags(langArr, langTagPane);

    var frameArr = ["Spring", "Spring Boot", "Mybatis", "TensorFlow", "Django"];
    var frameTagPane = $("#framework");
    loadTags(frameArr, frameTagPane);
}

/**
 * tag标签的点击事件
 * @param e
 */
function onTagClicked(e) {
    var tagValue = $(e).text();
    var tagInputArea = $("#tag-input-area");
    var previous = tagInputArea.val();
    if(previous) {
        if(previous.indexOf(tagValue) < 0)
            tagInputArea.val(previous + ',' + tagValue);
    } else {
        tagInputArea.val(tagValue);
    }
}

function popUp() {
    $("#tab-area").show();
}

