import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import Header from "../components/header/Header";

const Wrapper = styled.div`
	display: flex;
`;

function HomePage() {
	// const navigate = useNavigate();
	return (
		<Header />	
	);
}

export default HomePage;