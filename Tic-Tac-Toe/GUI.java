import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class GUI extends Application
{
    private Board board;
    private String outputBoard;

    private final int recWidth = 106;
    private final Color recColor = Color.rgb(238, 228, 218, 0.35);
    private final int textSize = 55;
    private final Text name = new Text("Tic-Tac-Toe");

    private GridPane pane;
    private StackPane sPane;
    private Text[][] text;
    private Rectangle[][] cells;
    private boolean isGameOver = false;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.setUpBoard();
        primaryStage.setResizable(false);
        primaryStage.setTitle(name.toString());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void setUpBoard()
    {
        board = new Board();
        pane = new GridPane();
        sPane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        pane.setHgap(15);
        pane.setVgap(15);
        cells = new Rectangle[board.gridSize][board.gridSize];
        pane.add(name, 0, 0, 3, 1);
        GridPane.setHalignment(name, HPos.CENTER);
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                cells[i][j] = new Rectangle(recWidth, recWidth, recColor);
                pane.add(cells[i][j], j, i + 1, 1, 1);
            }
        }
        String[][] curGrid = board.getGrid();
        text = new Text[board.gridSize][board.gridSize];
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                switch (curGrid[i][j])
                {
                    case " ":
                        text[i][j] = new Text(" ");
                        break;
                    case "O":
                        text[i][j] = new Text("O");
                        text[i][j].setFont(Font.font("Times New Roman",
                            FontWeight.BOLD, textSize));
                        break;
                    case "X":
                        text[i][j] = new Text("X");
                        text[i][j].setFont(Font.font("Times New Roman",
                            FontWeight.BOLD, textSize));
                        break;
                }
            }
        }
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                pane.add(text[i][j], j, i + 1, 1, 1);
                GridPane.setHalignment(text[i][j], HPos.CENTER);
            }
        }
        sPane = new StackPane(pane);
        scene = new Scene(sPane);
        scene.setOnMouseClicked(new mouseHandler());
    }

    public void update()
    {
        pane.getChildren().clear();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        pane.setHgap(15);
        pane.setVgap(15);
        pane.add(name, 0, 0, 3, 1);
        GridPane.setHalignment(name, HPos.CENTER);
        cells = new Rectangle[board.gridSize][board.gridSize];
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                cells[i][j] = new Rectangle(recWidth, recWidth, recColor);
                pane.add(cells[i][j], j, i + 1, 1, 1);
            }
        }
        String[][] curGrid = board.getGrid();
        text = new Text[board.gridSize][board.gridSize];
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                switch (curGrid[i][j])
                {
                    case " ":
                        text[i][j] = new Text(" ");
                        break;
                    case "O":
                        text[i][j] = new Text("O");
                        text[i][j].setFont(Font.font("Times New Roman",
                            FontWeight.BOLD, textSize));
                        break;
                    case "X":
                        text[i][j] = new Text("X");
                        text[i][j].setFont(Font.font("Times New Roman",
                            FontWeight.BOLD, textSize));
                        break;
                }
            }
        }
        for (int i = 0; i < board.gridSize; i++)
        {
            for (int j = 0; j < board.gridSize; j++)
            {
                pane.add(text[i][j], j, i + 1, 1, 1);
                GridPane.setHalignment(text[i][j], HPos.CENTER);
            }
        }

    }

    private class mouseHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent e)
        {
            System.out.println(e.getX() + " " + e.getY());
            if (!isGameOver)
            {
                int row = -1;
                int col = -1;
                if (e.getX() > 10 && e.getX() < 116)
                {
                    col = 0;
                } else if (e.getX() > 133 && e.getX() < 238)
                {
                    col = 1;
                } else if (e.getX() > 253 && e.getX() < 359)
                {
                    col = 2;
                }

                if (e.getY() > 44 && e.getY() < 149)
                {
                    row = 0;
                } else if (e.getY() > 164 && e.getY() < 270)
                {
                    row = 1;
                } else if (e.getY() > 286 && e.getY() < 390)
                {
                    row = 2;
                }
                System.out.println(row + " " + col);
                if (row != -1 && col != -1)
                {
                    board.makeMove(row, col);
                    System.out.println(board.toString());
                    update();
                }
            }
            if (board.checkWin() || board.isFull())
            {
                isGameOver = true;
                endGame();
            }

        }
    }

    private void endGame()
    {
        Text gameOver = new Text(" ");
        if (board.checkWin())
        {
            if (board.getTurn() % 2 == 0)
            {
                gameOver = new Text("Player 2 Won!");
            } else if (board.getTurn() % 2 == 1)
            {
                gameOver = new Text("Player 1 Won!");
            }
        }else if(board.isFull())
        {
            gameOver = new Text("Draw!");
        }
        // set font
        gameOver.setFont(Font.font("Times New Roman", FontWeight.BOLD, 55));
        // set color
        gameOver.setFill(Color.BLACK);
        // set the rectangle that is the size of the pane
        Rectangle rect = new Rectangle(pane.getWidth(), pane.getHeight(),
            Color.rgb(238, 228, 218, 0.35));
        // add rect to the sPane
        sPane.getChildren().addAll(rect);
        // add text to the sPane
        sPane.getChildren().addAll(gameOver);
        // center the text
        StackPane.setAlignment(gameOver, Pos.CENTER);
    }

    public static void main(String[] args)
    {
        launch();
    }
}

/*
 * 10 < x < 116 , 44 < y < 149, 133< x < 238 , 164 < y < 270 253< x < 359 , 286
 * < y < 390
 *
 */
