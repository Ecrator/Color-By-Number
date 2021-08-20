package ColorByNumber;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class LevelBrowser extends JFrame{
    
    JScrollPane pane;
    JPanel container;
    DataManager manager;
    ArrayList<LevelSelection> levels;
    public LevelBrowser() throws IOException{
        //Initializes the container, which
        //levels are shown
        //==========
        container=new JPanel();
        container.setBackground(Color.GRAY);
        manager=new DataManager();
        levels=new ArrayList<LevelSelection>();
        container.setLayout(new GridLayout(manager.levelAmount+2, 1, 3, 3));
        container.setPreferredSize(new Dimension(400, ((manager.levelAmount+2)*200)));
        pane=new JScrollPane(container);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //==========
        //creates LevelSelection objects and adds them
        //to the container
        //==========
        for(int i=0;i<manager.levelAmount;i++){
            levels.add(new LevelSelection(i));
            container.add(levels.get(i));
        }
        //==========
        //Initializes the frame, which the
        //container is added to
        //==========
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setTitle("Level Browser");
        this.add(pane);
        this.setVisible(true);
        //==========
    }
}
