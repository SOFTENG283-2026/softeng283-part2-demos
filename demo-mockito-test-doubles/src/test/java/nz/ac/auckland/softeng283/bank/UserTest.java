package nz.ac.auckland.softeng283.bank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserTest {

    @Test 
    public void withdraw_whenAmountIsThere_success(){

        // Arrange
        BankAccount bankAccount = Mockito.mock(BankAccount.class);
        Mockito.when(bankAccount.getBalance()).thenReturn(100);
        User user = new User(bankAccount);
        // ACT
        boolean result = user.withdraw(10);
        // ASSERT
        Mockito.verify(bankAccount,times(1)).setBalance(90);
        assertTrue(result);
    }



}
