import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { routesWithIdPatterns } from '../services/configUrl';
import { TokenService } from '../services/token.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private readonly servicetoken: TokenService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(routesWithIdPatterns.some( regex => regex.test(request.url))){
      request =request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.servicetoken.getToken()}`,
        }
      })
    }
    return next.handle(request);
  }
}
