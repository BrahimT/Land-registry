package cst8284.asgment1.landRegistry;

import java.util.Scanner;

/* 
 * Course Name:CST8284
 * Student Name:Brahim Toure
 * Class name: RegView
 * Date:6/22/2020
*/
public class RegView {
	private static Scanner scan = new Scanner(System.in);
	private static RegControl rc; 
	private static final int ADD_NEW_REGISTRANT = 1, FIND_REGISTRANT = 2, LIST_REGISTRANTS = 3, ADD_NEW_PROPERTY = 4,
			CHANGE_PROPERTY_REGISTRANT = 5, LIST_PROPERTY_BY_REGNUM = 6, LIST_ALL_PROPERTIES = 7, EXIT = 0;

	public RegView() {
		rc = new RegControl();
	}

	private static RegControl getRegControl() { 
		return rc;
	}

	public static void launch() {
		int choice = 0;
		do {
			choice = displayMenu();
			executeMenuItem(choice);
		} while (choice != EXIT);
	}

	private static int displayMenu() {
		// taken from assignment 1 previous semester by professor David Houtman
		System.out.println("Enter a selection from the following menu:");
		System.out.println(ADD_NEW_REGISTRANT + ".Enter a registrant \n" + FIND_REGISTRANT
				+ ".Find registrant by registration number\n" + LIST_REGISTRANTS + ".Display list of registrants \n"
				+ ADD_NEW_PROPERTY + ".Register a new property \n" + CHANGE_PROPERTY_REGISTRANT
				+ ".change property's registrant \n" + LIST_PROPERTY_BY_REGNUM
				+ ".Display all properties with the same registration number \n" +

				LIST_ALL_PROPERTIES + ".Display all registered properties \n" + EXIT + ". Exit program");
		String a = scan.next();
		scan.nextLine(); // 'eat' the next line in the buffer
		System.out.println();
		int choice = EXIT;
			choice = Integer.parseInt(a);
	
		return choice;
	}
	

	private static void executeMenuItem(int choice) {
		switch (choice) {
		case ADD_NEW_REGISTRANT:
			viewAddNewRegistrant();
			break;
		case FIND_REGISTRANT:
			viewFindRegistrant();
			break;
		case LIST_REGISTRANTS:
			viewListOfRegistrants();
			break;
		case ADD_NEW_PROPERTY:
			viewAddNewProperty();
			break;
		case CHANGE_PROPERTY_REGISTRANT:
			viewChangePropertyRegistrant();
			break;
		case LIST_PROPERTY_BY_REGNUM:
			viewListPropertyByRegNum();
			break;
		case LIST_ALL_PROPERTIES:
			viewListAllProperties();
			break;

		case EXIT:
			System.out.println("Exiting  program\n\n");
			break;
		default:
			System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();
	}

	private static String getResponseTo(String s) {
		System.out.print(s);
		return (scan.nextLine());
	}

	private static int requestRegNum() {
		String regNumber = getResponseTo("Enter registration number : ");
		return Integer.parseInt(regNumber);
	}

	private static Property makeNewPropertyFromUserInput() {
		int number = requestRegNum();
		String[] coordinatesStr = getResponseTo("Enter top and left coordinates of property (as X, Y): ").split(",");
		String[] propertyStr = getResponseTo("Enter length and width of property (as length, width): ").split(",");

		int[] coordinates = new int[] { Integer.parseInt(coordinatesStr[0].trim()),
				Integer.parseInt(coordinatesStr[1].trim()) };
		int[] propertyLengthWidth = new int[] { Integer.parseInt(propertyStr[0].trim()),
				Integer.parseInt(propertyStr[1].trim()) };

		if (propertyLengthWidth[0] < 20 || propertyLengthWidth[1] < 10 || coordinates[0] < 0 || coordinates[1] < 0
				|| coordinates[0] > 1000 || coordinates[1] > 1000) {
			return null;
		}

		Property property = new Property();
		property.setXLength(propertyLengthWidth[0]);
		property.setYWidth(propertyLengthWidth[1]);
		property.setXleft(coordinates[0]);
		property.setYTop(coordinates[1]);
		property.setRegNum(number);
		return (property);
	}

	private static Registrant makeNewRegistrantFromUserInput() {
		String fullName = getResponseTo("Enter registrant's first and last name: ");
		return (new Registrant(fullName));

	}

	public static void viewAddNewRegistrant() {
		Registrant added = getRegControl().addNewRegistrant(makeNewRegistrantFromUserInput());
		if (added != null) {
			System.out.println("Registrant added:\n" + added.toString());
		} else {
			System.out.println("Could not add new registrant; registrant array is full.");
		}
	}

	public static void viewFindRegistrant() {
		getRegControl().findRegistrant(requestRegNum());
	}

	public static void viewListOfRegistrants() {
		Registrant[] registrants = getRegControl().listOfRegistrants();
		if (registrants.length == 0) {
			System.out.println("No Registrants loaded yet");
		} else {
			System.out.println("List of registrants:\n");
			for (Registrant registrant : registrants) {
				System.out.println(registrant.toString());
			}
		}

	}

	public static void viewAddNewProperty() {
		Property newProperty = makeNewPropertyFromUserInput();
		Property existingProperty = getRegControl().addNewProperty(newProperty);
		if (existingProperty == null) {
			// full
		} else if (newProperty == existingProperty) {
			System.out.println("New property has been registered as:\n" + newProperty.toString());
		} else { // overlap
			System.out.println("New property could not be registered; There is already a property registered at:\n"
					+ existingProperty.toString());
		}
	}

	public static void viewChangePropertyRegistrant() {
		int oldRegNum = Integer.parseInt(getResponseTo("Enter original registrants number: "));
		int newRegNum = Integer.parseInt(getResponseTo("Enter new registrants number: "));

		Property[] properties = getRegControl().listOfProperties(oldRegNum);
		if (properties.length == 0) {
			System.out.println("No property found with registration number #" + oldRegNum);
			return;
		}

		Property p = getRegControl().changePropertyRegistrant(properties[0], newRegNum);

		if (p != null) {
			System.out.println("Operation completed; the new registration number, " + newRegNum + ", has replaced "
					+ oldRegNum + " in all affected properties.");
		}
	}

	public static void viewListPropertyByRegNum() {
		Property[] properties = getRegControl().listOfProperties(requestRegNum());
		if (properties.length == 0) {
			System.out.println("Property Registry empty; no properties to display");
		} else {
			for (Property property : properties) {
				System.out.println(property.toString());
			}
		}
	}

	public static void viewListAllProperties() {
		Property[] properties = getRegControl().listAllOfProperties();
		if (properties.length == 0) {
			System.out.println("Property Registry empty; no properties to display");
		} else {
			for (Property property : properties) {
				System.out.println("\n" + property.toString());
			}
		}
	}

}
