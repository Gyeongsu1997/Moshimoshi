import { useState, useEffect, useRef, useMemo, useCallback } from 'react';
import { useFetch } from '../hooks/useFetch';
import { getThreads } from '../services/threadAPI';
import Layout from './Layout/Layout';
import ThreadList from '../components/Thread/ThreadList';

function ThreadListPage() {
	const [pageNumber, setPageNumber] = useState(0);
	const [threads, setThreads] = useState([]);
	
	const [state] = useFetch(() => getThreads(pageNumber), [pageNumber]);
	const { loading, data, error } = state;

	const target = useRef(null);

	const handleObserver = useCallback((entries) => {
		const target = entries[0];
		if (target.isIntersecting && !loading && !data.isLast) {
			setPageNumber((prev) => prev + 1);
		}
	}, [loading, data.isLast]);

	const observer = useMemo(() => { 
		return new IntersectionObserver(handleObserver, {
			threshold: 0
		});
	}, [handleObserver]);

	useEffect(() => {
		observer.observe(target.current);
	}, [observer]);

	if (loading) return <div>로딩중..</div>;
	if (error) return <div>에러가 발생했습니다</div>;
  	if (!data) return null;

	setThreads(prev => [...prev, ...(data.list)]);

	return (
		<>
			<Layout>
				<ThreadList threads={threads} />
			</Layout>
			<div ref={target}></div>
		</>	
	);
}

export default ThreadListPage;