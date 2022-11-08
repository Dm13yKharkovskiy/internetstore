package exception.user;

public class InvalidPasswordException extends Exception{
    public String toString()
    {
        return "Введен неверный пароль!!!";
    }
}
