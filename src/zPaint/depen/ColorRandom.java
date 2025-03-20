
package zPaint.depen;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Alfred
 */
public class ColorRandom extends Color
{

    public ColorRandom()
    {
        super(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
    }
}
