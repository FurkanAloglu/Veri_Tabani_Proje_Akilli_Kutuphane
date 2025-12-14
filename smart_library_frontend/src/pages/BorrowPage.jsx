import React from 'react';

const BorrowPage = () => {
  return (
    <div>
      <h2>Ödünç / İade İşlemleri</h2>
      
      <div style={{ display: 'flex', gap: '40px', marginTop: '20px' }}>
        {/* Ödünç Verme Formu */}
        <div style={{ flex: 1, padding: '20px', border: '1px solid #ddd', borderRadius: '8px' }}>
          <h3>Kitap Ödünç Ver</h3>
          <div style={{ marginBottom: '10px' }}><input type="text" placeholder="Öğrenci No / TC" style={{ width: '100%', padding: '8px' }} /></div>
          <div style={{ marginBottom: '10px' }}><input type="text" placeholder="Kitap Barkod / ID" style={{ width: '100%', padding: '8px' }} /></div>
          <button style={{ width: '100%', padding: '10px', backgroundColor: '#4CAF50', color: 'white', border: 'none' }}>Ödünç Ver</button>
        </div>

        {/* İade Alma Formu */}
        <div style={{ flex: 1, padding: '20px', border: '1px solid #ddd', borderRadius: '8px' }}>
          <h3>Kitap İade Al</h3>
          <div style={{ marginBottom: '10px' }}><input type="text" placeholder="Kitap Barkod / ID" style={{ width: '100%', padding: '8px' }} /></div>
          <button style={{ width: '100%', padding: '10px', backgroundColor: '#2196F3', color: 'white', border: 'none' }}>İade Al</button>
        </div>
      </div>
    </div>
  );
};

export default BorrowPage;