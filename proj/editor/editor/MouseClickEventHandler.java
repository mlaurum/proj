package editor;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Created by Mike on 3/1/2016.
 */
public class MouseClickEventHandler implements EventHandler< MouseEvent > {

    Text positionText;
    int posX;
    int posY;


    MouseClickEventHandler(Group root){

    }

    @Override
    public void handle(MouseEvent mouseEvent){
        //TODO: this is messde up . it doesn't work.
        posX = (int) Math.round(mouseEvent.getX());
        posY = (int) Math.round(mouseEvent.getY()) - (int) Editor.textBod.getLayoutY();


        int lineNum = posY / Editor.height;
        int colNum = posX;
        int start =5;
        int lineLength = 5;


        if (lineNum > lineBuffer.numLine()){
            Editor.rect.setY((lineBuffer.numLine()-1) * Editor.height );
            lineBuffer.Node last = Editor.stream.getLine(lineBuffer.numLine());
            while(last.next.item != null){
                start += ((Text)last.next.item).getLayoutBounds().getWidth();
                last = last.next;
            }
            Editor.rect.setX(start);
            Editor.stream.current = last;
            mouseEvent.consume();
            return;
        }
        linkedList.Node linecheck = Editor.stream.getLine(lineNum+1);
        if (linecheck == null) {
            return;
        }
        while ( linecheck.item != null && linecheck.next.item != null
                && linecheck.next.next.item != null
                && !((Text)linecheck.next.next.item).getText().equals("\n")){
            lineLength += ((Text) linecheck.item).getLayoutBounds().getWidth();
            linecheck = linecheck.next;
        }
        if (posX > lineLength ) {
            Editor.rect.setX(lineLength + ((Text)linecheck.next.item).getLayoutBounds().getWidth());
            Editor.stream.current = linecheck.next;
            Editor.rect.setY(((Text)Editor.stream.current.item).getY());
            return;
        }

        //if the clicked in mid text
        linkedList.Node temp = Editor.stream.getLine(lineNum+1);


        while( start <= posX ){
            //TODO: cannot use width as you dont update it. It will bug out when you resize the text.
            if(temp.item == null || ((Text)temp.item).equals("\n")){temp = temp.next;
                continue;}
            start += ((Text) temp.item).getLayoutBounds().getWidth();
            temp = temp.next;
        }
        if ( Math.abs((start-temp.width)-posX)<Math.abs(start - posX)
                ){
            Editor.stream.current = temp;
            Editor.rect.setX(start-((Text) temp.item).getLayoutBounds().getWidth());
            Editor.rect.setY(lineNum * Editor.height);
            Editor.stream.changeCurrent(-1);


        }else {
            Editor.stream.current = temp;
            Editor.rect.setX(start);
            Editor.rect.setY(lineNum * Editor.height);

        }
    }



}
