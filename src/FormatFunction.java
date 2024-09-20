package src;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

public class FormatFunction {
    GUI g;
    private Float fontSize = 12f;  // Default size
    private Object[] font = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();;
    private String currFont = "Arial";  // Default font
    public FormatFunction(GUI g){
        this.g = g;
    }

    public void fontChange(){
        currFont = (String)JOptionPane.showInputDialog(null, "Choose Font", "Font", JOptionPane.PLAIN_MESSAGE, null, font, "Font");
        g.text.setFont(new Font(currFont, Font.PLAIN, 12));
        System.out.println(currFont);
    }

    public void fontSizeChange(){
        try {
            fontSize = Float.parseFloat(JOptionPane.showInputDialog("Change font size to:"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, fontSize + " is not a Valid Input", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        g.text.setFont(g.text.getFont().deriveFont(fontSize));    
    }

    public void fontStyleChange(String style){
        switch (style){
            case "bold":       
            case "italic":
            case "underline":
        }
    }
}
