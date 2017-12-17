import java.io.Serializable;

/*object that holds an account number and the DVD's associated with that account #
essentially a checkedOut object is a piece of paper that holds the account # and DVD associated with that account
and in the checkout positional list - grab the DVD that was checked and the account # that holds it
before adding object to a list, search through list, find ID number, and add that DVD to that ID*/

@SuppressWarnings("serial")
public class CheckedOut implements Serializable{			

	private CustomerType customer;				
	private DvdType[] DVDs = {null, null, null, null, null};	
	private int lastIndex = -1;

	//constructors
	
	public CheckedOut(CustomerType customer) {
		super();
		this.customer = customer;
	}

	public CheckedOut() {}
	
	//tostring
	
	@Override
	public String toString() {
		return "Name: " + customer.getFullName() + "\nAccount Number: " + customer.getAccountNumber() + "\nRented DVDs" + printRentedDVDs();
	}
	
	//setters and getters
	
	public CustomerType getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerType customer) {
		this.customer = customer;
	}

	public DvdType[] getDVDs() {
		return DVDs;
	}

	public void setDVDs(DvdType[] dVDs) {
		DVDs = dVDs;
	}

	//functional methods
	
	
	/**
	 * adds a DVD into a checkedOutObject
	 * @param outDVD the DVD the person is checking out
	 */
	public void addDVD(DvdType DVD)			//This object will essentially hold a list of DVDs
	{
		for(int lcv = 0; lcv < this.DVDs.length; lcv++)
		{   
            if (this.DVDs[lcv] == null)		//find an empty spot
            {
            	lastIndex++;
            	this.DVDs[lcv] = DVD;		//add DVD to the list
            	break;
            }       
        } 
		
	}
	
	/**
	 * removes a DVD from the list
	 * @param inDVD
	 */
	public void removeDVD(DvdType DVD)
	{
		int nullSpot = 0;
		
		for (int lcv = 0; lcv < DVDs.length; lcv++)
		{
			if(this.DVDs[lcv].equals(DVD))			
				{
				 this.DVDs[lcv] = null;
				 nullSpot = lcv;
				 break;
				}
			
		}
			DVDs[nullSpot] = DVDs[lastIndex];
			lastIndex--;
		
	}
	
	
	public boolean isEmpty()
	{
		if (this.getDVDs()[0] == null)
			return true;
		return false;
	}


	public boolean isFull()
	{
		if (!(this.getDVDs()[4] == null))
			return true;
		return false;
	}
	
	//Print a list of their rented DVDs
	public String printRentedDVDs()
	{
		String rentedDVDTitles = "";
		for (int i = 0; i < this.DVDs.length ; i++)
		{
			if (DVDs[i] != null)		//check for nulls to avoid the null pointer error
			{
				rentedDVDTitles += "\n" + DVDs[i].getTitle();
			}
			else
				continue;
		}
		return rentedDVDTitles;
		
	}
}
