import { useNavigate } from "react-router-dom";
import NavItem from "./NavItem";
import styles from "./Navbar.module.css";

const navItems = [
	{
		id: 1,
		path: "/home",
		name: "Home",
	},
	{
		id: 2,
		path: "/home",
		name: "공지사항",
	},
	{
		id: 3,
		path: "/home",
		name: "채팅하기",
	}
];

function Navbar(props) {
	// const { navItems } = props;
	// const navigate = useNavigate();

	return (
		<nav>
			<ul className={styles.navbar}>
				{navItems.map(item => {
					return (
						<NavItem key={item.id} name={item.name}/> //onClick={() => navigate(item.path)}
					);
				})}
			</ul>
		</nav>
	);
}

export default Navbar;