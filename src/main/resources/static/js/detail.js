$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getComments();
})
//
// // 메모를 불러와서 보여줍니다.
// function getMessages() {
//     // 1. 기존 메모 내용을 지웁니다.
//     $('#main_post_box').empty();
//     let idx = location.href.split("id=")[1];
//     $.ajax({
//         type: 'GET',
//         url: `/blogs/detail/${idx}`,
//         data: {},
//         success: function (response) {
//             console.log(response);
//             let blog = response;
//             let id = blog.id;
//             let title = blog.title;
//             let username = blog.username;
//             let contents = blog.contents;
//             let modifiedAt = blog.modifiedAt;
//             addHTML(id, title, username, contents, modifiedAt);
//         }
//     });
// }
//
// function addHTML(id, title, username, contents, modifiedAt){
//     let tempHtml = makeMessage(id, username, title, contents, modifiedAt);
//     $('#main_post_box').append(tempHtml);
// }
//
// function makeMessage(id, username, title, contents, modifiedAt){
//     return `<div class="main-post">
//     <article class="message is-dark">
//       <div class="message-header">
//         <p>${title}</p>
//         <button onclick="back_page()" class="delete" aria-label="delete"></button>
//       </div>
//       <div class="dateTime">
//         <article class="message is-dark">
//           <div id="id-username" class="message-body">
//             📝 글쓴이 ${username}<span class="time_text">⏳️ ${modifiedAt}</span>
//           </div>
//         </article>
//       </div>
//
//       <article class="message is-dark">
//         <div class="message-body">
//           ${contents}
//      <!--        삭제버튼-->
//          <div class="delete_btn" onclick="deleteOne(${id})" id="${id}delete">
//             <button class="button is-black" >삭제하기</button>
//          </div>
//         </div>
//       </article>
//     </article>
//  <!--        댓글 달기-->
//         <article class="media">
//             <div class="media-content">
//                 <div class="field">
//                     <p class="control">
//                         <textarea id="comment" class="textarea" placeholder="Add a comment..."></textarea>
//                     </p>
//                 </div>
//                 <div class="field">
//                     <p class="control">
//                         <button class="button" onclick="comment_save()">댓글 달기</button>
//                     </p>
//                 </div>
//             </div>
//         </article>
//
//         <!--        댓글 목록-->
//         <div id="commentList">
//             <article class="media">
//                 <div class="media-content">
//                     <div class="content">
//                         <p>
//                             <strong id="username">Kayli Eunice </strong>
//                             <br id="comment_text">
//                             Sed convallis scelerisque mauris, non pulvinar nunc mattis vel. Maecenas varius felis sit
//                             amet magna vestibulum euismod malesuada cursus libero. Vestibulum ante ipsum primis in
//                             faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus lacinia non nisl id
//                             feugiat.
//                             <br>
//                         </p>
//                     </div>
//                 </div>
//             </article>
//         </div>
//
//     </div>
//     </article>
// </div>`
// }
//
//
// function deleteOne(id) {
//     // 1. DELETE /api/memos/{id} 에 요청해서 메모를 삭제합니다.
//     $.ajax({
//         type: "DELETE",
//         url: `/home/${id}`,
//         success: function (response) {
//             alert('메시지 삭제에 성공하였습니다.');
//             window.location.href = '/';
//         }
//     })
// }
//
function back_page() {
    window.location.href = '/';
}

// function isValidcomment(comment){
//     if (comment == ''){
//         alert('내용을 입력해 주세요.')
//         return false;
//     }return true;
// }

function comment_save() {
    // let user = [[${user}]];
    // // 로그인 여부 검사시 필요

    let comment = $('#comment').val();
    let blogId = [[${data.id}]]

    let data = {'comment': comment, 'blogId': blogId};
    if (comment == '') {
        alert('내용을 입력해 주세요.')
        return;
    } else {
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/comments",
            contentType: "application/json", // JSON 형식으로 전달함을 알리기
            data: JSON.stringify(data),
            success: function () {
                alert('댓글 작성 완료!');
                window.location.reload();
            }
        })
    }
}

function getComments() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#comment_group').empty();
    let user = [[${user}]]
    let commentList = [[${commentList}]]
    for (let i = 0; i < commentList.length; i++) {
        let contents = commentList[i].contents;
        let nickname = commentList[i].nickname;
        let modifiedAt = commentList[i].modifiedAt; //시간은 나중에 추가하자....
        let commentId = commentList[i].commentId;
        let tempHtml = ``
        if (user == nickname) {
            tempHtml = `      
         <!--        댓글 달기-->
        <article class="media">
            <div class="media-content">
                <div class="field">
                    <p class="control">
                        <textarea id="comment" class="textarea" placeholder="Add a comment..."></textarea>
                    </p>
                </div>
                <div class="field">
                    <p class="control">
                        <button class="button" onclick="comment_save()">댓글 달기</button>
                    </p>
                </div>
            </div>
        </article>

        <!--        댓글 목록-->
        <div id="commentList">
            <article class="media">
                <div class="media-content">
                    <div class="content">
                        <p>
                            <strong id="username">${nickname}</strong>
                            <br id="comment_text">
                           ${contents}
                            <br>
                        </p>
                    </div>
                </div>
            </article>
        </div>`
        }   $('#comment_group').append(tempHtml);
    }
}
