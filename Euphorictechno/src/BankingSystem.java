import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem{
    private static Map<String, Account> accounts = new HashMap<>();
    private static int accountCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Banking System Menu:");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Display All Account Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    depositFunds(scanner);
                    break;
                case 3:
                    withdrawFunds(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    transferFunds(scanner);
                    break;
                case 6:
                    displayAllAccountDetails();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String accountHolderName = scanner.nextLine();
        String accountNumber = "ACC" + String.format("%04d", accountCounter++);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  

        Account account = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully with account number: " + accountNumber);
    }

    private static void depositFunds(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful. Current balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful. Current balance: $" + account.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Current balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferFunds(Scanner scanner) {
        System.out.print("Enter sender's account number: ");
        String senderAccountNumber = scanner.nextLine();
        Account senderAccount = accounts.get(senderAccountNumber);
        if (senderAccount != null) {
            System.out.print("Enter recipient's account number: ");
            String recipientAccountNumber = scanner.nextLine();
            Account recipientAccount = accounts.get(recipientAccountNumber);
            if (recipientAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    senderAccount.transfer(recipientAccount, amount);
                } else {
                    System.out.println("Invalid transfer amount.");
                }
            } else {
                System.out.println("Recipient account not found.");
            }}
            else {
                System.out.println("Sender account not found.");
            }
        }

        private static void displayAllAccountDetails() {
            if (accounts.isEmpty()) {
                System.out.println("No accounts found.");
            } else {
                System.out.println("All Account Details:");
                for (Map.Entry<String, Account> entry : accounts.entrySet()) {
                    Account account = entry.getValue();
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Account Holder Name: " + account.getAccountHolderName());
                    System.out.println("Balance: $" + account.getBalance());
                    
                }
            }
        }
    }
        
        
