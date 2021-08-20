package ColorByNumber;
import javax.swing.*;
import java.awt.event.*;
public class CanvasCreator extends JFrame implements MouseListener{
    JTextField nameField;
    JTextField sizeField;
    JLabel nameLabel;
    JLabel sizeLabel;
    JButton createButton;
    public CanvasCreator(){
        //intializes components
        //==========
        nameLabel=new JLabel("Name: ");
        sizeLabel=new JLabel("Size(x,y): ");
        nameField=new JTextField();
        sizeField=new JTextField();
        createButton=new JButton("Create");
        createButton.addMouseListener(this);
        //==========
        //initializes frame
        //==========
        this.setSize(300,200);
        this.setTitle("Canvas Settings");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //==========
        //organizes components
        //==========
        this.add(nameLabel);
        nameLabel.setBounds(50, 30, 50, 10);
        this.add(sizeLabel);
        sizeLabel.setBounds(50, 95, 75, 20);
        this.add(nameField);
        nameField.setBounds(90, 25, 150, 20);
        this.add(sizeField);
        sizeField.setBounds(105, 95, 150, 20);
        this.add(createButton);
        createButton.setBounds(110, 125, 80, 35);
        //==========
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //creates a blank canvas, the button
        //is the only component with a click listener
        //==========
        try{
            String[] sizeString=sizeField.getText().split(",");
            int[] canvasSize=new int[]{Integer.valueOf(sizeString[0]), Integer.valueOf(sizeString[1])};
            String name=nameField.getText();
            BlankCanvas canvas=new BlankCanvas(name, canvasSize);
            this.setVisible(false);
        }catch(Exception x){
            System.out.println("Failed to create blank canvas!");
            System.out.println(x);
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
