package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Tile extends JPanel implements MouseListener{
    Color color;
    boolean filled;
    int palleteNumber;
    JLabel numberLabel;
    Canvas canvas;
    public Tile(int IpalleteNumber, boolean Ifilled, Color Icolor, Canvas Icanvas){
        //Initializes values and components
        //==========
        palleteNumber=IpalleteNumber;
        filled=Ifilled;
        color=Icolor;
        canvas=Icanvas;
        if(filled){
            this.setBackground(color);
        }else{
            this.addMouseListener(this);
            numberLabel=new JLabel(String.valueOf(palleteNumber));
            this.add(numberLabel, SwingConstants.CENTER);
        }
        //==========
    }
    //constructor for preview tile
    //==========
    public Tile(int IpalleteNumber, boolean Ifilled, Color Icolor){
        //Initializes values and components
        //==========
        palleteNumber=IpalleteNumber;
        filled=Ifilled;
        color=Icolor;
        if(filled){
            this.setBackground(color);
        }
        //==========
    }
    //==========
    //contructor for placeholder tile
    //==========
    public Tile(int IpalleteNumber){
        palleteNumber=IpalleteNumber;
    }
    //==========
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){
        //if tile is clicked with the correct
        //selected color, it becomes filled
        //===========
        if(canvas.selectedPallete==palleteNumber&& !filled){
            filled=true;
            this.setBackground(color);
            this.remove(numberLabel);
        }
        //===========
        canvas.mouseDown=true;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        canvas.mouseDown=false;
    }
    @Override
    public void mouseEntered(MouseEvent e){
        //evaluates wether to fill the tile if
        //the mouse stays down
        //==========
        if(canvas.mouseDown){
            if(canvas.selectedPallete==palleteNumber&& !filled){
                filled=true;
                this.setBackground(color);
                this.remove(numberLabel);
            }
        }
        //==========
    }
    @Override
    public void mouseExited(MouseEvent e){}
}
