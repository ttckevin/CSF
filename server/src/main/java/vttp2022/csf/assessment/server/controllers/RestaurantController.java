package vttp2022.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.assessment.server.services.RestaurantService;

@Controller
@RequestMapping("/api")
public class RestaurantController {
    
    @Autowired
    private RestaurantService svc;

    @GetMapping(path="cuisines")
    @ResponseBody
    public ResponseEntity<String> getCuisines(){
        List<String> resp = svc.getCuisines();
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(String c : resp){
            arr.add(c);
            System.out.println(c);
        }
        return ResponseEntity.ok().body(arr.build().toString());
    }

    @GetMapping(path="{cuisine}/restaurants")
    @ResponseBody
    public ResponseEntity<String> getRestaurantByCuisine(@PathVariable String cuisine){
        System.out.println("BACKEND:" +cuisine);
        List<String> resp =svc.getRestaurantsByCuisine(cuisine);
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(String c : resp){
            arr.add(c);
            System.out.println(c);
        }
        return ResponseEntity.ok().body(arr.build().toString());
    }

}
