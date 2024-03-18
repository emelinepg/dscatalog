import ButtonIcon from 'components/ButtonIcon';
import './styles.css';
import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div className="home-container">
      <div className="base-card home-card">
        <div className="home-content-container" /* texto1, texto2, botao */>
          <div className="home-text-container">
            <h1>Conheça o melhor catálogo de produtos</h1>
            <p>
              Ajudaremos você a encontrar os melhores produtos disponíveis no
              mercado.
            </p>
          </div>
          <div>
            <Link to="/products">
              <ButtonIcon />
            </Link>
          </div>
        </div>
        <div className="home-image-container" /* imagem */>
          <MainImage />
        </div>
      </div>
    </div>
  );
};

export default Home;
