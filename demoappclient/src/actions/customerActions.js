import {actionTypes} from './types';
import axios from 'axios';
import { globalConstants } from '../constants/globalConstants';

export const customerActions = {
    getCustomers,
    getCustomerDetails,
    updateNote,
    getCustomersByParams,
    updateCustomerStatus,
    createNoteForCustomer
}

function getCustomers( queryParams ) {
    let getCustomersUrl = globalConstants.REST_API_BASE_URL + '/customers';
    if(queryParams) {
        getCustomersUrl = getCustomersUrl + queryParams;
    }
    return dispatch => {
        axios.get( getCustomersUrl , {
            headers: {
                'Content-Type': 'application/json'
            },
        })
      .then((response) => dispatch({
          type: actionTypes.GET_CUSTOMERS,
          customers: response.data 
      }))
      .catch( (error) => {
        console.log(error);
      });

    };
}

function getCustomerDetails( customerId ) {
    return dispatch => {
        axios.get(globalConstants.REST_API_BASE_URL + '/customers/' + customerId, {
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then((response) => dispatch({
            type: actionTypes.GET_CUSTOMER_DETAILS,
            customer: response.data 
        }))
      .catch( (error) => {
        console.log(error);
      });
    };
 }

 function updateNote( note ) {
    return dispatch => {
        let data = JSON.stringify(note);
        axios.put(globalConstants.REST_API_BASE_URL + '/notes/' + note.id, data ,{
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then((response) => dispatch({
            type: actionTypes.UPDATE_CUSTOMER_NOTE,
            updatedNote: response.data 
        }))
      .catch( (error) => {
        console.log(error);
      });
    };
 }

 function getCustomersByParams(name, status, sortBy) {
     let customersQueryParams = '?name='+name + '&status=' + status + '&sortBy=' + sortBy;
     return getCustomers( customersQueryParams );
 }

 function updateCustomerStatus( customer, status ) {
    return dispatch => {
        customer.status = status;
        let data = JSON.stringify( customer );
        axios.put(globalConstants.REST_API_BASE_URL + '/customers/' + customer.id, data ,{
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then((response) => dispatch({
            type: actionTypes.UPDATE_CUSTOMER_STATUS,
            customer: response.data 
        }))
      .catch( (error) => {
        console.log(error);
      });
    };
 }

 function createNoteForCustomer( customer, noteContent ) {
    return dispatch => {  
        let data = JSON.stringify({
            content : noteContent
            });   
        axios.post(globalConstants.REST_API_BASE_URL + '/customers/' + customer.id + '/notes', data ,{
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then((response) => dispatch({
            type: actionTypes.CREATE_CUSTOMER_NOTE,
            customer: response.data 
        }))
      .catch( (error) => {
        console.log(error);
      });
    };
 }
