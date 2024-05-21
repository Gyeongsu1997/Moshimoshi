let ThreadPostRequest = function (content, anonymous) {
	this.content = content;
	this.anonymous = anonymous;
};

let btnPost = document.getElementById('btnPost');
let tfPostThread = document.getElementById('tfPostThread');
let chkAnonymous = document.getElementById('chkAnonymous');

btnPost.addEventListener('click', function () {
	let content = tfPostThread.value;
	let anonymous = chkAnonymous.checked;
	let request = new ThreadPostRequest(content, anonymous);
	// /api/threads로 POST 요청
});