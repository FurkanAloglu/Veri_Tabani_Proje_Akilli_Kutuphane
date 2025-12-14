import React, { useState } from 'react';

const CategoriesPage = () => {
  const [categories] = useState([
    { id: 1, name: 'Roman' },
    { id: 2, name: 'Bilim Kurgu' },
    { id: 3, name: 'Tarih' },
  ]);

  return (
    <div>
      <h2>Kategoriler</h2>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {categories.map(cat => (
          <li key={cat.id} style={{ padding: '10px', borderBottom: '1px solid #eee', display: 'flex', justifyContent: 'space-between' }}>
            <span>{cat.name}</span>
            <button>Sil</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CategoriesPage;