import { useForm } from 'react-hook-form';
import './styles.css';
import { Product } from 'types/product';
import { getTokenData } from 'utils/auth';
import { requestBackend } from 'utils/requests';
import { saveAuthData } from 'utils/storage';
import { AxiosRequestConfig } from 'axios';
import { useHistory } from 'react-router-dom';

const Form = () => {
  const history = useHistory();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Product>();

  const onSubmit = (formData: Product) => {
    const data = {
      ...formData,
      imgUrl:
        'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg',
      categories: [{ id: 1, name: '' }],
    };
    const config: AxiosRequestConfig = {
      method: 'POST',
      url: '/products',
      data,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      history.push('/admin/products');
    });
  };

  const handleCancel = () => {
    history.push('/admin/products');
  };

  return (
    <div className="product-crud-container">
      <div className="base-card product-crud-form-card">
        <h1 className="product-crud-form-card-title">DADOS DO PRODUTO</h1>
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="row">
          <div className="col-lg-6">
            <div className="product-crud-input">
              <input
                {...register('name', {
                  required: 'Campo obrigatório',
                })}
                type="text"
                className={`form-control base-input ${
                  errors.name ? 'is-invalid' : ''
                }`}
                placeholder="Nome do produto"
                name="name"
              />
              <div className="invalid-feedback d-block">
                {errors.name?.message}
              </div>
            </div>
            <div className="product-crud-input">
              <input
                {...register('price', {
                  required: 'Campo obrigatório',
                })}
                type="text"
                className={`form-control base-input ${
                  errors.name ? 'is-invalid' : ''
                }`}
                placeholder="Preço"
                name="price"
              />
              <div className="invalid-feedback d-block">
                {errors.price?.message}
              </div>
            </div>
          </div>
          <div className="col-lg-6 product-crud-input">
            <textarea
              rows={10}
              {...register('description', {
                required: 'Campo obrigatório',
              })}
              className={`form-control base-input h-auto ${
                errors.description ? 'is-invalid' : ''
              }`}
              placeholder="Descrição"
              name="description"
            ></textarea>
            <div className="invalid-feedback d-block">
              {errors.description?.message}
            </div>
          </div>
        </div>
        <div className="product-crud-form-buttons-container">
          <button
            className="btn btn-outline-danger product-crud-form-button"
            onClick={handleCancel}
          >
            CANCELAR
          </button>
          <button className="btn btn-primary text-white product-crud-form-button">
            SALVAR
          </button>
        </div>
      </form>
    </div>
  );
};

export default Form;
