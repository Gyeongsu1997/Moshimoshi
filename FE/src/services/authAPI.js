import { BASE_URL } from '../constants/constants.js';
import * as StatusCode from '../constants/StatusCode.js';
import { getAccessToken, getRefreshToken, setToken, removeToken } from '../utils/token.js';

export async function postLogin({ loginId, password }) {
	try {
		// loginId, password validation
		const response = await fetch(`${BASE_URL}/api/auth/login`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ loginId, password })
		});

		if (response.ok) {
			const data = await response.json();
			setToken("accessToken", data.accessToken);
            setToken("refreshToken", data.refreshToken);
			return StatusCode.SUCCESS;
		} else {
			return StatusCode.UNKNOWN;
		}
	} catch (e) {
		return StatusCode.UNKNOWN;
	}
}

async function postRefresh() {
	const refreshToken = getRefreshToken();
	if (!refreshToken) {
		throw new Error(StatusCode.AUTH_FAILED);
	}

	const response = await fetch(`${BASE_URL}/api/auth/refresh`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ refreshToken })
	});

	if (response.ok) {
		const data = await response.json();
		setToken("accessToken", data.accessToken);
		setToken("refreshToken", data.refreshToken);
		return data.accessToken;
	} else {
		throw new Error(StatusCode.AUTH_FAILED);
	}
};

export async function authRequest(url, options) {
	try {
		const accessToken = getAccessToken();
		if (!accessToken) {
			throw new Error(StatusCode.AUTH_FAILED);
		}

		let response = await fetch(url, {
			...options,
			headers: {
				...options.headers,
				Authorization: `Bearer ${accessToken}`
			}
		});

		if (response.status === 401) { //access token 만료 상태코드
			const newAccessToken = await postRefresh();
			response = await fetch(url, {
				...options,
				headers: {
					...options.headers,
					Authorization: `Bearer ${newAccessToken}`
				}
			});
		}

		return response;
	} catch (e) {
		if (e.message === StatusCode.AUTH_FAILED) {
			removeToken('accessToken');
			removeToken('refreshToken');
		}
		throw e;
	}
}