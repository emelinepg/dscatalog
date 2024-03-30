import './styles.css'
import ProductPrice from 'components/ProductPrice';
import { Product } from 'types/product';

type Props = {
    product: Product;
}

const ProductCrudCard = ( { product } : Props ) => {

    return (
        <div className="base-card product-card">
            <div className="image-container">
                <img src={product.imgUrl} alt={product.name} />
            </div>
            <div className="info-container">
                <h4>{product.name}</h4>
                <ProductPrice price={product.price} />
            </div>
        </div>
    );
}

export default ProductCrudCard;