var replyid;
function replyUpdateButton(id) {
     document.getElementById('replyPostContent-'+id).readOnly = false;
     const container = document.getElementById('replyMode-'+id);
     var button = '<button class="btn ReplyPosting" onclick="replyUpdate()">등록</button>'
     replyid = id;
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
        id : document.getElementById("reply-id-"+replyid).value,
        content : document.getElementById("replyPostContent-"+replyid).value,
    }
    var videoId = document.getElementById('video-id').value;
    console.log(videoId);
    httpRequest = new XMLHttpRequest();
    httpRequest.open("POST","/reply/update", true);
    httpRequest.responseType = "json";
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.send(JSON.stringify(data));
    setTimeout(function(){
        window.location.href="/video?id="+videoId;
    }, 300);
}