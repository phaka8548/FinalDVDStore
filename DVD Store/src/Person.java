import java.io.Serializable;

//Superclass to Customer Type
@SuppressWarnings("serial")
public class Person implements Serializable{

	String fullName;
	/**
	 * set the fullname of the person object
	 * @param fullName
	 */
	public Person(String fullName) {
		super();
		this.fullName = fullName;
	}
	
}
