package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class BlankPallete extends JFrame implements MouseListener{
    JPanel container;
    BlankCanvas canvas;
    ArrayList<PalleteColor> colors;
    JSlider rSlider, gSlider, bSlider;
    JPanel previewColor;
    JButton addButton;
    JButton saveButton;
    Boolean editMode;
    public BlankPallete(BlankCanvas Icanvas, Boolean IeditMode){
        //initiates components
        //==========
        canvas=Icanvas;
        container=new JPanel();
        previewColor=new JPanel();
        editMode=IeditMode;
        container.setBackground(Color.GRAY);
        container.setPreferredSize(new Dimension(800, 200));
        addButton=new JButton("Add");
        saveButton=new JButton("Save");
        colors=new ArrayList<PalleteColor>();
        rSlider=new JSlider(0, 255);
        gSlider=new JSlider(0, 255);
        bSlider=new JSlider(0, 255);
        rSlider.addMouseListener(this);
        gSlider.addMouseListener(this);
        bSlider.addMouseListener(this);
        this.addMouseListener(this);
        addButton.addMouseListener(this);
        saveButton.addMouseListener(this);
        //==========
        //initiates frame
        //==========
        this.setSize(400,400);
        this.setTitle("Pallete");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //==========
        //organizes components
        //==========
        this.add(container);
        container.setBounds(0, 200, 400, 160);
        this.add(rSlider);
        rSlider.setBounds(30, 35, 200, 20);
        this.add(gSlider);
        gSlider.setBounds(30, 85, 200, 20);
        this.add(bSlider);
        bSlider.setBounds(30, 135, 200, 20);
        this.add(previewColor);
        previewColor.setBounds(250, 20, 100, 150);
        this.add(addButton);
        addButton.setBounds(135, 160, 80, 30);
        this.add(saveButton);
        saveButton.setBounds(45, 160, 80, 30);
        //==========
        //adds eraser object
        //==========
        colors.add(new PalleteColor(this));
        //==========
        updatePreview();
        container.removeAll();
        container.setLayout(new GridLayout((colors.size()/5)+1,colors.size(), 1, 1));
        for(PalleteColor color: colors){
            container.add(color);
        }
        container.revalidate();
    }

    public void updatePreview(){
        //sets the preview panel color according
        //to the slider values
        //==========
        previewColor.setBackground(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
        //==========
    }

    public void addColor(){
        //adds a palleteColor object to the array
        //and the container
        //==========
        colors.add(new PalleteColor(previewColor.getBackground(), this, colors.size()));
        container.removeAll();
        container.setLayout(new GridLayout((colors.size()/5)+1,colors.size(), 1, 1));
        for(PalleteColor color: colors){
            container.add(color);
        }
        container.revalidate();
        //==========
    }

    public void addColor(int r, int g, int b){
        //adds a palleteColor object to the array
        //and the container
        //==========
        Color Color=new Color(r, g, b);
        colors.add(new PalleteColor(Color, this, colors.size()));
        container.removeAll();
        container.setLayout(new GridLayout((colors.size()/5)+1,colors.size(), 1, 1));
        for(PalleteColor color: colors){
            container.add(color);
        }
        container.revalidate();
        //==========
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //adds a palleteColor object if the
        //button is clicked
        //==========
        try{
            if(((JButton)e.getSource()).getText().equals("Add")){
                addColor();
            }
        }catch(Exception x){}
        //==========
        //creates a text file containing the canvas data
        //or overwrites an existing file if in editMode
        //==========
        try{
            if(((JButton)e.getSource()).getText().equals("Save")){
                if(!editMode){
                    canvas.manager.createCanvas(canvas);
                }else{
                    canvas.manager.overwriteCanvas(canvas);
                }
                this.setVisible(false);
                canvas.setVisible(false);
                Menu menu=new Menu();
            }
        }catch(Exception x){}
        //==========
    }
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){
        //updates preview while in frame
        //==========
        updatePreview();
        //==========
    }
    @Override
    public void mouseExited(MouseEvent e){}
}
