import React from "react";
import styles from "./Thread.module.css";
import { ReactComponent as AnonymousAvatar } from "../../assets/file-person-fill.svg";
import { ReactComponent as ImagesIcon } from "../../assets/images.svg";


function Thread({ thread }) {
	return (
		<div className={styles.thread}>
			<div className={styles.top}>
				<AnonymousAvatar />
				<p>익명</p>
				<p>06/04 13:27</p>
			</div>
			<div>{thread.content}</div>
			<div className={styles.bottom}>
				<div>
					<i></i> {/*하트(공감)*/}
					<p>3</p>
				</div>
				<div>
					<i></i> {/*스크랩 아이콘*/}
					<p>4</p>
				</div>
				<div>
					<ImagesIcon />
					<p>1</p>
				</div>
			</div>
		</div>
	);
}

export default React.memo(Thread);