import { BASE_URL } from '../constants/constants.mjs';

const LogInRequest = function (loginId, password) {
	this.loginId = loginId;
	this.password = password;
};

const logIn = async function (request) {
	try {
		const response = await fetch(`${BASE_URL}/api/auth/login`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(request)
		});
		if (response.ok) {
			const data = await response.json();
			//token 저장
		} else {
			//
		}
	} catch (e) {
		//예외 처리
	}
}

const logOut = function () {
	removeToken("accessToken");
	removeToken("refreshToken");
	//redirect
}