package application;

import view.IView;
import view.ViewCalculadora;


public class Main
{
    public static void main(String[] args)
    {
        IView view = new ViewCalculadora();

        view.loopInfinito();
        view.sair();
    }
}

