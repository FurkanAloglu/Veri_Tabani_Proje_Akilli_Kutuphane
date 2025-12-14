import React from 'react';
import { Outlet, Link } from 'react-router-dom';

const Layout = () => {
  const navStyle = {
    padding: '1rem',
    backgroundColor: '#f8f9fa',
    borderBottom: '1px solid #dee2e6',
    marginBottom: '20px',
    display: 'flex',
    gap: '15px',
    alignItems: 'center'
  };

  return (
    <div>
      <nav style={navStyle}>
        <h3 style={{ margin: 0, marginRight: '20px' }}>Akıllı Kütüphane</h3>
        <Link to="/">Ana Sayfa</Link>
        <Link to="/books">Kitaplar</Link>
        <Link to="/authors">Yazarlar</Link>
        <Link to="/categories">Kategoriler</Link>
        <Link to="/borrow">Ödünç İşlemleri</Link>
        <Link to="/login" style={{ marginLeft: 'auto', color: 'red' }}>Çıkış Yap</Link>
      </nav>
      <div className="container" style={{ padding: '0 20px' }}>
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;