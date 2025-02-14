package database.dbWriter;

import controller.ISavableInDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import models.BaseModel;


public class DatabaseWriter
{
    private FileWriter writer;

    @SuppressWarnings("CallToPrintStackTrace")
    public DatabaseWriter(String fileName, boolean append)
    {
        try
        {
            this.writer = new FileWriter(fileName, append);
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

    public void recycle(String fileName) throws IOException
    {
        this.close();
        this.init(fileName);
    }

    public boolean init(String fileName) throws IOException
    {
        if(this.writer != null)
            { return false; }
        this.writer = new FileWriter(fileName);
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
