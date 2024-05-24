import { BASE_URL } from '../constants/constants.mjs';
import * as token from '../utils/token.mjs';

const PostRequest = function (content, anonymous) {
	this.content = content;
	this.anonymous = anonymous;
};

const postThread = async function (request) {
	try {
		const response = await fetch(`${BASE_URL}/api/threads`, {
			method: 'POST',
			headers: {
				'Authorization': `Bearer ${token.getAccessToken()}`,
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(request)
		});
		if (response.ok) {
			const data = await response.json();
			return data;
		} else {
			//
			return null;
		}
	} catch (e) {
		//예외 처리
		return null;
	}
};

export { PostRequest, postThread };