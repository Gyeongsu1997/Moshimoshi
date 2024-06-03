import Author from "./Author";
import Anonymous from "./Anonymous";
import CommentInfo from "./CommentInfo";

function Thread(props) {
	const { thread } = props;

	return (
		<>
			{ thread.anonymous ? <Anonymous created={thread.created} /> : <Author />}
			<p>{thread.content}</p>
			<CommentInfo />
		</>
	);
}

export default Thread;