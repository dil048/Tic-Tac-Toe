public class Board
{
    // Instance Variables
    public final int gridSize;
    public final String empty = " ";
    public final String player1 = "O";
    public final String player2 = "X";
    private int turn;
    private String[][] grid;

    public Board()
    {
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
    }

    // Constructor for the class
    // Takes String 2D array as parameter
    // Create a grid with position 0-8
    public Board(Board other)
    {
        this.gridSize = 3;
        this.setGrid(other.getGrid());
        this.setTurn(other.getTurn());

    }

    public String[][] getGrid()
    {
        return grid;
    }

    public void setGrid(String[][] grid)
    {
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    // Display the grid
    public void displayBoard()
    {
        System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
        System.out.println("-+-+-");
        System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
        System.out.println("-+-+-");
        System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);

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
                displayBoard();
            } else
            {
                grid[row][col] = player2;
                displayBoard();
            }
            turn++;
        } else
        {
            System.out.print("Invalid move, the space is already taken");
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