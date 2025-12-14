import { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import { useNavigate } from 'react-router-dom';

const AuthorsPage = () => {
    const [authors, setAuthors] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAuthors = async () => {
            try {
                // Token otomatik ekleniyor, sadece endpoint yazıyoruz
                const response = await api.get('/authors');
                setAuthors(response.data);
            } catch (err) {
                console.error("Veri çekme hatası:", err);
                // Token geçersizse Login'e at
                if (err.response && (err.response.status === 401 || err.response.status === 403)) {
                    navigate('/login');
                } else {
                    setError('Yazarlar yüklenirken bir hata oluştu.');
                }
            }
        };

        fetchAuthors();
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('token'); // Token'ı sil
        navigate('/login'); // Girişe yönlendir
    };

    return (
        <div style={{ maxWidth: '800px', margin: '50px auto', padding: '20px', fontFamily: 'Arial, sans-serif' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
                <h2 style={{ margin: 0 }}>📚 Kütüphane Yazarları</h2>
                <button 
                    onClick={handleLogout}
                    style={{ background: '#333', color: '#fff', padding: '8px 16px', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
                >
                    Çıkış Yap
                </button>
            </div>
            
            {error && <div style={{ background: '#ffdddd', color: 'red', padding: '10px', borderRadius: '5px', marginBottom: '20px' }}>{error}</div>}

            {authors.length === 0 && !error ? (
                <p style={{ textAlign: 'center', color: '#666' }}>Henüz kayıtlı yazar bulunmuyor.</p>
            ) : (
                <ul style={{ listStyle: 'none', padding: 0 }}>
                    {authors.map((author) => (
                        <li key={author.id} style={{ 
                            background: '#f9f9f9',
                            marginBottom: '10px',
                            padding: '15px',
                            borderRadius: '8px',
                            borderLeft: '5px solid #007bff',
                            boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
                            display: 'flex',
                            justifyContent: 'space-between',
                            alignItems: 'center'
                        }}>
                            <div>
                                <strong style={{ fontSize: '1.1em' }}>{author.name} {author.surname}</strong>
                            </div>
                            <span style={{ fontSize: '0.8em', color: '#888' }}>ID: {author.id}</span>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default AuthorsPage;