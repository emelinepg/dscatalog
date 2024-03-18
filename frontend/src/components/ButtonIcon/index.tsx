import './styles.css';
import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';

const ButtonIcon = () => {
  return (
    <div className="btn-container">
      <div>
        <button className="btn btn-primary">
          Inicie agora a sua busca
        </button>
      </div>
      <div className="btn-icon-container">
        <ArrowIcon />
      </div>
    </div>
  );
};

export default ButtonIcon;
