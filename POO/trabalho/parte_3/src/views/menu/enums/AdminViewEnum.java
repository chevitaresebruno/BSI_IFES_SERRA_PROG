package views.menu.enums;

import utils.ScannerHandller;


public enum AdminViewEnum
{
    QUIT(0),
    CADASTRAR_ADIMINISTRADOR(1),
    CADASTRAR_ALUNO(2),
    CADASTRAR_PRODUTO(3),
    CADASTRAR_SALA(4);

    private final int cod;

    AdminViewEnum(int opcao_menu)
    {
        this.cod = opcao_menu;
    }

    public int getOption()
    {
        return cod;
    }

    public static AdminViewEnum buildByInput(String msg)
    {
        int input = ScannerHandller.getInt(msg);

        for(AdminViewEnum ave : values())
        {
            if(ave.cod == input)
            {
                return ave;
            }
        }

        return null;
    }
}

