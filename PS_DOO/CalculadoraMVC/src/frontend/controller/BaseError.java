package frontend.controller;


public class BaseError extends Error
{
    public BaseError(String msg)
    {
        super(msg);
    }

    public void printMsg()
    {
        System.err.println(this.getMessage());
    }
}
