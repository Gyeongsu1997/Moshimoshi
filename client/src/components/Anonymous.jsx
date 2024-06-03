import Author from "Author.jsx";
import { ReactComponent as AnonymousAvatar } from "../assets/file-person-fill.svg";

const anonymous = {
	nickname: "익명"
};

function Anonymous(props) {
	return (
		<Author user={anonymous} create={props.created}>
			<AnonymousAvatar />
		</Author>
	);
}

export default Anonymous; 