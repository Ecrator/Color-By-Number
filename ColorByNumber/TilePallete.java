package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class TilePallete extends JFrame{
    Canvas canvas;
    ArrayList<PalleteColor> colors;
    JPanel container;
    JScrollPane pane;
    public TilePallete(Canvas Icanvas){
        //Initializes values and components
        //==========
        canvas=Icanvas;
        colors=new ArrayList<>();
        container=new JPanel();
        container.setLayout(new GridLayout(1,1));
        container.setBackground(Color.GRAY);
        pane=new JScrollPane(container);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //==========
        //Initializes Frame
        //==========
        this.setSize(600,150);
        this.setTitle("Pallete");
        this.add(pane);
        this.setVisible(true);
        //==========
    }
    public TilePallete(CanvasPreview Icanvas){
        //Initializes values and components
        //==========
        colors=new ArrayList<>();
        //==========
    }

    public void loadColors(String colorCodes){
        //splits each color code into an array, then
        //creates a PalleteColor object using the code
        //==========
        String[] splitData=colorCodes.split("@");
        int number=0;
        for(String palletes: splitData){
            String[] temp=palletes.split(",");
            int[] values=new int[3];
            for(int i=0;i<3;i++){
                values[i]=Integer.valueOf(temp[i]);
            }
            colors.add(new PalleteColor(values, this, number));
            number++;
        }
        updateColors();
        //==========
    }

    public void updateColors(){
        //clears the container, sets the gridlayout
        //length relative to the amount of colors in the
        //pallete, then adds the color objects in the array
        //==========
        try{
            container.removeAll();
            container.setLayout(new GridLayout(1,colors.size(), 1, 1));
            container.setPreferredSize(new Dimension(300+(colors.size()*50), 150));
            for(PalleteColor color: colors){
                container.add(color);
            }
        }catch(Exception x){}
        //==========
    }
}
