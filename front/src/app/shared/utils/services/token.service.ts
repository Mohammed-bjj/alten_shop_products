import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import { Role, Token } from 'app/base/auth/auth.model';





@Injectable()
export class TokenService {

  constructor(private http: HttpClient) { }

  public set(data: Token){
    localStorage.setItem('token', data.accessToken);
  }


  public handle(data: Token){
        this.set(data);
  }


   decodeToken(token: string): JwtPayload | null {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      console.error('Invalid token:', error);
      return null;
    }
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public isTokenValide(): boolean{
    const token = this.getToken();
    if(!token){
      return false;
    }
    const decodedToken = this.decodeToken(token);
    if (!decodedToken) {
      return false;
    }

    try{

      const decodedToken = jwtDecode(token);
      const currentTime = Math.floor(Date.now() / 1000);

      if(decodedToken.exp && decodedToken.exp < currentTime) {
        return false;
      }
      if(decodedToken.iat && decodedToken.iat > currentTime) {
        return false;
      }
      return true;
    }catch(err){
      console.error('Invalid token:', err);
      return false;
    }
  }
    
  public LoggeIn(){
    return this.isTokenValide();
  }

  public getInfo(){
    return this.getToken() ? this.decodeToken(this.getToken()): null;
  }
  public remove(){
    localStorage.removeItem('token');
  }


   public isAdmin(){
    return this.getInfo()['scope'] == Role.admin;
   }

  




   
}
