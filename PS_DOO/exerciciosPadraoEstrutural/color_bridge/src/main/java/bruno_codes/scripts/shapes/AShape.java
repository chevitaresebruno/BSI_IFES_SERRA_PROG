package bruno_codes.scripts.shapes;

import bruno_codes.scripts.colors.ICollor;


public abstract class AShape
{
    private ICollor collor;

    public AShape(ICollor collor)
    {
        this.collor = collor;
    }    

    public ICollor getCollor()
    {
        return this.collor;
    }

    public void setCollor(ICollor newCollor)
    {
        this.collor = newCollor;
    }

    public abstract void show();
}

