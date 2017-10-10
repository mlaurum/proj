package editor;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Hashtable;
import java.util.LinkedList;



/**
 * Created by Mike on 2/29/2016.
 */
public class KeyEventHandler implements EventHandler<KeyEvent> {
    double textPosX;
    double textPosY;
    int textNum;
    private static final Hashtable<Integer, String> stream = new Hashtable<>();
    public static final int LITERALLY_ANY_NUMBER = 20;
    /** The Text to display on the screen. */
    private Text displayText = new Text(LITERALLY_ANY_NUMBER, LITERALLY_ANY_NUMBER, "");
    private int fontSize = LITERALLY_ANY_NUMBER;


    private String fontName = "Verdana";

    public KeyEventHandler(Group root) {
        textPosX = 0;
        textPosY = 0;
        textNum = 0;


        // Initialize some empty text and add it to root so that it will be displayed.
        displayText = new Text(textPosX, textPosY, "");
        // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
        // that when the text is assigned a y-position, that position corresponds to the
        // highest position across all letters (for example, the top of a letter like "I", as
        // opposed to the top of a letter like "e"), which makes calculating positions much
        // simpler!

        displayText.setTextOrigin(VPos.TOP);
        displayText.setFont(Font.font(fontName, fontSize));
        displayText.setWrappingWidth(10);

        // All new Nodes need to be added to the root in order to be displayed.
        root.getChildren().add(displayText);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
            // the KEY_TYPED event, javafx handles the "Shift" key and associated
            // capitalization.
            String characterTyped = keyEvent.getCharacter();
            String current = "";

            if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.
                stream.put(textNum, characterTyped);
                for (int i = 0; i < stream.size(); i++) {
                    current += stream.get(i);
                }
                System.out.println(stream.toString());
                displayText.setText(current);
                textNum += 1;
                keyEvent.consume();

            } else if (stream.size() > 0 && characterTyped.charAt(0) == 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.
                textNum -= 1;
                stream.remove(textNum);
                for (int i = 0; i < stream.size(); i++) {
                    current += stream.get(i);
                }
                System.out.println(stream.toString());
                displayText.setText(current);
                keyEvent.consume();

            }
            repoText();

        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
            // events have a code that we can check (KEY_TYPED events don't have an associated
            // KeyCode).
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.UP) {
                fontSize += 5;
                displayText.setFont(Font.font(fontName, fontSize));

            } else if (code == KeyCode.DOWN) {
                fontSize = Math.max(0, fontSize - 5);
                displayText.setFont(Font.font(fontName, fontSize));

            }
        }
    }

    private void repoText() {


        // Re-position the text.
        displayText.setX(textPosX);
        displayText.setY(textPosY);

        // Make sure the text appears in front of any other objects you might add.

    }
}