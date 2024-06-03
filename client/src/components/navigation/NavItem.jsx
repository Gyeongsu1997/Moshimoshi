function NavItem(props) {
	return (
		<li onClick={props.onClick}>{props.name}</li>
	);
}

export default NavItem;