package model.serializers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import application.Constrains;

public final class ExistsOperationSerializer
{
    public static String[] getOperations()
    {
        List<String> classes = new ArrayList<>();
        
        File folder = new File(Constrains.BackendDir + "/model/operations/aritimeticas/");
        if (folder.exists() && folder.isDirectory())
        {
            for (File file : folder.listFiles())
            {
                if (file.isFile() && file.getName().endsWith(".java"))
                {
                    classes.add(file.getName().replace(".java", ""));
                }
            }
        }

        System.out.println(classes);
        return classes.toArray(String[]::new);
    }
}
