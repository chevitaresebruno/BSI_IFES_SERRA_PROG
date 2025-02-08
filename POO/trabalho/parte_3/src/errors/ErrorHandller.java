package errors;


public final class ErrorHandller
{
    private static boolean debug = true;

    public static void ativarDebug()
    {
        ErrorHandller.debug = true;
    }

    public static void desativarDebug()
    {
        ErrorHandller.debug = false;
    }

    public static String mensagemDeErro(ErroBase erro)
    {
        StackTraceElement[] stackTrace = erro.getStackTrace();

        if(stackTrace.length > 0)
        {
            if(ErrorHandller.debug)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(erro.toString()).append("\n");
        
                for (StackTraceElement element : stackTrace) {
                    sb.append("\tat ").append(element.toString()).append("\n");
                }
        
                return sb.toString();
            }
            else
            {
                StackTraceElement element = stackTrace[0];

                String mensagem = "--- ERRO ---\n0 - at %s\n1 - Chamada na classe %s\n2 - Método %s\n3 - Linha %d\n4 - Arquivo %s\n\n%s";
                
                return String.format(mensagem, element.toString(), element.getClassName(), element.getMethodName(), element.getLineNumber(), element.getFileName(), erro.toString());
            }
        }

        return erro.toString();
    }
}

