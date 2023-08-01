function replyUpdateButton() {
     document.getElementById('replyPostContent').readOnly = false;
     const container = document.querySelector('.replyMode')
     var button = '<button class="btn ReplyPosting" onclick="replyUpdate()">등록</button>'
     container.innerHTML += button;
}

function replyDelete(id, parentId) {
    let data = {
        parentId : parentId,
        id : id,
    }
    httpRequest = new XMLHttpRequest();
    httpRequest.open("DELETE","/reply/delete", true);
    httpRequest.responseType = "json";
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.send(JSON.stringify(data));
    setTimeout(function(){
        window.location.href="/player";
    }, 300);
}

function replyUpdate() {
    let data = {
        id : document.getElementById("reply-id").value,
        content : document.getElementById("replyPostContent").value,
    }
    let videoId = document.querySelector(".video-id").value;
    httpRequest = new XMLHttpRequest();
    httpRequest.open("POST","/reply/update", true);
    httpRequest.responseType = "json";
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.send(JSON.stringify(data));
    setTimeout(function(){
        window.location.href="/video?id=" + videoId;
    }, 300);
}