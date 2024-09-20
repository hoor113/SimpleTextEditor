package src;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileFunction {
    GUI g;
    FileDialog fd;
    private String fileName, fileDirectory;
    public FileFunction(GUI g){
        this.g = g;
    }

    public void newFile(){
        g.text.setText("");
        g.frame.setTitle("New File");
    }

    public void openFile(){
        fd = new FileDialog(g.frame, "Open a File", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null){
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            g.frame.setTitle(fileName);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileDirectory + fileName));
            
            g.text.setText("");
            String writer = null;
            while ((writer = br.readLine()) != null){
                g.text.append(writer + '\n');
            }

            br.close();
        } catch (Exception e) {
            System.out.println("ERROR OPENING FILE");
            e.printStackTrace();
        }
    }
    
    public void saveFile(){
        if (fileName == null){
            saveAs();
        }
        else{
            try {
                FileWriter fw = new FileWriter(fileDirectory + fileName);
                fw.write(g.text.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println("ERROR SAVING PROCESS");
                e.printStackTrace();        
            }
        }
    }

    public void saveAs(){
        fd = new FileDialog(g.frame, "Save Current File", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null){
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            g.frame.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            fw.write(g.text.getText());
            fw.close();
        } catch (Exception e) {
            System.out.println("ERROR SAVING FILE");
            e.printStackTrace();
        }
    }

    public void exit(){
        System.exit(0);
    }
}
