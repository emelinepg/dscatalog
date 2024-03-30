import { Category } from 'types/category';
import './styles.css';
import { ReactComponent as SearchIcon } from 'assets/images/search.svg';
import { Controller, useForm } from 'react-hook-form';
import { AxiosRequestConfig } from 'axios';
import { requestBackend } from 'utils/requests';
import Select from 'react-select';
import { useEffect, useState } from 'react';

type ProductFilterData = {
  name: string;
  category: Category | null;
};

const ProductFilter = () => {
  const [selectCategories, setSelectCategories] = useState<Category[]>([]);

  useEffect(() => {
    requestBackend({ url: '/categories' }).then((response) => {
      setSelectCategories(response.data.content);
    });
  }, []);

  const {
    register,
    handleSubmit,
    setValue,
    getValues,
    control,
  } = useForm<ProductFilterData>();

  const onSubmit = (formData: ProductFilterData) => {
    console.log('ENVIOU', formData);
  };

  const handleFormClear = () => {
    setValue('name', '');
    setValue('category', null)
  }

  const handleChangeCategory = (value: Category) => {
    setValue('category', value);

    const obj = {
        name: getValues('name'),
        category: getValues('category')
    }
    console.log('ENVIOU', obj);
  }

  return (
    <div className="base-card product-filter-container">
      <form onSubmit={handleSubmit(onSubmit)} className="product-filter-form">
        <div className="product-filter-name-container">
          <input
            {...register('name')}
            type="text"
            className="form-control"
            placeholder="Nome do produto"
            name="name"
          />
          <button className="product-filter-button">
            <SearchIcon />
          </button>
        </div>
        <div className="product-filter-options-container">
          <div className="product-filter-category-container">
            <Controller
              name="category"
              control={control}
              render={({ field }) => (
                <Select
                  {...field}
                  options={selectCategories}
                  isClearable
                  placeholder="Categoria"
                  classNamePrefix="product-filter-select"
                  onChange={value => handleChangeCategory(value as Category)}
                  getOptionLabel={(category: Category) => category.name}
                  getOptionValue={(category: Category) => String(category.id)}
                />
              )}
            />
          </div>
          <button onClick={handleFormClear} className="btn btn-outline-secondary product-filter-button">LIMPAR <span className="filter-btn-product-filter">FILTRO</span></button>
        </div>
      </form>
    </div>
  );
};

export default ProductFilter;
