import java.io.Serializable;

//Superclass to Customer Type
public class Person implements Serializable{

	String fullName;

	public Person(String fullName) {
		super();
		this.fullName = fullName;
	}
	
}
