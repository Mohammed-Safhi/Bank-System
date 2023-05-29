    package Bank_System;

abstract class User {
    int ID;
	String FirstName, LastName;
    int Password;

	abstract public void setID(int ID);
    abstract public int getID();
    abstract public String getFirstName();
    abstract public String getLastName();
	abstract public void setFirstName(String FirstName);
	abstract public void setLastName(String LastName);
	abstract public void setPassword(int Password);
	abstract public int getPassword();
}

    
