import styles from "./ThreadForm.module.css";

function ThreadForm(props) {
	const {inputs, onChange, onSubmit, handleCancel} = props;

	return (
		<div>
			<form onSubmit={onSubmit}>
				<div>
					<textarea
						style={{width: '100%', height: '10rem', resize: "none"}}
						value={inputs.content}
						onChange={onChange}
						placeholder="내용을 입력해주세요."
					/>
				</div>
				<div className={styles.bottom}>
					<input
						name="isAnonymous"
						type="checkbox"
						checked={inputs.isAnonymous}
						onChange={onChange}
					/>
					<label htmlFor="isAnonymous">익명</label>
					<button type="submit">완료</button>
					<button onClick={handleCancel}>취소</button>
				</div>
			</form>
		</div>
	);
}

export default ThreadForm;