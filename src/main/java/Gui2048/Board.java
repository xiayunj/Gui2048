package Gui2048;/*  File Header:
 *  Name: Yunjun Xia
 *  Login: cs8bwaqa
 *  Email: yux079@ucsd.edu
 *  Date: 2/3/2018
 *  File: Board.java
 *  Sources of Help: None
 *  Summary of the file: Board class which can generate fundamental board where tiles
 *  will be placed and moved around.
 */

/**
 * Sample Board
 * <p/>
 *     0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

/* Class Header:
 * Name: Board
 * Purpose: generate new board, load saved board, add a new random tile in a board,
 *          save the current board, make a movement in the tiles and check the game is over.
 * Capacity: 8 methods with 9 helper methods and 2 constructors
 * Instance Variables: @private final Random random;  
 *                     -- a reference to the Random object, passed in as a parameter 
 *                        in Board's constructors
 *                     @private int[][] grid;  
 *                     -- a 2D int array, its size being boardSize * boardSize
 *                     @private int score;  
 *                     -- the current score, incremented as tiles merge in gameplay
 */
import Gui2048.Direction;

import java.util.*;
import java.io.*;

public class Board {
    public final int NUM_START_TILES = 2; 
    public final int TWO_PROBABILITY = 90;
    public final int GRID_SIZE;


    private final Random random; // a reference to the Random object, passed in 
    // as a parameter in Boards constructors
    private int[][] grid;  // a 2D int array, its size being boardSize*boardSize
    private int score;     // the current score, incremented as tiles merge 


    /////////////////Constructors////////////////
    // Constructs a fresh board with random tiles
    public Board(Random random, int boardSize) {
        //initialize the instance variables
        this.random = random;
        this.GRID_SIZE = boardSize;
        this.score=0;
        this.grid=new int[boardSize][boardSize];
        //add two random tiles 
        this.addRandomTile();
        this.addRandomTile();
    }


    // Construct a board based off of an input file
    // assume board is valid
    public Board(Random random, String inputBoard) throws IOException {
        //initialize the instance variables from the saved board
        this.random = random;
        Scanner input=new Scanner(new File(inputBoard));//create a scanner to read the saved board
        this.GRID_SIZE=Integer.parseInt(input.next());
        this.grid=new int[GRID_SIZE][GRID_SIZE];
        this.score=Integer.parseInt(input.next());

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0;j<GRID_SIZE; j++){
                grid[i][j]=Integer.parseInt(input.next());
            }
        }
    }

    /////////////////Methods///////////////////////
    /** Saves the current board to a file
     * @param String outputBoard: the output file where the board saved
     * @return None
     */
    public void saveBoard(String outputBoard) throws IOException {
        String outputGridSize=Integer.toString(GRID_SIZE);//create a string of the current board grid's size
        String outputScore=Integer.toString(this.getScore());//create a string of the current board score
        int[][] outputGrid=this.getGrid();

        String outputString=outputGridSize+"\n"+outputScore+"\n";//add grid size and score to the string

        //add every tile number to the string
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                outputString=outputString+Integer.toString(outputGrid[i][j])+" ";
                if(j==GRID_SIZE-1)
                {outputString=outputString+"\n";}
            }}
        //output the string into a file
        FileOutputStream outputFile=new FileOutputStream(outputBoard);
        byte[] outputBytes=outputString.getBytes();
        outputFile.write(outputBytes);
        outputFile.close();

    }



    /** Adds a random tile (of value 2 or 4) to a random empty space on the board
     * @param None
     * @return None
     */
    public void addRandomTile() {

        int count= 0;//initialize count to be 0
        //count the empty tiles
        for(int x=0; x<GRID_SIZE; x++){
            for(int y=0; y<GRID_SIZE; y++){
                if( grid[x][y]==0)
                {count++;}
            }
        }
        //if no empty tiles, return
        if(count==0){return;}
        else{
            int location=random.nextInt(count);
            int value=random.nextInt(100);
            int temp=0;//set a temporary integer to count empty tiles

            for(int i=0; i<GRID_SIZE; i++){
                for(int j=0; j<GRID_SIZE; j++){
                    if(temp==location && grid[i][j]==0) //if temp equals location, we add the random tile
                    { if(value<TWO_PROBABILITY)
                        { grid[i][j]=2;
                            return;}
                        else {grid[i][j]=4;
                            return;}
                    }
                    else if(temp!=location && grid[i][j]==0) //else let temp+1
                    {temp++;}
                }
            }
        }
    }


    /**  determins whether the board can move in a certain direction
     *  return true if such a move is possible
     * @param Direction direction: the direction where we want to move the board
     * @return true if the move is possible
     *         false if the move is not possible
     */
    public boolean canMove(Direction direction){
        //check whether we can move in totally 4 directions
        if (direction.equals(Direction.LEFT) && this.canMoveLeft()==false) {return false;}
        if (direction.equals(Direction.RIGHT) && this.canMoveRight()==false) {return false;}
        if (direction.equals(Direction.UP) && this.canMoveUp()==false) {return false;}
        if (direction.equals(Direction.DOWN) && this.canMoveDown()==false) {return false;}
        return true;
    }

    ////////////4 Helper method of canMove(Direction directiona)////////////
    /**  determins whether the board can move to left
     *  return true if such a move is possible
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private boolean canMoveLeft() {
        //check whether we can move
        for (int i=0; i<GRID_SIZE; i++){
            for (int j=0; j<GRID_SIZE-1; j++){
                if((grid[i][j]==0 && grid[i][j+1]!=0) || (grid[i][j]!=0 && grid[i][j]==grid[i][j+1]))
                    return true;

            }
        }
        return false;
    }

    /**  determins whether the board can move to right
     *  return true if such a move is possible
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private boolean canMoveRight() {
        //check whether we can move
        for (int i=0; i<GRID_SIZE; i++){
            for(int j=GRID_SIZE-1; j>0; j--){
                if((grid[i][j]==0 && grid[i][j-1]!=0) || (grid[i][j]!=0 && grid[i][j]==grid[i][j-1]))
                    return true;
            }
        }
        return false;
    }

    /**  determins whether the board can move up
     *  return true if such a move is possible
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private boolean canMoveUp() {
        //check whether we can move
        for (int j=0; j<GRID_SIZE; j++){
            for (int i=0; i<GRID_SIZE-1; i++){
                if((grid[i][j]==0 && grid[i+1][j]!=0) || (grid[i][j]!=0 && grid[i][j]==grid[i+1][j]))
                    return true;
            }
        }
        return false;
    }

    /**  determins whether the board can move down
     *  return true if such a move is possible
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private boolean canMoveDown() {
        //check whether we can move
        for (int j=0; j<GRID_SIZE; j++){
            for (int i=GRID_SIZE-1; i>0; i--){
                if((grid[i][j]==0 && grid[i-1][j]!=0) || (grid[i][j]!=0 && grid[i][j]==grid[i-1][j]))
                    return true;
            }
        }
        return false;
    }



    /**  move the board in a certain direction
      return true if such a move is successful
     * @param Direction direction: the direction where we want to move the board
     * @return true if the move is possible
     *         false if the move is not possible
     */
    public boolean move(Direction direction) {
        //check if the board cannot be moved then return false
        if(this.canMove(direction)==false) {return false;}
        else{
            //move the board in the desired direction
            if(direction.equals(Direction.LEFT)) {this.moveLeft();}
            if(direction.equals(Direction.RIGHT)) {this.moveRight();}
            if(direction.equals(Direction.UP)) {this.moveUp();}
            if(direction.equals(Direction.DOWN)) {this.moveDown();}
        }
        return true;  
    }

    //////////////5 Helper Methods move(Direction direction)////////////////
    /**  move the ArrayList generated by the board
     * @param ArrayList<Integer> list: the arraylist generated by the board tiles
     * @return None
     */
    private void moveArrayList(ArrayList<Integer> list){
        int temp;//initialize a temporary integer
        //remove the zeros between non-zero tiles, then add another new 0 in the end of the arrayList
        for(int m=GRID_SIZE-1;m>=0; m--){
            if(list.get(m).equals(0)){
                list.remove(m);
                list.add(0);
            }
        }
        //add adjacent tiles of same numbers together and set the first one to be the sum,
        //the second one to be 0
        for(int n=0; n<GRID_SIZE-1; n++){
            if(list.get(n).equals(list.get(n+1)))
            {temp=list.get(n)+list.get(n+1);
                list.set(n,temp);
                list.set(n+1,0);
                score+=temp;//increment the score
            }
        }     
        //remove the 0 between non-zero tiles again 
        for(int x=GRID_SIZE-1;x>=0; x--){
            if(list.get(x).equals(0)){
                list.remove(x);
                list.add(0);
            }
        }
    }

    /**  move the board to left
      return true if such a move is successful
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private void moveLeft(){
        ArrayList<Integer> list=new ArrayList<Integer>();//create a new arraylist
        for (int i=0; i<GRID_SIZE; i++){
            for (int j=0; j<GRID_SIZE; j++)    
            { list.add(grid[i][j]);}  //add integers to the arraylist                   
            this.moveArrayList(list);//let list to call the helper method
            //transfer the data of the arraylist back to the grid
            for(int y=0; y<GRID_SIZE; y++)
            { grid[i][y]=list.get(y);}
            list=new ArrayList<Integer>();
        }                           
    }

    /**  move the board to right
      return true if such a move is successful
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private void moveRight(){
        ArrayList<Integer> list=new ArrayList<Integer>();//create a new arraylist
        for (int i=0; i<GRID_SIZE; i++){
            for (int j=GRID_SIZE-1; j>=0; j--)    
            { list.add(grid[i][j]);}  //add integers to the arraylist                        
            this.moveArrayList(list);//let list to call the helper method
            //transfer the data of the arraylist back to the grid
            for(int y=0; y<GRID_SIZE; y++)
            { grid[i][GRID_SIZE-y-1]=list.get(y);}
            list=new ArrayList<Integer>();//reset kist
        }                           
    }

    /**  move the board up
      return true if such a move is successful
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private void moveUp(){
        ArrayList<Integer> list=new ArrayList<Integer>();//create a new arraylist
        for (int j=0; j<GRID_SIZE; j++){
            for (int i=0; i<GRID_SIZE; i++)    
            { list.add(grid[i][j]);}   //add integers to the arraylist                     
            this.moveArrayList(list);//let list to call the helper method
            //transfer the data of the arraylist back to the grid
            for(int y=0; y<GRID_SIZE; y++)
            { grid[y][j]=list.get(y);}
            list=new ArrayList<Integer>();//reset list
        }                           
    }

    /**  move the board down
      return true if such a move is successful
     * @param None
     * @return true if the move is possible
     *         false if the move is not possible
     */
    private void moveDown(){
        ArrayList<Integer> list=new ArrayList<Integer>();//create a new arraylist
        for (int j=0; j<GRID_SIZE; j++){
            for (int i=GRID_SIZE-1; i>=0; i--)    
            { list.add(grid[i][j]);}   //add integers to the arraylist                       
            this.moveArrayList(list);//let list to call the helper method
            //transfer the data of the arraylist back to the grid
            for(int y=0; y<GRID_SIZE; y++)
            { grid[GRID_SIZE-y-1][j]=list.get(y);}
            list=new ArrayList<Integer>();//reset list
        }                           
    }



    /** Check to see if we have a game over
     * @param None
     * @return true if the game is over
     *         false if the game is not over
     */
    public boolean isGameOver() {
        //check whether the movement in 4 directions can proceed
        if(canMoveLeft()==true || canMoveRight()==true || canMoveUp()==true || canMoveDown()==true)
        {return false;}
        return true;
    }

    // Return the reference to the 2048 Grid
    public int[][] getGrid() {
        return grid;
    }

    // Return the score
    public int getScore() {
        return score;
    }

    @Override
        public String toString() {
            StringBuilder outputString = new StringBuilder();
            outputString.append(String.format("Score: %d\n", score));
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++)
                    outputString.append(grid[row][column] == 0 ? "    -" :
                            String.format("%5d", grid[row][column]));

                outputString.append("\n");
            }
            return outputString.toString();
        }


}
