import './styles.css'
import Navbar from "./Navbar";
import { Switch } from 'react-router-dom';
import Users from './Users';
import PrivateRoute from 'components/PrivateRoute';
import Products from './Products';

const Admin = () => {

    return(
        <div className="admin-container">
            <Navbar />
            <div className="admin-content-container">
                <Switch>
                    <PrivateRoute path="/admin/products">
                        <Products />
                    </PrivateRoute>
                    <PrivateRoute path="/admin/categories">
                        <h1>Category CRUD</h1>
                    </PrivateRoute>
                    <PrivateRoute path="/admin/users" roles={['ROLE_ADMIN']}>
                        <Users />
                    </PrivateRoute>
                </Switch>
            </div>
        </div>
    );
}

export default Admin;