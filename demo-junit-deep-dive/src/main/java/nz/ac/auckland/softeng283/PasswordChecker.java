package nz.ac.auckland.softeng283;

public class PasswordChecker {

    /**
     * Checks whether a password is at least 8 characters long and contains
     * at least one digit between 0 and 9.
     *
     * @param password the password to check; must not be null
     * @return true if both requirements are met, otherwise false
     * @throws IllegalArgumentException if password is null
     */
    public boolean isValid(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character >= '0' && character <= '9') {
                return true;
            }
        }

        return false;
    }
}
