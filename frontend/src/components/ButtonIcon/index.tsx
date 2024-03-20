import './styles.css';
import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';

type Props = {
  text : String;
}

const ButtonIcon = ( { text } : Props ) => {
  return (
    <div className="btn-container">
      <div>
        <button className="btn btn-primary">
          {text}
        </button>
      </div>
      <div className="btn-icon-container">
        <ArrowIcon />
      </div>
    </div>
  );
};

export default ButtonIcon;