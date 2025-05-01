package trabalho_1.tipos;

public class SearchIsntanceController
{
    private static Aluno al;
    
    public static void initialize()
    {
        SearchIsntanceController.al = new Aluno(null, null, 0);
    }

    public static Aluno searchByMatricula(String matricula)
    {
        SearchIsntanceController.al.matricula = matricula;
        return SearchIsntanceController.al;
    }
}
