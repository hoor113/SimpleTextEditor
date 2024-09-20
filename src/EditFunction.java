package src;
public class EditFunction {
    GUI g;
    public EditFunction(GUI g){
        this.g = g;
    }

    public void undo(){
        g.um.undo();
    }

    public void redo(){
        g.um.redo();
    }
    public void find(){
        
    }
    public void replace(){
        
    }
}
