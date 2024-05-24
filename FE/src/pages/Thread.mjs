import * as threadAPI from '../api/threadAPI.mjs';

const btnPostThread = document.getElementById('btnPostThread');
const tfPostThread = document.getElementById('tfPostThread');
const chkAnonymous = document.getElementById('chkAnonymous');


const handlePost = async function () {
	const content = tfPostThread.value;
	const anonymous = chkAnonymous.checked;
	const request = new threadAPI.PostRequest(content, anonymous);
	const data = await threadAPI.postThread(request);
	if (data === null) {
		//예외 처리
	}
	//리다이렉트
};

btnPostThread.addEventListener('click', handlePost);