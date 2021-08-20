package ColorByNumber;

public class AutoSave implements Runnable{
    Canvas canvas;
    DataManager manager;
    public AutoSave(Canvas Icanvas){
        //initiates refrence varibles
        //==========
        canvas=Icanvas;
        manager=new DataManager();
        //==========
    }

    @Override
    public void run(){
        //reacurring thread which calls the
        //save canvas method every 10 seconds
        //==========
        try{
            Thread.sleep(10000);
        }catch(Exception x){
            System.out.println("Failed AutoSave Delay!");
        }
        manager.saveCanvas(canvas);
        run();
        //==========
    }
}
