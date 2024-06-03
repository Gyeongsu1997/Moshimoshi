import SideItem from "./SideItem";

const sideItems = [
	{
		id: 1,
		name: "dd",
	},
];

function Sidebar(props) {
	return (
		<aside>
			<ul>
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