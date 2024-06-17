import styles from "./Button.module.css";

function Button(props) {
	const { title, onClick, theme = 'blue'} = props;

	return (
		<>
			<div className={styles.flex}>
				<button
					className={theme === 'blue' ? styles['btn-blue'] : styles['btn-white']}
					onClick={onClick}
				>
				{/* {title} */}
				버튼입니다
				</button>
			</div>
			<div className={styles.flex2}>
				<button className={styles['btn-light']}>
					버튼입니다
				</button>
			</div>
		</>
	)
}

export default Button;