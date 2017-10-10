package editor;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mike on 3/5/2016.
 */
public class lineBuffer<Item> extends  linkedList<Item> {
    public HashMap<Integer, Node> lineTrack = new HashMap<>();
    public int line =0;
    public static int size = 0;
    public lineBuffer(){
        super(5,5);
    }
    public void newLine(Node cur){

        lineTrack.put(line , cur);
        line+=1;
        size += 1;
    }

    public void clearLine(){
        lineTrack.clear();
        size = 0;
        line = 0;
    }

    public static int numLine(){
        return size;
    }

    public Node getLine(int liney){


        return lineTrack.get(liney-1);

    }

    public String toString(){
        return lineTrack.toString();
        /*String j = "";
        for (int i = 0; i<lineTrack.size(); i++){
            j+= ((lineTrack.get(i).next.item) + ", ");
            System.out.println(i);
        }
        return j;*/

    }
}
