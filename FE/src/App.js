import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';
import { AuthProvider } from './contexts/AuthContext';
import Header from './components/Header/Header';
// Pages
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import NotFound from './pages/NotFound';
import ThreadListPage from './pages/Thread/ThreadListPage';
import ThreadWritePage from './pages/Thread/ThreadWritePage';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route index element={<ThreadListPage />} />
          <Route path="/auth">
            <Route path="login" element={<LoginPage />} />
          </Route>
          <Route path="/threads" element={<ThreadListPage />} />
          <Route path="/threads/write" element={<ThreadWritePage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
