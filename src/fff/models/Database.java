package fff.models;

import com.sun.org.apache.xpath.internal.axes.OneStepIterator;
import fff.models.users.Admin;
import fff.models.users.Customer;
import fff.models.users.Owner;
import fff.models.users.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Database {
	
	private ObservableList<Admin> admins;
	private ObservableList<Owner> owners;
	private ObservableList<Customer> customers;
	private ObservableList<Restaurant> restaurants;
	private ObservableList<Rating> ratings;
	
	public Database(){
		this.admins = FXCollections.observableArrayList();
		this.owners = FXCollections.observableArrayList();
		this.customers = FXCollections.observableArrayList();
		this.restaurants = FXCollections.observableArrayList();
		this.ratings = FXCollections.observableArrayList();
	}
	
	public void readData() throws IOException {
		readUsers("admin");
		readUsers("owner");
		readUsers("customer");
		readRestaurants();
	}
	
	private void readUsers(String type) throws IOException {
		String path = "data/users/"+type+"s";
		InputStream data = Database.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(data));
		String res;
		
		while((res=br.readLine())!=null){
			String userPath = path+"/"+res+"/details.csv";
			InputStream userData = Database.class.getResourceAsStream(userPath);
			BufferedReader userBr = new BufferedReader(new InputStreamReader(userData));
			
			String[] line = userBr.readLine().split(",");
			String ID = line[0];
			String username = line[1];
			String password = line[2];
			String fullName = line[3];
			String email = line[4];
			
			switch(type){
				case "admin":
					Admin a = new Admin(ID,username,password,fullName,email);
					admins.add(a);
					break;
				case "owner":
					Owner o = new Owner(ID,username,password,fullName,email);
					owners.add(o);
					break;
				case "customer":
					Customer c = new Customer(ID,username,password,fullName,email);
					customers.add(c);
					break;
				default: break;
			}
			userBr.close();
		}
		br.close();
	}
	
	private void readRestaurants() throws IOException {
		String path = "data/restaurants";
		InputStream data = Database.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(data));
		String res;
		while((res=br.readLine())!=null) {
			String resPath = path+"/"+res+"/details.csv";
			InputStream resData = Database.class.getResourceAsStream(resPath);
			BufferedReader resBr = new BufferedReader(new InputStreamReader(resData));
			
			String[] line = resBr.readLine().split(",");
			String id = line[0];
			String name = line[1];
			int post = Integer.parseInt(line[2]);
			String cuisine = line[3];
			String owner = line[4];
			
			Restaurant restaurant = new Restaurant(id,name,post,cuisine,owner);
			restaurants.add(restaurant);
			
			String menuPath = path+"/"+res+"/menu.csv";
			InputStream menuData = Database.class.getResourceAsStream(menuPath);
			BufferedReader menuBr = new BufferedReader(new InputStreamReader(menuData));
			String men;
			while((men=menuBr.readLine())!=null){
				String[] line2 = men.split(",");
				Food food = new Food(line2[0],Float.parseFloat(line2[1]));
				restaurant.addFood(food);
			}
			findOwner(owner).addRestaurant(restaurant);
			
			resBr.close();
		}
		br.close();
	}
	
	private void readRatings() throws IOException{
		String path = "data/ratings.csv";
		InputStream data = Database.class.getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(data));
		String ratingLine;
		while((ratingLine=reader.readLine())!=null){
			String[] line = ratingLine.split(",");
			String id = line[0];
			String resID = line[1];
			String cusID = line[2];
			int value = Integer.parseInt(line[3]);
			Rating rating = new Rating(id,resID,cusID,value);
			ratings.add(rating);
			
			findRestaurant(resID).addRating(rating);
			findCustomer(cusID).addRating(rating);
		}
		reader.close();
	}
	
	public UserAccount findUser(String username){
		for(UserAccount user:admins)
			if(user.getUsername().equals(username))return user;
		for(UserAccount user:owners)
			if(user.getUsername().equals(username))return user;
		for(UserAccount user:customers)
			if(user.getUsername().equals(username))return user;
		return null;
	}
	
	public Owner findOwner(String ID){
		for(Owner o:owners) if(o.getID().equals(ID)) return o;
		return null;
	}
	
	public Restaurant findRestaurant(String ID){
		for(Restaurant r:restaurants) if(r.getID().equals(ID)) return r;
		return null;
	}
	
	public Customer findCustomer(String ID){
		for(Customer c:customers) if (c.getID().equals(ID)) return c;
		return null;
	}
	
	public ObservableList<Admin> getAdmins() {
		return admins;
	}
	
	public ObservableList<Owner> getOwners() {
		return owners;
	}
	
	public ObservableList<Customer> getCustomers() {
		return customers;
	}
	
	public ObservableList<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public ObservableList<Rating> getRatings() {
		return ratings;
	}
	
	public static void main(String[] args) {
		Database database = new Database();
		try {
			database.readData();
			database.readRatings();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		for(Restaurant r:database.getRestaurants()){
			System.out.println(r.getName()+" "+r.getID());
			for(Food f:r.getMenu()){
				System.out.println("\t"+String.format("%2.2f",f.getPrice())+"\t"+f.getName());
			}
			System.out.println("--------------------------------");
		}
		*/
	}
}
