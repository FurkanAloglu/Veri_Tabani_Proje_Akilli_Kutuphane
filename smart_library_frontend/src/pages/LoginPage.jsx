import React, { useState } from 'react';
import api from '../api/axiosConfig'; 
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await api.post('/auth/login', {
                email: email,
                password: password
            });

            const token = response.data.token;            
            localStorage.setItem('token', token);

            alert("Giriş Başarılı!");
            navigate('/authors'); 

        } catch (err) {
            console.error("Login hatası:", err);
            setError('Giriş başarısız! Email veya şifre hatalı.');
        }
    };

    return (
        <div style={{ padding: '50px', textAlign: 'center' }}>
            <h2>Giriş Yap</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <input 
                        type="email" 
                        placeholder="Email Adresiniz" 
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required 
                        style={{ padding: '10px', margin: '10px' }}
                    />
                </div>
                <div>
                    <input 
                        type="password" 
                        placeholder="Şifre" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required 
                        style={{ padding: '10px', margin: '10px' }}
                    />
                </div>
                <button type="submit" style={{ padding: '10px 20px', cursor: 'pointer' }}>
                    GİRİŞ YAP
                </button>
            </form>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
    );
};

export default LoginPage;