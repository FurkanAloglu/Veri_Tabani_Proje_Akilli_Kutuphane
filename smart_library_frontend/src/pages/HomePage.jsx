import React from 'react';

const HomePage = () => {
  return (
    <div>
      <h1>Hoşgeldiniz</h1>
      <p>Kütüphane yönetim paneline hoşgeldiniz. İşlemlerinizi menüden seçebilirsiniz.</p>
      
      <div style={{ display: 'flex', gap: '20px', marginTop: '30px' }}>
        <div style={cardStyle}>
          <h3>Toplam Kitap</h3>
          <p style={{ fontSize: '24px', fontWeight: 'bold' }}>120</p>
        </div>
        <div style={cardStyle}>
          <h3>Ödünçteki Kitaplar</h3>
          <p style={{ fontSize: '24px', fontWeight: 'bold' }}>15</p>
        </div>
      </div>
    </div>
  );
};

const cardStyle = {
  border: '1px solid #ccc', padding: '20px', borderRadius: '8px', width: '200px', textAlign: 'center', backgroundColor: '#fff'
};

export default HomePage;