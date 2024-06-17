import { useState } from "react";

const reasons = [
	{
		id: 1,
		value: "PORNOGRAPHY",
		name: "음란물"
	},
	{
		id: 2,
		value: "AD",
		name: "광고"
	}
];

function Report({ postReport }) {
	const [value, setValue] = useState('');

	const handleChange = (e) => {
		setValue(e.target.value);
	}

	const handleSubmit = async (e) => {
		e.preventDefault();
		const status = await postReport({value});
	}


	return (
		<div onSubmit={handleSubmit}>
			<form>
				<select value={value} onChange={handleChange}>
					{reasons.map(reason => {
						return <option key={reason.id} value={reason.value}>{reason.name}</option>;
					})}
				</select>
				<button type="submit">제출</button>
			</form>
		</div>
	);
}

export default Report;