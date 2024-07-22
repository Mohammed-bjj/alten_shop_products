import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { routeLogin } from './configUrl';
import { Token, AuthCredential } from 'app/base/auth/auth.model';
import { Observable } from 'rxjs';



@Injectable()
export class AuthService {



  


  constructor(private http: HttpClient) { }



  public login(credential: AuthCredential): Observable<Token>{
    return this.http.post<Token>(`${routeLogin}`, credential);
  }

   


}
