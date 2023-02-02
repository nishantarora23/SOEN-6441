import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract class representing an issue payment.
 * This class contains the cheque number and a cheque counter to keep track of the cheques.
 */
abstract class IssuePayment{
	protected int chequeNumber;
	protected static int chequeCounter = 1; // Counter to keep track of cheque numbers

	/**
	 * Abstract method to display the details of the payment.
	 */
	public abstract void display();

	/**
	 * Constructor for the class IssuePayment.
	 */
	public IssuePayment(){
		chequeNumber = chequeCounter++;
	}
}

/**
 * Abstract class representing an employee.
 * This class extends the IssuePayment class and contains information of the employee.
 */
abstract class Employee extends IssuePayment{

	private String firstName;
	private String lastName;
	private int age;
	private int id;

	/**
	 * Gets the first name of the employee.
	 * @return the first name of the employee
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the employee.
	 * @param firstName the first name to set for the employee
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the employee.
	 * @return the last name of the employee
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the employee.
	 * @param lastName the last name to set for the employee
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the age of the employee.
	 * @return the age of the employee
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age of the employee.
	 * @param age the age to set for the employee
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the id of the employee.
	 * @return the id of the employee
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the employee.
	 * @param id the id to set for the employee
	 */
	public void setId(int id) {
		this.id = id;
	}
}

/**
 * Class representing a part-time employee.
 * This class extends the Employee class and contains the echelon and number of hours worked by the employee.
 */
class PartTimeEmployee extends Employee{

	/**
	 * Constructor to initialize the instance variables of the class
	 * 
	 * @param firstName The first name of the part-time employee
	 * @param lastName The last name of the part-time employee
	 * @param age The age of the part-time employee
	 * @param id The id of the part-time employee
	 * @param echelon The echelon of the part-time employee
	 * @param noOfHours The number of hours worked by the part-time employee
	 */
	public PartTimeEmployee(String firstName, String lastName, int age, int id, int echelon, int noOfHours) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setAge(age);
		super.setId(id);
		this.echelon = echelon;
		this.noOfHours = noOfHours;
	}

	private static int echelon;
	private static int noOfHours;

	/**
	 * Method to calculate the salary of the part-time employee
	 * @return The salary of the part-time employee
	 */
	public static double getSalary() {
		if(echelon==1) {
			return 15*noOfHours;
		}
		else if(echelon==2) {
			return 20*noOfHours;
		}
		else if(echelon==3) {
			return 25*noOfHours;
		}
		else if(echelon==4) {
			return 30*noOfHours;
		}
		else if(echelon==5) {
			return 40*noOfHours;
		}
		else {
			return 0;
		}
	}

	/**
	 * Method to display the details of the part-time employee's payment
	 * It displays the cheque number, payable to and amount
	 */
	@Override
	public void display() {
		System.out.println("Cheque Number: " + chequeNumber);
		System.out.println("Payable To: " + getFirstName()+" "+getLastName());
		System.out.println("Amount: " + getSalary() +" CAD");		
	}
}

/**
 * The FullTimeEmployee class extends the Employee class and represents a full-time employee.
 * 
 */
class FullTimeEmployee extends Employee{
	private double fixedSalary;

	/**
	 * Constructor to create a new FullTimeEmployee object.
	 * 
	 * @param firstName The first name of the employee.
	 * @param lastName The last name of the employee.
	 * @param age The age of the employee.
	 * @param id The id of the employee.
	 * @param fixedSalary The fixed salary of the full-time employee.
	 */
	public FullTimeEmployee(String firstName, String lastName, int age, int id, double fixedSalary) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setAge(age);
		super.setId(id);
		this.fixedSalary = fixedSalary;
	}

	/**
	 * Overridden method to display the full-time employee's payment information.
	 */
	@Override
	public void display() {
		System.out.println("Cheque Number: " + chequeNumber);
		System.out.println("Payable To: " + getFirstName()+" "+getLastName());
		System.out.println("Amount: " + this.fixedSalary +" CAD");
	}
}

/**
 * The Bill class extends the IssuePayment class and represents a bill to be paid.
 * 
 */
class Bill extends IssuePayment{

	/**
	 * Constructor to create a new Bill object.
	 * 
	 * @param companyName The name of the company to pay the bill to.
	 * @param dueAmount The due amount of the bill.
	 */
	public Bill(String companyName, double dueAmount) {
		this.companyName = companyName;
		this.dueAmount = dueAmount;
		//this.dueDate = dueDate;
	}
	private String companyName;
	private double dueAmount;
	//private Calendar dueDate;

	/**
	 * Overridden method to display the bill's payment information.
	 */
	@Override
	public void display() {
		System.out.println("Cheque Number: " + chequeNumber);
		System.out.println("Payable To: " + companyName);
		System.out.println("Amount: " + dueAmount +" CAD");
	}

}

/**
 * AccountsPayable class manages the employee and bill objects and implements 
 * methods to add an employee, add a bill, issue pay cheques and display menu
 */
public class AccountsPayable{

	// List to store employee and bill objects
	ArrayList<IssuePayment> employeeBills = new ArrayList<IssuePayment>();

	private Scanner sc = new Scanner(System.in);

	/**
	 * Method to add a part-time employee to the employeeBills list
	 */
	public void addPartTimeEmployee() {
		System.out.println("\nEnter Employee details");
		System.out.print("First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Age: ");
		int age = sc.nextInt();
		System.out.print("ID: ");
		int id = sc.nextInt();
		System.out.print("Echelon (1-5): ");
		int echelon = sc.nextInt();
		System.out.print("Part-time hours: ");
		int noOfHours = sc.nextInt();
		employeeBills.add(new PartTimeEmployee(firstName,lastName,age,id,echelon,noOfHours));
	}

	/**
	 * Method to add a full-time employee to the employeeBills list
	 */
	public void addFullTimeEmployee() {
		System.out.println("\nEnter Employee details");
		System.out.print("First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Age: ");
		int age = sc.nextInt();
		System.out.print("ID: ");
		int id = sc.nextInt();
		System.out.print("Salary (per month): ");
		double salary = sc.nextDouble();
		employeeBills.add(new FullTimeEmployee(firstName,lastName,age,id, salary*12));
		sc.nextLine();
	}
	
	public void addEmployee() {
		int choice = 0;
		while (choice != 3) {
			System.out.println("\nChoose the Employee Type");
			System.out.println("1. Full-time");
			System.out.println("2. Part-time");
			System.out.println("3. Return to the main menu");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addFullTimeEmployee();
				break;
			case 2:
				addPartTimeEmployee();
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid choice.");
			}
		}
	}
	
	/**
	 * Method to add a bill to the employeeBills list
	 */
	public void addBill() {
		System.out.println("\nEnter Bill Details");
		System.out.print("Company name: ");
		String name = sc.nextLine();
		System.out.print("Due Amount: ");
		double amount = sc.nextDouble();
		employeeBills.add(new Bill(name, amount));
		sc.nextLine();
	}

	/**
	 * Method to issue pay cheques to employees and bills
	 */
	public void issuePayCheques() {
		for (IssuePayment employeeBill : employeeBills) {
			employeeBill.display();
			System.out.println();
		}
	}

	/**
	 * Method to display the menu and handle user input
	 */
	public void displayMenu() {
		int choice = 0;
		System.out.println("\nWelcome to Application");
		while (choice != 4) {
			System.out.println("\n1. Add an employee");
			System.out.println("2. Add a bill");
			System.out.println("3. Issue cheques");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				addBill();
				break;
			case 3:
				issuePayCheques();
				break;
			case 4:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid choice.");
			}
		}
	}
	public static void main(String[] args) {
		AccountsPayable payments = new AccountsPayable();
		payments.displayMenu();
	}
}