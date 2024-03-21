import ProductPrice from 'components/ProductPrice';
import './styles.css';
import { Product } from 'types/product';
import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import { Link } from 'react-router-dom';

const ProductDetails = () => {
  const product: Product = {
    id: 3,
    name: 'Macbook Pro',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    price: 1250.0,
    imgUrl:
      'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg',
    date: '2020-07-14T10:00:00Z',
    categories: [
      {
        id: 3,
        name: 'Computers',
      },
    ],
  };

  return (
    <div className="product-details-container">
      <div className="base-card product-details-card">
        <Link to="/products">
          <div className="goback-container">
            <ArrowIcon />
            <h2>VOLTAR</h2>
          </div>
        </Link>
        <div className="row">
          <div className="col-xl-6">
            <div className="image-container">
              <img src={product.imgUrl} alt={product.name} />
            </div>
            <div className="name-price-container">
              <h1>{product.name}</h1>
              <ProductPrice price={product.price} />
            </div>
          </div>
          <div className="col-xl-6">
            <div className="description-container">
              <h4>Descrição do Produto</h4>
              <p>{product.description}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
