package editor;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Mike on 3/1/2016.
 */
public class cursor {
    private Rectangle boundedBox = new Rectangle(0,0);

    public static void updateLine(){
        Editor.cursorX = 5;
        Editor.cursorY += Editor.fontSize;
    }

    public double updatePos(String checkSize){
        Text c = new Text(0,0, checkSize);
        c.setFont(Font.font("Verdana", 12));
        double width = c.getLayoutBounds().getWidth();
        return width;
    }

    public double updatePosY(String checkSize){
        Text c = new Text(0,0, checkSize);
        c.setFont(Font.font("Verdana", 12));
        double height = c.getLayoutBounds().getHeight();
        return height;
    }


}
