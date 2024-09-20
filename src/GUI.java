package src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI extends JFrame implements ActionListener{
    JFrame frame;
    JTextArea text;
    JScrollPane scroll;
    JMenuBar menubar;

    JMenu file;
    JMenuItem newFile, open, save, saveAs, exit;

    JMenu edit;
    JMenuItem undo, redo, find, replace;

    JMenu format;
    JMenuItem size, font;
    JMenu style;
    JMenuItem bold, italic, underline;

    JMenu help;
    JMenuItem instruction, about;

    FileFunction fi = new FileFunction(this);
    EditFunction ed = new EditFunction(this);
    FormatFunction fo = new FormatFunction(this);
    AboutFunction ab = new AboutFunction(this);

    UndoManager um = new UndoManager();

    public static void main(String[] args) {
        new GUI();
    }

    public GUI(){
        CreateFrame();
        CreateTextArea();
        CreateMenuBar();
    }
    
    public void CreateFrame(){
        frame = new JFrame("LKNPad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("LKNPad");
    }

    public void CreateTextArea(){
        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.getDocument().addUndoableEditListener(
            new UndoableEditListener() {
                public void undoableEditHappened(UndoableEditEvent e){
                    um.addEdit(e.getEdit());
                }
            }
        );
        frame.add(text);
        scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll);
    }

    public void CreateMenuBar(){
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        help = new JMenu("Help");
        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(help);

        // File menu
        newFile = new JMenuItem("New File");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(exit);
        newFile.addActionListener(this);
        newFile.setActionCommand("new");
        open.addActionListener(this);
        open.setActionCommand("open");
        save.addActionListener(this);
        save.setActionCommand("save");
        saveAs.addActionListener(this);
        saveAs.setActionCommand("saveas");
        exit.addActionListener(this);
        exit.setActionCommand("exit");

        // Edit menu
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        find = new JMenuItem("Find");
        replace = new JMenuItem("Replace");
        edit.add(redo);
        edit.add(undo);
        edit.add(find);
        edit.add(replace);
        redo.addActionListener(this);
        redo.setActionCommand("redo");
        // if (um.canRedo()) redo.setEnabled(true);
        // else redo.setEnabled(false);
        undo.addActionListener(this);
        // undo.setActionCommand("undo");
        // if (um.canUndo()) undo.setEnabled(true);
        undo.setEnabled(false);
        find.addActionListener(this);
        find.setActionCommand("find");
        replace.addActionListener(this);
        replace.setActionCommand("replace");

        //Format menu
        font = new JMenuItem("Font");
        size = new JMenuItem("Size");
        style = new JMenu("Style");
        format.add(font);
        format.add(size);
        format.add(style);
        font.addActionListener(this);
        font.setActionCommand("font");
        size.addActionListener(this);
        size.setActionCommand("size");
        bold = new JMenuItem("Bold");
        italic = new JMenuItem("Italic");
        underline = new JMenuItem("Underline");
        style.add(bold);
        style.add(italic);
        style.add(underline);
        bold.addActionListener(this);
        bold.setActionCommand("bold");
        // bold.setIcon(new ImageIcon("/img/icon/bold-strong-bold-format-editor-tool-toolbar-svgrepo-com.svg"));
        italic.addActionListener(this);
        italic.setActionCommand("italic");
        underline.addActionListener(this);
        underline.setActionCommand("underline");
        
        //About menu
        instruction = new JMenuItem("Instruction");
        about = new JMenuItem("About");
        help.add(instruction);
        help.add(about);
        instruction.addActionListener(this);
        instruction.setActionCommand("instruction");
        about.addActionListener(this);
        about.setActionCommand("about");
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            // File menu
            case "new": fi.newFile(); break;
            case "open": fi.openFile(); break;
            case "save": fi.saveFile(); break;
            case "saveas": fi.saveAs(); break;
            case "exit": fi.exit(); break;
            // Edit menu
            case "undo": ed.undo(); break;
            case "redo": ed.redo(); break;
            case "find": ed.find(); break;
            case "replace": ed.replace(); break;
            // Format menu
            case "font": fo.fontChange(); break;
            case "size": fo.fontSizeChange(); break;
            case "bold": fo.fontStyleChange("bold"); break;
            case "italic": fo.fontStyleChange("italic"); break;
            case "underline": fo.fontStyleChange("underline"); break;
            // About menu
            case "instruction": ab.instruction(); break;
            case "about": ab.instruction(); break;
        }
    }
}
