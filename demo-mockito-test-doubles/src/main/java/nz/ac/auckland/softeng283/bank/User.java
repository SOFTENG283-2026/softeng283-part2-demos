package nz.ac.auckland.softeng283.bank;

public class User {

  private final BankAccount account;

  public User(BankAccount account) {
    this.account = account;
  }

  public boolean withdraw(int amount) {
    if (account.getBalance() < amount) {
      return false;
    }
    account.setBalance(account.getBalance() - amount);
    return true;
  }
}
