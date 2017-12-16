import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DriverDvdStore {



	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
			
//read dvdlist from a file
		
						ObjectInputStream DVDIn = new ObjectInputStream(new FileInputStream("DVDLibrary.dat"));
						@SuppressWarnings("unchecked")
						LinkedPositionalList<DvdType> DVDLibrary = (LinkedPositionalList<DvdType>) DVDIn.readObject();
						System.out.println("The DVD List");
						printAllDVDs(DVDLibrary);
		
//read customer list from a file
		
						ObjectInputStream CustomerIn = new ObjectInputStream(new FileInputStream("CustomerLibrary.dat"));
						@SuppressWarnings("unchecked")
						LinkedPositionalList<CustomerType> customerLibrary = (LinkedPositionalList<CustomerType>) CustomerIn.readObject();
						System.out.println("The Customer List");
						printAllCustomers(customerLibrary);
	
//read checkedout list from the file
			
						ObjectInputStream CheckedOutIn = new ObjectInputStream(new FileInputStream("CheckedOutLibrary.dat"));
						@SuppressWarnings("unchecked")
						LinkedPositionalList<CheckedOut> CheckedOutLibrary = (LinkedPositionalList<CheckedOut>) CheckedOutIn.readObject();
						System.out.println("The list of checked out DVDs");
						printAllRentedDVDs(CheckedOutLibrary);

//enter the code for the menu system here
						
						
						
			
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
//end the menu system here
						
						

							
//write a checked out list from the file
				try 
					{
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CheckedOutLibrary.dat"));
						out.writeObject(CheckedOutLibrary);
						out.close();
						System.out.println("CheckedOutLibrary saved to file correctly");
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
						System.out.println("DVDLibrary Data saved to file correctly");
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
						System.out.println("CustomerLibrary saved to file correctly");
					} 
				catch (IOException e) 
					{
						System.err.println("Err! IOException: " + e.getMessage());
					}				
				
						
	}
	
	
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
	
	
	
//Saving DVD to a file using Object Serialization--------------------
	/**
	* Create a file
		* @throws IOException 
	*/
	public static ObjectOutputStream createDVDOutputFile(String fileName) throws IOException
		{
			ObjectOutputStream DVDOutFile = new ObjectOutputStream(new FileOutputStream(fileName)); //writes a data file
			return DVDOutFile;
		}
		
	/**
	 * @param DVDType that implements serializable
	 * This method writes a stream of a serialized task object into a file
		*/
	public static void saveDVDToFile(DvdType DVD1, ObjectOutputStream out) //pass in the objectOutputStream
		{
			try {
				out.writeObject(DVD1);						//an object can write itself to a stream of bytes using ObjectOutputStream 
				System.out.println("Data was saved to file correctly");
			} catch (IOException e) {
				System.err.println("Caught IOException: " + e.getMessage());
			}
		}
		
	/** 
	 * @param DVDlibrary a positional list that holds all the DVDs
	 * Writes all the DVDs to a file using serialization
	 * @throws IOException 
	 */
	public static void saveDVDListToFile(LinkedPositionalList<DvdType> DVDlibrary, String fileName) throws IOException
		{
			Position<DvdType> DVDiterator = DVDlibrary.first();			//Set initial position to point to first position (node) in array
			ObjectOutputStream DVDOut = createDVDOutputFile(fileName);			//create one output file for the entire list
			while (DVDiterator != null) 
			{ 
				saveDVDToFile(DVDiterator.getElement(), DVDOut);		//save each individual DVD from list to file
				//go to next element
				DVDiterator = DVDlibrary.after(DVDiterator);			//make iterator the next position 
			 }
			DVDOut.close();		//after putting all the objects in the list, close the file
		}
		
//Saving a DVD from a text file using serialization---------------------
	/**
	* Create a file
    * @throws IOException 
	*/
	public static ObjectInputStream createDVDInputFile(String fileName) throws IOException
		{
			ObjectInputStream DVDInFile = new ObjectInputStream(new FileInputStream(fileName)); //writes a data file
			return DVDInFile;
		}
		
		
//add to an existing positional list
	/**
	 * @throws IOException 
	 * 
	 */
		@SuppressWarnings("unchecked")
	public static LinkedPositionalList<DvdType> readDVDListFromFile(String fileName) throws IOException, ClassNotFoundException
		{
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
				LinkedPositionalList<DvdType> newDVDList = (LinkedPositionalList<DvdType>) in.readObject();
				return newDVDList;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
}
