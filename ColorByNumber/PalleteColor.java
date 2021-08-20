package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PalleteColor extends JPanel implements MouseListener{
    Color color;
    TilePallete pallete;
    BlankPallete Bpallete;
    int palleteNumber;
    JLabel numberLabel;
    boolean blank;
    public PalleteColor(int[] values, TilePallete Ipallete, int IpalleteNumber){
        //Initializes values and components
        //==========
        blank=false;
        color=new Color(values[0], values[1], values[2]);
        this.setBackground(color);
        pallete=Ipallete;
        palleteNumber=IpalleteNumber;
        numberLabel=new JLabel(String.valueOf(palleteNumber));
        this.add(numberLabel, SwingConstants.CENTER);
        this.addMouseListener(this);
        //==========
    }
    public PalleteColor(Color Icolor, BlankPallete Ipallete, int IpalleteNumber){
        //Initializes values and components(blank)
        //==========
        blank=true;
        color=Icolor;
        this.setBackground(color);
        Bpallete=Ipallete;
        palleteNumber=IpalleteNumber;
        numberLabel=new JLabel(String.valueOf(palleteNumber));
        this.add(numberLabel, SwingConstants.CENTER);
        this.addMouseListener(this);
        //==========
    }
    //Eraser
    //==========
    public PalleteColor(BlankPallete Ipallete){
        blank=true;
        color=UIManager.getColor("Panel.background");
        this.setBackground(color);
        Bpallete=Ipallete;
        numberLabel=new JLabel("Eraser");
        this.add(numberLabel, SwingConstants.CENTER);
        this.addMouseListener(this);
    }
    //==========
    @Override
    public void mouseClicked(MouseEvent e){
        //sets the Canvas's selected pallete
        //through refrence variables
        //==========
        if(!blank){
            pallete.canvas.selectedPallete=palleteNumber;
        }else{
            Bpallete.canvas.selectedPallete=palleteNumber;
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
