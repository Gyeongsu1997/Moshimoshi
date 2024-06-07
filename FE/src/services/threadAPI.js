import { BASE_URL } from '../constants/constants.js';
import * as token from '../utils/token.js';

export const PostRequest = function (content, anonymous) {
	this.content = content;
	this.anonymous = anonymous;
};

export const postThread = async function (request) {
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

export async function getThreads(pageNumber = 0) {
	const response = await fetch(`${BASE_URL}/api/threads?page=${pageNumber}`);
	return response;
}