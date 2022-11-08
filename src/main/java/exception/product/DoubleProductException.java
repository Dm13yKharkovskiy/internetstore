package exception.product;

public class DoubleProductException extends Exception{
    public String toString()
    {
        return "Данный товар уже существует!!!";
    }

}
