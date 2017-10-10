package editor;


import javafx.scene.text.Text;

import java.util.ArrayList;

public class linkedList<Item> {

     class Node {
        public Node prev;
        Item item;
        public Node next;
        int width;
        private int place;

        private Node(Node previous, Item item, Node next, int width, int place){
            prev = previous;
            this.item = item;
            this.next = next;
            this.width = width;
            this.place = place;
        }


    }


    private static int size;

    public Node sentinel;
    public Node current;
    private int posy=0;
    private int posx=0;
    public static twins undoRedo;

    public Node getSentinel(){
        return sentinel;
    }

    public linkedList(int width, int height){

        sentinel = new Node(null, null, null, width, height);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size=0;
        current = sentinel;

    }

    /*public linkedList(Item x){
	sentinel = new Node(sentinel, null, null);
	Node one = new Node(sentinel , x , sentinel);
	sentinel.next = one;
	sentinel.prev = one;
	size=1;
	}*/


    public void addEnd(Node item){

        current.next.prev=item;
        current.next = item;
        size += 1;
    }
/*
    public void addLast(Item item){
        Node back = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = back;
        sentinel.prev = back;
        size += 1;
    }*/
    public void addCurrent( Item item){

        Node front = new Node(current, item, current.next, (int)Math.round(((Text)item).getLayoutBounds().getWidth()) , posx);
        current.next.prev=front;
        current.next = front;
        size += 1;
        Editor.stream.changeCurrent(1);
        Editor.textBod.getChildren().add(Editor.stream.getCur().item);



    }

    //TODO:finish
    public void changeCurrent(int pos){
        if (pos == -1){

            current = current.prev;

        }else if(pos == 1){
            current = current.next;
        }
        else if (current.item == null){
            current = current.prev;
        }


    }




    public Node getPrev(){

        return current.prev;
    }
    public Node getNext(){
        return current.next;
    }
    public Node getCur(){
        return current;
    }


    public boolean isEmpty(){
        if (size == 0)
        {return true;}
        return false;
    }

    public int size(){
        return size;
    }

    public Node get(int index){
        //change to size implementation...
        if (index>=size){
            return null;
        }
        Node p = sentinel;
        for(int i = 0; i<=index; i++){
            p = p.next;
        }
        return p;
    }

    public Item removeFirst(){
        //check size zero list
        Node toDel = sentinel.next;
        sentinel.next = toDel.next;
        toDel.next.prev = sentinel;
        size-=1;
        return toDel.item;

    }

    public Item removeLast(){
        //remeber to check if it is a size zero list
        Node toDel = sentinel.prev;
        sentinel.prev = toDel.prev;
        toDel.prev.next = sentinel;
        size-=1;
        return toDel.item;
    }

    public Node removeCurrent(){
        //remeber to check if it is a size zero list
         /*Item temp =  current.item;
        current.item = (Item) new Text(current.width, current.place, " ");*/

        Node toDel = current;
        current = toDel.prev;
        toDel.prev.next = toDel.next;

       /* current.prev.next = back;
        sentinel.prev = back;
*/
        size-=1;

        Editor.textBod.getChildren().remove(toDel.item);
        return toDel;

    }

    /*public addLine(Item item){
        Node front = new Node(current, item, current.next, ((Text)item).getLayoutBounds().getWidth(), ((Text)item).getLayoutBounds().getHeight());
        current.next.prev=front;
        current.next = front;
        size += 1;
        posy += Editor.fontSize;
        posx = 0;
        Editor.cursorX = posx;
    }*/








}
