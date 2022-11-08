package exception.user;

public class UserNotFoundException extends Exception{
    public String toString()
    {
        return "Данный пользователь не существует!!!";
    }
}
