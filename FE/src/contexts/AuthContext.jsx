import React, { createContext, useContext, useReducer } from "react";
import { getAccessToken } from "../utils/token";

const initialState = {
	isLoggedIn: getAccessToken() != null ? true : false
};

function authReducer(state, action) {
	switch (action.type) {
		case 'LOGIN':
			return {
				...state,
				isLoggedIn: true
			};
		case 'LOGOUT':
			return {
				...state,
				isLoggedIn: false
			};
		default:
			throw new Error(`Unhanded action type: ${action.type}`);
	}
}

const AuthStateContext = createContext(null);
const AuthDispatchContext = createContext(null);

export function AuthProvider({ children }) {
	const [state, dispatch] = useReducer(authReducer, initialState);
	return (
		<AuthStateContext.Provider value={state}>
			<AuthDispatchContext.Provider value={dispatch}>
				{children}
			</AuthDispatchContext.Provider>
		</AuthStateContext.Provider>
	);
}

export function useAuthState() {
	const state = useContext(AuthStateContext);
	if (!state) {
		throw new Error('Cannot find AuthProvider');
	}
	return state;
}

export function useAuthDispatch() {
	const dispatch = useContext(AuthDispatchContext);
	if (!dispatch) {
		throw new Error('Cannot find AuthProvider');
	}
	return dispatch;
}