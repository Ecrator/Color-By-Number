package ColorByNumber;
import java.util.ArrayList;
import java.io.*;
public class DataManager {
    ArrayList<File> Files;
    int levelAmount;
    public DataManager(){
        //Initializes objects
        //==========
        Files=new ArrayList<File>();
        levelAmount=-1;
        //==========
        //uses a filereader which checks for a file called "Canvas",
        //whith an ascending variable until the file isn't found,
        //levelAmount is then set to this variable and file is added
        //to the File array
        //==========
        try{
            int temp=0;
            while(true){
                try{
                    FileReader testExist=new FileReader("Data\\Canvas"+String.valueOf(temp)+".txt");
                    testExist.close();
                    File tempFile=new File("Data\\Canvas"+String.valueOf(temp)+".txt");
                    Files.add(tempFile);
                    temp++;
                }catch(Exception x){
                    levelAmount=temp;
                    break;
                }
            }
        }catch(Exception x){
            System.out.println("Failed to read file amount!");
            System.out.println(x);
        }
        //==========
    }
    public String getName(int fileNumber) throws IOException{
        //Goes through each line in the file until
        //it finds the Name keyword, which it then splits
        //the String and returns the name left behind
        //==========
        FileReader file=new FileReader(Files.get(fileNumber));
        BufferedReader reader=new BufferedReader(file);
        String name;
        try{
            while(true){
                String line=reader.readLine();
                if(line.contains("Name: ")){
                    name=line.split(": ")[1];
                    reader.close();
                    return name;
                }
            }
        }catch(Exception x){
            System.out.println("Failed to get file name!");
            System.out.println(x);
        }
        return null;
        //==========
    }

    public String getProgress(int fileNumber)throws IOException{
        //Goes through each line in the file until
        //it finds the Progress keyword, which it then splits
        //the String and returns the progress left behind
        //==========
        FileReader file=new FileReader(Files.get(fileNumber));
        BufferedReader reader=new BufferedReader(file);
        String progress;
        try{
            while(true){
                String line=reader.readLine();
                if(line.contains("Progress: ")){
                    progress=line.split(": ")[1];
                    reader.close();
                    return progress;
                }
            }
        }catch(Exception x){
            System.out.println("Failed to get file progress!");
            System.out.println(x);
        }
        return null;
        //==========
    }

    public String getData(int fileNumber) throws IOException{
        //Goes through each line in the file until
        //it finds the Data keyword, which it then splits
        //the String and returns the data left behind
        //==========
        FileReader file=new FileReader(Files.get(fileNumber));
        BufferedReader reader=new BufferedReader(file);
        String data;
        try{
            while(true){
                String line=reader.readLine();
                if(line.contains("TileData: ")){
                    data=line.split(": ")[1];
                    reader.close();
                    return data;
                }
            }
        }catch(Exception x){
            System.out.println("Failed to get file data!");
            System.out.println(x);
        }
        return null;
        //==========
    }

    public void saveCanvas(Canvas canvas){
        try{
            //Initiates refrence and local variables
            //==========
            int fileNumber=canvas.fileNumber;
            Tile[][] tiles=canvas.tiles;
            String name=getName(fileNumber);
            String canvasSize=String.valueOf(canvas.canvasSize[0])+","+String.valueOf(canvas.canvasSize[1]);
            String palleteData=canvas.data.split("#")[0];
            String progress=String.valueOf(canvas.getProgress());
            FileWriter Fwriter=new FileWriter(Files.get(fileNumber));
            BufferedWriter writer=new BufferedWriter(Fwriter);
            //==========
            //writes the name, progress, pallete, and 
            //canvas size, using the variables
            //==========
            writer.write("Name: "+name);
            writer.write("\nProgress: "+progress+"%");
            writer.write("\nTileData: "+palleteData+"#"+canvasSize+"#");
            //==========
            //writes the data of all the tiles, which
            //aren't placeholders
            //==========
            boolean firstTile=true;
            for(int y=0;y<canvas.canvasSize[1];y++){
                for(int x=0;x<canvas.canvasSize[0];x++){
                    if(tiles[x][y].palleteNumber>=0){
                        if(!firstTile){
                            writer.write("%");
                        }else{firstTile=false;}
                        writer.write(x+","+y+"/");
                        writer.write(tiles[x][y].palleteNumber+"/");
                        writer.write(String.valueOf(tiles[x][y].filled));
                    }
                }
            }
            //==========
            writer.close();
        }catch(Exception x){
            System.out.println("Failed to save canvas!");
            System.out.println(x);
        }
    }

    public void overwriteCanvas(BlankCanvas canvas){
        try{
            //Initiates refrence and local variables
            //==========
            BlankTile[][] tiles=canvas.tiles;
            String name=canvas.name;
            String canvasSize=String.valueOf(canvas.canvasSize[0])+","+String.valueOf(canvas.canvasSize[1]);
            String palleteData=canvas.getPalleteData();
            FileWriter Fwriter=new FileWriter(new File("Data\\Canvas"+canvas.fileNumber+".txt"));
            BufferedWriter writer=new BufferedWriter(Fwriter);
            //==========
            //writes the name, progress, pallete, and 
            //canvas size, using the variables
            //==========
            writer.write("Name: "+name);
            writer.write("\nProgress: 0%");
            writer.write("\nTileData: "+palleteData+"#"+canvasSize+"#");
            //==========
            //writes the data of all the tiles, which
            //aren't placeholders
            //==========
            boolean firstTile=true;
            for(int y=0;y<canvas.canvasSize[1];y++){
                for(int x=0;x<canvas.canvasSize[0];x++){
                    if(tiles[x][y].palleteNumber>0){
                        if(!firstTile){
                            writer.write("%");
                        }else{firstTile=false;}
                        writer.write(x+","+y+"/");
                        writer.write(tiles[x][y].palleteNumber-1+"/");
                        writer.write("false");
                    }
                }
            }
            //==========
            writer.close();
        }catch(Exception x){
            System.out.println("Failed to overwrite canvas!");
            System.out.println(x);
        }
    }

    public void createCanvas(BlankCanvas canvas){
        try{
            //Initiates refrence and local variables
            //==========
            BlankTile[][] tiles=canvas.tiles;
            String name=canvas.name;
            String canvasSize=String.valueOf(canvas.canvasSize[0])+","+String.valueOf(canvas.canvasSize[1]);
            String palleteData=canvas.getPalleteData();
            FileWriter Fwriter=new FileWriter(new File("Data\\Canvas"+levelAmount+".txt"));
            BufferedWriter writer=new BufferedWriter(Fwriter);
            //==========
            //writes the name, progress, pallete, and 
            //canvas size, using the variables
            //==========
            writer.write("Name: "+name);
            writer.write("\nProgress: 0%");
            writer.write("\nTileData: "+palleteData+"#"+canvasSize+"#");
            //==========
            //writes the data of all the tiles, which
            //aren't placeholders
            //==========
            boolean firstTile=true;
            for(int y=0;y<canvas.canvasSize[1];y++){
                for(int x=0;x<canvas.canvasSize[0];x++){
                    if(tiles[x][y].palleteNumber>0){
                        if(!firstTile){
                            writer.write("%");
                        }else{firstTile=false;}
                        writer.write(x+","+y+"/");
                        writer.write(tiles[x][y].palleteNumber-1+"/");
                        writer.write("false");
                    }
                }
            }
            //==========
            writer.close();
        }catch(Exception x){
            System.out.println("Failed to create canvas!");
            System.out.println(x);
        }
    }

    public void deleteCanvas(int fileNumber){
        //deletes the selected canvas and renames
        //the canvases after it to keep numerical order
        //==========
        try{
            Files.get(fileNumber).delete();
            Files.remove(fileNumber);
            for(int i=fileNumber;i<Files.size();i++){
                File tempFile=new File("Data\\Canvas"+i+".txt");
                Files.get(i).renameTo(tempFile);
            }
        }catch(Exception x){
            System.out.println("Failed to delete Canvas!");
            System.out.println(x);
        }
        //==========
    }
}
