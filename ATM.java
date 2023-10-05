import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Transaction class
class Transaction {
    private String type;
    private double amount;
    private Date date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    // Getters for transaction details
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

// TransactionHistory class
class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

// User class
class User {
    private String userId;
    private String userPin;
    private double balance;
    private TransactionHistory transactionHistory;

    public User(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new TransactionHistory();
    }

    // Getters and setters for user details
    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }
}

// ATM class
public class ATM {
    private User currentUser;
    private Scanner scanner; // Declare the Scanner as an instance variable

    public ATM() {
        this.currentUser = null;
        this.scanner = new Scanner(System.in); // Initialize the Scanner in the constructor
    }

    public void start() {
        // Simulate user authentication
        authenticateUser();

        if (currentUser != null) {
            boolean quit = false;

            while (!quit) {
                // Display menu and perform selected operation
                System.out.println("ATM Menu:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayTransactionHistory();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private void authenticateUser() {
        // Simulate user authentication logic (e.g., by comparing input with stored user data)
        // For simplicity, let's assume user credentials are "user123" and "1234"
        String userId = "user123";
        String userPin = "1234";

        System.out.print("Enter User ID: ");
        String inputUserId = scanner.next();

        System.out.print("Enter PIN: ");
        String inputPin = scanner.next();

        if (userId.equals(inputUserId) && userPin.equals(inputPin)) {
            // Authentication successful
            currentUser = new User(userId, userPin, 1000.0); // Initialize with an initial balance
            System.out.println("Authentication successful. Welcome, " + userId + "!");
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }

    private void displayTransactionHistory() {
        // Display transaction history for the current user
        TransactionHistory history = currentUser.getTransactionHistory();
        List<Transaction> transactions = history.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Date: " + transaction.getDate());
                System.out.println();
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getBalance()) {
            // Sufficient balance for withdrawal
            currentUser.setBalance(currentUser.getBalance() - amount);
            Transaction withdrawalTransaction = new Transaction("Withdrawal", amount);
            currentUser.getTransactionHistory().addTransaction(withdrawalTransaction);
            System.out.println("Withdrawal successful. Current balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            Transaction depositTransaction = new Transaction("Deposit", amount);
            currentUser.getTransactionHistory().addTransaction(depositTransaction);
            System.out.println("Deposit successful. Current balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void transfer() {
        System.out.print("Enter the recipient's User ID: ");
        String recipientUserId = scanner.next();

        // Check if the recipient exists (in a real application, this would involve a user database)
        // For simplicity, let's assume recipient exists
        User recipient = new User(recipientUserId, "", 0.0);

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            Transaction transferOutTransaction = new Transaction("Transfer (Out)", amount);
            Transaction transferInTransaction = new Transaction("Transfer (In)", amount);

            currentUser.getTransactionHistory().addTransaction(transferOutTransaction);
            recipient.getTransactionHistory().addTransaction(transferInTransaction);

            System.out.println("Transfer successful. Current balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
