import * as authAPI from '../api/authAPI.mjs';
import * as token from '../utils/token.mjs';


const tfLoginId = document.getElementById('tfLoginId');
const tfPassword = document.getElementById('tfPassword');
const btnLogIn = document.getElementById('btnLogIn');
const btnLogOut = document.getElementById('btnLogOut');

const handleLogin = async function () {
	const request = new authAPI.LogInRequest(tfLoginId.value, tfPassword.value);
	const data = await authAPI.logIn(request);
	if (data === null) {
		//예외 처리
	}
	token.setToken('accessToken', data.accessToken);
	token.setToken('refreshToken', data.refreshToken);
	//리다이렉트
};

const handleLogOut = function () {
	token.removeToken("accessToken");
	token.removeToken("refreshToken");
	//redirect
}

btnLogIn.addEventListener('click', handleLogin);
btnLogOut.addEventListener('click', handleLogOut);