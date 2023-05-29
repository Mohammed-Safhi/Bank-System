    package Bank_System;

import java.util.Calendar;
import java.text.SimpleDateFormat;

class Client extends User {
	int ID;
	private String FirstName, LastName;
	private double Balance;
	private int Password;
	private String Email;
	private AccountDate date = new AccountDate();
	private final String initial_date = date.getCurrentDate();
	private String deactivation_date = "Account Active";
	private boolean account_active = true;  
	public static int clientCounter=0;
	public static int deactivatedCounter = 0;
	
	Client(int Id, String FName, String LName, String email, int pass, double AccBalance){
		this.ID=Id;
		this.FirstName= FName;
		this.LastName=LName;
		this.Email=email;
		this.Balance=AccBalance;
		this.Password=pass;
		Client.clientCounter++;
		System.out.println("Account created on: ");
		System.out.println(initial_date);
	}
	
	public void setID(int ID) {
		this.ID=ID;
	}
    public int getID() {
		return ID;
	}
    public String getFirstName() {
		return FirstName;
	}
    public String getLastName() {
		return LastName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName=FirstName;
	}
	public void setLastName(String LastName) {
		this.LastName=LastName;
	}
	public void setPassword(int Password) {
		this.Password=Password;
	}
	public int getPassword() {
		return Password;
	}
	public void setBalance(double d) {
		this.Balance=d;
	}
	public double getBalance() {
		return Balance;
	}
	public String getInitialDate() {
		return initial_date;
	}
	 String getEmail() {
		return Email;
	}
	 void setEmail(String Email) {
		this.Email=Email;
	}
	public void Deactivate() {
		account_active = false;
		deactivation_date = date.getCurrentDate();
		deactivatedCounter++;
	}
	public String getDeactivationDate() {
		return deactivation_date;
	}
}
 
    
