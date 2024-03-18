import './styles.css'
import ProductImage from 'assets/images/product.png'
import ProductPrice from 'components/ProductPrice';

const ProductCard = () => {

    return (
        <div className="base-card product-card">
            <div className="image-container">
                <img src={ProductImage} alt="ProductImage" />
            </div>
            <div className="info-container">
                <h4>Computador Desktop - Intel Core i7</h4>
                <ProductPrice />
            </div>
        </div>
    );
}

export default ProductCard;