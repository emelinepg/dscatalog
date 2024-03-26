import { NavLink } from 'react-router-dom';
import './styles.css';
import { hasAnyRoles } from 'utils/requests';

const Navbar = () => {
  return (
    <nav className="admin-navbar-container">
      <ul>
        <li>
          <NavLink to="/admin/products" className="admin-navbar-item">
            <p>Produtos</p>
          </NavLink>
        </li>
        <li>
          <NavLink to="/admin/categories" className="admin-navbar-item">
            <p>Categorias</p>
          </NavLink>
        </li>
        {hasAnyRoles(['ROLE_ADMIN']) && (
          <li>
            <NavLink to="/admin/users" className="admin-navbar-item">
              <p>Usuários</p>
            </NavLink>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
