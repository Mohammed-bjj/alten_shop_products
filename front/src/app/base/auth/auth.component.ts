import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'app/shared/utils/services/auth.service';
import { AuthCredential, Role, Token } from './auth.model';
import { TokenService } from 'app/shared/utils/services/token.service';
import { Router } from '@angular/router';
import { StateAuthAccount } from 'app/shared/utils/services/stateAuthAccount.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  loginForm: FormGroup;
  credential: AuthCredential = {username: '', password: ''};
  msgErrorAuth:boolean = false; 

  constructor(
    private fb: FormBuilder, 
    private readonly stateAuthAccount: StateAuthAccount,
    private readonly route: Router,
    private readonly authService :AuthService, 
    private readonly tokenService: TokenService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }



  onSubmit(): void {
    if (this.loginForm.valid) {
      this.credential.username = this.loginForm.get('username')?.value;
      this.credential.password = this.loginForm.get('password')?.value;
      this.authService.login(this.credential).subscribe({
        next: (res) => this.handleResponse(res),
        error: (err) => {
          console.log("Error auth : ",err.error);
          this.msgErrorAuth = true;
        }
      })
      
    }
  }

  switcherRoute(isAdmin: boolean){
      switch(isAdmin){
        case true: 
            this.route.navigateByUrl('/admin/products');
            break;
        case false:
          this.route.navigateByUrl('/products');
      }
  }

  handleResponse(accessToken: Token){
      this.tokenService.handle(accessToken);
      this.stateAuthAccount.changeStatus(true);
      this.switcherRoute(this.tokenService.isAdmin());
  }

}
