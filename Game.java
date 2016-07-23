import java.util.*;
public class Game {
	
	public static void main(String[] args) {
	Scanner sc = new Scanner (System.in);
	String [][] a = new String[3][3];
	String answer = "Y";
	do
	{
		boardx b = new boardx(a);
		b.setTurn(0);
		b.displayBoard();
	do
	{
	Scanner ab = new Scanner (System.in);
	if(b.getTurn()%2==0)
	{
	System.out.println("Player 1 ,please enter your position");
	}else
	{
		System.out.println("Player 2 ,please enter your position ");
	}
	int pos = sc.nextInt();
	while(pos<0||pos>8)
	{
		b.displayBoard();
		System.out.println("Invalid input, please enter an integer between 0-8");
		pos = sc.nextInt();
	}
	b.makeMove(pos);
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
