import './styles.css';

const Form = () => {
  return (
    <div className="product-crud-container">
      <div className="base-card product-crud-form-card">
        <h1 className="product-crud-form-card-title">DADOS DO PRODUTO</h1>
      </div>
      <form action="">
        <div className="row">
          <div className="col-lg-6">
            <div className="product-crud-input">
              <input type="text" className="form-control base-input" />
            </div>
            <div className="product-crud-input">
              <input type="text" className="form-control base-input" />
            </div>
            <div className="product-crud-input">
              <input type="text" className="form-control base-input" />
            </div>
          </div>
          <div className="col-lg-6 product-crud-input">
            <textarea
              name=""
              rows={10}
              className="form-control base-input h-auto"
            ></textarea>
          </div>
        </div>
        <div className="product-crud-form-buttons-container">
          <button className="btn btn-outline-danger product-crud-form-button">CANCELAR</button>
          <button className="btn btn-primary text-white product-crud-form-button">SALVAR</button>
        </div>
      </form>
    </div>
  );
};

export default Form;
