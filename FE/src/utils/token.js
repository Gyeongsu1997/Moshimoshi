export const getAccessToken = function () {
	return localStorage.getItem('accessToken');
}

export const getRefreshToken = function () {
	return localStorage.getItem('refreshToken');
}

export const setToken = function (key, token) {
	localStorage.setItem(key, token);
}

export const removeToken = function (key) {
	localStorage.removeItem(key);
}