import './styles.css';

type Props = {
  price: number;
}

const ProductPrice = ({ price } : Props ) => {
  return (
    <div className="price-container">
      <h5>R$</h5>
      <h3>{price}<span className="fs-6">,00</span></h3>
    </div>
  );
};

export default ProductPrice;
