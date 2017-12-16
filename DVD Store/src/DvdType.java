import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial")
public class DvdType implements Serializable{
	
	private String title;
	private String[] stars;
	private String producer;
	private String director;
	private String proCompany;
	private int copies;
	
	//constructors
	
	public DvdType(String title, String[] stars, String producer, String director, String proCompany, int copies) {
		super();
		this.title = title;
		this.stars = stars;
		this.producer = producer;
		this.director = director;
		this.proCompany = proCompany;
		this.copies = copies;
	}

	public DvdType() {}

	//tostring
	
	@Override
	public String toString() {
		return "Title: " + title + "\nStars: " + Arrays.toString(stars) + "\nProducer: " + producer
				+ "\nDirector: " + director + "\nProduction Company: " + proCompany + "\nNumber of Copies in the Store: " + copies + "\n";
	}

	//setters and getters

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String[] getStars() {
		return stars;
	}


	public void setStars(String[] stars) {
		this.stars = stars;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getProCompany() {
		return proCompany;
	}


	public void setProCompany(String proCompany) {
		this.proCompany = proCompany;
	}


	public int getCopies() {
		return copies;
	}


	public void setCopies(int copies) {
		this.copies = copies;
	}
	
	
	//functional methods
	
	public void checkOut() {
		if (dvdAvailible(this))
			copies--;
		else
			System.out.print("This dvd is not availible");
	}
	
	public void checkIn() {
		copies++;
	}
	
	public boolean dvdAvailible(DvdType dvd) {
		boolean availible = true; 
		if (copies == 0)
			availible = false;
		
		return availible;	
	}

}