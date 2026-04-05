import java.sql.*;
import java.util.Scanner;

public class BankApplication {
    public static Scanner sc = new Scanner(System.in);
    public static String url = "jdbc:mysql://localhost:3306/BankMangement";
    public static String user = "root";
    public static String password = "local";
    public static Connection con = null;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" Connection Failed: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n -------------------- Bank Menu --------------------- ");
            System.out.println("1. Create Account ");
            System.out.println("2. Deposit ");
            System.out.println("3. Withdraw ");
            System.out.println("4. Get Balance ");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");

            int input = sc.nextInt();
            try {
                switch (input) {
                    case 1: createAccount(); break;
                    case 2: depositAmount(); break;
                    case 3: withdrawAmount(); break;
                    case 4: checkBalance(); break;
                    case 5:
                        System.out.println(" Thank you for using Bank Application ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.next();
        System.out.print("Enter Customer Name: ");
        String customerName = sc.next();
        System.out.print("Enter Account Type (Saving/Current): ");
        String accountType = sc.next();
        System.out.print("Enter Initial Balance: ");
        float balance = sc.nextFloat();

        String sql = "INSERT INTO user (account_number, customer_name, account_type, account_balance) VALUES (?,?,?,?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, accountNumber);
            pst.setString(2, customerName);
            pst.setString(3, accountType);
            pst.setFloat(4, balance);
            pst.executeUpdate();
            System.out.println(" Account created successfully for " + customerName);
        } catch (SQLException ex) {
            System.out.println(" Account creation failed: " + ex.getMessage());
        }
    }

    private static void depositAmount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.next();
        System.out.print("Enter Amount to Deposit: ");
        float amount = sc.nextFloat();

        String sql = "UPDATE user SET account_balance = account_balance + ? WHERE account_number = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setFloat(1, amount);
            pst.setString(2, accountNumber);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println(" Deposited " + amount + " into account " + accountNumber);
            } else {
                System.out.println(" Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("  Deposit failed: " + e.getMessage());
        }
    }

    private static void withdrawAmount() throws LowBalanceException{
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.next();
        System.out.print("Enter Amount to Withdraw: ");
        float amount = sc.nextFloat();


        String checkSql = "SELECT account_balance FROM user WHERE account_number = ?";
        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setString(1, accountNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                float currentBalance = rs.getFloat("account_balance");
                if (currentBalance >= amount) {
                    String updateSql = "UPDATE user SET account_balance = account_balance - ? WHERE account_number = ?";
                    try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {
                        updateStmt.setFloat(1, amount);
                        updateStmt.setString(2, accountNumber);
                        updateStmt.executeUpdate();
                        System.out.println(" Withdrawn " + amount + " from account " + accountNumber);
                    }
                } else {
                    System.out.println(" Insufficient balance.");
                }
            } else {
                System.out.println(" Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("  Withdraw failed: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.next();

        String sql = "SELECT customer_name, account_balance FROM user WHERE account_number = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("customer_name");
                float balance = rs.getFloat("account_balance");
                System.out.println("Customer: " + name + " |  Balance: " + balance);
            } else {
                System.out.println(" Account not found.");
            }
        } catch (SQLException e) {
            System.out.println(" Balance check failed: " + e.getMessage());
        }
    }
}
