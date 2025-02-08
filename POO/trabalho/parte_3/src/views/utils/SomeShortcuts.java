package views.utils;


public final class SomeShortcuts
{
    private static boolean genericResponse(boolean sucess, String modelName, String type)
    {
        if(sucess)
        {
            System.out.println(String.format("Sucesso ao %s %s", type, modelName));
            return true;
        }

        System.err.println(String.format("Erro ao %s %s!", type, modelName));
        return false;
    }

    public static boolean genericAddResponse(boolean sucess, String modelName)
    {
        return SomeShortcuts.genericResponse(sucess, modelName, "cadastrar");
    }

    public static boolean genericUpdateResponse(boolean sucess, String modelName)
    {
        return genericResponse(sucess, modelName, "atualizar");
    }

    public static boolean genericDeleteResponse(boolean sucess, String modelName)
    {
        return genericResponse(sucess, modelName, "deletar");
    }

    public static boolean genericGetResponse(boolean sucess, String modelName)
    {
        return genericResponse(sucess, modelName, "pegar");
    }
}

