import java.util.Scanner;


//Week-4 Class-1 User
//class User {// old User class without inheritance
/*
    class User {
        String username;
        String password;
        String email;
        double balance;

        User(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.balance = 0.0;
        }
    }
*/

/*---------------------------------------------------------------------------------------------------------*/
//Week-5 Updated User Class with Encapsulation
/*class User {

    private String username;
    private String password;
    private String email;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername()
    {
         return username; 
    }
    public void setUsername(String username)
    { 
        this.username = username;
    }

    public String getPassword()
    { 
        return password; 
    }
    public void setPassword(String password)
    { 
        this.password = password; 
    }

    public String getEmail() 
    { 
        return email; 
    }

    public void setEmail(String email) 
    { 
        this.email = email; 
    }
}*/
/*---------------------------------------------------------------------------------------------------------*/
// New BankAccount class for encapsulation and user linking
/*class BankAccount {
    
    private static int accountCounter = 100; // Starting account number
    private int accountNumber;
    private double balance;

    public BankAccount() {
        this.accountNumber = ++accountCounter;

        this.balance = 0.0;
    }

    public int getAccountNumber() 
    { 
        return accountNumber; 
    }
    public double getBalance() 
    { 
        return balance; 
    }
    public void setBalance(double balance) 
    { 
        this.balance = balance; 
    }

}*/

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

// Week 7: Interfaces
interface ILogin {
    boolean login(String username, String password);
}

interface ITransaction {
    void deposit(double amount);
    void withdraw(double amount);
}

// Week 7: Loan class
class Loan {
    private double principal;
    private double interestRate;
    private int termMonths;
    private double monthlyPayment;

    public Loan(double principal, double interestRate, int termMonths) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        calculateMonthlyPayment();
    }

    private void calculateMonthlyPayment() {
        double monthlyRate = (interestRate / 100) / 12;
        if (monthlyRate == 0) {
            monthlyPayment = principal / termMonths;
        } else {
            monthlyPayment = (principal * monthlyRate) /
                    (1 - Math.pow(1 + monthlyRate, -termMonths));
        }
    }

    public double getMonthlyPayment() { return monthlyPayment; }
    public double getTotalPayment() { return monthlyPayment * termMonths; }

    public void displayLoanDetails() {
        System.out.println("\n======= Loan Details =======");
        System.out.println("Principal Amount: " + principal);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Term(Loan Duration): " + termMonths + " months");
        System.out.println("Monthly Payment: " + String.format("%.2f", monthlyPayment));
        System.out.println("Total Payment: " + String.format("%.2f", getTotalPayment()));
        System.out.println("============================");
    }
}

//Week-6 Part
class Person{
    protected String name;
    protected String email;
}

//Updated User class that extends Person & implements ILogin...
class User extends Person implements ILogin {

    private String username;
    private String password;
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;   // inherited from Person
        this.password = password;
    }

    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }

    // Overridden getter/setter for inherited email
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

/*---------------------------------------------------------------------------------------------------------*/
//class BankAccount { ... }   // old code is above commented (Week-5)

//Abstract Account class with subclasses
abstract class Account implements ITransaction{
    private static int accountCounter = 100; 
    protected int accountNumber;
    protected double balance;

    public Account() {
        this.accountNumber = ++accountCounter;
        this.balance = 0.0;
    }

    public int getAccountNumber() 
    { 
        return accountNumber; 
    }
    public double getBalance() 
    { 
        return balance; 
    }
    public void deposit(double amount) 
    { 
        if (amount <= 0) {
            System.out.println("Incorrect Amount to Deposit!");
        } else {
            balance += amount; 
            System.out.println("You have Deposited: " + amount);
            
        }
        
    }

    //Abstract method for withdrawal
    public abstract void withdraw(double amount);
}

//SavingAccount subclass with rule: balance must stay >= 500
class SavingAccount extends Account {
    //@Override
    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= 500) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance! Savings account must keep at least Rs.500.");
        }
    }
}

//CurrentAccount subclass with overdraft allowed up to -1000
class CurrentAccount extends Account {
    //@Override
    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= -1000) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded! You cannot withdraw this amount.");
        }
    }
}
/*---------------------------------------------------------------------------------------------------------*/
//Main Class BankManagementSystem...
 class BankManagementSystem {

    // Declarations of whole code...
    int s = 100;
    int n = 0;
    // String[] usernames = new String[s];
    // String[] emails = new String[s];
    // String[] passwords = new String[s];
    // double[] balances = new double[s];

    //Object Declaration from User class...
    User[] users = new User[s];

    // New: Array for BankAccount objects(Week-5)
    // BankAccount[] accounts = new BankAccount[s];

    //Replace with polymorphic Account array(Week-6)

    Account[] accounts = new Account[s];
    Scanner sc = new Scanner(System.in);

/*---------------------------------------------------------------------------------------------------------*/
    // MAIN METHOD...
    public static void main(String[] args) {

        BankManagementSystem obj_getdata = new BankManagementSystem();
        obj_getdata.getdata();
    }
/*=========================================================================================================*/
    // Week 3: Validation Part
    boolean isValidPassword(String password) {

        if (password.length() < 8) {
            return false;
        }
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (int i = 0; i < password.length(); i = i + 1) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    boolean isValidEmail(String email) {
    email = email.trim();


    if (!email.contains("@") || !email.contains(".")) {
        return false;
    }


    String[] parts = email.split("@");
    if (parts.length != 2) {
        return false; 
    }

    String fpart = parts[0];
    String spart = parts[1];

    if (fpart.isEmpty() || spart.isEmpty()) {
        return false;
    }

    String[] domain = {"gmail.com", "yahoo.com", "icloud.com"};
    boolean validDomain = false;
for (int i = 0; i < domain.length; i++) {
    if (spart.equalsIgnoreCase(domain[i])) {
        validDomain = true;
        break;
    }
}

    if (!validDomain) {
        return false; 
    }


    if (!spart.matches("^[A-Za-z0-9._-]+$")) {
        return false;
    }

    return true;
}
/*=========================================================================================================*/
    // Method 1 getdata...
    void getdata() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= MENU =========");
            System.out.println("Press 1 to Register User");
            System.out.println("Press 2 to Login User");
            System.out.println("Press 3 to Exit");
            System.out.println("========================");
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:// call to registerUser() Method
                    registerUser();
                    break;
                case 2:// call to loginUser() Method if the user registered already...
                    if (n == 0) {
                        System.out.println("No users registered yet. Please register first.");
                        System.out.println("========Register========"); // User must register before log-in.
                        registerUser();
                    }
                    loginUser();
                    break;
                case 3:// Exit from Code...
                    exit = true;
                    System.out.println("\nThank you for using the system!");
                    break;
                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }

/*---------------------------------------------------------------------------------------------------------*/
    // Method-2 registeruser...
    void registerUser() {
        if (n >= s) {
            System.out.println("User registration limit reached. No more users can register.");
            return;
        }
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (username == "") {
            System.out.println("Please enter Username to Register!");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (users[i].getUsername().equals(username)) {
                System.out.println("\nOops! Username already exists. Try another.");
                return;
            }
        }
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        if (email == "") {
            System.out.println("Please enter Email to Register!");
            return;
        }
        while (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please try again.\nMake sure that your domain one must be from \nthese {gmail.com}, {yahoo.com}, {icloud.com} \nand '@' & '.' must be placed in your Email.");
            email = sc.nextLine();
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (password == "") {
            System.out.println("Please enter Password to Register!");
            return;
        }
        while (!isValidPassword(password)) {
            System.out.println(
                    "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
            password = sc.nextLine();
        }
        //Week-1 part
        // Storing value to string type of array...(& incrementation)
        // usernames[n] = username;
        // emails[n] = email;
        // passwords[n] = password;
        // balances[n] = 0.0;

        //Week-4 part
         users[n] = new User(username, email, password);
        //Week-5 part
        //accounts[n] = new BankAccount();

        // Week-6 part Ask user to select account type
        System.out.println("Choose Account Type: 1. Saving  2. Current");
        int type = sc.nextInt(); sc.nextLine();

        if (type == 1) 
        {
        accounts[n] = new SavingAccount();
        }
        else
        {
             accounts[n] = new CurrentAccount();
        }
        n++;

        System.out.println("\nRegistration successful!");
    }

/*---------------------------------------------------------------------------------------------------------*/
    // Method-3 loginuser...
    void loginUser() {

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (username == "") {
            System.out.println("Please enter Username to Login!");
            return;
        }
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        if (email == "") {
            System.out.println("Please enter Password to Login!");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (password == "") {
            System.out.println("Please enter Password to Login!");
            return;
        }

        // Checks the Equality of the Login info. to Registration Info., if matched then return True value, else Error!
        for (int i = 0; i < n; i++) {
            // WITHOUT CLASS => if (!(usernames[i].equals(username) && passwords[i].equals(password) && emails[i].equals(email)))
            if(!(users[i].getUsername().equals(username) && users[i].getEmail().equals(email) && users[i].getPassword().equals(password))) {
                System.out.println("\nInvalid credentials!, Try again!");
            }
            // (Else part) to Give 5 option Deposit, Withdraw, View Balance, View Acc.
            // Details & Logout facility.
            else {
                System.out.println("\nLogin successful...");
                System.out.printf("Welcome, %s!\nYour balance is Rs. %.2f\n", username,accounts[i].getBalance());
                System.out.println("Your Account Number is: " + accounts[i].getAccountNumber());
                System.out.println("Choose below option to use our banking facility:");

                boolean exit = false;
                while (!exit) {
                    System.out.println("\n============ MENU =============");
                    System.out.println("Press 1 to Deposit");
                    System.out.println("Press 2 to Withdraw");
                    System.out.println("Press 3 to View Balance");
                    System.out.println("Press 4 to View Account Details");
                    System.out.println("Press 5 to Apply for Loan");  // LOAN SECTION...
                    System.out.println("Press 6 to Logout");          
                    System.out.println("===============================");
                    System.out.println();
                    System.out.print("Choose an option to Use our Facility: ");

                    int option = sc.nextInt();

                    if (option == 1) {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        accounts[i].deposit(amount);
                        System.out.println("After Transaction Your Account Current Balance: " + accounts[i].getBalance());
                        
                    } else if (option == 2) {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();

                        accounts[i].withdraw(amount);
                        System.out.println("After Transaction Your Account Current Balance: " + accounts[i].getBalance());

                    } else if (option == 3) {
                        System.out.println("Account Current Balance: " + accounts[i].getBalance());
                    } else if (option == 4) {
                        System.out.println("===============================");
                        System.out.println("Username: " + username);
                        System.out.println("Email: " + email);
                        System.out.println("Account Number: " + accounts[i].getAccountNumber());
                        System.out.println("Available Balance: " + accounts[i].getBalance());
                        System.out.println("===============================");
                    }else if (option == 5) {//LOAN SECTION USED

                        System.out.print("Enter Loan Amount: ");
                        double principal = sc.nextDouble();
                        System.out.print("Enter Annual Interest Rate (%): ");
                        double rate = sc.nextDouble();
                        System.out.print("Enter Term (Months): ");
                        int term = sc.nextInt();

                        //Here loan object created to access Loan class...
                        Loan loan = new Loan(principal, rate, term);
                        loan.displayLoanDetails();
                    } else if (option == 6) {
                        System.out.println("Logout Successfully...");
                        break;
                    } else {
                        System.out.println("Invalid option!, Choose Proper Option!");
                    }
                }

            }
        }
    }
    // End of Method-3 loginuser...
 /*---------------------------------------------------------------------------------------------------------*/
}
