package cst8284.asgment1.landRegistry;

/* 
 * Course Name:CST8284
 * Student Name:Brahim Toure
 * Class name: Registrant
 * Date:6/22/2020
*/
public class Registrant {
	private static final int REGNUM_START = 1000;
	private static int currentRegnum = REGNUM_START;
	private int regNum; 
	private String firstName;
	private String lastName;

	public Registrant() {
		this("unknown unknwon");
	}

	public Registrant(String firstNamelastName) {
		String[] name = firstNamelastName.split(" ");
		setFirstName(name[0]);
		setLastName(name[1]);
		regNum = currentRegnum;
		incrToNextRegnum();
	}

	public int getRegNum() {
		return regNum;
	}

	private static void incrToNextRegnum() {
		currentRegnum++;
	}

	public String getFirstName() {
		return firstName;

	}

	public String getLastName() {
		return lastName;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Registrant)) {
			return false;
		}
		Registrant s = (Registrant) obj;
		return getFirstName().equals(s.getFirstName()) && getLastName().equals(s.getLastName())
				&& getRegNum() == s.getRegNum();
	}

	@Override
	public String toString() {
		return "Name:" + getFirstName().trim() + " " + getLastName() + "\nRegistration Number: #" + getRegNum();

	}

}
