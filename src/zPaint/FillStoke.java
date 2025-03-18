package zPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Alfred
 */
public class FillStoke extends JTabbedPane
{

    private final Fill fillPanel = new Fill();
    private final StrokePaint stokePaintPanel = new StrokePaint();
    private final StrokeStyle stokeStylePanel = new StrokeStyle();

    public FillStoke()
    {
        super(JTabbedPane.TOP);
        this.addTab("Fill", fillPanel);
        this.addTab("Stoke Paint", stokePaintPanel);
        this.addTab("Stoke Style", stokeStylePanel);
        this.setSelectedIndex(0);
    }
}
class Fill extends JPanel {
    public Fill() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el relleno
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Selector de color
        JButton colorButton = new JButton("Seleccionar Color");
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Seleccionar Color de Relleno", Color.RED);
            if (newColor != null) {
                setBackground(newColor);
            }
        });

        // Opciones de relleno
        JCheckBox fillCheckBox = new JCheckBox("Relleno Activo", true);
        JComboBox<String> fillTypeComboBox = new JComboBox<>(new String[]{"Sólido", "Degradado", "Patrón"});
        JSlider opacitySlider = new JSlider(0, 100, 100);
        opacitySlider.setMajorTickSpacing(25);
        opacitySlider.setPaintTicks(true);
        opacitySlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(colorButton);
        controlsPanel.add(fillCheckBox);
        controlsPanel.add(new JLabel("Tipo de Relleno:"));
        controlsPanel.add(fillTypeComboBox);
        controlsPanel.add(new JLabel("Opacidad:"));
        controlsPanel.add(opacitySlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

class StrokePaint extends JPanel {
    public StrokePaint() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el trazo
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Selector de color del trazo
        JButton strokeColorButton = new JButton("Seleccionar Color del Trazo");
        strokeColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Seleccionar Color del Trazo", Color.BLACK);
            if (newColor != null) {
                setBackground(newColor);
            }
        });

        // Opciones de trazo
        JCheckBox strokeCheckBox = new JCheckBox("Trazo Activo", true);
        JComboBox<String> strokeTypeComboBox = new JComboBox<>(new String[]{"Sólido", "Degradado", "Patrón"});
        JSlider strokeWidthSlider = new JSlider(1, 10, 1);
        strokeWidthSlider.setMajorTickSpacing(1);
        strokeWidthSlider.setPaintTicks(true);
        strokeWidthSlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(strokeColorButton);
        controlsPanel.add(strokeCheckBox);
        controlsPanel.add(new JLabel("Tipo de Trazo:"));
        controlsPanel.add(strokeTypeComboBox);
        controlsPanel.add(new JLabel("Ancho del Trazo:"));
        controlsPanel.add(strokeWidthSlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

class StrokeStyle extends JPanel {
    public StrokeStyle() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel de controles para el estilo del trazo
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // 5 filas, 1 columna
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Estilo del trazo
        JComboBox<String> strokeStyleComboBox = new JComboBox<>(new String[]{"Continua", "Punteada", "Rayada"});
        JSlider dashPatternSlider = new JSlider(1, 10, 1);
        dashPatternSlider.setMajorTickSpacing(1);
        dashPatternSlider.setPaintTicks(true);
        dashPatternSlider.setPaintLabels(true);

        // Añadir controles al panel
        controlsPanel.add(new JLabel("Estilo del Trazo:"));
        controlsPanel.add(strokeStyleComboBox);
        controlsPanel.add(new JLabel("Patrón de Guiones:"));
        controlsPanel.add(dashPatternSlider);

        add(controlsPanel, BorderLayout.CENTER);
    }
}

/*class Fill extends JPanel
{

    private final JButton solidColorButton;
    private final JButton gradientButton;
    private final JButton noFillButton;
    private final JPanel previewPanel;

    public Fill()
    {
        setLayout(new BorderLayout());

        // Panel de botones para elegir tipo de relleno
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        solidColorButton = new JButton("Solid");
        gradientButton = new JButton("Gradient");
        noFillButton = new JButton("No Fill");

        buttonPanel.add(solidColorButton);
        buttonPanel.add(gradientButton);
        buttonPanel.add(noFillButton);

        // Panel de vista previa del color
        previewPanel = new JPanel();
        previewPanel.setBackground(Color.RED);
        previewPanel.setPreferredSize(new Dimension(100, 50));

        // Eventos
        solidColorButton.addActionListener(e -> chooseColor());
        noFillButton.addActionListener(e -> previewPanel.setBackground(null));

        add(buttonPanel, BorderLayout.NORTH);
        add(previewPanel, BorderLayout.CENTER);
    }

    private void chooseColor()
    {
        Color color = JColorChooser.showDialog(this, "Choose Fill Color", previewPanel.getBackground());
        if (color != null)
        {
            previewPanel.setBackground(color);
        }
    }
}

class StrokePaint extends JPanel
{

    private final JButton strokeColorButton;
    private final JPanel previewPanel;

    public StrokePaint()
    {
        setLayout(new BorderLayout());

        strokeColorButton = new JButton("Choose Stroke Color");
        previewPanel = new JPanel();
        previewPanel.setBackground(Color.BLUE);
        previewPanel.setPreferredSize(new Dimension(100, 50));

        strokeColorButton.addActionListener(e -> chooseColor());

        add(strokeColorButton, BorderLayout.NORTH);
        add(previewPanel, BorderLayout.CENTER);
    }

    private void chooseColor()
    {
        Color color = JColorChooser.showDialog(this, "Choose Stroke Color", previewPanel.getBackground());
        if (color != null)
        {
            previewPanel.setBackground(color);
        }
    }
}

class StrokeStyle extends JPanel
{

    private final JSpinner strokeWidthSpinner;
    private final JComboBox<String> strokeTypeCombo;

    public StrokeStyle()
    {
        setLayout(new GridLayout(2, 2));

        // Espesor del trazo
        JLabel strokeWidthLabel = new JLabel("Stroke Width:");
        strokeWidthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // Tipo de línea
        JLabel strokeTypeLabel = new JLabel("Stroke Type:");
        String[] strokeTypes =
        {
            "Solid", "Dashed", "Dotted"
        };
        strokeTypeCombo = new JComboBox<>(strokeTypes);

        add(strokeWidthLabel);
        add(strokeWidthSpinner);
        add(strokeTypeLabel);
        add(strokeTypeCombo);
    }
}

/*class Fill extends JPanel{

    public Fill()
    {
        setBackground(Color.red);
    } 
}

class StokePaint extends JPanel{

    public StokePaint()
    {
        setBackground(Color.BLUE);
    } 
}

class StokeStyle extends JPanel{

    public StokeStyle()
    {
        setBackground(Color.YELLOW);
    } 
}*/
