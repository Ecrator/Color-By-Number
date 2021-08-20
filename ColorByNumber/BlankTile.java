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
        //===========
    }
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
}
