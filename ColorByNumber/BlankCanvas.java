package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class BlankCanvas extends JFrame{
    DataManager manager;
    String name;
    int fileNumber;
    int[] canvasSize;
    BlankPallete pallete;
    int selectedPallete;
    BlankTile[][] tiles;
    JPanel container;
    JScrollPane pane;
    String[] canvasData;
    public BlankCanvas(String Iname, int[] IcanvasSize){
        //initiates components and variables
        //==========
        name=Iname;
        canvasSize=IcanvasSize;
        pallete=new BlankPallete(this, false);
        container=new JPanel();
        manager=new DataManager();
        container.setLayout(new GridLayout(canvasSize[0], canvasSize[1]));
        pane=new JScrollPane(container);
        tiles=new BlankTile[canvasSize[0]][canvasSize[1]];
        //==========
        //initiates frame
        //==========
        this.setSize(800,800);
        this.setResizable(false);
        this.setTitle(name);
        this.add(pane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //==========
        updateCanvas();
    }

    public BlankCanvas(String Iname, int IfileNumber) throws IOException{
        //initializes values and components
        //==========
        name=Iname;
        fileNumber=IfileNumber;
        manager=new DataManager();
        canvasData=manager.getData(fileNumber).split("#");
        pallete=new BlankPallete(this, true);
        container=new JPanel();
        pane=new JScrollPane(container);
        canvasSize=new int[]{Integer.valueOf(canvasData[1].split(",")[0]), Integer.valueOf(canvasData[1].split(",")[1])};
        container.setLayout(new GridLayout(canvasSize[0], canvasSize[1]));
        tiles=new BlankTile[canvasSize[0]][canvasSize[1]];
        //==========
        //creates a BlankPallete and gives it the
        //pallete data
        //==========
        String[] palleteData=canvasData[0].split("@");
        int temp=0;
        for(String colorData: palleteData){
            String[] colorValues=colorData.split(",");
            int[] values=new int[]{Integer.valueOf(colorValues[0]), Integer.valueOf(colorValues[1]), Integer.valueOf(colorValues[2])};
            pallete.addColor(values[0], values[1], values[2]);
            temp++;
        }
        //==========
        //reads tiledata
        //==========
        String tileData=canvasData[2];
            String[] splitTileData=tileData.split("%");
            for(String tile: splitTileData){
                String[] data=tile.split("/");
                int palleteNumber=Integer.valueOf(data[1]);
                Color color=pallete.colors.get(Integer.valueOf(data[1])+1).color;
                String[] coords=data[0].split(",");
                tiles[Integer.valueOf(coords[0])][Integer.valueOf(coords[1])]=new BlankTile(this, color, palleteNumber+1);
            }
        //==========
        //initiates frame
        //==========
        this.setSize(800,800);
        this.setResizable(false);
        this.setTitle(name);
        this.add(pane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //==========
        updateCanvas();
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
                    tiles[x][y]=new BlankTile(this);
                    container.add(tiles[x][y]);
                }
            }
        }
        //==========
    }

    public String getPalleteData(){
        //returns a string which list the RGB values
        //of each pallete color
        //==========
        String data="";
        for(int i=1;i<pallete.colors.size();i++){
            if(isUsed(pallete.colors.get(i).color)){
                if(data.length()>3){
                    data+="@";
                }
                data+=pallete.colors.get(i).color.getRed()+",";
                data+=pallete.colors.get(i).color.getGreen()+",";
                data+=pallete.colors.get(i).color.getBlue();
            }else{
                for(int y=0;y<canvasSize[1];y++){
                    for(int x=0;x<canvasSize[0];x++){
                        if(tiles[x][y].palleteNumber>i){
                            tiles[x][y].palleteNumber-=1;
                        }
                    }
                }
            }
        }
        return data;
        //==========
    }

    public boolean isUsed(Color color){
        for(int y=0;y<canvasSize[1];y++){
            for(int x=0;x<canvasSize[0];x++){
                if(tiles[x][y].color==color){
                    return true;
                }
            }
        }
        return false;
    }
}
