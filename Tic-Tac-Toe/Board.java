import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Board
{
    // Instance Variables
    public final int gridSize;
    public final String empty = " ";
    public final String player2 = "O";
    public final String player1 = "X";
    private int turn;
    private String[][] grid;
    private ArrayList<Board> savedBoard;

    public Board()
    {
        savedBoard = new ArrayList<>();
        this.gridSize = 3;
        this.grid = new String[3][3];
        for(int i = 0;i<gridSize;i++)
        {
            for(int j = 0;j<gridSize;j++)
            {
                grid[i][j]= empty;
            }
        }
        this.turn  = 0;
        this.saveBaordToList();
    }

    public Board(Board other)
    {
        this.gridSize = 3;
        this.savedBoard = other.getSavedBoard();
        this.setGrid(other.getGrid());
        this.setTurn(other.getTurn());

    }
    
    public Board(String inputBoard) throws IOException
    {
        this.gridSize = 3;
        Scanner sc = new Scanner(new File(inputBoard));
        this.setTurn(sc.nextInt());
        grid = new String[this.gridSize][this.gridSize];
        for(int i =0;i<this.gridSize;i++)
        {
            for(int j = 0;j<this.gridSize;j++)
            {
                grid[i][j]= sc.next();
            }
        }
        sc.close();
    }
    
    public void saveBoard(String outputBoard) throws IOException 
    {
       PrintWriter printwriter = new PrintWriter(new File(outputBoard));

       for (int row = 0; row < this.gridSize; row++) 
       {
          for (int column = 0; column < this.gridSize; column++) 
          {
             printwriter.print(grid[row][column]);
             printwriter.print(" ");
          }
          printwriter.println();
       }
       printwriter.close();
    }
    public void reset()
    {
        for(int i = 0;i<gridSize;i++)
        {
            for(int j = 0;j<gridSize;j++)
            {
                grid[i][j] = empty;
            }
        }
    }
    public void saveBaordToList()
    {
        this.savedBoard.add(new Board(this));
    }
    

    public ArrayList<Board> getSavedBoard()
    {
        return savedBoard;
    }


    public void setSavedBoard(ArrayList<Board> savedBoard)
    {
        this.savedBoard = savedBoard;
    }


    public String[][] getGrid()
    {
        return grid;
    }

    public void setGrid(String[][] grid)
    {
        this.grid = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    // Takes int as parameter
    // return the current turn + 1
    public int getTurn()
    {
        return this.turn;
    }

    // Set turn.
    public void setTurn(int input)
    {
        this.turn = input;
    }

    // Takes int as parameter
    // Places O or X depending on the whose turn it is
    // Displays the current grid
    public void makeMove(int row, int col)
    {
        if (grid[row][col].equals(" "))
        {
            if (turn % 2 == 0)
            {
                grid[row][col] = player1;
            } else
            {
                grid[row][col] = player2;
            }
            turn++;
            this.saveBaordToList();
        }
    }

    // return the true if win condition is reached false if not.
    public boolean checkWin()
    {
        boolean win = false;
        for (int row = 0; row < gridSize; row++)
        {
            if (!grid[row][0].equals(this.empty)
                && grid[row][1].equals(grid[row][0])
                && grid[row][2].equals(grid[row][0]))
            {
                win = true;
            }
        }
        for (int col = 0; col < gridSize; col++)
        {
            if (!grid[0][col].equals(this.empty)
                && grid[1][col].equals(grid[0][col])
                && grid[2][col].equals(grid[0][col]))
            {
                win = true;
            }
        }
        if ((!grid[0][0].equals(" ") && grid[1][1].equals(grid[0][0])
            && grid[2][2].equals(grid[0][0]))
            || (!grid[0][2].equals(" ")) && grid[1][1].equals(grid[0][2])
                && grid[2][0].equals(grid[0][2]))
        {
            win = true;
        }
        return win;
    }
    public boolean isFull()
    {
        for(int i =0;i<gridSize;i++)
        {
            for(int j = 0;j<gridSize;j++)
            {
                if(grid[i][j].equals(empty))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void saveBoard()
    {
        this.savedBoard.add(new Board(this));
    }
    
    public Board undo()
    {
        try
        {
            Board prevboard = new Board(this.getSavedBoard().get(this.getTurn()-2));
            prevboard.setTurn(this.getTurn()-2);
            return prevboard;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String toString()
    {
        StringBuilder a = new StringBuilder();
        a.append(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]+"\n");
        a.append("-+-+-"+"\n");
        a.append(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]+"\n");
        a.append("-+-+-"+"\n");
        a.append(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]+"\n");
        return a.toString();
    }
}