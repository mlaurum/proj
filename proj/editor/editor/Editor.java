package editor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

import java.io.*;
import java.util.List;


public class Editor extends Application {

    public static String title;
    public static int WINWIDTH = 500;
    public static int WINHEIGHT = 500;
    public static int cursorX = 5;
    public static int cursorY = 0;
    static int textPosX = 5;
    static int textPosY= 50;
    int pos = 0;
    public static lineBuffer<Text> stream = new lineBuffer<>();
    public static final int LITERALLY_ANY_NUMBER = 12;
    public static int fontSize = LITERALLY_ANY_NUMBER;
    public Text displayText = new Text(fontSize, fontSize, "");
    public static Rectangle rect;
    public cursor checkSize = new cursor();
    public static String fontName = "Verdana";
    public static Group root = new Group();
    public static Group textBod = new Group();
    public static int height = 15;
    public static int runningHeight= height;
    public static twins undoRedo = new twins();
    public static ScrollBar scrollBar;


    public static void displayAll(String inputFilename){

        try {
            title = inputFilename;
            File inputFile = new File(inputFilename);
            // Check to make sure that the input file exists!
            if (!inputFile.exists()) {
                    /*Editor newEditor = new Editor(inputFilename);
                    newEditor.launch(args);*/
                return;
            }
            FileReader reader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int intRead = -1;
            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            /*FileWriter writer = new FileWriter(inputFilename);*/
            while ((intRead = bufferedReader.read()) != -1) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = (char) intRead;
                /*writer.write(charRead);*/
                Editor.outPut(charRead);
            }

            reRend(stream);
            stream.changeCurrent(1);
            stream.changeCurrent(1);
            reRend(stream);

        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to open file: " + inputFilename);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }


    }
    public static void outPut(char qute){
        String tempString = Character.toString(qute);
        Text j = new Text( tempString);
        j.setFont(Font.font(fontName, fontSize));
        j.setTextOrigin(VPos.TOP);
        stream.addCurrent(j);
//        stream.changeCurrent(1);
//        textBod.getChildren().add(j);
    }

    public class KeyEventHandler implements EventHandler<KeyEvent> {
        int charWidth = 0;
        int charHeight = 0;



        public KeyEventHandler(Group root) {
            textPosX = 0;
            textPosY = 0;


            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(cursorX, cursorY, "");
            displayText.setFont(Font.font(fontName, 12));
            rect = new Rectangle(cursorX, cursorY, 1, height);
            height = (int) Math.round(displayText.getLayoutBounds().getHeight());


            /*if (stream.getCur() == null){
                Text buffer = new Text(cursorX,cursorY,"");
                stream.addCurrent(buffer);
                stream.changeCurrent(1);
            }*/

            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!

            /*cursorX = displayText.getLayoutBounds().getWidth();*/


            // All new Nodes need to be added to the root in order to be displayed.
            /*root.getChildren().add(displayText);*/

            root.getChildren().add(textBod);
            textBod.getChildren().add(rect);
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (keyEvent.isShortcutDown()) {
                    if (keyEvent.getCode() == KeyCode.P) {
                        System.out.print(rect.getX() + ", ");
                        System.out.println(rect.getY());
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.S) {
                        try {

                            //TODO: implment deBug mode
                            FileWriter writer = new FileWriter(title);

                            lineBuffer.Node j = stream.sentinel.next;
                            while (j.next != stream.sentinel) {

                                writer.write(((Text)j.item).getText());
                                j = j.next;
                            }


                            // Close the reader and writer.
                            writer.close();
                        }
                        catch (FileNotFoundException fileNotFoundException) {
                            System.out.println("File not found! Exception was: " + fileNotFoundException);
                        } catch (IOException ioException) {
                            System.out.println("Error when copying; exception was: " + ioException);
                        }
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.Z){
                        undoRedo.undo();
                        reRend(stream);

                    }
                    if (keyEvent.getCode() == KeyCode.Y){
                        undoRedo.redo();
                        reRend(stream);

                    }

                    if (keyEvent.getCode() == KeyCode.EQUALS || keyEvent.getCode() == KeyCode.PLUS) {
                        fontSize += 4;
                        Text j = new Text("d");
                        j.setFont(Font.font(fontName, fontSize));
                        height = (int) Math.round(j.getLayoutBounds().getHeight());
                        reRend(stream);

                    }
                    if (keyEvent.getCode() == KeyCode.MINUS || keyEvent.getCode() == KeyCode.UNDERSCORE) {
                        fontSize -= 4;
                        Text j = new Text("d");
                        j.setFont(Font.font(fontName, fontSize));
                        height = (int) Math.round(j.getLayoutBounds().getHeight());
                        reRend(stream);

                    }

                }
                if (code == KeyCode.ENTER) {
                    return;
                    /*stream.newLine();*/
                    /*Text j = new Text(cursorX, cursorY, "\n");
                    *//*stream.addLine(j);*//*
                    stream.addCurrent(j);

                    //root.getChildren().add(stream.getCur());*/

                }
                if (code == KeyCode.LEFT && stream.getCur().item != null) {

                    int cursPosX = (int) Math.round(rect.getX());
                    int cursPosY = (int) Math.round(rect.getY());
                    charWidth = (int) Math.round(stream.getCur().item.getLayoutBounds().getWidth());

                    if (stream.getCur().item.getText().equals("\n")) {
                        charWidth = (int) Math.round(stream.getPrev().item.getLayoutBounds().getWidth());
                        cursPosX = (int) Math.round(stream.getPrev().item.getX() + charWidth);
                        cursPosY = (int) Math.round(stream.getPrev().item.getY())/2;

                    } else {
                        cursPosX -= charWidth;
                    }
                    stream.changeCurrent(-1);
                    rect.setX(cursPosX);
                    rect.setY(cursPosY);
                    pos--;


                } else if (code == KeyCode.RIGHT && stream.getNext().item != null) {
                    stream.changeCurrent(1);
                    int cursPosX = (int) Math.round(rect.getX());
                    int cursPosY = (int) Math.round(rect.getY());
                    charWidth = (int) Math.round(stream.getCur().item.getLayoutBounds().getWidth());

                    if (stream.getCur().item.getText().equals("\n")) {

                        cursPosX = 5;
                        cursPosY += stream.getPrev().item.getLayoutBounds().getHeight()/2;


                    } else {
                        cursPosX += charWidth;
                    }

                    rect.setX(cursPosX);
                    rect.setY(cursPosY);
                    pos++;

                }

                if (code == KeyCode.UP) {
                    //TODO: fix cases
                    rect.setY(rect.getY() - (int) Math.round((stream.getCur()).item.getLayoutBounds().getHeight()));
                    findCurrent.changeToCurrent(rect);

                } else if (code == KeyCode.DOWN) {
                    //TODO: fix cases
                    rect.setY(rect.getY() + (int) Math.round((stream.getCur()).item.getLayoutBounds().getHeight()));
                    findCurrent.changeToCurrent(rect);
                }
            } else if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();
                if (keyEvent.isShortcutDown()) {
                    keyEvent.consume();
                    return;
                }

                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    if(characterTyped.equals("\r")){
                        characterTyped = "\n";
                    }

                    Text currentText = new Text(cursorX, cursorY, characterTyped);
                    currentText.setTextOrigin(VPos.TOP);
                    currentText.setFont(Font.font(fontName, fontSize));

                    stream.addCurrent(currentText);

                    undoRedo.push("del", stream.getCur());

                    pos++;
                    reRend(stream);
                    /*rect.setX(cursorX);
                    rect.setY(cursorY);*/


                    keyEvent.consume();

                } else if (stream.size() > 0 && characterTyped.charAt(0) == 8 && stream.getCur() != null) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.

                    linkedList.Node deleted = stream.removeCurrent();

                    undoRedo.push("add", deleted);

                    pos--;
                    /*rect.setX(cursorX);
                    rect.setY(cursorY);*/

                    reRend(stream);
                    keyEvent.consume();

                }
                repoText();


            }
        }

        /*for(int i = pos; i < list.size(); i++){
            lineBuffer.Node temp =  list.get(i);

            System.out.println("pos = "+pos);
            System.out.println("i = "+i);
            int cur = Math.round(temp.width);
            System.out.println(cur);
            ((Text)temp.item).setX(hold+cursorX);
            hold +=temp.width;

        }*/
        public void checkSpace(lineBuffer.Node j) {

        }

        private void repoText() {


            // Re-position the text.
            displayText.setX(textPosX);
            displayText.setY(textPosY);

            // Make sure the text appears in front of any other objects you might add.

        }
    }
    public static void reRend(lineBuffer list) {
        int posx = 5;
        int posy = 0;
        int spacepos = 0;

            /*int tempCurs = 5;*/
        stream.clearLine();
        if (list == null){return;}
        lineBuffer.Node temp = list.sentinel;
        Text test = new Text("m");
        test.setFont(Font.font(fontName, fontSize));
        height = (int) test.getLayoutBounds().getHeight();
        rect.setHeight(height);
        rect.setX(posx);
        rect.setY(posy);
        stream.newLine(temp.next);


        for (int i = 0; i < list.size() && temp.next != list.sentinel; i++) {
            if (temp == null || temp.next == null){return;}
            temp = temp.next;
            int charWidth = (int) ((Text)temp.item).getLayoutBounds().getWidth();
            ((Text) temp.item).setFont(Font.font(fontName, fontSize));
            int cur = (int) Math.round(((Text) temp.item).getLayoutBounds().getWidth());


            if (((Text) temp.item).getText().equals(" ")) {
                spacepos = (int) Math.round(((Text) temp.item).getLayoutBounds().getWidth());
                lineBuffer.Node tempo = temp;

                while (tempo.next.item != null && !((Text) tempo.next.item).getText().equals(" ")) {
                    tempo = tempo.next;
                    spacepos += ((Text) tempo.item).getLayoutBounds().getWidth();

                }
//                System.out.println(((Text) temp.item).getX() + "word" + spacepos + " " + temp.next.item);
                if (posx + spacepos > WINWIDTH - (int) Math.round(scrollBar.getWidth())- 5) {
                    posx = 5;
                    posy += height;
                    stream.newLine(temp);

                }

            }

            if (((Text) temp.item).getText().equals("\n") || cursorX + posx + charWidth > WINWIDTH - (int) Math.round(scrollBar.getWidth()) - 5) {
                    /*tempCurs = 5;*/
                posx = 5;
                posy += height;
                stream.newLine(temp);


            }
//
//
//                if (cur + posx > WINWIDTH - 10){
//                    System.out.println("spacepos: "+ spacepos);
//                    System.out.println("posx: " + posx);
//                    if (spacepos < WINWIDTH-10){
//                        Text j = new Text("\n");
//                        tempo.item = j;
//                    }else if(spacepos > WINWIDTH-10 ){
//
//                    }
//                }


            ((Text) temp.item).setX(posx);
            ((Text) temp.item).setY(posy);


            posx += cur;

           /* height = (int) Math.round(((Text) temp.item).getLayoutBounds().getHeight());*/



            if (stream.current == temp) {

                rect.setX(posx);
                rect.setY(posy);

            }
            /*tempCurs += cur;*/

        }
        scrollBar.setMax((height * stream.size) - WINHEIGHT);
        /*System.out.println("posy: " + posy);
        System.out.println("height: " + 15);
        System.out.println("runningHeight: " + runningHeight);
        System.out.println("textPosY: "+ textPosY); */
/*        if (posy +height >runningHeight ){
//TODO: properly update height
            textPosY -= 15;
            textBod.setLayoutY(textPosY);
            rect.setY(posy);
            runningHeight +=15;

        } else if (WINHEIGHT < runningHeight && posy +height < runningHeight -height  ){

            textPosY += 15;
            textBod.setLayoutY(textPosY);
            runningHeight -=15;
        }*/
    }

    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors =
                {Color.BLACK, Color.WHITE};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            rect.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }

    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(root, WINWIDTH, WINHEIGHT, Color.WHITE);
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root);

        EventHandler<MouseEvent> mousy = new MouseClickEventHandler(root);

        // Make a vertical scroll bar on the right side of the screen.
        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        // Set the height of the scroll bar so that it fills the whole window.
        scrollBar.setPrefHeight(WINHEIGHT);

        // Set the range of the scroll bar.
        scrollBar.setLayoutX(scene.getWidth()-scrollBar.getWidth());
        scrollBar.setMin(0);
        scrollBar.setMax(0);
        scrollBar.setValue(0);


        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldScreenWidth,
                    Number newScreenWidth) {
                // Re-compute Allen's width.
                WINWIDTH = newScreenWidth.intValue();
                scrollBar.setLayoutX(scene.getWidth()-scrollBar.getWidth());
                reRend(stream);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldScreenHeight,
                    Number newScreenHeight) {
                WINHEIGHT = newScreenHeight.intValue();
                scrollBar.setPrefHeight(WINHEIGHT);
                reRend(stream);
            }
        });


        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnMouseClicked(mousy);
        root.getChildren().add(scrollBar);

        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                // newValue describes the value of the new position of the scroll bar. The numerical
                // value of the position is based on the position of the scroll bar, and on the min
                // and max we set above. For example, if the scroll bar is exactly in the middle of
                // the scroll area, the position will be:
                //      scroll minimum + (scroll maximum - scroll minimum) / 2
                // Here, we can directly use the value of the scroll bar to set the height of Josh,
                // because of how we set the minimum and maximum above.
//TODO:check if it is okay to just cast int for old and new val.

                int Value = (int) Math.round((double) newValue);
                textBod.setLayoutY(-Value);
            }
        });

        makeRectangleColorChange();
        Parameters parm = getParameters();
        List args = parm.getRaw();
        if (args.size() > 2) {
            System.out.println("Expected usage: CopyFile <source filename> <optional>");
            System.exit(1);
        } else if (args.size() < 1){
            System.out.println("Expected usage: CopyFile <source filename> <optional>");
            System.exit(1);
        } else if (args.size() == 1) {
            String inputFilename = (String) args.get(0);
            displayAll(inputFilename);
        } else if (args.get(1) == "deBug"){
            System.out.println(stream.getCur());;
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String args[]) {

        launch(args);

    }
}
