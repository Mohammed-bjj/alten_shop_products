import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from '@angular/router';
import { TokenService } from '../services/token.service';
import { StateAuthAccount } from '../services/stateAuthAccount.service';
import { Role } from 'app/base/auth/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AfterAuthGuad implements CanActivate {


  constructor(
    private readonly tokenService: TokenService, 
    private readonly stateAuthAccount: StateAuthAccount,
    private readonly router: Router
  ){}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean  {
      if(this.tokenService.LoggeIn()){
          this.stateAuthAccount.changeStatus(true);
          this.tokenService.isAdmin() ?  this.router.navigateByUrl("/admin/products"): this.router.navigateByUrl("/products");
          return false
      }
    return true;
  }
  
}
