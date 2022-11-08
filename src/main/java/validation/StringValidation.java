package validation;

import java.util.Objects;

public class StringValidation {

    public static boolean inputStringIsNotNull(String input) {
        return input.trim().length() > 0;
    }

    public static boolean checkRepeatPassword(String password, String repeatPassword) {
        return Objects.equals(password, repeatPassword);
    }
}
