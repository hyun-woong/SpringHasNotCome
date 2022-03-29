$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();
})

// 메모를 불러와서 보여줍니다.
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#content-list').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/home',
        success: function (response) {
            console.log(response)
            for (let i = 0; i < response.length; i++) {
                let blog = response[i];
                let id = blog.id;
                let title = blog.title;
                let username = blog.username;
                let modifiedAt = blog.modifiedAt;
                addHTML(id, title, username, modifiedAt)
            }
        }
    })
}

// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, username, title, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `
             <tr>
                <th>${id}</th>
                <td>${title}</td>
                <td style="cursor: pointer" class="title_focus" onclick="showPost(${id})">${username}</td>
                <td>${modifiedAt}</td>
            </tr>

       `
    // 2. #cards-box 에 HTML을 붙인다.
    $('#content-list').append(tempHtml);
}

//
function writePost() {
    window.location.href = '/post.html';
}

function showPost(id) {
    console.log(id);
    window.location.href = `/api/detail/${id}`
}