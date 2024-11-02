package scripts.controllers.adt;

import scripts.chore.adt.Senha;
import scripts.controllers.java_utils.ScannerHandller;


public final class ControllerSenha {
    
    public static String inputSenha()
    {
        String senha; 

        ScannerHandller.init();
        senha = ScannerHandller.getString("Informe a Senha: ");
        ScannerHandller.end();

        return senha;
    }

    public static Senha newSenha() throws Exception
    {
        return new Senha(inputSenha());
    }

    public static boolean compareSenha(Senha senha, String input) throws Exception 
    {
        return senha.compare(input);
    }

    public static boolean compareInputSenha(Senha object) throws Exception
    {
        return object.compare(inputSenha());
    }
}
