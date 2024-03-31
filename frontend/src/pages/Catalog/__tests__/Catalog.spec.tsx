import { render, screen, waitFor } from "@testing-library/react";
import Catalog from "..";
import { Router } from 'react-router-dom';
import history from "utils/history";


test('Should render Catalog with products', async() => {
    //ARRANGE
    
    //ACT
    render(
        <Router history={history}>
            <Catalog />
        </Router>
    );
    
    //ASSERT
    expect(screen.getByText("Catálogo de Produtos")).toBeInTheDocument();
    
    await waitFor(() => {
        expect(screen.getByText('Macbook Pro')).toBeInTheDocument();
    })
    
    screen.debug;

}); 