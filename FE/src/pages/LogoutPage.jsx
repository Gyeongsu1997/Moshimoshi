import { Navigate } from "react-router-dom";
import { useAuthDispatch } from "../contexts/AuthContext";
import { removeToken } from "../utils/token";

function LogoutPage() {
	removeToken('accessToken');
	removeToken('refreshToken');

	const dispatch = useAuthDispatch();
	dispatch({ type: 'LOGOUT' });

	return <Navigate to="/" replace={true} />;
}

export default LogoutPage;