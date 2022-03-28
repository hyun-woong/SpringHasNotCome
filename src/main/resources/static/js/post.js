
// 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
// 사용자가 내용을 올바르게 입력하였는지 확인합니다.
function isValidtitle(title_box) {
    if (title_box == '') {
        alert('제목을 입력해주세요');
        return false;
    }
    if (title_box.trim().length > 20) {
        alert('공백 포함 20자 이하로 입력해주세요');
        return false;
    }
    return true;
}

function isValidusername(username_box) {
    if (username_box == '') {
        alert('이름을 입력해주세요');
        return false;
    }
    if (username_box.trim().length > 6) {
        alert('공백 포함 6자 이하로 입력해주세요');
        return false;
    }
    return true;
}

function isValidContents(contents_box) {
    if (contents_box == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents_box.trim().length > 300) {
        alert('공백 포함 300자 이하로 입력해주세요');
        return false;
    }
    return true;
}
//=========================여까지가 내용을 제대로 채웠는지 확인하는 부분=========================
// 메모를 생성합니다.
function post_save() {
    // 1. 작성한 메모를 불러옵니다.
    let title = $('#title_box').val();
    let username = $('#username_box').val();
    let contents = $('#contents_box').val();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidtitle(title) == false) {
        return;
    }
    if (isValidusername(username) == false) {
        return;
    }
    if (isValidContents(contents) == false) {
        return;
    }
    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'username': username,'title': title, 'contents': contents};
    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/home",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('게시글이 성공적으로 작성되었습니다.');
            window.location.href = '/templates/index.html';
        }
    });
}