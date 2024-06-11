import SideItem from "./SideItem";
import styles from "./Sidebar.module.css";

const sideItems = [
	{
		id: 1,
		name: "Home",
	},
	{
		id: 2,
		name: "공지사항",
	},
	{
		id: 3,
		name: "채팅하기",
	},
];

function Sidebar(props) {
	return (
		<aside>
			<ul className={styles.sidebar}>
				{sideItems.map(item => {
					return (
						<SideItem key={item.id} name={item.name} />
					);
				})}
			</ul>
		</aside>
	);
}

export default Sidebar;