import java.io.Serializable;

//Customer Type is a subclass of Person Class
@SuppressWarnings("serial")
public class CustomerType extends Person implements Serializable{

	private String fullName;
	private int accountNumber;
	private String email;
	//private DvdType[] rentedDVDs = {null, null, null, null, null};	//Customers can only rent 5 DVD's at a time
	private static int numCustomers = 1;

	//constructor
	/**
	 * 
	 * @param fullName
	 * @param accountNumber
	 * @param email
	 */
	public CustomerType(String fullName, int accountNumber, String email) {
		super(fullName);	//inherited from superclass
		this.fullName = fullName;
		this.accountNumber = accountNumber;
		this.email = email;
		numCustomers++;
	}
	

	//tostring
	
	public CustomerType(String fullName) {
		super(fullName);
		// TODO Auto-generated constructor stub
	}


	@Override
	/**
	 * Override to string to print Customer information
	 */
	public String toString() {
		 
		//String rentedDVDList = printRentedDVDs();
		return "Name: " + fullName + "\nAccount Number: " + accountNumber + "\nemail: " + email + "\n";
	}
		
	//setters and getters
	/**
	 * 
	 * @return number of customers
	 */
	public static int getNumCustomers() {
		return numCustomers;
	}
	/**
	 * 
	 * @return fullname of customer
	 */
	public String getFullName() {
		return fullName;
	}
    /**
     * 
     * @param fullName 
     */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 
	 * @return account number of customer
	 */
	public double getAccountNumber() {
		return accountNumber;
	}
	/**
	 * 
	 * @param accountNumber
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * 
	 * @return email of customer
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

}