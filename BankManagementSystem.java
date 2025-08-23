import java.util.Scanner;
//Week-4 Class-1 User
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

/*---------------------------------------------------------------------------------------------------------*/
//Main Class BankManagementSystem...
public class BankManagementSystem {

    // Declarations of whole code...
    int s = 100;
    int n = 0;
    // String[] usernames = new String[s];
    // String[] emails = new String[s];
    // String[] passwords = new String[s];
    // double[] balances = new double[s];

    //Object Declaration from User class...
    User[] users = new User[s];

    
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
        /*
         * int atPos = email.indexOf("@");
         * int dotPos = email.lastIndexOf(".");
         * if (atPos < 1 || dotPos < atPos + 2 || dotPos >= email.length() - 1) {
         * return false;
         * }
         */
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
            if (users[i].username.equals(username)) {
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
            System.out.println("Invalid email format. Please try again.");
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
            if(!(users[i].username.equals(username) && users[i].email.equals(email) && users[i].password.equals(password))) {
                System.out.println("\nInvalid credentials!, Try again!");
            }
            // (Else part) to Give 5 option Deposit, Withdraw, View Balance, View Acc.
            // Details & Logout facility.
            else {
                System.out.println("\nLogin successful...");
                System.out.printf("Welcome, %s!\nYour balance is Rs. %.2f\n\n", username, users[i].balance);
                System.out.println("Choose below option to use our banking facility:");

                boolean exit = false;
                while (!exit) {
                    System.out.println("\n============ MENU =============");
                    System.out.println("Press 1 to Deposit");
                    System.out.println("Press 2 to Withdraw");
                    System.out.println("Press 3 to View Balance");
                    System.out.println("Press 4 to View Account Details");
                    System.out.println("Press 5 to Logout");
                    System.out.println("===============================");
                    System.out.println();
                    System.out.print("Choose an option to Use our Facility: ");

                    int option = sc.nextInt();

                    if (option == 1) {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        if (amount <= 0) {
                            System.out.println("Incorrect Amount to Deposit!");
                        } else {
                            users[i].balance = users[i].balance + amount;
                            System.out.println("You have Deposited: " + amount);
                            System.out.println("After Transaction Your Account Current Balance: " + users[i].balance);
                        }

                    } else if (option == 2) {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        if (amount <= 0) {
                            System.out.println("Incorrect Amount to Withdraw!");
                        } else {
                            if (amount <= users[i].balance) {
                                users[i].balance = users[i].balance - amount;
                                System.out.println("Withdrawn: " + amount);
                            } else {
                                System.out.println(
                                        "Insufficient balance!, You cannot withdraw money greater than your current Balance.");
                            }
                        }

                        System.out.println("After Transaction Your Account Current Balance: " + users[i].balance);

                    } else if (option == 3) {
                        System.out.println("Account Current Balance: " + users[i].balance);
                    } else if (option == 4) {
                        System.out.println("Username: " + username);
                        System.out.println("Email: " + email);
                        System.out.println("Available Balance: " + users[i].balance);
                    } else if (option == 5) {
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