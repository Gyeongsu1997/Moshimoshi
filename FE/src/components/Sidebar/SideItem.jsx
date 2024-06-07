function SideItem(props) {
	return (
		<li onClick={props.onClick}>{props.name}</li>
	);
}

export default SideItem;