import Navbar from "../navigation/Navbar";
import logo from '../../logo.svg';

function Header(props) {
	return (
		<header>
			<a className="logo" href="#home"><img src={logo} alt="logo"></img></a>
			<Navbar />
		</header>
	);
}

export default Header;