import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';
import { StateAuthAccount } from '../services/stateAuthAccount.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(
    private readonly tokenService: TokenService, 
    private readonly stateAuthAccount: StateAuthAccount,
    private readonly router: Router
  ){}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean  {
      if(!this.tokenService.LoggeIn()){
          this.tokenService.remove();
          this.stateAuthAccount.changeStatus(false);
          this.router.navigateByUrl("/login");
          return false
      }
      if(!this.tokenService.isAdmin()){
        this.router.navigateByUrl("/products");
        return false;
      }
    return true;
  }
  
}
