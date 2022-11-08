package model;

public class Code {

    private long idOrder;
    private int code;

    public Code(long idOrder){
        this.idOrder = idOrder;
        this.code = getConfirmCode();
    }
    public Code(long idOrder, int code){
        this.idOrder = idOrder;
        this.code = code;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private static int getConfirmCode() {
        return (int) (Math.random() * 10000);
    }
}
