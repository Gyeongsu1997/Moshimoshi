import styled from "styled-components";

const Wrapper = styled.div`
	display: flex;
	justify-content: flex-start;

	& > {
		padding: 3px;
	}
	& > span:last-child {
		margin-left: auto;
	}
`;

function Author(props) {
	return (
		<Wrapper>
			{props.children}
			<span>{props.user.nickname}</span>
			<span>{props.created}</span>
		</Wrapper>
	);
}

export default Author;