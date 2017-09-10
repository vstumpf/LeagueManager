import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./dashboard.component";
import { UserDetailComponent } from "./user/user-detail.component";
import { NgModule } from "@angular/core";
import { UserListComponent } from "./user/user-list.component";

const routes: Routes = [
  { path:'', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'users/:id', component: UserDetailComponent },
  { path: 'users', component: UserListComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
