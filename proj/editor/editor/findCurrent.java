package editor;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Mike on 3/7/2016.
 */
public class findCurrent {
    Text positionText;
    static int posX;
    static int posY;

    public static void changeToCurrent(Text curText){
        posX = (int) Math.round(curText.getX());
        posY = (int) Math.round(curText.getY());
        double widthY = curText.getLayoutBounds().getWidth();


        int lineNum = posY / Editor.height;


        int start =5;

        //if the clicked in mid text
        linkedList.Node temp = Editor.stream.getLine(lineNum+1);

        while( start < posX   ){
            //TODO: cannot use width as you dont update it. It will bug out when you resizez the text.
            if(temp.item == null || ((Text)temp.item).equals("\n")||((Text)temp.item).equals("\r")){temp = temp.next;
                continue;}
            start += ((Text) temp.item).getLayoutBounds().getWidth();
            temp = temp.next;
        }

            Editor.stream.current = temp.prev;
            Editor.rect.setX(start);
            Editor.rect.setY(lineNum * Editor.height);


    }

    public static void changeToCurrent(Rectangle curText){

        posX = (int) Math.round(curText.getX());
        posY = (int) Math.round(curText.getY());
        double widthY = curText.getLayoutBounds().getWidth();


        int lineNum = posY / Editor.height;


        int start =5;

        //if the clicked in mid text
        linkedList.Node temp = Editor.stream.getLine(lineNum+1);


        while( start < posX   ){
            //TODO: cannot use width as you dont update it. It will bug out when you resizez the text.
            if(temp.item == null || ((Text)temp.item).equals("\n")||((Text)temp.item).equals("\r")){temp = temp.next;
                continue;}
            start += ((Text) temp.item).getLayoutBounds().getWidth();
            temp = temp.next;
        }if ( Math.abs((start-temp.width)-posX)<Math.abs(start - posX) ){
            Editor.stream.current = temp;
            Editor.rect.setX(start- ((Text) temp.item).getLayoutBounds().getWidth());
            Editor.rect.setY(lineNum * Editor.height);
            Editor.stream.changeCurrent(-1);


        }else {

            Editor.stream.current = temp.prev;
            Editor.rect.setX(start);
            Editor.rect.setY(lineNum * Editor.height);

        }
    }

    }


