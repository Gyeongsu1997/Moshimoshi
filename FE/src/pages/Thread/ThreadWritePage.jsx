import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import Layout from "../Layout/Layout";
import { useAuthState, useAuthDispatch } from "../../contexts/AuthContext";
import { postThread } from "../../services/threadAPI";
import * as StatusCode from "../../constants/StatusCode";

function ThreadWritePage() {
	const navigate = useNavigate();
	const [content, setContent] = useState('');
	const [anonymous, setAnonymous] = useState(true);

	const { isLoggedIn } = useAuthState();
	const dispatch = useAuthDispatch();
	if (!isLoggedIn) {
		return <Navigate to="/auth/login" replace={true} />;
	}
	
	const onSubmit = async (e) => {
		e.preventDefault();
		const status = await postThread({content, anonymous});
		if (status === StatusCode.SUCCESS) {
			navigate('/threads');
		} else {
			dispatch({ type: 'LOGOUT' });
			navigate('/auth/login');
		}
	};

	return (
		<Layout>
			<div>
				<form onSubmit={onSubmit}>
					<div>
						<textarea
							style={{width: '100%', height: '10rem', resize: "none"}}
							value={content}
							onChange={e => setContent(e.target.value)}
							placeholder="내용을 입력해주세요."
						/>
					</div>
					<div style={{ display: "flex" }}>
						<input
							name="isAnonymous"
							type="checkbox"
							checked={anonymous}
							onChange={e => setAnonymous(e.target.checked)}
						/>
						<label htmlFor="isAnonymous">익명</label>
						<button type="submit">완료</button>
						<button onClick={() => navigate(-1)}>취소</button>
					</div>
				</form>
			</div>
		</Layout>
	);
}

export default ThreadWritePage;