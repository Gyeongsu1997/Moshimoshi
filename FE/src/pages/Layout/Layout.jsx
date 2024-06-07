import Header from "../../components/Header/Header";
import Sidebar from "../../components/Sidebar/Sidebar";
import styles from "./Layout.module.css";

function Layout({ children }) {
	return (
		<>
			<Header />
			<div className={styles.layout}>
				<Sidebar />
				{children}
				<Sidebar />
			</div>
		</>
	);
}

export default Layout;