import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Dashboard() {
    const [books, setBooks] = useState([]);
    const [borrowings, setBorrowings] = useState([]);
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    // API'den Veri Çekme Fonksiyonu
    const fetchData = async () => {
        const headers = { 'Authorization': `Bearer ${token}` };

        try {
            // 1. Kitapları Çek
            const bookRes = await fetch('http://localhost:8080/api/books', { headers });
            if (bookRes.ok) {
                setBooks(await bookRes.json());
            }

            // 2. Ödünçleri Çek (Burayı düzelttik)
            const borrowRes = await fetch('http://localhost:8080/api/borrowings', { headers });
            if (borrowRes.ok) {
                setBorrowings(await borrowRes.json());
            }

        } catch (error) {
            console.error("Veri çekme hatası:", error);
        }
    };

    useEffect(() => {
        if (!token) {
            navigate('/login');
            return;
        }
        fetchData();
    }, [navigate, token]);

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    const handleBorrow = async (bookId) => {
        try {
            const res = await fetch('http://localhost:8080/api/borrowings', {
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ bookId: bookId })
            });
            if (res.ok) {
                alert("Kitap ödünç alındı!");
                fetchData();
            } else {
                alert("Hata oluştu! (Giriş yapmamış olabilirsin)");
            }
        } catch (err) { console.error(err); }
    };

    const handleReturn = async (borrowId) => {
        try {
            const res = await fetch(`http://localhost:8080/api/borrowings/${borrowId}/return`, {
                method: 'PUT',
                headers: { 'Authorization': `Bearer ${token}` }
            });
            if (res.ok) {
                alert("İade işlemi başarılı!");
                fetchData();
            }
        } catch (err) { console.error(err); }
    };

    return (
        <div className="container" style={{padding: '20px'}}>
            <div style={{display:'flex', justifyContent:'space-between', marginBottom:'20px'}}>
                <h1>Kütüphane Paneli</h1>
                <button onClick={handleLogout} style={{background:'red', color:'white'}}>Çıkış</button>
            </div>
            
            <div style={{display:'grid', gridTemplateColumns:'1fr 1fr', gap:'20px'}}>
                <div style={{border:'1px solid #ccc', padding:'10px'}}>
                    <h3>Kitaplar</h3>
                    <ul>
                        {books.map(book => (
                            <li key={book.id} style={{marginBottom:'10px', display:'flex', justifyContent:'space-between'}}>
                                {book.title}
                                <button onClick={() => handleBorrow(book.id)}>Ödünç Al</button>
                            </li>
                        ))}
                    </ul>
                </div>
                <div style={{border:'1px solid #ccc', padding:'10px'}}>
                    <h3>Ödünç Alınanlar</h3>
                    <ul>
                        {borrowings.map(b => (
                            <li key={b.borrowID} style={{marginBottom:'10px'}}>
                                <div>Kitap ID: {b.bookId}</div>
                                <div>Durum: {b.returnDate ? "İade Edildi" : "Okunuyor"}</div>
                                {!b.returnDate && <button onClick={() => handleReturn(b.borrowID)}>İade Et</button>}
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
}