/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package bruno_codes;

import bruno_codes.scripts.colors.Blue;
import bruno_codes.scripts.colors.Red;
import bruno_codes.scripts.shapes.AShape;
import bruno_codes.scripts.shapes.Circle;
import bruno_codes.scripts.shapes.Square;

/**
 *
 * @author Bruno
 */
public class Color_bridge
{

    public static void main(String[] args)
    {
        AShape s = new Circle(new Red());
        s.show();

        s.setCollor(new Blue());
        s.show();

        s = new Square(new Red());
        s.show();

        s.setCollor(new Blue());
        s.show();
    }
}
