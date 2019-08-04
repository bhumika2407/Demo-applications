import React from 'react';
import {BrowserRouter,  Route, Switch } from "react-router-dom";
import {Container} from 'reactstrap';
import {Provider} from 'react-redux';
import './App.css';
import Customers from './components/Customers';
import CustomerDetails from './components/CustomerDetails';
import Header from './components/Header';
import {store} from './helpers/store';

function App() {
  return (
    <Provider store={store}>
      <div className="App">
      <Header />
      <div className="app-body">

        <Container fluid>
            <BrowserRouter>
              <Switch>
                    <Route exact path="/" component={Customers} />
                    <Route path="/customer/details" component={CustomerDetails} />
                    
              </Switch>
            </BrowserRouter>
          </Container>
      </div>
          
      </div>
    </Provider>
  );
}

export default App;
