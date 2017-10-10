package editor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.LinkedList;

public class Editorfake extends Application {
    public static final int WINWIDTH = 500;
    public static final int WINHEIGHT = 500;
     /** An EventHandler to handle keys that get pressed. */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
        double textPosX;
        double textPosY;
        LinkedList<String> stream = new LinkedList<String>();
        public static final int LITERALLY_ANY_NUMBER = 20;
        /** The Text to display on the screen. */
        private Text displayText = new Text(LITERALLY_ANY_NUMBER, LITERALLY_ANY_NUMBER, "");
        private int fontSize = LITERALLY_ANY_NUMBER;
        

        private String fontName = "Verdana";

        public KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            textPosX = 0;
            textPosY = 0;

            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(textPosX, textPosY, "");
            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font(fontName, fontSize));

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
                
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace 
                    // key, which is represented as a character of value = 8 on Windows.
                    stream.addLast(characterTyped);
                    String current = "";
                    for (int i = 0; i < stream.size(); i++) {
                        current += stream.get(i);
                    }
                    displayText.setText(current);
                    keyEvent.consume();

                } else if (stream.size() > 0 && characterTyped.charAt(0) == 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace 
                // key, which is represented as a character of value = 8 on Windows.
                    String current = "";

                    for (int i = 0; i < stream.size() - 1; i++) {
                        current += stream.get(i);
                    }
                    stream.removeLast();
                    
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
            // Figure out the size of the current text.
            double textHeight = displayText.getLayoutBounds().getHeight();
            double textWidth = displayText.getLayoutBounds().getWidth();

            // Calculate the position so that the text will be placed to the right.
            textPosY = textPosY;
            textPosX = textPosX;

            // Re-position the text.
            displayText.setX(textPosX);
            displayText.setY(textPosY);

            // Make sure the text appears in front of any other objects you might add.
            
        }
    }


    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        
        Scene scene = new Scene(root, WINWIDTH, WINHEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root, WINWIDTH, WINHEIGHT);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        primaryStage.setTitle("Letter Display");

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
