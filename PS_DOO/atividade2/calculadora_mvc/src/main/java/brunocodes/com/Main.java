package brunocodes.com;

import brunocodes.com.view.IView;
import brunocodes.com.view.console.CalculadoraAritimeticaView;

public class Main {
    public static void main(String[] args)
    {
        IView view = new CalculadoraAritimeticaView();

        view.exibir();
    }
}