import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DriverDvdStore {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {

//-----------------------------------------------------------------------------------------load list from file		

System.out.println("The list are being loaded...");
		
//read dvdlist from a file
		
	ObjectInputStream DVDIn = new ObjectInputStream(new FileInputStream("DVDLibrary.dat"));
	@SuppressWarnings("unchecked")
	LinkedPositionalList<DvdType> DVDLibrary = (LinkedPositionalList<DvdType>) DVDIn.readObject();
	//System.out.println("The DVD List");

		
//read customer list from a file
		
	ObjectInputStream CustomerIn = new ObjectInputStream(new FileInputStream("CustomerLibrary.dat"));
	@SuppressWarnings("unchecked")
	LinkedPositionalList<CustomerType> customerLibrary = (LinkedPositionalList<CustomerType>) CustomerIn.readObject();
	//System.out.println("The Customer List");
	
	
//read checkedout list from the file
			
	ObjectInputStream CheckedOutIn = new ObjectInputStream(new FileInputStream("CheckedOutLibrary.dat"));
	@SuppressWarnings("unchecked")
	LinkedPositionalList<CheckedOut> checkedOutLibrary = (LinkedPositionalList<CheckedOut>) CheckedOutIn.readObject();
	//System.out.println("The list of checked out DVDs");

//-----------------------------------------------------------------------------------------end load list from file	

System.out.println("The list have been successfully loaded!");

//----------------------------------------------------------------------------------------Start of the user interface
/*												
printAllDVDs(DVDLibrary);					
printAllCustomers(customerLibrary);						
printAllRentedDVDs(CheckedOutLibrary);		
*/

System.out.println("\nChoose function to perform by entering the corresponding number\n" +
				   "1. Rent a DVD\n" +
				   "2. Return a DVD\n" +
				   "3. Print a list of all currently checked out DVDs\n" +
				   "4. Print a list of Customers\n" +
				   "5. Show which DVDs a specific customer has checked out\n" +
				   "6. Show the details of a specific customer\n" +
				   "7. Print a list of all the DVDs in the store\n" +
				   "8. Show the details of a specific DVD\n" +
				   "9. Show whether a specific DVD is availible\n" +
				   "10. Remove a DVD from the database\n" +
				   "Coming Soon: Add a DVD to the database, Add a Customer to the Database\n" +
				   "11. exit\n"
					);

Scanner input = new Scanner(System.in);


int selection = 0;
String name = null;
String title = null;
while (selection != 11)
{
	System.out.print("Enter your selection here: ");
	System.out.println();
	selection = Integer.parseInt(input.nextLine());
	switch (selection)
	{
		case(1):
		{	
			System.out.println("You have selected rent a DVD");
			System.out.print("Enter the name of the customer here: ");
			name = input.nextLine();
			System.out.println();
			System.out.print("Enter the title of the DVD here: ");
			title = input.nextLine();
			System.out.println();
			
			rentDVD(name, title, DVDLibrary, customerLibrary, checkedOutLibrary);
			break;
		}
		case(2):
		{
			System.out.println("You have selected return a DVD");
			System.out.print("Enter the name of the customer here: ");
			name = input.nextLine();
			System.out.println();
			System.out.print("Enter the title of the DVD here: ");
			title = input.nextLine();
			System.out.println();
			
			returnDVD(name, title, DVDLibrary, customerLibrary, checkedOutLibrary);
			break;
		}
		case(3):
		{
			printAllRentedDVDs(checkedOutLibrary);
			break;
		
		}
		case(4):
		{
			printAllCustomers(customerLibrary);
			break;
		}
		case(5):
		{
			System.out.println("You have selected to show the DVDs a specific customer has checked out");
			System.out.print("Enter the name of the customer here: ");
			name = input.nextLine();
			System.out.println();
			showCustomersCheckedOut(name, customerLibrary, checkedOutLibrary);
			break;
		}
		case(6):
		{
			System.out.println("You have selected to show the details of a specific customer");
			System.out.print("Enter the name of the customer here: ");
			name = input.nextLine();
			System.out.println();
			System.out.println(showCustomer(name, customerLibrary));
			break;
		}
		case(7):
		{
			printAllDVDs(DVDLibrary);
			break;
		}
		case(8):
		{
			System.out.println("You have selected to show the details of a DVD");
			System.out.print("Enter the title of the DVD here: ");
			title = input.nextLine();
			System.out.println();
			
			System.out.println(showDVD(title, DVDLibrary));
			break;
		}
		case(9):
		{
			System.out.println("You have selected to check if a DVD is availible");
			System.out.print("Enter the title of the DVD here: ");
			title = input.nextLine();
			System.out.println();
			
			if (checkDVD(title, DVDLibrary))
				System.out.println(title + " is availible");
			else
				System.out.println(title + " isn't availible");
			
			break;
		}
		case(10):
		{
			System.out.println("You have selected to remove a DVD from the stores Library");
			System.out.print("Enter the title of the DVD here: ");
			title = input.nextLine();
			System.out.println();
			
			removeDVD(title, DVDLibrary);
			break;
		}
		case(11):
		{
			break;
		}
						
	}
	
	
}

			
//------------------------------------------------------------------------------------------End of the user interface
System.out.println();							
System.out.println("The program has ended, the list are being saved...");			
//-------------------------------------------------------------------------------------------write list to file
							
//write a checked out list from the file
	try 
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CheckedOutLibrary.dat"));
			out.writeObject(checkedOutLibrary);
			out.close();
			System.out.println("CheckedOutLibrary saved to file correctly!");
		} 
	catch (IOException e) 
		{
			System.err.println("Err! IOException: " + e.getMessage());
		}
								
//write dvdlist to a file
	try 
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DVDLibrary.dat"));
			out.writeObject(DVDLibrary);
			out.close();
			System.out.println("DVDLibrary Data saved to file correctly!");
		} 
	catch (IOException e) 
		{
			System.err.println("Err! IOException: " + e.getMessage());
		}
				
//write customerlist to a file
	try 
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CustomerLibrary.dat"));
			out.writeObject(customerLibrary);
			out.close();
			System.out.println("CustomerLibrary saved to file correctly!");
		} 
	catch (IOException e) 
		{
			System.err.println("Err! IOException: " + e.getMessage());
		}				
				
	input.close();					
	} //-------------------------------------------------------------------------------------------------end of the main
	
	
//search methods
	
	/**
	 * 
	 * @param title of DVD to search for
	 * @param DVDlibrary within a certain store or library (our project will really only have one library, currently called "temp")
	 * @return Position<DvdType> the position of the DVD
	 * @throws NoSuchElementException if DVD doesn't exist in library
	 */
	public static Position<DvdType> searchForDVD(String title, LinkedPositionalList<DvdType> DVDlibrary)

	{
		Position<DvdType> DVDiterator = DVDlibrary.first();			//Set initial position to point to first position (node) in array
		while (DVDiterator != null) 
		{ 
			 //compare the titles
			if (title.compareTo(DVDiterator.getElement().getTitle()) == 0)	//get title of element in that position
			{
				return DVDiterator;
			}
			//else, go to next element
			DVDiterator = DVDlibrary.after(DVDiterator);			//make iterator the next position 
		 }
		return null;
	}
	
	/**
	 * 
	 * @param full name name of customer to search for
	 * @param Linked Positional List that holds CustomerType Objects CusDatabase
	 * @return the position of the Customer
	 * @throws NoSuchElementException if customer doesn't exist in database
	 */
	public static Position<CustomerType> searchForCustomer(String name, LinkedPositionalList<CustomerType> CusDatabase)

	{
				Position<CustomerType> Customeriterator = CusDatabase.first();			//Set initial position to point to first position (node) in array
				while (Customeriterator != null) 
				{ 
					 //compare the titles
					if (name.compareTo(Customeriterator.getElement().getFullName()) == 0)	//get title of element in that position
					{
						return Customeriterator;
					}
					//else, go to next element
					Customeriterator = CusDatabase.after(Customeriterator);			//make iterator the next position 
				 }
				return null;
	}
	
	/**
	 * 
	 * @param id of checkedOut item 
	 * @param tempCheck
	 * @return Position<CheckedOut> the id of checkedOut item
	 * @throws NoSuchElementException if item doesn't exist 
	 */
	public static Position<CheckedOut> searchForCheckedOut(CustomerType customer, LinkedPositionalList<CheckedOut> tempCheck)
	{
		Position<CheckedOut> checkiterator = tempCheck.first();			//Set initial position to point to first position (node) in array
		while (checkiterator != null) 
		{
			 //compare the titles
			if (customer.getAccountNumber() == checkiterator.getElement().getCustomer().getAccountNumber())	//get title of element in that position
			{
				return checkiterator;
			}
			//else, go to next element
			checkiterator =tempCheck.after(checkiterator);			//make iterator the next position 
		 }
		return null;
}
	
//dvd methods using the searchforDVD method
	
	/**
	 * 
	 * @param title
	 * @param DVDlibrary
	 * @return the details of the DVD
	 */
	public static String showDVD(String title, LinkedPositionalList<DvdType> DVDlibrary)
	{
		Position<DvdType> DVDPosition = searchForDVD(title, DVDlibrary);
		return DVDPosition.getElement().toString() + "\n";
	}
	
	/**
	 * Method returns a boolean to see if a DVD exists within a certain library
	 * @param title
	 * @param DVDlibrary
	 * @return
	 */
	public static boolean checkDVD(String title, LinkedPositionalList<DvdType> DVDlibrary)
	{
		if(searchForDVD(title, DVDlibrary) != null)		//if this method returns a position (not null), evaluates to true
			return true;
		return false;
	}

//customer methods using the searchForCustomer method
	
	/**
	 * 
	 * @param Name of the Customer
	 * @param CustomerLibrary
	 * @return the details of the customer
	 */
	public static String showCustomer(String name, LinkedPositionalList<CustomerType> Customerlibrary)
	{
		Position<CustomerType> CustomerPosition = searchForCustomer(name, Customerlibrary);
		return CustomerPosition.getElement().toString() + "\n";
	}

	/**
	 * Method returns a boolean to see if a name exists within a certain library
	 * @param name
	 * @param DVDlibrary
	 * @return
	 */
	public static boolean checkCustomer(String name, LinkedPositionalList<CustomerType> Customerlibrary)
	{
		if(searchForCustomer(name, Customerlibrary) != null)		//if this method returns a position (not null), evaluates to true
			return true;
		return false;
	}
	
//checkedOut methods using the searchForCheckedOut method
	
	/**
	 * Method returns a boolean to see if a CheckedOut exists within a certain library
	 * @param customer
	 * @param CheckedOutLibrary
	 * @return
	 */
	public static boolean checkCheckedOut(CustomerType customer, LinkedPositionalList<CheckedOut> CheckedOutlibrary)
	{
		if(searchForCheckedOut(customer, CheckedOutlibrary) != null)		//if this method returns a position (not null), evaluates to true
			return true;
		return false;
	}

	/**
	 * 
	 * @param customer
	 * @param CheckedOutLibrary
	 * @return the details of the CheckedOut Object
	 */
	public static String showCheckedOut(CustomerType customer, LinkedPositionalList<CheckedOut> CheckedOutlibrary)
	{
		Position<CheckedOut> CheckedOutPosition = searchForCheckedOut(customer, CheckedOutlibrary);
		return CheckedOutPosition.getElement().toString() + "\n";
	}

//show what dvds someone has checked out based on their name
	
	/**
	 * 
	 * @param name
	 * @param CustomerList
	 * @param CheckedOutList
	 * @return The dvds a customer has checked out
	 */
	public static String showCustomersCheckedOut(String name, LinkedPositionalList<CustomerType> Customerlibrary, LinkedPositionalList<CheckedOut> tempCheck)
		{
			if (!checkCustomer(name, Customerlibrary))
					return name + " isn't a customer.";
			
				Position<CustomerType> CustomerPosition = searchForCustomer(name, Customerlibrary);
				
					if (checkCheckedOut(CustomerPosition.getElement(), tempCheck))
						return showCheckedOut(CustomerPosition.getElement(), tempCheck);
					else
						return name + " hasn't checked anything out";
		}
			
//print list methods

	/**
	 * This method prints out all the DVDs's titles given a positional list
	 * @param DVDlibrary
	 */
	public static void printAllDVDs(LinkedPositionalList<DvdType> DVDlibrary)
	{
		System.out.println("All DVD's in store:");
		//this is an iterator. Set its position to the beginning of the list.
		Position<DvdType> marker = DVDlibrary.first();
		//loop through the whole list and reference each element to obtain methods to get Titles
		while (marker != null) 
		{ 
			 System.out.println(marker.getElement().getTitle());		//print title of DVD
			 marker = DVDlibrary.after(marker); 						//re-initialize marker to next
		 }
		System.out.println();
	}
	
	/**
	 * This method prints out all the customers and the dvds they have rented given a positional list
	 * @param Customerlibrary
	 */
	public static void printAllRentedDVDs(LinkedPositionalList<CheckedOut> CheckedOutlibrary)
	{
		System.out.println("List of customers and their checked out DVDs: \n");
		//this is an iterator. Set its position to the beginning of the list.
		Position<CheckedOut> marker = CheckedOutlibrary.first();
		//loop through the whole list and reference each element to obtain methods to get Titles
		while (marker != null) 
		{ 
			 System.out.println(marker.getElement().toString());		//print title of DVD
			 marker = CheckedOutlibrary.after(marker); 						//re-initialize marker to next
		 }
		System.out.println();
	}
	
	/**
	 * This method prints out all the customers given a positional list
	 * @param Customerlibrary
	 */
	public static void printAllCustomers(LinkedPositionalList<CustomerType> customerLibrary)
	{
		System.out.println("List of all the customers: \n");
		//this is an iterator. Set its position to the beginning of the list.
		Position<CustomerType> marker = customerLibrary.first();
		//loop through the whole list and reference each element to obtain methods to get Titles
		while (marker != null) 
		{ 
			 System.out.println(marker.getElement().toString());		//print title of DVD
			 marker = customerLibrary.after(marker); 						//re-initialize marker to next
		 }
		System.out.println();
	}
	
//remove methods
	
	/**
	 * 
	 * @param title
	 * @param DVDlibrary
	 * @return 
	 */
	public static String removeDVD(String title, LinkedPositionalList<DvdType> DVDlibrary)
	{
		try
		{
			Position<DvdType> DVDPosition = searchForDVD(title, DVDlibrary);	//searching for the title					
			DVDlibrary.remove(DVDPosition);			//remove the Position from that positional list
			return title + " has been removed \n";
		}
		catch (Exception e)
		{
			return "This DVD doesn't exist \n";
		}
	}
	
	/**
	 * 
	 * @param customer
	 * @param tempCust
	 * @return 
	 */
	public static String removeCustomer(String customer, LinkedPositionalList<CustomerType> tempCust)
	{
		try
		{
			Position<CustomerType> custPosition = searchForCustomer(customer, tempCust);	//searching for the title					
			tempCust.remove(custPosition);			//remove the Position from that positional list
			return customer + " has been removed";
		}
		catch (Exception e)
		{
			return "This customer doesn't exist";
		}
}
	
	/**
	 * 
	 * @param title
	 * @param tempCheck
	 * @return 
	 */
	public static String removeCheckedOut(CustomerType customer, LinkedPositionalList<CheckedOut> tempCheck)
	{
		try
		{
			Position<CheckedOut> CheckedOutPosition = searchForCheckedOut(customer, tempCheck);	//searching for the title					
			tempCheck.remove(CheckedOutPosition);			//remove the Position from that positional list
			return customer.getFullName() + " has been removed";
		}
		catch (Exception e)
		{
			return "This checked out DVD doesn't exist";
		}
}
	
//Rent and Return - the search methods and the classes
	
	/**
	 * 
	 * @param customer name - a string
	 * @param dvd title - a string
	 * @param dvdlinkedlist
	 * @param customerlinkedlist
	 * @param checkedoutlinklist
	 * @return void
	 */
	public static void rentDVD(String name, String title, LinkedPositionalList<DvdType> DVDlibrary, LinkedPositionalList<CustomerType> customerLibrary, LinkedPositionalList<CheckedOut> checkedOutLibrary)
	{
		if (checkDVD(title, DVDlibrary) && checkCustomer(name, customerLibrary))
				{
					Position<DvdType> x = searchForDVD(title, DVDlibrary);
					
					
					Position<CustomerType> y = searchForCustomer(name, customerLibrary);
					
					if (checkCheckedOut(y.getElement(), checkedOutLibrary))
					{
						Position<CheckedOut> z = searchForCheckedOut(y.getElement(), checkedOutLibrary);
						
						if (!z.getElement().isFull())
						{
							z.getElement().addDVD(x.getElement());
							x.getElement().checkOut();
							System.out.println("Added " + title + " to the dvds " + name + " has checked out.");
						} else {
							System.out.println(name + " cannot rent anymore dvds");
							
						}
					} else {
						CheckedOut w = new CheckedOut(y.getElement());
						w.addDVD(x.getElement()); 
						checkedOutLibrary.addFirst(w);
						x.getElement().checkOut();
						System.out.println(name + " has checked out " + title);
					}
					
				}
		else if (!checkDVD(title, DVDlibrary))
				System.out.println(title + " is not availible");
		else if (!checkCustomer(name, customerLibrary))
				System.out.println(name + " is not a customer");

		System.out.println();
	}
	
	/**
	 * 
	 * @param customer name - a string
	 * @param dvd title - a string
	 * @param dvdlinkedlist
	 * @param customerlinkedlist
	 * @param checkedoutlinklist
	 * @return void
	 */
	public static void returnDVD(String name, String title, LinkedPositionalList<DvdType> DVDlibrary, LinkedPositionalList<CustomerType> customerLibrary, LinkedPositionalList<CheckedOut> checkedOutLibrary)
	{
		if (checkDVD(title, DVDlibrary) && checkCustomer(name, customerLibrary))
		{
			Position<DvdType> x = searchForDVD(title, DVDlibrary);
			
			Position<CustomerType> y = searchForCustomer(name, customerLibrary);
			
			if (checkCheckedOut(y.getElement(), checkedOutLibrary))
					{
						Position<CheckedOut> z = searchForCheckedOut(y.getElement(), checkedOutLibrary);
						z.getElement().removeDVD(x.getElement());
						
						
						if (z.getElement().isEmpty())
						{
							checkedOutLibrary.remove(z);
							x.getElement().checkIn();
							System.out.println(name + " has no more DVDs after returning " + title);
						} else {
							x.getElement().checkIn();
							System.out.println(name + " returned " + title);
						}
						
					}
		}
		else if (!checkDVD(title, DVDlibrary))
			System.out.println(title + " is not availible");
		else if (!checkCustomer(name, customerLibrary))
			System.out.println(name + " is not a customer");

	System.out.println();
	}
	
		
}


