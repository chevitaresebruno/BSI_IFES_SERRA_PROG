package database.dbWriter;

import controller.ISavableInDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import models.BaseModel;
import models.tad.Senha;


public class DatabaseWriter
{
    private FileWriter writer;
    private boolean append;

    @SuppressWarnings("CallToPrintStackTrace")
    public DatabaseWriter(String fileName, boolean append)
    {
        try
        {
            this.writer = new FileWriter(fileName, append);
            this.append = append;

            if(Senha.checkCanEncrypty() && !this.append)
            {
                System.out.println("ENCRYPTY");
                this.writer.write("PASSWORDS ENCRYPTED\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            this.writer = null;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean createDatabase(String fileName)
    {
        try
        {
            File f = new File(fileName); 
            return f.createNewFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }    

    public boolean recycle(String fileName, boolean append) throws IOException
    {
        if(this.writer != null)
            { return false; }
        this.writer = new FileWriter(fileName, append);
        this.append = append;

        if(Senha.checkCanEncrypty() && !this.append)
        {
            System.out.println("ENCRYPTY");
            this.writer.write("PASSWORDS ENCRYPTED\n");
        }

        return true;
    }
    
    public void close() throws IOException /* Commit */
    {
        if(this.writer == null)
            { return; }

        this.writer.write("FIM");
        this.writer.close();
        this.writer = null;
    }

    public void write(String s) throws IOException
    {
        this.writer.write(s);
    }

    @SuppressWarnings("rawtypes")
    public static<Model extends BaseModel> void write(DatabaseWriter dbw, ISavableInDatabase<Model> modelController, Model m) throws IOException
    {
        dbw.writer.write(modelController.toSaveDatabaseFormat(m));
    }

    @SuppressWarnings("rawtypes")
    public static<Model extends BaseModel> void write(DatabaseWriter dbw, ISavableInDatabase<Model> modelController, List<Model> ms) throws IOException
    {
        for(Model m : ms)
        {
            dbw.writer.write(modelController.toSaveDatabaseFormat(m));
            dbw.writer.write("\n");
        }
    }
}
