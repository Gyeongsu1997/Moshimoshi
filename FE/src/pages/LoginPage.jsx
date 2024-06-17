import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthDispatch } from '../contexts/AuthContext';
import { postLogin } from '../services/authAPI';
import * as StatusCode from '../constants/StatusCode';
import Layout from './Layout/Layout';

function LoginPage() {
	const navigate = useNavigate();
	const dispatch = useAuthDispatch();

	const [inputs, setInputs] = useState({
		loginId: '',
		password: ''
	});

	const { loginId, password } = inputs;

	const onChange = (e) => {
		const { value, name } = e.target;
		setInputs({
			...inputs,
			[name]: value
		});
	};

	const handleLogin = async (e) => {
		e.preventDefault();
		const status = await postLogin({
			loginId,
			password
		});

		if (status === StatusCode.SUCCESS) {
			dispatch({ type: 'LOGIN' });
            navigate("/", { replace: true });
		} else {
			setInputs({
				loginId: '',
				password: ''
			});
			// 존재하지 않는 아이디입니다
			// 비밀번호가 올바르지 않습니다
			// 다시 시도해 주세요 등
		}
	};

	return (
		<Layout>
			<div>
				<form onSubmit={handleLogin}>
					<label htmlFor="loginId">아이디</label>
					<input
						type="text"
						name="loginId"
						value={loginId}
						onChange={onChange}
						placeholder="아이디를 입력해 주세요."
					/>
					<label htmlFor="password">비밀번호</label>
					<input
						type="password"
						name="password"
						value={password}
						onChange={onChange}
						placeholder="비밀번호를 입력해 주세요."
					/>
					<button type="submit">로그인</button>
				</form>
				<div>
					<button>ID 찾기</button>
					<button>비밀번호 찾기</button>
					<button onClick={() => navigate('/signup')}>회원가입</button>
				</div>
			</div>
		</Layout>
	);
}

export default LoginPage;