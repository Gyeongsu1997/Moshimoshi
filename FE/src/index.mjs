import * as thread from './api/thread.mjs';

// const makeArticle = function (threadInfo) {
// 	$article = document.createElement('article');

// 	$divTop = document.createElement('div');
	
// 	$iProfile = document.createElement('i');
// 	$divNickname = document.createElement('div');
// 	$divCreatedAt = document.createElement('div');
// 	$divCreatedAt.innerText = threadInfo.createdAt;


// 	$divMid = document.createElement('div');
// 	$divMid.innerText = threadInfo.content;

// 	$divBot = document.createElement('div');
// 	$divComment = document.createElement('div');
// 	$divThumbsUp = document.createElement('div');
// 	$divImage = document.createElement('div');

// 	$article.appendChild($divTop);
// 	$article.appendChild($divMid);
// 	$article.appendChild($divBot);
// 	return $article;
// };


// $threads = document.getElementById('threads');
// // $threads.appendChild($article);

// fetch('http://localhost:8080/api/threads', option)
// .then(res => res.text())
// .then(text => console.log(text));

const btnPostThread = document.getElementById('btnPostThread');
const tfPostThread = document.getElementById('tfPostThread');
const chkAnonymous = document.getElementById('chkAnonymous');

btnPostThread.addEventListener('click', function () {
	const content = tfPostThread.value;
	const anonymous = chkAnonymous.checked;
	const request = new thread.PostRequest(content, anonymous);
	thread.postThread(request);
});