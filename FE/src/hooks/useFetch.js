import { useReducer, useEffect } from "react";

const initialState = {
	loading: false,
	data: null,
	error: null
};

const loadingState = {
	loading: true,
	data: null,
	error: null
};

const success = data => ({
	loading: false,
	data,
	error: null
});


const error = error => ({
	loading: false,
	data: null,
	error
});

function reducer(state, action) {
	switch (action.type) {
		case 'LOADING':
			return loadingState;
		case 'SUCCESS':
			return success(action.data);
		case 'ERROR':
			return error(action.error);
		default:
			throw new Error(`Unhandled action type: ${action.type}`);
	}
}

function useFetch(callback, deps = [], skip=false) {
	const [state, dispatch] = useReducer(reducer, initialState);

	const fetchData = async () => {
		dispatch({ type: 'LOADING' });
		try {
			const response = await callback();
			if (!response.ok) {
				//예외처리
			}
			const data = await response.json();
			dispatch({ type: 'SUCCESS', data});
		} catch (error) {
			dispatch({ type: 'ERROR', error });
		}
	};

	useEffect(() => {
		if (skip) return;
		fetchData();
		// eslint-disable-next-line
	}, deps);

	return [state, fetchData];
}

export default useFetch;