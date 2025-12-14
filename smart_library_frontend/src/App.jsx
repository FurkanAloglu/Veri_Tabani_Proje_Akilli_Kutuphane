import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import AuthorsPage from './pages/AuthorsPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/login" element={<LoginPage />} />
        
        <Route path="/authors" element={<AuthorsPage />} />
      </Routes>
    </Router>
  );
}

export default App;