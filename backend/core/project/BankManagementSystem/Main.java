
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        TerminalLog.infoLog("Welcome to our Bank");
        while (true) {
            TerminalLog.infoLog("\nEnter the key to perform the operation ");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static BankAccount findAccount(int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public static void createAccount() {
        try {
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Type (Savings/Current): ");
            String type = sc.nextLine();

            System.out.print("Enter Balance: ");
            float balance = sc.nextFloat();

            BankAccount acc = new BankAccount(name, type, balance);
            accounts.add(acc);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());


        }
    }

    public static void deposit() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            BankAccount acc = findAccount(accNo);

            if (acc == null) {
                System.out.println("Account not found");
                return;
            }

            System.out.print("Enter amount: ");
            float amount = sc.nextFloat();

            acc.deposit(amount);
            System.out.println("Deposit successful");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void withdraw() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            BankAccount acc = findAccount(accNo);

            if (acc == null) {
                System.out.println("Account not found");
                return;
            }

            System.out.print("Enter amount: ");
            float amount = sc.nextFloat();

            acc.withdraw(amount);
            System.out.println("Withdraw successful");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void checkBalance() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            BankAccount acc = findAccount(accNo);

            if (acc == null) {
                System.out.println("Account not found");
                return;
            }

            float balance = acc.checkBalance();
            System.out.println("Balance: " + balance);

        } catch (Exception e) {
            TerminalLog.errorLog("Error: " + e.getMessage());
        }
    }
}