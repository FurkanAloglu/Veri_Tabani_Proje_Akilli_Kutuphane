import React, { useState } from 'react';

const BooksPage = () => {
  // Örnek veri (Backend API'den çekilecek)
  const [books] = useState([
    { id: 1, title: 'Simyacı', author: 'Paulo Coelho', category: 'Roman', status: 'Mevcut' },
    { id: 2, title: '1984', author: 'George Orwell', category: 'Bilim Kurgu', status: 'Ödünçte' },
    { id: 3, title: 'Suç ve Ceza', author: 'Fyodor Dostoyevski', category: 'Klasik', status: 'Mevcut' },
  ]);

  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
        <h2>Kitap Listesi</h2>
        <button style={{ padding: '10px 20px', cursor: 'pointer' }}>+ Yeni Kitap Ekle</button>
      </div>

      <table border="1" cellPadding="10" style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th>ID</th>
            <th>Kitap Adı</th>
            <th>Yazar</th>
            <th>Kategori</th>
            <th>Durum</th>
            <th>İşlemler</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.category}</td>
              <td>{book.status}</td>
              <td>
                <button style={{ marginRight: '5px' }}>Düzenle</button>
                <button style={{ color: 'red' }}>Sil</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BooksPage;