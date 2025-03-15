package views.menu.enums;

import utils.ScannerHandller;


public enum MenuPrincipalEnum
{
    UNDEFINED(-1),
    QUIT(0),
    LOGIN(1);
    
    private final int tipoLogin;

    MenuPrincipalEnum(int tipoLogin)
    {
        this.tipoLogin = tipoLogin;
    }

    public int getTipoLogin()
    {
        return this.tipoLogin;
    }

    public static MenuPrincipalEnum buildByInput(String msg)
    {
        int input = ScannerHandller.getInt(msg);

        for(MenuPrincipalEnum mpe : values())
        {
            if(mpe.tipoLogin == input)
            {
                return mpe;
            }
        }

        return MenuPrincipalEnum.UNDEFINED;
    }
}

