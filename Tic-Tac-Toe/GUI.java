
import java.io.File;
import java.util.Scanner;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
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

    private final int recWidth = 106;
    private final Color recColor = Color.rgb(238, 228, 218, 0.35);
    private final int textSize = 55;
    private final Text name = new Text("Tic-Tac-Toe");

    private final Button credit = new Button("Credit");
    private final Button start = new Button("Start Game");
    private final Button instruction = new Button("How to Play");
    private final Button save = new Button("Save");
    private final Button load = new Button("Load Game");
    private final Button undo = new Button("Undo");
    private final Button restart = new Button("Restart");

    private Button backToFirst;
    private VBox initalOptions;
    private GridPane pane;
    private StackPane sPane;
    private TextFlow textPane;

    private Text[][] text;
    private Rectangle[][] cells;
    private boolean isGameOver = false;

    private Scene scene;
    private Scene boardScene;
    private Scene instructionScene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle(name.toString());
        this.initalPage();
        stage.setScene(scene);
        stage.show();

    }

    public void initalPage()
    {
        initalOptions = new VBox();
        initalOptions.setStyle("-fx-background-color: rgb(102, 250, 102)");
        credit.setShape(new Circle(30, Color.AQUA));
        credit.setMinSize(120, 40);
        start.setShape(new Circle(30, Color.AQUA));
        start.setMinSize(120, 40);
        instruction.setShape(new Circle(30, Color.AQUA));
        instruction.setMinSize(120, 40);
        load.setShape(new Circle(30, Color.AQUA));
        load.setMinSize(120, 40);
        initalOptions.setSpacing(30);
        name.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.ITALIC, textSize));
        name.setFill(Color.TEAL);
        initalOptions.setAlignment(Pos.CENTER);
        initalOptions.getChildren().addAll(name, start, load, instruction,
            credit);
        scene = new Scene(initalOptions, 370, 440);
        start.setOnAction(e -> {
            this.setUpBoard();
            stage.setScene(boardScene);
            boardScene.setOnMouseClicked(new mouseHandler());
            stage.show();
        });
        instruction.setOnAction(e -> {
            this.instuctionPage();
            stage.setScene(instructionScene);
            stage.show();
        });

    }

    public void instuctionPage()
    {
        try
        {
            backToFirst = new Button("Back");
            BorderPane bPane = new BorderPane();
            bPane.setStyle("-fx-background-color: rgb(192, 192, 192)");
            textPane = new TextFlow();
            StringBuilder s = new StringBuilder();
            File file = new File("instruction.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext())
            {
                String next = sc.next();
                if(next.equals("Source:"))
                {
                    next = "\n\nSource:";
                }
                s.append(next+ " ");
            }
            sc.close();
            Text instruction = new Text(s.toString());
            textPane.getChildren().add(instruction);
            textPane.setLineSpacing(5);
            bPane.setCenter(textPane);
            bPane.setBottom(backToFirst);
            backToFirst.setShape(new Circle(30, Color.AQUA));
            backToFirst.setMinSize(120, 40);
            bPane.setPadding(new Insets(4,4,170,4));
            BorderPane.setAlignment(backToFirst, Pos.CENTER);
            instructionScene = new Scene(bPane,370,440);
            backToFirst.setOnAction(e -> {
                this.initalPage();
                stage.setScene(scene);
                stage.show();
            });
        } catch (Exception e)
        {
        }
    }

    public void setUpBoard()
    {
        backToFirst = new Button("Back");
        board = new Board();
        pane = new GridPane();
        sPane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        pane.setHgap(15);
        pane.setVgap(15);
        cells = new Rectangle[board.gridSize][board.gridSize];
        name.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.ITALIC, 25));
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
        pane.add(backToFirst, 0, 4, 1, 1);
        GridPane.setHalignment(backToFirst, HPos.CENTER);
        pane.add(undo, 1, 4, 1, 1);
        GridPane.setHalignment(undo, HPos.CENTER);
        pane.add(save, 2, 4, 1, 1);
        GridPane.setHalignment(save, HPos.CENTER);
        sPane = new StackPane(pane);
        boardScene = new Scene(sPane, 370, 440);
        boardScene.setOnMouseClicked(new mouseHandler());
        backToFirst.setOnAction(e -> {
            this.initalPage();
            stage.setScene(scene);
            stage.show();
        });

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
        name.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.ITALIC, 25));
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
        pane.add(backToFirst, 0, 4, 1, 1);
        GridPane.setHalignment(backToFirst, HPos.CENTER);
        pane.add(undo, 1, 4, 1, 1);
        GridPane.setHalignment(undo, HPos.CENTER);
        pane.add(save, 2, 4, 1, 1);
        GridPane.setHalignment(save, HPos.CENTER);
        undo.setOnAction(new undoButtonHandler());
        save.setOnAction(new saveButtonHandler());

    }

    private class mouseHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent e)
        {
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
                if (row != -1 && col != -1)
                {
                    board.makeMove(row, col);
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

    private class undoButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            if (board.getTurn() > 1 && board.undo() != null)
            {
                board = board.undo();
                update();
            }
        }
    }

    private class saveButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            if (board.getTurn() > 2 && board.undo() != null)
            {
                board = board.undo();
                update();
            }
        }
    }

    private void endGame()
    {
        isGameOver = false;
        backToFirst = new Button("Back");
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
        } else if (board.isFull())
        {
            gameOver = new Text("It is a Cat game!");
        }
        // set font
        gameOver.setFont(Font.font("Times New Roman", FontWeight.BOLD, 45));
        // set color
        gameOver.setFill(Color.BLACK);
        // set the rectangle that is the size of the pane
        Rectangle rect = new Rectangle(pane.getWidth(), pane.getHeight(),
            Color.rgb(238, 228, 218, 0.35));
        sPane.getChildren().addAll(rect, gameOver,restart,backToFirst);
        restart.setShape(new Circle(30, Color.AQUA));
        restart.setMinSize(150, 50);
        backToFirst.setShape(new Circle(30, Color.AQUA));
        backToFirst.setMinSize(150, 50);
        StackPane.setMargin(gameOver, new Insets(0,0,250,0));
        StackPane.setMargin(backToFirst, new Insets(250,0,0,0));
        restart.setOnAction(e -> {
            this.setUpBoard();
            stage.setScene(boardScene);
            stage.show();
        });
        backToFirst.setOnAction(e -> {
            this.initalPage();
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void main(String[] args)
    {
        launch();
    }
}
