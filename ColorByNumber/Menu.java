package ColorByNumber;
import javax.swing.*;
import java.awt.event.*;
public class Menu extends JFrame implements MouseListener{
    JButton browserButton;
    JButton creatorButton;
    JButton editorButton;
    public Menu(){
        //initiates components
        //==========
        browserButton=new JButton("Browser");
        creatorButton=new JButton("Creator");
        editorButton=new JButton("Editor");
        browserButton.addMouseListener(this);
        creatorButton.addMouseListener(this);
        editorButton.addMouseListener(this);
        //==========
        //initiates Frame
        //==========
        this.setSize(300,400);
        this.setLayout(null);
        this.setTitle("Menu");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //==========
        //organizes components
        //==========
        this.add(browserButton);
        this.add(creatorButton);
        this.add(editorButton);
        browserButton.setBounds(75, 50, 150, 75);
        creatorButton.setBounds(75, 150, 150, 75);
        editorButton.setBounds(75, 250, 150, 75);
        //==========
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //click listener that uses the button
        //text to distinct them
        //==========
        try{
            if(((JButton)e.getSource()).getText().equals("Browser")){
                LevelBrowser browser=new LevelBrowser();
            }else if(((JButton)e.getSource()).getText().equals("Creator")){
                CanvasCreator creator=new CanvasCreator();
            }else if(((JButton)e.getSource()).getText().equals("Editor")){
                CanvasEditor editor=new CanvasEditor();
            }
            this.setVisible(false);
        }catch(Exception x){
            System.out.println("Failed to select mode!");
        }
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
