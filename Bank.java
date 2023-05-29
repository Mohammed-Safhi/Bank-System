    package Bank_System;
import java.util.*;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.*;

public class Bank {
	static Scanner input= new Scanner(System.in);
	static Client[] bankSystem = new Client[50];
	static Client[] deactivated = new Client[50];
	static Employee[] emp = new Employee[3];
	Session newSession = null;
	MimeMessage mimeMessage = null;
	

	static boolean x = true;
	
	public static void main(String[]args) throws MessagingException, IOException {

		emp[0] = new Employee(1000);
		emp[1] = new Employee(2000);
		emp[2] = new Employee(3000);
		

		choice();
		System.out.println("Thank you for coming!");
	}
	
	public static void choice() throws MessagingException, IOException {
		System.out.print(
                "\n\t\t***********************************************" +
                "\n\t\t***          Welcome to Maze Bank           ***" +
                "\n\t\t***********************************************" +
                "\n\t\t***     Are you a Client or an Emloyee      ***" +
                "\n\t\t***   0.Exit      1.Employee     2.Client   ***" +
                "\n\t\t***********************************************" +
                "\n\nYour choice is: ");
		int choice=input.nextInt();
		
		if(choice==1){
			System.out.println("Enter your ID: ");
			int ID_Entered= input.nextInt();
			int i = searchByID(ID_Entered, emp);
			if(i == -1) {
				System.out.println("User does not exist.");
				choice();
			}
			else {
				System.out.println("Enter your password: ");
				int password = input.nextInt();
				if(password == emp[i].getPassword()) {
					employeeMenu(emp[i]);
				}
				else {
					System.out.println("Wrong Password!");
					choice();
				}	
			}   
        }
		else if(choice==2){
			System.out.println("Enter your ID: ");
			int ID_Entered= input.nextInt();
			int i= searchByID(ID_Entered);
			if(i==-1) {
				System.out.println("User does not exist.");
				choice();
			}
			else {
				System.out.println("Enter your password: ");
				int password= input.nextInt();
				if(password==bankSystem[i].getPassword()) {
					clientMenu(bankSystem[i]);	
				}
				else {
					System.out.println("Wrong Password!");
					choice();
				}	
			}      	
		}
		else if(choice==0){
			System.out.println("Thank you for coming!");
			System.exit(0);
		}
		else {
			System.out.println("INVALID OPTION!");
		}
		
	}
	public static void clientMenu(Client client) throws MessagingException, IOException {
		System.out.print(
                "\n\t\t***********************************************" +
                "\n\t\t***          Bank Management System         ***" +
                "\n\t\t***             Client's Menu               ***" +
                "\n\t\t***********************************************" +
                "\n\t\t***  0.Exit                                 ***" +
                "\n\t\t***  1.Withdraw                             ***" +
                "\n\t\t***  2.Deposit                              ***" +
                "\n\t\t***  3.Check Balance                        ***" +
                "\n\t\t***  4.Transter                             ***" +
                "\n\t\t***  5.Go to Main Menu                      ***" +
                "\n\t\t***********************************************" +
                "\n\nYour choice is: ");
		do{
            
			int clientPage=input.nextInt();
			
			switch(clientPage) {
				case 1:
					Withdraw(client);
					break;
				case 2:
					Deposit(client);
					break;
				case 3:
					checkBalance(client);
					break;
				case 4:
					Transfer(client);
					break;
				case 5:
					choice();
					break;
				case 0:
					x=false;
					System.out.println("Thank you for coming!");
					System.exit(0);
				break;
								
						
				default:
					System.out.println("INVALID OPTION!");
			}

        }while(x==true);
		
	}
	public static void employeeMenu(Employee emp) throws MessagingException, IOException {
		emp.printHello();
		System.out.print(
                "\n\t\t***********************************************" +
                "\n\t\t***          Bank Management System         ***" +
                "\n\t\t***             Employee's Menu             ***" +
                "\n\t\t***********************************************" +
                "\n\t\t***  0.Exit                                 ***" +
                "\n\t\t***  1.Add Client                           ***" +
                "\n\t\t***  2.Deactivate Client                    ***" +
                "\n\t\t***  3.Update Client's information          ***" +
                "\n\t\t***  4.Print Client's information           ***" +
                "\n\t\t***  5.Go to Main Menu                      ***" + 
                "\n\t\t***********************************************" +
                "\n\nYour choice is: ");
		do{
            
			int employeePage = input.nextInt();
					
			switch(employeePage) {
				case 1:
					addClient();
					choice();
					break;
				case 2:
					deactivateAccount(emp);
					choice();
					break;
				case 3:
					updateClients(bankSystem);
					choice();
					break;
				case 4:
					printClients(bankSystem);
					choice();
					break;
				case 5:
					choice();
					break;
				case 6:
					
					break;
				case 0:
					x=false;
					break;
				default:
					System.out.println("INVALID OPTION!");
    		}

        }while(x==true);

	}

	public static int searchByID(int ID) {
		for(int i=0;i<Client.clientCounter;i++) {
			if(bankSystem[i].getID()==ID) {
				return i;
			}else {
				continue;
			}
		}
		
		return -1;
	}
	
	public static int searchByID(int ID, Employee[] emp) {
		for(int i=0;i<3;i++) {
			if(emp[i].getID()==ID) {
				return i;
			}else {
				continue;
			}
		}
		
		return -1;
	}
	
	public static  void Transfer(Client client) throws MessagingException, IOException {
		 double transfer;
		 int transfer_to;
		 System.out.println("Enter the ID you want to transfer to: ");
		 transfer_to=input.nextInt();
		 int i=searchByID(transfer_to);
		 System.out.println("Your beneficiary is: "+bankSystem[i].getFirstName()+" "+bankSystem[i].getLastName());
		 System.out.println("Is this your beneficiary? yes(1)/no(0)");
		 int answer= input.nextInt();
		 if(answer==1) {
			 System.out.println("Enter the amount to transfer: ");
			 transfer= input.nextDouble();
			 client.setBalance(client.getBalance()-transfer);
			 bankSystem[i].setBalance(bankSystem[i].getBalance()+transfer);
			 System.out.println("Your new balance: "+client.getBalance());
			 System.out.println("Transaction Completed.");
			 choice();
		 }else if(answer==0) {
			 System.out.println("Try again.");
			 Transfer( client);
		 }else {
			 System.out.println("the answer you entered is invalid.");
			 Transfer(client);
		 }
		 
		 
	 }
	public static void addClient() {
		String FName,LName,email;
		int Id,pass = 0;
		float AccBalance;
		
		System.out.print("Please enter the following info:\nID: ");
		Id=input.nextInt();
		int i=searchByID(Id);
		if(i==-1) {
			System.out.print("First name: ");
		FName=input.next();
		System.out.print("Last name: ");
		LName=input.next();
		System.out.print("Email: ");
		email=input.next();
		System.out.print("Enter new password: ");
		pass=input.nextInt();
		System.out.print("Your balance: ");
		AccBalance=input.nextFloat();
		
		bankSystem[Client.clientCounter]= new Client( Id,  FName,  LName,email, pass,  AccBalance);
		System.out.println("Your information has been successfully added.");
		}else {
			System.out.println("Client Exists");
			addClient();
		}
		
		
		
		
		
	}

	public static void Withdraw(Client client) throws MessagingException, IOException {
		int withdraw;
		System.out.println("Enter the withdrawn amount: ");
		withdraw= input.nextInt();
		if(withdraw%50==0) {
			if(withdraw<=client.getBalance()) {
				client.setBalance(client.getBalance()-withdraw);
				System.out.println("Your Balance is: "+client.getBalance());
				choice();
			}else {
				System.out.println("The amount exceeded the balance.");
				Withdraw(client);
			}
		}else {
			System.out.println("the amount you entered is invalid.");
			Withdraw(client);
		}
		
	}

	public static void Deposit(Client client) throws MessagingException, IOException {
		int deposit;
		System.out.println("Enter the amount to deposit: ");
		deposit= input.nextInt();
		if(deposit%50==0) {
			client.setBalance(client.getBalance()+deposit);
			System.out.println("Your Balance is: "+client.getBalance());
			System.out.println("Deposit Completed");
			choice();
			
		}else {
			System.out.println("the amount you entered is invalid.");
			Deposit(client);			
		}
	}

	public static void checkBalance(Client client) throws MessagingException, IOException {
		System.out.println("Your Balance is: "+ client.getBalance());
		choice();
	}

	public static void Remove(Client[] bankSystem, int index) {
		for(int i = index; i < Client.clientCounter; i++) {
			bankSystem[i] = bankSystem[i+1];
		}
	}

	public static void deactivateAccount(Employee emp) throws MessagingException, IOException {
		System.out.println("Enter Client ID: ");
		int id = input.nextInt();
		int index = searchByID(id);
		if (index == -1) {
			System.out.println("This client does not exist or is deactivated.");
			employeeMenu(emp);
		} else {
			deactivated[Client.deactivatedCounter] = bankSystem[index];
			bankSystem[index].Deactivate();
			Remove(bankSystem, index);
			System.out.println("Account deactivated successfully!");
			System.out.println("Deactivation date: " + deactivated[Client.deactivatedCounter-1].getDeactivationDate());
			choice();
		}
	}

	public static void printClients(Client[] bankSystem) {
		System.out.println(
			"\n\t\t**********************************************************************************************"
		);
		for(int i = 0; i < Client.clientCounter; i++) {
			System.out.println("\n\t\tID: " + bankSystem[i].getID() + 
				"\t Name: " + bankSystem[i].getFirstName() + " " + bankSystem[i].getLastName() +
				"\t Email: " + bankSystem[i].getEmail() +
				"\t Balance: " + bankSystem[i].getBalance() +
				"\t Initial Date: " + bankSystem[i].getInitialDate()
			);
		}
		System.out.println(
			"\n\t\t**********************************************************************************************"
		);
	}
	public void sendEmail() throws MessagingException {
		String fromUser = "faisalmudarra1000@gmail.com";  //Enter sender email id
		String fromUserPassword = "faisal123F";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	public MimeMessage draftEmail(Client client) throws AddressException, MessagingException, IOException  {
		 
		 
		String[] emailReceipients = {client.getEmail()};  //Enter list of email recepients
		String emailSubject = "Maze Bank";
		String emailBody = "Your information has been successfully updated!\t If you did not order this update please contact us.";
		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);
	   
      
	    
	    
		 MimeBodyPart bodyPart = new MimeBodyPart();
		 bodyPart.setText(emailBody,"html/text");
		 MimeMultipart multiPart = new MimeMultipart();
		 multiPart.addBodyPart(bodyPart);
		 mimeMessage.setContent(multiPart);
		 return mimeMessage;
	}

	public void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties,null);
	}

	public static void updateClients(Client[] bankSystem) throws AddressException, MessagingException, IOException {
		
		
		System.out.println("Enter Client's ID: ");
		int ID = input.nextInt();
		int index = searchByID(ID);
		if (index == -1) {
			System.out.println("Client does not exist.");
			choice();
		} else {
			System.out.println("\nCurrent Client Information: "+
				"\nFirst Name: " + bankSystem[index].getFirstName() +
				"\t\tLast Name: " + bankSystem[index].getLastName() +
				"\t\tEmail: " + bankSystem[index].getEmail() +
				"\t\tPassword: " + bankSystem[index].getPassword() + "\n"
			);
			System.out.println("What do you wish to update?" +
				"\n\t1- First Name." +
				"\n\t2- Last Name." +
				"\n\t3- Email." +
				"\n\t4- Password."
			);
			int option = input.nextInt();
			if (option == 1) {
				System.out.println("Enter new First Name:");
				String name = input.next();
				bankSystem[index].setFirstName(name);
				
			} else if (option == 2) {
				System.out.println("Enter new Last Name:");
				String name = input.next();
				bankSystem[index].setLastName(name);
				
			} else if (option == 3) {
				System.out.println("Enter new Email:");
				String email = input.next();
				bankSystem[index].setEmail(email);
				
			} else if(option == 4) {
				System.out.println("Enter new Password:");
				int pass = input.nextInt();
				bankSystem[index].setPassword(pass);
				
			}else  {
				System.out.println("Invalid Input.");
				updateClients(bankSystem);
			}
			


			System.out.println("\nUpdated Client Information: "+
				"\nFirst Name: " + bankSystem[index].getFirstName() +
				"\t\tLast Name: " + bankSystem[index].getLastName() +
				"\t\tEmail: " + bankSystem[index].getEmail() +
				"\t\tPassword: " + bankSystem[index].getPassword() + "\n"
			);
			
			
			
		

		}
		
	}

	
	

}
    
