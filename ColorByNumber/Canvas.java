package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Canvas extends JFrame implements KeyListener{
    DataManager manager;
    String data;
    int fileNumber;
    JScrollPane pane;
    JPanel container;
    TilePallete pallete;
    int selectedPallete;
    Tile[][] tiles;
    int[] canvasSize;
    Thread autoSave;
    public Canvas(int IfileNumber) throws IOException{
        //Intializes values and components
        //==========
        fileNumber=IfileNumber;
        manager=new DataManager();
        container=new JPanel();
        pane=new JScrollPane(container);
        pallete=new TilePallete(this);
        autoSave=new Thread(new AutoSave(this));
        data=manager.getData(fileNumber);
        //==========
        //Initializes Frame
        //==========
        this.setSize(800,800);
        this.setTitle(manager.getName(fileNumber));
        this.addKeyListener(this);
        this.setVisible(true);
        //==========
        readData();
        autoSave.start();
    }

    public void readData(){
        try{
            //strips the pallete data and gives it to
            //the pallete object to load
            //==========
            String palleteData=data.split("#")[0];
            pallete.loadColors(palleteData);
            //==========
            //strips the canvas porportions and sets the
            //container size and layout porportional to
            //the canvasSize variable
            //==========
            String[] CanvasSize=data.split("#")[1].split(",");
            canvasSize=new int[]{Integer.valueOf(CanvasSize[0]), Integer.valueOf(CanvasSize[0])};
            tiles=new Tile[Integer.valueOf(canvasSize[0])][Integer.valueOf(canvasSize[1])];
            container.setLayout(new GridLayout(canvasSize[0], canvasSize[1]));
            container.setPreferredSize(new Dimension(800+(canvasSize[0]*30), 800+(canvasSize[1]*30)));
            //==========
            //strips the tile data and creates an array of
            //all the tiles, which the coords determine the position
            //in the tiles array, the pallete number is used to
            //pull the color from the pallete's color array,
            //then the rest of the values are used to make a 
            //tile object, which is Initialized in the tiles array
            //==========
            String tileData=data.split("#")[2];
            String[] splitTileData=tileData.split("%");
            for(String tile: splitTileData){
                String[] data=tile.split("/");
                int palleteNumber=Integer.valueOf(data[1]);
                boolean filled=Boolean.valueOf(data[2]);
                Color color=pallete.colors.get(Integer.valueOf(data[1])).color;
                String[] coords=data[0].split(",");
                tiles[Integer.valueOf(coords[0])][Integer.valueOf(coords[1])]=new Tile(palleteNumber, filled, color, this);
            }
            //==========
            updateCanvas();
        }catch(Exception x){
            System.out.println("Failed to read data!");
            System.out.println(x);
        }
    }

    public void updateCanvas(){
        //re-adds components to frame and the
        //tils array to the container, uninitialized
        //tiles are initialized as placeholders
        //==========
        this.remove(pane);
        this.add(pane);
        container.removeAll();
        container.setBackground(Color.GRAY);
        for(int y=0;y<canvasSize[1];y++){
            for(int x=0;x<canvasSize[0];x++){
                if(tiles[x][y]!=null){
                    container.add(tiles[x][y]);
                }else{
                    tiles[x][y]=new Tile(-1);
                    container.add(tiles[x][y]);
                }
            }
        }
        //==========
    }

    public int getProgress(){
        //increases the total varible when a initiated
        //tile is given, placeholder tiles have a pallete
        //number of -1. Each tiles is checked if it filled or
        //not, determining the filled value, which is divided
        //by the total, multipled by 100, then returned
        //==========
        float filled=0;
        float total=0;
        for(int y=0;y<canvasSize[1];y++){
            for(int x=0;x<canvasSize[0];x++){
                if(tiles[x][y].palleteNumber>=0){
                    if(tiles[x][y].filled){
                        filled++;
                    }
                    total++;
                }
            }
        }
        float temp=(filled/total)*100;
        int progress=(int)temp;
        return progress;
        //==========
    }

    public void Magnify(boolean in){
        //changes the size of the container to magnify
        //the canvas and sets the scrollbar value to 
        //update the pane
        //==========
        Dimension size=container.getPreferredSize();
        JScrollBar bar=pane.getVerticalScrollBar();
        if(in){
            size.width+=60;
            size.height+=60;
            bar.setValue((bar.getMaximum()/2)+(bar.getHeight()/2));
        }else{
            if((size.height-60)>800){
                size.width-=60;
                size.height-=60;
                bar.setValue((bar.getMaximum()/2)-(bar.getHeight()/2));
            }
        }
        container.setPreferredSize(size);
        //==========
    }

    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        //key listener to adjust magnification
        //==========
        char key=e.getKeyChar();
        if(key=='='||key=='+'){
            Magnify(true);
        }else if(key=='-'||key=='_'){
            Magnify(false);
        }else if(key=='s'){
            manager.saveCanvas(this);
        }
        //==========
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
