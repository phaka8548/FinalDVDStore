import java.io.Serializable;

//Superclass to Customer Type
@SuppressWarnings("serial")
public class Person implements Serializable{

	String fullName;

	public Person(String fullName) {
		super();
		this.fullName = fullName;
	}
	
}
