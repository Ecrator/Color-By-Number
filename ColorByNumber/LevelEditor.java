package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class LevelEditor extends JPanel implements MouseListener{
    String name;
    JLabel nameLabel;
    JButton button;
    DataManager manager;
    int fileNumber;
    CanvasPreview previewCanvas;
    public LevelEditor(int IfileNumber) throws IOException{
        //Initializes values and components
        //==========
        manager=new DataManager();
        button=new JButton("Edit");
        fileNumber=IfileNumber;
        name=manager.getName(fileNumber);
        nameLabel=new JLabel(name);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
        button.addMouseListener(this);
        previewCanvas=new CanvasPreview(fileNumber, true);
        //==========
        //Organizes components on the panel
        //==========
        this.add(nameLabel);
        nameLabel.setBounds(40, 20, 500, 50);
        this.add(button);
        button.setBounds(40, 90, 100, 40);
        this.add(previewCanvas);
        previewCanvas.setBounds(250, 20, 150,150);
        //==========
    }

    public void loadCanvas(){
        //Creates a Canvas object
        //==========
        try{
            BlankCanvas canvas=new BlankCanvas(name, fileNumber);
        }catch(Exception x){
            System.out.println("Failed to Load Canvas!");
            System.out.println(x);
        }
        //==========
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Click listener which loads a canvas
        //object, the JButton is the only object
        //with a click listener
        //==========
        loadCanvas();
        //==========
    }
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
}
