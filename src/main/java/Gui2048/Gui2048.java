package Gui2048;
/*  File Header:
 *  Name: Yunjun Xia
 *  Login: cs8bwaqa
 *  Email: yux079@ucsd.edu
 *  Date: 2/25/2018
 *  File: Gui2048.java
 *  Sources of Help: None
 *  Summary of the file: Create the graphic user interface of 2048 so that
 *  you can play this game in a window!
 */


import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;

import java.io.*;

/* Class Header:
 * Name: Gui2048
 * Purpose: Subclass of Application which can make a GUI of game 2048.
 *          It also has a private myKeyHsndler class which implements
 *          the EventHandler<KeyEvent> interface.
 * Capacity: 1 private class which has an override handle method, 1 override
 *           start method, 1 helper method and 2 given private method,
 *           1 main method.
 * Instance Variables: @private String outputBoard: The filename to save
 *                      the board
 *                     @private Board board: The 2048 game board
 *                     @private GridPane pane: The pane of the game board
 *                     @private  BorderPane bp: The entire pane of the game
 */
public class Gui2048 extends Application
{
    private String outputBoard; // The filename for where to save the Board
    private Board board; // The 2048 Game Board

    private GridPane pane;

    /** Add your own Instance Variables here */
    private BorderPane bp;

    /** Override start method to show the game in a window
     *
     * @param primaryStage The primary stage to hold the scene showing in window
     *
     * @return None
     *
     */
    @Override
        public void start(Stage primaryStage)
        {
            // Process Arguments and Initialize the Game Board
            processArgs(getParameters().getRaw().toArray(new String[0]));

            // Create the pane that will hold all of the visual objects
            pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

            //I changed the background color to a pink color
            pane.setStyle("-fx-background-color: rgb(255, 204, 204)");
            // Set the spacing between the Tiles
            pane.setHgap(15); 
            pane.setVgap(15);

            /** Add your Code for the GUI Here */

            bp=new BorderPane(); //create a new border pane
            updatePane(); //update the pane

            Scene scene=new Scene(bp); //create a new scene of the border pane
            scene.setOnKeyPressed(new myKeyHandler()); //set key event handler
            primaryStage.setTitle("Gui2048"); //set tile of the window
            primaryStage.setScene(scene); //set the scene to the stage

            //set the width and height of the stage
            primaryStage.setWidth((board.GRID_SIZE+1.5)*Constants2048.TILE_WIDTH);
            primaryStage.setHeight((board.GRID_SIZE+1.8)*Constants2048.TILE_WIDTH);
            primaryStage.show();//show the stage

        }

    /** Add your own Instance Methods Here */

    /** Main method to launch the game
    */
    public static void main(String[] args){
        launch(args);
    }

    /** Update the pane after each move, save the game and determines whether
     *  the game is over
     *
     * @param None
     *
     * @return None
     */
    private void updatePane(){
        bp.getChildren().clear();//first to clear the previous border pane

        HBox top=new HBox(Constants2048.TILE_WIDTH);//create a new hbox
        top.setPadding(new Insets(20,100,10,100));//set padding
        //create the texts on top
        Text text1=new Text("2048");
        Text text2=new Text("Score: "+board.getScore());
        text1.setFont(Font.font("Times New Roman", FontWeight.BOLD,35));
        text2.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));
        text1.setFill(Color.rgb(153,0,76));
        text2.setFill(Color.rgb(102,0,51));
        //set the background color as the same as that of the grid pane
        top.setStyle("-fx-background-color: rgb(255,204,204)");
        top.getChildren().addAll(text1,text2);

        bp.setTop(top);//set the top of the border pane to be the hbox

        pane.getChildren().clear();//clear the previous grid pane
        int[][] grid=board.getGrid();//get grid of the board
        //set each grid of pane to be that in the grid of the board
        for(int i=0; i<board.GRID_SIZE; i++){
            for(int j=0; j<board.GRID_SIZE; j++){
                //create the small rectangle and set its width and height
                Rectangle rec=new Rectangle();
                rec.setWidth(Constants2048.TILE_WIDTH);
                rec.setHeight(Constants2048.TILE_WIDTH);
                //create the number on each tile
                Text value=new Text();
                value.setText(Integer.toString(grid[i][j]));
                //set different tiles with different colors and font size
                if(grid[i][j]<8){
                    value.setFill(Constants2048.COLOR_VALUE_DARK);
                    if(grid[i][j]==0){
                        value.setText("");
                        rec.setFill(Constants2048.COLOR_EMPTY);}
                    else if(grid[i][j]==2){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_2);}
                    else if(grid[i][j]==4){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_4);}
                }

                else if(grid[i][j]>=8){
                    value.setFill(Constants2048.COLOR_VALUE_LIGHT);
                    if(grid[i][j]==8){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_8);}
                    else if(grid[i][j]==16){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_16);}
                    else if(grid[i][j]==32){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_32);}
                    else if(grid[i][j]==64){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
                        rec.setFill(Constants2048.COLOR_64);}
                    else if(grid[i][j]==128){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
                        rec.setFill(Constants2048.COLOR_128);}
                    else if(grid[i][j]==256){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
                        rec.setFill(Constants2048.COLOR_256);}
                    else if(grid[i][j]==512){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
                        rec.setFill(Constants2048.COLOR_512);}
                    else if(grid[i][j]==1024){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
                        rec.setFill(Constants2048.COLOR_1024);}
                    else if(grid[i][j]==2048){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
                        rec.setFill(Constants2048.COLOR_2048);}
                    else if(grid[i][j]>2048){
                        value.setFont(Font.font("Times New Roman", FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
                        rec.setFill(Constants2048.COLOR_OTHER);}

                }

                pane.add(rec,j,i);//add the rectangle to pane
                pane.add(value,j,i);//add the text to pane
                pane.setHalignment(value,HPos.CENTER);//set the text at center
            }
        }

        bp.setCenter(pane);//set the grid pane at center of the border pane

        //check whether the game is over
        if(board.isGameOver()==true){
            System.out.println("Game is Over!");//print to console

            //create game over text
            Text gameOver=new Text();
            gameOver.setText("Game Over!\n");
            gameOver.setFont(Font.font("Times New Roman",FontWeight.BOLD,70));
            gameOver.setFill(Color.rgb(102,0,102));

            //create the rectangle of gameover in center and top of bp
            Rectangle recGameOver=new Rectangle();
            Rectangle recGameOverTop=new Rectangle();

            recGameOverTop.setWidth((board.GRID_SIZE+1.68)*Constants2048.TILE_WIDTH);
            recGameOverTop.setHeight(Constants2048.TILE_WIDTH*0.75);
            recGameOverTop.setFill(Color.rgb(255,204,229,0.5));

            recGameOver.setWidth((board.GRID_SIZE+1.68)*Constants2048.TILE_WIDTH);
            recGameOver.setHeight((board.GRID_SIZE+1.25)*Constants2048.TILE_WIDTH);
            recGameOver.setFill(Color.rgb(255,204,229,0.5));

            //create two stack panes which helps to set nodes on top of others
            StackPane sp=new StackPane();
            StackPane sp2=new StackPane();

            sp2.getChildren().addAll(top,recGameOverTop);
            sp.getChildren().addAll(pane,recGameOver,gameOver);

            //set the two stack panes to top and center of bp
            bp.setCenter(sp);
            bp.setTop(sp2);
            //set the nodes in center
            bp.setAlignment(gameOver,Pos.CENTER);
            bp.setAlignment(recGameOver,Pos.CENTER);
            bp.setAlignment(recGameOverTop,Pos.CENTER);

        }

    }

    /* Class Header:
     * Name: myKeyHandler
     * Purpose: implements key event handler interface and used to make key
     *          pressing possible in GUI
     * Capacity: 1 override handle method
     */        
    private class myKeyHandler implements EventHandler<KeyEvent> {

        /** Override handle method to decide what happens after pressing different
         *  keys
         *  @param e KeyEvent of different key pressing
         *
         *  @return None
         */
        @Override
            public void handle(KeyEvent e){
                if(e.getCode()==KeyCode.S){
                    try{
                        board.saveBoard(outputBoard);
                        System.out.println("Saving Board to "+outputBoard);
                    } catch(IOException exception){
                        System.out.println("saveBoard threw an Exception");
                    }
                }
                //set key of movement in 4 directions
                else if(e.getCode()==KeyCode.LEFT){
                    if(board.move(Direction.LEFT)==true){
                        board.addRandomTile();
                        System.out.println("Moving Left");
                        updatePane();
                    }
                }

                else if(e.getCode()==KeyCode.RIGHT){
                    if(board.move(Direction.RIGHT)==true){
                        board.addRandomTile();
                        System.out.println("Moving Right");
                        updatePane();
                    }
                }

                else if(e.getCode()==KeyCode.UP){
                    if(board.move(Direction.UP)==true){
                        board.addRandomTile();
                        System.out.println("Moving Up");
                        updatePane();
                    }
                }

                else if(e.getCode()==KeyCode.DOWN){
                    if(board.move(Direction.DOWN)==true){
                        board.addRandomTile();
                        System.out.println("Moving Down");
                        updatePane();
                    }
                }
            }
    }

    /** DO NOT EDIT BELOW */

    /** The method used to process the command line arguments
     * @param args String[]
     *
     * @return None
     */
    private void processArgs(String[] args)
    {
        String inputBoard = null;   // The filename for where to load the Board
        int boardSize = 0;          // The Size of the Board

        // Arguments must come in pairs
        if((args.length % 2) != 0)
        {
            printUsage();
            System.exit(-1);
        }

        // Process all the arguments 
        for(int i = 0; i < args.length; i += 2)
        {
            if(args[i].equals("-i"))
            {   // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            }
            else if(args[i].equals("-o"))
            {   // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            }
            else if(args[i].equals("-s"))
            {   // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            }
            else
            {   // Incorrect Argument 
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if(outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified or less than 2
        if(boardSize < 2)
            boardSize = 4;

        // Initialize the Game Board
        try{
            if(inputBoard != null)
                board = new Board(new Random(), inputBoard);
            else
                board = new Board(new Random(), boardSize);
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + 
                    " was thrown while creating a " +
                    "Board from file " + inputBoard);
            System.out.println("Either your Board(String, Random) " +
                    "Constructor is broken or the file isn't " +
                    "formated correctly");
            System.exit(-1);
        }
    }

    /** Print the Usage Message 
     * @param None
     *
     * @return None
     */
    private static void printUsage()
    {
        System.out.println("Gui2048");
        System.out.println("Usage:  Gui2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the "+ 
                "form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that " + 
                "should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be " + 
                "used to save the 2048 board");
        System.out.println("                If none specified then the " + 
                "default \"2048.board\" file will be used");  
        System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
                "board if an input file hasn't been"); 
        System.out.println("                specified.  If both -s and -i" + 
                "are used, then the size of the board"); 
        System.out.println("                will be determined by the input" +
                " file. The default size is 4.");
    }
}
