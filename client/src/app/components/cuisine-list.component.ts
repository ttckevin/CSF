import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit{

	// TODO Task 2
	// For View 1

  cList!:string[]

  constructor(private svc: RestaurantService, private router: Router,){}

  ngOnInit(): void {
    this.retrieveCList();      
  }

  async retrieveCList(){
    await this.svc.getCuisineList();
    this.cList = this.svc.cuisines.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/);
  }

  select(i:string){
    console.log(i);
    this.router.navigate(['/restaurants', i])
    this.svc.getCuisine(i);
  }
}
