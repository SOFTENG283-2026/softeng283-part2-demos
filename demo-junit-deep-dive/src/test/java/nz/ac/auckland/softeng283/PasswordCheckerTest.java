package nz.ac.auckland.softeng283;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordCheckerTest {

  PasswordChecker passwordChecker;

  @BeforeEach
  public void setUp() {
    passwordChecker = new PasswordChecker();
  }

  @Test
  public void isValid_shortLenght_noValid() {
    // arrange
    String input = "1234567";

    // act
    boolean result = passwordChecker.isValid(input);

    // assert
    assertFalse(result);
  }

  @Test
  public void isValid_boundaryLenght_Valid() {
    // arrange
    String input = "12345678";

    // act
    boolean result = passwordChecker.isValid(input);

    // assert
    assertTrue(result);
  }

  @Test
  public void isValid_boundaryPlusOneLenght_Valid() {
    // arrange
    String input = "123456789";

    // act
    boolean result = passwordChecker.isValid(input);

    // assert
    assertTrue(result);
  }

  @Test
  public void isValid_nullValue_Exception() {
    // arrange
    String input = null;

    assertThrows(IllegalArgumentException.class, () -> passwordChecker.isValid(input));

  }

@Test 
public void isValid_validInput8_valid(){
String input = "abcsrtrtrtt8";

boolean result1 = passwordChecker.isValid(input);

assertTrue(result1);
}

@Test 
public void isValid_validInput10_valid(){
String input2 = "abcsyuyuyuy10";

boolean result2 = passwordChecker.isValid(input2);

assertTrue(result2);
}


}
