import Navbar from "../Navigation/Navbar";
import logo from '../../logo.svg';
import styles from "./Header.module.css";

function Header(props) {
	return (
		<header className={styles.header}>
			{/* <a className="logo" href="#home"><img src={logo} alt="logo"></img></a> */}
			<a href="#home"><img src="https://poiemaweb.com/img/logo.png" alt="logo"></img></a>
			<Navbar />
		</header>
	);
}

export default Header;