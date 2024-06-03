import styled from "styled-components";
import { ReactComponent as ImagesIcon } from "../assets/images.svg";

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

function CommentInfo(props) {
	return (
		<Wrapper>
			<i></i> {/*하트(공감)*/}
			<span>{props.comment.number}</span>
			<i></i> {/*스크랩 아이콘*/}
			<span>{props.scrap}</span>
			<ImagesIcon />
			<span>{props.image.number}</span>
		</Wrapper>
	);
}

export default CommentInfo;