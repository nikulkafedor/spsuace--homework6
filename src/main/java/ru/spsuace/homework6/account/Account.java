package ru.spsuace.homework6.account;

public class Account {

    private final String name;
    private volatile long balance;

    public Account(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        checkPositiveSum(amount);
        balance = balance + amount;
    }

    public void withdraw(long amount) {
        checkPositiveSum(amount);
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        balance = balance - amount;
    }

    private static void checkPositiveSum(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должа быть положительной");
        }
    }
}
