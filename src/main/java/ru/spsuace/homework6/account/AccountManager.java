package ru.spsuace.homework6.account;

/**
 * Потокобезопасный класс, который позволяет переводить средства со счета на счет.
 * Класс может использоваться из многих потоков одновременно.
 * 4 балла за решение.
 */
public class AccountManager {


    /**
     * Метод переводит деньги с одного счета, на другой.
     * Если на исходном счету нет нужного количества денег, то метод возвращает false.
     * Класс Account непотокобезопасный и менять его нельзя
     */
    public boolean transfer(Account from, Account to, long sum) {
        Object lock1 = from.hashCode() < to.hashCode() ? from : to;
        Object lock2 = from.hashCode() < to.hashCode() ? to : from;
        synchronized (lock1) {
            synchronized (lock2) {
                if (from.getBalance() < sum) {
                    return false;
                } else {
                    from.withdraw(sum);
                    to.deposit(sum);
                    return true;
                }
            }
        }
    }
}



