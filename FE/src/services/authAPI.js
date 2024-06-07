import { BASE_URL } from '../constants/constants.js';
import { getRefreshToken, setToken } from '../utils/token.js';

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
			return {
				success: true,
				data
			};
		} else {
			//예외처리
			return {
				success: false,
				// 에러에 대한 정보
			};
		}
	} catch (e) {
		//예외 처리
		return {
			success: false,
			// 에러에 대한 정보 (e.toString())
		};
	}
}

export async function postRefresh() {
	try {
		const refreshToken = getRefreshToken();

		if (!refreshToken) {
			// 예외처리
			return {

			};
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
			return data.accessToken; // 재요청
		} else {
			//throw new Error("Refresh token is invalid or expired.");
		}
	} catch (e) {
		//예외 처리
	}
};

export async function authRequest() {

}