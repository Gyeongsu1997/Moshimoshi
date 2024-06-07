import Header from '../components/Header/Header';
import Sidebar from '../components/Sidebar/Sidebar';
import styles from './ThreadPage.module.css';

function ThreadPage() {
	return (
		<div>
			<Header />
			<div className={styles['thread-page']}>
				<Sidebar />
				<div>
					main content
				</div>
			</div>
		</div>
	);
}

export default ThreadPage;