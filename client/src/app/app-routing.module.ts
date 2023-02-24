import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CuisineListComponent } from './components/cuisine-list.component';
import { RestaurantCuisineComponent } from './components/restaurant-cuisine.component';


const routes: Routes = [
  {path:"", component:CuisineListComponent},
  {path:"restaurants/:cuisine", component:RestaurantCuisineComponent},
  {path:"**", redirectTo:"", pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
