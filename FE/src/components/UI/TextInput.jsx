function TextInput({value, onChange}) {
	return (
		<textarea
			value={value}
			onChange={onChange}
			placeholder="내용을 입력해주세요."
		/>
	);
}

export default TextInput;