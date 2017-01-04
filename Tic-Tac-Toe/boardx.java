public class boardx 
{
	// Instance Variables
	private String [][] board;
	private static final String empty = " ";
	private static final String player1 = "O";
	private static final String player2 = "X";
	private static int turn;
	private String [][] pos = {{"0","1","2"},
							   {"3","4","5"},
							   {"6","7","8"}};
	//Constructor for the class
	//Takes String 2D array as parameter
	//Create a board with position 0-8
	public boardx(String [][] a)
	{
		board = a;
		System.out.println("Game is ready");
		System.out.println();
		for(int i = 0; i<3;i++)
		{
			for(int j = 0; j<3;j++)
			{
				board[i][j]=pos[i][j];
			}
		}
	}
	//Display the board
	public void displayBoard()
	{
		System.out.println(board[0][0]+"|"+ board[0][1]+"|"+board[0][2]);
		System.out.println("-+-+-");
		System.out.println(board[1][0]+"|"+ board[1][1]+"|"+board[1][2]);
		System.out.println("-+-+-");
		System.out.println(board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
		
	}
	//Takes int as parameter
	//return the current turn + 1
	public int getTurn()
	{
		return turn;
	}
	//Set turn.
	public void setTurn(int s)
	{
		turn =s;
	}
	//Takes int as parameter
	//Places O or X depending on the whose turn it is
	//Displays the current board
	public void makeMove(int s)
	{
		int row = s/3;
		int col = s%3;
		if(board[row][col].equals(pos[row][col]))
		{
			if(turn%2==0)
			{
				board[row][col] = player1;
				displayBoard();
			}else
			{
				board[row][col] = player2;
				displayBoard();
			}
			turn++;	
		}else
		{
			System.out.print("Invalid move, the space is already taken");
		}
	}
	//return the true if win condition is reached false if not.
	public boolean checkWin()
	{
	boolean win = false;
	for(int row  = 0; row<3;row++)
	{
		if(!board[row][0].equals(pos[row][0])&& board[row][1].equals(board[row][0])&&board[row][2].equals(board[row][0]))
		{
			win=true;
		}
	}
	for(int col = 0; col<3;col++)
	{
		if(!board[0][col].equals(pos[0][col])&& board[1][col].equals(board[0][col])&&board[2][col].equals(board[0][col]))
		{
			win=true;
		}
	}
	if((!board[0][0].equals(pos[0][0])&& board[1][1].equals(board[0][0])&&board[2][2].equals(board[0][0]))||
		   (!board[0][2].equals(pos[0][2])&& board[1][1].equals(board[0][2])&&board[2][0].equals(board[0][2])))
		 {
		   	win =true;
		 }
	return win;
	}		
}