package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BlankTile extends JPanel implements MouseListener{
    Color color;
    int palleteNumber;
    BlankCanvas canvas;
    public BlankTile(BlankCanvas Icanvas){
        //Initializes values and components
        //==========
        canvas=Icanvas;
        this.addMouseListener(this);
        //==========
    }

    public BlankTile(BlankCanvas Icanvas, Color Icolor, int IpalleteNumber){
        //initializes values and components
        //==========
        canvas=Icanvas;
        color=Icolor;
        palleteNumber=IpalleteNumber;
        this.setBackground(color);
        this.addMouseListener(this);
        //==========
    }
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){
        //if tile is clicked with the correct
        //selected color, it becomes filled
        //===========
        color=canvas.pallete.colors.get(canvas.selectedPallete).color;
        palleteNumber=canvas.selectedPallete;
        this.setBackground(color);
        canvas.mouseDown=true;
        //===========
    }
    @Override
    public void mouseReleased(MouseEvent e){
        canvas.mouseDown=false;
    }
    @Override
    public void mouseEntered(MouseEvent e){
        if(color==null||color==UIManager.getColor("Panel.background")){
            this.setBackground(Color.YELLOW);
        }
        if(canvas.mouseDown){
            color=canvas.pallete.colors.get(canvas.selectedPallete).color;
            palleteNumber=canvas.selectedPallete;
            this.setBackground(color);
        }
    }
    @Override
    public void mouseExited(MouseEvent e){
        if(color==null||color==UIManager.getColor("Panel.background")){
            this.setBackground(UIManager.getColor("Panel.background"));
        }
    }
}
