package frontend.controller;

import controller.OperacoesExistentes;

public final class TITPController
{
    public static String[] pegarOperacoes()
    {
        OperacoesExistentes oe = new OperacoesExistentes();
        return oe.call().getInformation();
    } 
}
