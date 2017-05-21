package models;

import models.users.Admin;
import models.users.Customer;
import models.users.Owner;
import models.users.UserAccount;
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
	
	public Database(){
		this.admins = FXCollections.observableArrayList();
		this.owners = FXCollections.observableArrayList();
		this.customers = FXCollections.observableArrayList();
		this.customers = FXCollections.observableArrayList();
		
		try {
			readData();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void readData() throws IOException {
		readUsers("admin");
		readUsers("owner");
		readUsers("customer");
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
	
	public UserAccount getUser(String username){
		for(UserAccount user:admins)
			if(user.getUsername().equals(username))return user;
		for(UserAccount user:owners)
			if(user.getUsername().equals(username))return user;
		for(UserAccount user:customers)
			if(user.getUsername().equals(username))return user;
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
	
	public static void main(String[] args) throws IOException {
		Database database = new Database();
		database.readUsers("admin");
		database.readUsers("owner");
		database.readUsers("customer");
		System.out.println(database.getUser("Eau11").getClass().getSimpleName());
	}
}
