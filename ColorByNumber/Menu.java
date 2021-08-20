package ColorByNumber;
import javax.swing.*;
import java.awt.event.*;
public class Menu extends JFrame implements MouseListener{
    JButton browserButton;
    JButton creatorButton;
    public Menu(){
        //initiates components
        //==========
        browserButton=new JButton("Browser");
        creatorButton=new JButton("Creator");
        browserButton.addMouseListener(this);
        creatorButton.addMouseListener(this);
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
        browserButton.setBounds(45, 50, 200, 100);
        creatorButton.setBounds(45, 200, 200, 100);
        //==========
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //click listener that uses the button
        //text to distinct them
        //==========
        try{
            if(((JButton)e.getSource()).getText().equals("Browser")){
                ioikl,LevelBrowser browser98=new LevelBrowser(); m
                this.setVisible(false);
            }else if(((JButton)e.getSource()).getText().equals("Creator")){
                CanvasCreator creator=new CanvasCreator();
                this.setVisible(false);
            }
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
