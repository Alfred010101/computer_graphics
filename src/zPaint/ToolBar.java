
package zPaint;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class ToolBar extends JPanel
{

    public ToolBar()
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JButton("Pincel"));
        this.add(new JButton("Borrador"));
        this.add(new JButton("Color"));
    }
    
}
