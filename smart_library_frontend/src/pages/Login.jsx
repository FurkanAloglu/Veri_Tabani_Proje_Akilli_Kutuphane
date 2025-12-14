import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('token', data.token); 
                localStorage.setItem('email', email);     
                navigate('/dashboard');
            } else {
                alert("Giriş başarısız! Email veya şifre hatalı.");
            }
        } catch (error) {
            console.error("Hata:", error);
            alert("Sunucuya bağlanılamadı!");
        }
    };

    return (
        <div style={{ padding: '50px', maxWidth: '400px', margin: '0 auto', textAlign: 'center' }}>
            <h2>📚 Akıllı Kütüphane Giriş</h2>
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
                <input 
                    type="email" 
                    placeholder="E-posta" 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)} 
                    required 
                    style={{ padding: '10px' }}
                />
                <input 
                    type="password" 
                    placeholder="Şifre" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    required 
                    style={{ padding: '10px' }}
                />
                <button type="submit" style={{ padding: '10px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer' }}>
                    GİRİŞ YAP
                </button>
            </form>
            <p style={{marginTop: '20px', fontSize: '12px', color: 'gray'}}>
                Test için: Backend'de oluşturduğun öğrenci maili ile gir.
            </p>
        </div>
    );
}