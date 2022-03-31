//
//
//
// $(document).ready(function () {
//     // HTML 문서를 로드할 때마다 실행합니다.
//     getComments();
// })
//
// function back_page() {
//     window.location.href = '/';
// }
//
// function comment_save() {
//     // let user = [[${user}]];
//     // // 로그인 여부 검사시 필요
//
//     let comment = $('#comment').val();
//     let blogId = [[${data.id}]]
//
//     let data = {'comment': comment, 'blogId': blogId};
//     if (comment === '') {
//         alert('내용을 입력해 주세요.')
//     } else {
//         console.log(data);
//         $.ajax({
//             type: "POST",
//             url: "/comments",
//             contentType: "application/json", // JSON 형식으로 전달함을 알리기
//             data: JSON.stringify(data),
//             success: function () {
//                 alert('댓글 작성 완료!');
//                 window.location.reload();
//             }
//         })
//     }
// }
//
// function getComments() {
//     // 1. 기존 메모 내용을 지웁니다.
//     $('#comment_group').empty();
//     let user = [[${user}]]
//     let commentList = [[${commentList}]]
//     for (let i = 0; i < commentList.length; i++) {
//         let contents = commentList[i].contents;
//         let nickname = commentList[i].nickname;
//         let modifiedAt = commentList[i].modifiedAt; //시간은 나중에 추가하자....
//         let commentId = commentList[i].commentId;
//         let tempHtml = ``
//         if (user == nickname) {
//             tempHtml = `
//          <!--        댓글 달기-->
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
//                             <strong id="username">${nickname}</strong>
//                             <br id="comment_text">
//                            ${contents}
//                             <br>
//                         </p>
//                     </div>
//                 </div>
//             </article>
//         </div>`
//         }   $('#comment_group').append(tempHtml);
//     }
// }
