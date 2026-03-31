class BankAccount {

    private Integer accountNumber;
    private String accountHolderName;
    private String accountType;
    private float accountBalance;

    private static int counter = 1001;

    public BankAccount(String name, String type, float balance)
            throws LowBalanceException, NegativeBalanceException {

        if (balance < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }

        if (type.equalsIgnoreCase("Savings") && balance < 1000) {
            throw new LowBalanceException("Minimum 1000 required for Savings");
        }

        if (type.equalsIgnoreCase("Current") && balance < 5000) {
            throw new LowBalanceException("Minimum 5000 required for Current");
        }

        this.accountNumber = counter++;
        this.accountHolderName = name;
        this.accountType = type;
        this.accountBalance = balance;

        TerminalLog.successLog("Account created successfully. Account No: " + accountNumber);
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void deposit(float amount) throws NegativeAmountException {
        if (amount < 0) {
            throw new NegativeAmountException("Amount cannot be negative");
        }
        accountBalance += amount;
    }

    public void withdraw(float amount)
            throws NegativeAmountException, InsufficientFundsException {

        if (amount < 0) {
            throw new NegativeAmountException("Amount cannot be negative");
        }

        float minBalance = accountType.equalsIgnoreCase("Savings") ? 1000 : 5000;

        if ((accountBalance - amount) < minBalance) {
            throw new InsufficientFundsException("Minimum balance violation");
        }

        accountBalance -= amount;
    }

    public float checkBalance()
            throws NegativeBalanceException, LowBalanceException {

        if (accountBalance < 0) {
            throw new NegativeBalanceException("Balance is negative");
        }

        if (accountType.equalsIgnoreCase("Savings") && accountBalance <= 1000) {
            throw new LowBalanceException("Low balance in Savings account");
        }

        if (accountType.equalsIgnoreCase("Current") && accountBalance <= 5000) {
            throw new LowBalanceException("Low balance in Current account");
        }

        return accountBalance;
    }

    @Override
    public String toString() {
        return "AccNo: " + accountNumber +
               ", Name: " + accountHolderName +
               ", Type: " + accountType +
               ", Balance: " + accountBalance;
    }

    
}