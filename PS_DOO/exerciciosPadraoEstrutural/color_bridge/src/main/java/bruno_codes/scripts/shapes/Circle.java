package bruno_codes.scripts.shapes;

import bruno_codes.scripts.colors.ICollor;


public class Circle extends AShape
{
    public Circle(ICollor collor)
    {
        super(collor);
    }    


    @Override
    public void show()
    {
        System.out.print("I am a ");
        this.getCollor().applyCollor();
        System.err.println(" Circle");
    }
}

