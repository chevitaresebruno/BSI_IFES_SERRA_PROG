package application;

import frontend.views.IView;
import frontend.views.console.ViewCalculadora;


public class Main
{
    public static void main(String[] args)
    {
        IView view = new ViewCalculadora();

        view.loopInfinito();
        view.sair();
    }
}

