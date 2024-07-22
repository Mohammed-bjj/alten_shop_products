import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product, ProductsResponse } from 'app/shared/ui/list/search.model';
import { routeAdminProducts,routeUserProducts } from './configUrl';


@Injectable()
export class ProductServiceHttp {



  


  constructor(private http: HttpClient) { }

   
  public getProducts(): Observable<ProductsResponse> {
    return this.http.get<ProductsResponse>(routeUserProducts);
  }


  public getOneProductsFilteredBy(productID: number): Observable<ProductsResponse> {
    return this.http.get<ProductsResponse>(`${routeUserProducts}/${productID}`);
  }

  

  public updateProduct(product: Product): Observable<Product>{
      return this.http.patch<Product>(`${routeAdminProducts}/${product.id}`, product);
  }



  public saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${routeAdminProducts}`, product);
  }
    

  public deleteAllProducts() {
    return this.http.delete(`${routeAdminProducts}`);
  }


  public deleteOneroduct(productID: number) {
    return this.http.delete(`${routeAdminProducts}/${productID}`);
  }

}
