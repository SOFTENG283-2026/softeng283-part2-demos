package nz.ac.auckland.softeng283.bank;

public class BankAccount {
  int balance;

  public BankAccount(int balance) {
    this.balance = balance;
  }

  public int getBalance() {return balance;}

  public void setBalance(int amount){

    balance = amount;
  }
}
