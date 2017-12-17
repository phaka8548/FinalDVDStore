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
	/**
	 * 
	 * @param title
	 * @param stars
	 * @param producer
	 * @param director
	 * @param proCompany
	 * @param copies
	 */
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
	/**
	 * @return title of dvd type
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set title of dvd type
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return array of stars in dvd type
	 */
	public String[] getStars() {
		return stars;
	}

	/**
	 * set stars of star array
	 * @param stars
	 */
	public void setStars(String[] stars) {
		this.stars = stars;
	}

	/**
	 * @return the producer of dvd type
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * set producer of dvd type
	 * @param producer 
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * @return director of dvd type
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * set director of dvd type
	 * @param director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return producing company of dvd type
	 */
	public String getProCompany() {
		return proCompany;
	}

	/**
	 * set producing company of dvd type
	 * @param proCompany
	 */
	public void setProCompany(String proCompany) {
		this.proCompany = proCompany;
	}

	/**
	 * @return number of copies of dvd type
	 */
	public int getCopies() {
		return copies;
	}

	/**
	 * set number of copies of dvd type
	 * @param copies
	 */
	public void setCopies(int copies) {
		this.copies = copies;
	}
	
	
	//functional methods
	/**
	 * decrement the number of copies when checking out, and warn user if dvd is not in list
	 */
	public void checkOut() {
		if (dvdAvailible(this))
			copies--;
		else
			System.out.print("This dvd is not availible");
	}
	/**
	 * increment number of copies of dvd type
	 */
	public void checkIn() {
		copies++;
	}
	/**
	 * 
	 * @param dvd
	 * @return true if dvd is available
	 */
	public boolean dvdAvailible(DvdType dvd) 
	{
		boolean availible = true; 
		if (copies == 0)
			availible = false;
		
		return availible;	
	}
}
