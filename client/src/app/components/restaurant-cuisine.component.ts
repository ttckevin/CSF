import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit{
	
  rList!:string[]
  cuisine!:string
	// TODO Task 3
	// For View 2
  constructor(private svc: RestaurantService, private router: Router, private ac:ActivatedRoute){}
  ngOnInit(): void {
    
    this.retrieveRList();
  }

  async retrieveRList(){
    // this.ac.params.subscribe(resp=>{console.log(resp)});
    this.ac.params.subscribe(resp=>{this.cuisine=resp['cuisine']});
    console.log('rerr',this.cuisine);
    // this.cuisine = this.cuisine.replace("_","/");
    // console.log('after replace',this.cuisine)
    await this.svc.getRestaurantsByCuisine(this.cuisine);
    if(this.svc.restaurants.length>1) this.rList = this.svc.restaurants.split(',');
  }
  select(i:string){}
}
