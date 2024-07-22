import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StateAuthAccount } from 'app/shared/utils/services/stateAuthAccount.service';
import { TokenService } from 'app/shared/utils/services/token.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() isAuthenticated = false;
     

  public userName: string;
  public userMenuItems: MenuItem[] = [
    { label: 'Profile', icon: 'pi pi-cog', routerLink: '/user/profile' },
    { label: 'Messages', icon: 'pi pi-envelope', routerLink: '/user/messages' },
    { label: 'Notifications', icon: 'pi pi-bell', routerLink: '/user/notifications' },
    { label: 'Logout', icon: 'pi pi-power-off', command: null },
    {label: 'login', icon: 'pi pi-user', command: null}
  ];
  
  public iconAuth: string =  this.userMenuItems[4].icon;
  

  constructor(
    private readonly route: Router,
    private readonly stateAuthAccount: StateAuthAccount,
    private readonly tokenService: TokenService

  ) {}
  
  
  ngOnInit(): void {
  
    this.stateAuthAccount.authStatus.subscribe( {
      next: (res) => {
        this.isAuthenticated = res;
        this.iconAuth = this.isAuthenticated ? this.userMenuItems[3].icon: this.userMenuItems[4].icon;
        this.userName = this.isAuthenticated ? this.tokenService.getInfo().sub : '';
      },
      error: (err) => console.log("Error : ", err.error)
    })
  }


  public login(){
    if(!this.isAuthenticated){
      this.route.navigateByUrl('/login')
    } else {
      this.logout();
    }

  }


  private logout(){
    this.tokenService.remove();
    this.stateAuthAccount.changeStatus(false);
    this.route.navigateByUrl('/');
  }

}
