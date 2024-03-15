import Navbar from 'components/Navbar';
import ButtonIcon from 'components/ButtonIcon';
import './styles.css';
import { ReactComponent as MainImage } from 'assets/images/main-image.svg';

const Home = () => {
  return (
    <>
      <Navbar />
      <div className="home-container">
        <div className="home-card">
          <div className="home-content-container" /* texto1, texto2, botao */>
            <div className="home-text-container">
              <h1>Conheça o melhor catálogo de produtos</h1>
              <p>
                Ajudaremos você a encontrar os melhores produtos disponíveis no
                mercado.
              </p>
            </div>
            <ButtonIcon />
          </div>
          <div className="home-image-container" /* imagem */>
            <MainImage />
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
