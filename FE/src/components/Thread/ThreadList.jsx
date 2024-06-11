import Thread from "./Thread";

function ThreadList({ threads }) {	
	return (
		<div>
			{threads.map(thread => {
				return <Thread key={thread.threadId} thread={thread} />
			})}
		</div>
	);
}

export default ThreadList;