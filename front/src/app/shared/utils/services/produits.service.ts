import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product } from 'app/shared/ui/list/search.model';
import { routeAdminProducts,routeUserProducts } from './configUrl';
import { NewProductRequest, ProductResponse } from 'app/models/product.model';


@Injectable()
export class ProductServiceHttp {



  


  constructor(private http: HttpClient) { }

   
  public getProducts(): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(routeUserProducts);
  }


  public getOneProductsFilteredBy(productID: number): Observable<Product> {
    return this.http.get<Product>(`${routeUserProducts}/${productID}`);
  }

  

  public updateProduct(product: Product): Observable<Product>{
      return this.http.patch<Product>(`${routeAdminProducts}/${product.id}`, product);
  }



  public saveProduct(product: NewProductRequest): Observable<Product> {
    return this.http.post<Product>(`${routeAdminProducts}`, product);
  }
    

  public deleteAllProducts() {
    return this.http.delete(`${routeAdminProducts}`);
  }


  public deleteOneroduct(productID: number) {
    return this.http.delete(`${routeAdminProducts}/${productID}`);
  }

}
