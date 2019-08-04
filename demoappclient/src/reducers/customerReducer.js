import {actionTypes} from '../actions/types';

const initialState =  {
    items : [],
    item : {}
}

export function customerReducer(state = initialState, action) {
    switch(action.type) {

        case actionTypes.GET_CUSTOMERS:
            return {
                ...state,
                items : action.customers
            }
        case actionTypes.GET_CUSTOMER_DETAILS:
                return {
                    ...state,
                    item : action.customer,
                    message: ""
                }
        case actionTypes.UPDATE_CUSTOMER_NOTE:
                return {
                    ...state,
                    note: action.note
                }
        case actionTypes.UPDATE_CUSTOMER_STATUS:
                return {
                    ...state,
                    item: action.customer,
                    message: 'Updated the status successfully'
                }
        case actionTypes.CREATE_CUSTOMER_NOTE:
                return {
                    ...state,
                    item: action.customer
                }
        default:
            return state
    }
}