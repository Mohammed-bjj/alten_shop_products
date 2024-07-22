import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TokenService } from './token.service';


@Injectable()
export class StateAuthAccount{


  private loggedIn = new BehaviorSubject<boolean>(this.tokenService.LoggeIn());
  

  authStatus = this.loggedIn.asObservable();

  


  constructor(private readonly tokenService: TokenService) { }



  changeStatus(value: boolean){
   this.loggedIn.next(value); 
  }


}
