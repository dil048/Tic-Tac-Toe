import java.util.*;
public class Tester 
{
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner (System.in);
		String answer = "Y";
		do
		{
			Board b = new Board();
			b.setTurn(0);
			b.displayBoard();
			do
			{
				if(b.getTurn()%2==0)
				{
					System.out.println("Player 1 ,please enter your position");
				}else
				{
					System.out.println("Player 2 ,please enter your position ");
				}
				int row = sc.nextInt();
				int col = sc.nextInt();
				while((row<0||row>2)||(col<0||col>2))
				{
					b.displayBoard();
					System.out.println("Invalid input, please enter an integer between 0-8");
					row = sc.nextInt();
					col = sc.nextInt();
					
				}
				b.makeMove(row,col);
				if(b.checkWin())	
				{
					if(b.getTurn()%2==0)
					{
						System.out.println("Player 2 won");
					}else if(b.getTurn()%2==1)
					{
						System.out.println("Player 1 won ");
					}else
					{
						System.out.println("Draw");
					}
					System.out.print("Press Y to play again. Any other key to quit:");
					answer = sc.next().toUpperCase();
				}
			}while((!b.checkWin()&& b.getTurn()%9!=0));
		}while(answer.equals("Y"));
	System.out.println("\nThank You for playing");
	}	
}
