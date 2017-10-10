package editor;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Mike on 3/7/2016.
 */
public class twins {
    private static HashMap[] undoStack = new HashMap[100];
    private static HashMap[] redoStack = new HashMap[100];
    private int writeIndexU=0;
    private int writeIndexR = 0;

    public void push(String tag, lineBuffer.Node nodeToAdd ){
        //TODO: bug in undo " I try" -> "I ry" undo " I try" undo keep " ry"
        //TODO: change current to match.
        HashMap<String, linkedList.Node> pair = new HashMap<>();
        pair.put(tag, nodeToAdd);
        undoStack[writeIndexU%100] = pair;
        redoStack = new HashMap[100];
        writeIndexU += 1;


    }

    public void undo(){
        if (writeIndexU == 0 || undoStack[(writeIndexU%100)-1]==null){
            System.out.println(writeIndexU); return;}
        HashMap hm = popU();
        if (hm == null){
            System.out.println("usatck: " + undoStack.toString() + "rStack: " +redoStack.toString());
        }
        if (hm.containsKey("add")){
            System.out.println("add: " + (hm.get("del")));
           /* Editor.stream.addCurrent(((Text)((linkedList.Node)hm.get("add")).item));*/
            ((linkedList.Node) hm.get("add")).prev.next = (linkedList.Node) hm.get("add");
            ((linkedList.Node) hm.get("add")).next.prev = (linkedList.Node)hm.get("add");
            Editor.textBod.getChildren().add(((Text) ((linkedList.Node) hm.get("add")).item));
            Editor.stream.size += 1;
            findCurrent.changeToCurrent(((Text) ((linkedList.Node) hm.get("add")).item));
            System.out.println(Editor.stream.current);
        } else {
            /*Editor.stream.removeCurrent();*/
            ((linkedList.Node) hm.get("del")).prev.next = ( (linkedList.Node)hm.get("del") ).next;
            ((linkedList.Node) hm.get("del")).next.prev = ( (linkedList.Node)hm.get("del") ).prev;
            Editor.textBod.getChildren().remove(((Text) ((linkedList.Node) hm.get("del")).item));
            Editor.stream.size -= 1;
            findCurrent.changeToCurrent(((Text) ((linkedList.Node) hm.get("del")).item));
            System.out.println(Editor.stream.current);
        }
        redoStack[writeIndexR] = hm;
        writeIndexR += 1;

    }

    public void redo(){
        if (writeIndexR == 0 || redoStack[(writeIndexR % 100)-1]==null){
            System.out.println(writeIndexR);return; }
        HashMap hm = popR();
        if (hm.containsKey("add")){
            /*Editor.stream.removeCurrent();*/
            ((linkedList.Node) hm.get("add")).prev.next = ( (linkedList.Node)hm.get("add") ).next;
            ((linkedList.Node) hm.get("add")).next.prev = ( (linkedList.Node)hm.get("add") ).prev;
            Editor.textBod.getChildren().remove(((Text) ((linkedList.Node) hm.get("add")).item));
           /* Editor.stream.size -= 1;*/
            System.out.println("Redo del: " + Editor.stream.size());
            findCurrent.changeToCurrent(((Text) ((linkedList.Node) hm.get("add")).item));

        } else {
            System.out.println( "Redo add: "+((Text)((linkedList.Node)hm.get("del")).item));
            /*Editor.stream.addCurrent(((Text)((linkedList.Node)hm.get("del")).item));*/
            ((linkedList.Node) hm.get("del")).prev.next = (linkedList.Node) hm.get("del");
            ((linkedList.Node) hm.get("del")).next.prev = (linkedList.Node)hm.get("del");
            Editor.textBod.getChildren().add(((Text) ((linkedList.Node) hm.get("del")).item));
            /*Editor.stream.size += 1;*/
            findCurrent.changeToCurrent(((Text) ((linkedList.Node) hm.get("del")).item));
        }

        undoStack[writeIndexU%100] = hm;
        writeIndexU += 1;
    }

    private HashMap popU(){
        if (writeIndexU == 0){ writeIndexU = 100;}
        writeIndexU -= 1;
        HashMap temp = undoStack[writeIndexU%100];
        undoStack[writeIndexU%100] = null;

        return temp;
    }

    private HashMap popR(){
        if (writeIndexR == 0){ writeIndexR = 100;}
        writeIndexR -= 1;
        HashMap temp = redoStack[writeIndexR%100];
        redoStack[writeIndexR%100] = null;

        return temp;
    }
}
