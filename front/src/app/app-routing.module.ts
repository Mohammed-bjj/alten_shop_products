import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent, TableComponent } from './shared/ui';
import { Product } from './models/product.model';
import { AuthGuard } from './shared/utils/guards/auth.guard';
import { AuthComponent } from './base/auth/auth.component';
import { AfterAuthGuad } from './shared/utils/guards/afterAuth.guard';


const routes: Routes = [
  {path: 'products', component: ListComponent, data: {data: Product} },
  {path: 'admin/products', component: TableComponent, data: {data: Product}, canActivate: [AuthGuard]},
  {path: 'login', component: AuthComponent, canActivate: [AfterAuthGuad]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule],
})

export class AppRoutingModule {}
