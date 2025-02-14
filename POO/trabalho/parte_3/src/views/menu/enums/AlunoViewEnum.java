package views.menu.enums;

import utils.ScannerHandller;


public enum AlunoViewEnum
{
    QUIT(0),
    FAZER_PEDIDO(1),
    ENTREGAR_PEDIDO(2),
    LISTAR_PEDIDOS(3),
    ADICIONAR_SALDO(4);

    private final int cod;

    AlunoViewEnum(int opcao_menu)
    {
        this.cod = opcao_menu;
    }

    public int getOption()
    {
        return cod;
    }
    
    public static AlunoViewEnum buildByInput(String msg)
    {
        int input = ScannerHandller.getInt(msg);

        for(AlunoViewEnum ave : values())
        {
            if(ave.cod == input)
            {
                return ave;
            }
        }

        return null;
    }
}

