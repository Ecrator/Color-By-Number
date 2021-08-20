package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class CanvasPreview extends JPanel{
    DataManager manager;
    String data;
    int fileNumber;
    Tile[][] tiles;
    int[] canvasSize;
    TilePallete pallete;
    public CanvasPreview(int IfileNumber) throws IOException{
        //Intializes values and components
        //==========
        fileNumber=IfileNumber;
        manager=new DataManager();
        data=manager.getData(fileNumber);
        pallete=new TilePallete(this);
        //==========
        readData();
    }

    public void readData(){
        try{
            //strips the pallete data and gives it to
            //the pallete object to load
            //==========
            String palleteData=data.split("#")[0];
            pallete.loadColors(palleteData);
            //==========
            //strips the canvas porportions and
            //sets the layout porportional to
            //the canvasSize variable
            //==========
            String[] CanvasSize=data.split("#")[1].split(",");
            canvasSize=new int[]{Integer.valueOf(CanvasSize[0]), Integer.valueOf(CanvasSize[0])};
            tiles=new Tile[Integer.valueOf(canvasSize[0])][Integer.valueOf(canvasSize[1])];
            this.setLayout(new GridLayout(canvasSize[0], canvasSize[1]));
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
                tiles[Integer.valueOf(coords[0])][Integer.valueOf(coords[1])]=new Tile(palleteNumber, filled, color);
            }
            //==========
            updateCanvas();
        }catch(Exception x){
            System.out.println("Failed to read data!");
            System.out.println(x);
        }
    }

    public void updateCanvas(){
        //re-adds tils array to the container, uninitialized
        //tiles are initialized as placeholders
        //==========
        for(int y=0;y<canvasSize[1];y++){
            for(int x=0;x<canvasSize[0];x++){
                if(tiles[x][y]!=null){
                    this.add(tiles[x][y]);
                }else{
                    tiles[x][y]=new Tile(-1);
                    this.add(tiles[x][y]);
                }
            }
        }
        //==========
    }
}
