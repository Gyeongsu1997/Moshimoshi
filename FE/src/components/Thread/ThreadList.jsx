import Thread from "./Thread";

function ThreadList() {
	const threads = [1, 2, 3, 4, 5];
	
	return (
		<div>
			{threads.map(thread => {
				return <Thread />
			})}
		</div>
	);
}

export default ThreadList;