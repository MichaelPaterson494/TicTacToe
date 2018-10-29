import java.util.Scanner;
import java.util.Arrays;

public class Game {

    GridStates[][] grid = new GridStates[3][3];
    int turn = -1;
    boolean running = true;

    public Game(){
        //fills grid with empty cells upon new game. Initializes game.
        for (int i = 0; i < 3; ++i) {
            Arrays.fill(grid[i], GridStates.EMPTY);
        }
    }

    public void run() {
        while(running == true){
            draw();
            takeTurn();
        }
    }

    private GridStates playerTurn(){
        GridStates playerOne = GridStates.PLAYER1;
        GridStates playerTwo = GridStates.PLAYER2;
        if(turn % 2 != 0){
            return playerOne;
        } else return playerTwo;
    }

    private int[] coords() {
        //creates a scanner to receive coordinates for player movement.
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter 'x' Coordinate: ");
        int x = sc.nextInt();
        System.out.print("\nEnter 'y' Coordinate: ");
        int y = sc.nextInt();
        return new int[]{x, y};
    }

    private void takeTurn(){
        System.out.format("\nIt is %s's turn!\n",playerTurn().toString());
        while(true) {
            int[] pos = coords();
            if(!(pos[0] <= 2 && pos[0] >= 0)){
                System.out.println("Error, invalid 'x' Coordinate. Err 1003");
            }

            else if(!(pos[1] <= 2 && pos[1] >= 0)){
                System.out.println("Error, invalid 'y' Coordinate. Err 1002");
            }

            else if (grid[pos[0]][pos[1]] != GridStates.EMPTY) {
                System.out.println("Error, Position taken. Err 1001");
            }

            else{
                grid[pos[0]][pos[1]] = playerTurn();
                break;
            }
        }
        if(checkWin(playerTurn())){
            System.out.format("\nCONGRATULATIONS!!! %s You Win!!!\n", playerTurn().toString());
            draw();
            running = false;
        }
        else if(checkFull()){
            System.out.println("Whoops, seems like a draw.");
            draw();
            running = false;
        }
        turn++;
    }

    private void draw(){
        System.out.print("    ");
        for(int i = 0; i < 3; i++){
            System.out.format("%d ", i);
        }
        System.out.println("\n");
        for(int y = 0; y < 3; y++) {
            System.out.format("%d   ", y);
            for(int x = 0; x < 3; x++){
                GridStates selectedCell = grid[x][y];
                System.out.format("%c ", selectedCell.getState());
            }
            System.out.println();
        }
    }

    private boolean checkRow(int y, GridStates state){
        if (grid[0][y] == state && grid[1][y] == state && grid[2][y] == state){
            return true;
        } else { return false;}

    }

    private boolean checkCol(int x, GridStates state){
        if (grid[x][0] == state && grid[x][1] == state && grid[x][2] == state){
            return true;
        } else { return false;}
    }


    private boolean checkWin(GridStates state){

        for (int i = 0; i < 3; i++) {
            if(checkRow(i, state) || checkCol(i, state)){
                return true;
            }
        }
        int lRCounter = 0;
        int rLCounter = 0;
        for(int i = 0; i < 3; i++){
            if(grid[i][i] == state){
               lRCounter++;
            }
            if(grid[2-i][i] == state){
                rLCounter++;
            }
        }
        if(lRCounter == 3 || rLCounter == 3){
            return true;
        }
        return false;
    }

    private boolean checkFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == GridStates.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
}
