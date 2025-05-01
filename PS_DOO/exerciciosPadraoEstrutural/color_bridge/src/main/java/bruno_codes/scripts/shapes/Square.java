package bruno_codes.scripts.shapes;

import bruno_codes.scripts.colors.ICollor;


public class Square extends AShape
{
    public Square(ICollor collor)
    {
        super(collor);
    }

    @Override
    public void show()
    {
        System.out.print("I am a ");
        this.getCollor().applyCollor();
        System.out.println(" Square");
    }
}

