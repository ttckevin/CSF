import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { firstValueFrom } from 'rxjs'
import { Restaurant, Comment } from './models'

@Injectable({
	providedIn: 'root'
  })
export class RestaurantService {

	cuisines!: string
	restaurants!: string
	constructor(private Http: HttpClient){}

	getCuisine(i:string):string{
		return i;
	}

	// TODO Task 2 
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public async getCuisineList(): Promise<any> {
		// Implememntation in here
		try {
			const resp = await firstValueFrom(this.Http.get('api/cuisines'))
			return this.cuisines = resp.valueOf().toString()
		} catch (error) {
			return console.log(error)
		}
	}

	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public async getRestaurantsByCuisine(c:String) :Promise<any> {
		// Implememntation in here
		const variable = (c.replace("_","/"));

		try {
			const resp = await firstValueFrom(this.Http.get(`api/${variable}/restaurants`))
			this.restaurants=resp.valueOf().toString()
		} catch (error) {
			console.log(error)
		}
	}
	
	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// public getRestaurant(???): Promise<Restaurant> {
	// 	// Implememntation in here

	// }

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	// public postComment(comment: Comment): Promise<any> {
	// 	// Implememntation in here

	// }
}
