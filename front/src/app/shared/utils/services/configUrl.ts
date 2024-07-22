
import { environment } from 'environment/environment';


// config des url pour l'api

const apiUserProducts = '/api/products';
const apiAdminProducts = '/api/admin/products'
const apiAuth = '/auth/login' 

const {backendHost, isReadDataFromJsonFile, jsonUrlProducts} = environment;



export const routeUserProducts = isReadDataFromJsonFile ?  jsonUrlProducts : `${backendHost}${apiUserProducts}`;
export const routeAdminProducts = `${backendHost}${apiAdminProducts}`

export const routeLogin = `${backendHost}${apiAuth}`;


export const routesWithIdPatterns = [
  /^http:\/\/localhost:8085\/api\/admin\/products$/,          
  /^http:\/\/localhost:8085\/api\/admin\/products\/\d+$/     
];
