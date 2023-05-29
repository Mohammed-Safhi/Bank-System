    package Bank_System;

import java.util.Scanner;

public class Employee extends User {
	private int ID;
	private String FirstName, LastName;
	private int Password;

	Employee(int ID_Password) {
		this.ID = ID_Password;
		this.Password = ID_Password;
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

	void printHello() {
		if (ID == 0001) {
			System.out.println(
				"\n\t\t***********************************************" +
                "\n\t\t***             Welcome Admin!              ***" +
                "\n\t\t***********************************************"
			);
		}
		else {
			System.out.println(
				"\n\t\t***********************************************" +
                "\n\t\t***            Welcome Employee!            ***" +
                "\n\t\t***********************************************"
			);
		}
	}

}

    
