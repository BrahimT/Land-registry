package cst8284.asgment1.landRegistry;

/* 
 * Course Name:CST8284
 * Student Name:Brahim Toure
 * Class name: RegControl
 * Date:6/22/2020
*/
public class RegControl {
	private Registrant[] registrants = new Registrant[3];
	private Property[] properties = new Property[5];
	private int lastRegistrationIndex = 0;
	private int lastPropertyIndex = 0;

	private Registrant[] getRegistrants() {
		return registrants;

	}

	private Property[] getProperties() {
		return properties;

	}

	private int getLastRegistrationIndex() {
		return lastRegistrationIndex;

	}

	private int getLastPropertyIndex() {
		return lastPropertyIndex;

	}

	public Registrant addNewRegistrant(Registrant reg) {
		if (getLastRegistrationIndex() >= getRegistrants().length) {
			return null;
		}
		getRegistrants()[getLastRegistrationIndex()] = reg;
		lastRegistrationIndex++;
		return reg;

	}

	public Registrant findRegistrant(int regNum) {
		for (int i = 0; i < getLastRegistrationIndex(); i++) {
			if (getRegistrants()[i].getRegNum() == regNum) {
				return getRegistrants()[i];
			}
		}
		return null;
	}

	public Registrant[] listOfRegistrants() {
		Registrant[] r = new Registrant[getLastRegistrationIndex()];
		for (int i = 0; i < getLastRegistrationIndex(); i++) {
			r[i] = getRegistrants()[i];
		}
		return r;
	}

	public Property addNewProperty(Property prop) {
		if (getLastPropertyIndex() >= getProperties().length) {
			return null;
		}
		Property existingProperty = propertyOverlaps(prop);
		if (existingProperty != null) {
			return existingProperty;
		}
		getProperties()[getLastPropertyIndex()] = prop;
		lastPropertyIndex++;
		return prop;

	}

	public Property changePropertyRegistrant(Property originalProperty, int newRegNum) {
		Registrant r = findRegistrant(newRegNum);
		if (r == null) {
			return null;
		}
		
		for (int i = 0; i < getLastPropertyIndex(); i++) {
			if (getProperties()[i].getRegNum() == originalProperty.getRegNum()) {
				getProperties()[i].setRegNum(newRegNum);
			}
		}
		return originalProperty;

	}

	public Property[] listOfProperties(int regNum) {
		int count = 0;
		for (int i = 0; i < getLastPropertyIndex(); i++) {
			if (getProperties()[i].getRegNum() == regNum) {
				count++;
			}
		}
		Property[] p = new Property[count];
		for (int i = 0; i < p.length; i++) {
			if (getProperties()[i].getRegNum() == regNum) {
				p[i] = getProperties()[i];
			}
		}
		return p;
	}

	public Property[] listAllOfProperties() {
		Property[] p = new Property[getLastPropertyIndex()];
		for (int i = 0; i < getLastPropertyIndex(); i++) {
			p[i] = getProperties()[i];
		}
		return p;
	}

	private Property propertyOverlaps(Property prop) {
		for (int i = 0; i < getLastPropertyIndex(); i++) {
			if (getProperties()[i].overLaps(prop)) {
				return getProperties()[i];
			}
		}
		return null;
	}

}
