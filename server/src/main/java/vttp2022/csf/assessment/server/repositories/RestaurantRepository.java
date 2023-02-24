package vttp2022.csf.assessment.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.internal.operation.AggregateOperation;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {

	@Autowired
	private MongoTemplate template;
	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	@Value("${SPRING_DATA_MONGODB_URI}") String uri;
	private static final String csf = "csf";

	// db.restaurants.distinct('cuisine');
	public List<String> getCuisines() {
		// Implmementation in here
		List<String> res = new LinkedList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(csf);
            MongoCollection<Document> collection = database.getCollection("restaurants");
            try {
                DistinctIterable<String> docs = collection.distinct("cuisine", String.class);
                MongoCursor<String> results = docs.iterator();
                while(results.hasNext()) {
					res.add(results.next().replace("/", "_"));
                }
            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }
        }

		System.out.println("Repository Cuisine");
		return res;
	}

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	// db.restaurants.find({cuisine:'Asian'},{name:true,_id:false})
	public List<String> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
		List<String> n = new LinkedList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(csf);
            MongoCollection<Document> collection = database.getCollection("restaurants");
		// if(cuisine.contains("%2F")) cuisine =cuisine.replace("%2F","/");
		Bson filter = Filters.eq("cuisine", cuisine);

		FindIterable<Document> documents = collection.find(filter);
		MongoCursor<Document> cursor = documents.iterator();
		
		while (cursor.hasNext()) {
			n.add((String) cursor.next().get("name"));
		}
	}
		return n;
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public Optional<Restaurant> getRestaurant(String name) {
		// Implmementation in here
		return null;
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public void addComment(Comment comment) {
		// Implmementation in here
		
	}
	
	// You may add other methods to this class

}
