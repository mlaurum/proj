package editor;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Mike on 3/1/2016.
 */
public class wordBuffer<Item>  {


        private ArrayList<linkedList> lineTrack;
        private linkedList<Item> col = new linkedList<>(0,0);

        private int currentLine;
        private int line;


        /* Empty constructor*/
        public wordBuffer(){
            lineTrack = new ArrayList<>();
            lineTrack.add( col);
            line = 0;
        }


        public void addCurrent(Item item){
            lineTrack.get(line).addCurrent(item);
        }

        public void removeCurrent(){

        }

        public void changeCurrent(int pos){
            lineTrack.get(line).changeCurrent(pos);
        }

        public void addLine(Item item){
            line++;
            lineTrack.add(new linkedList(0,0));
        }

        public  void delLine(){
            linkedList.Node temp = (linkedList.Node) lineTrack.get(line).get(0);
            line --;
            lineTrack.get(line).addEnd(temp);

        }

        public Item get(int i){
            Item holdr = (Item) lineTrack.get(line).get(i);
            return holdr;
        }

        public Item getCur(){
            Item holdr = (Item) lineTrack.get(line).getCur();
            return holdr;
        }

        public Item getPrev(){
            Item holdr = (Item) lineTrack.get(line).getPrev();
            return holdr;
        }

        public Item getNext(){
            Item holdr = (Item) lineTrack.get(line).getNext();
            return holdr;
        }

        public boolean isEmpty(){
            if (lineTrack.get(line).size() == 0)
            {return true;}
            return false;
        }

        public int size(){
            return lineTrack.get(line).size();
        }










}
