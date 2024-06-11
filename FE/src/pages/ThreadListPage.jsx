import { useState, useEffect, useRef, useMemo, useCallback } from 'react';
import useFetch from '../hooks/useFetch';
import { getThreads } from '../services/threadAPI';
import Layout from './Layout/Layout';
import ThreadList from '../components/Thread/ThreadList';

function ThreadListPage() {
	const [threads, setThreads] = useState([]);
	const [page, setPage] = useState(0);
	const [hasNext, setHasNext] = useState(true);
	
	const [state] = useFetch(() => getThreads(page), [page]);
	const { loading, data, error } = state;

	const target = useRef(null);

	const handleObserver = useCallback((entries) => {
		const target = entries[0];
		if (target.isIntersecting && !loading && hasNext) {
			setPage((prev) => prev + 1);
		}
	}, [loading, hasNext]);

	const observer = useMemo(() => { 
		return new IntersectionObserver(handleObserver);
	}, [handleObserver]);

	useEffect(() => {
		const observedTarget = target.current;
		if (observedTarget) {
			observer.observe(observedTarget);
		}
		return () => {
			if (observedTarget) {
				observer.unobserve(observedTarget);
			};
		};
	}, [observer]);	

	useEffect(() => {
		if (data) {
			setThreads(prev => [...prev, ...(data.list)]);
			setHasNext(data.hasNext);
		}
	}, [data]);

	if (error) return <div>에러가 발생했습니다</div>;
	if (page === 0 && loading) return <div>로딩중..</div>;
  	if (page === 0 && !data) return <div>데이터가 없습니다</div>;

	return (
		<>
			<Layout>
				<ThreadList threads={threads} />
			</Layout>
			{hasNext && <div ref={target}></div>}
		</>	
	);
}

export default ThreadListPage;