import { BASE_URL } from '../constants/constants.js';
import { authRequest } from './authAPI.js';
import * as StatusCode from '../constants/StatusCode.js'

export async function postThread({ content, anonymous }) {
	try {
		const response = await authRequest(`${BASE_URL}/api/threads`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				content,
				anonymous
			})
		});

		if (response.ok) {
			return StatusCode.SUCCESS;
		} else {
			return StatusCode.UNKNOWN;
		}
	} catch (e) {
		if (e.message === StatusCode.AUTH_FAILED) {
			return StatusCode.AUTH_FAILED;
		}
		return StatusCode.UNKNOWN;
	}
};

export async function getThreads(page = 0) {
	const response = await fetch(`${BASE_URL}/api/threads?page=${page}`);
	return response;
}