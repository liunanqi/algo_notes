import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class EightQueens extends Application {
    public static final String path = System.getProperty("user.dir");
    public static final int SIZE = 8;

    private Random rand = new Random(System.currentTimeMillis());
    //第i行棋子放置于第queens[i]列
    private int[] queens = {rand.nextInt(8), -1, -1, -1, -1, -1, -1, -1};

    @Override
    public void start(Stage primaryStage) {
        boolean result = search();
        //创建网格面板
        GridPane chessBoard = new GridPane();
        chessBoard.setAlignment(Pos.CENTER);
        Label[][] labels = new Label[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                chessBoard.add(labels[i][j] = new Label(), i, j);
                labels[i][j].setStyle("-fx-border-color: black");
                labels[i][j].setPrefSize(60, 60);
            }
        }

        if(result) {
            File imageFile = new File("queen.jpg");
            String uri = imageFile.getAbsoluteFile().toURI().toString();
            //创建棋子显示图片
            Image image = new Image(uri);
            for (int i = 0; i < SIZE; i++) {
                labels[i][queens[i]].setGraphic(new ImageView(image));
            }
            primaryStage.setTitle("Solution Found");
        } else {
            primaryStage.setTitle("Solution Not Found!" + queens[0]);
        }

        //新建场景并放入stage中
        Scene scene = new Scene(chessBoard, 60 * (SIZE + 1), 60 * (SIZE + 1));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean search() {
        int k = 0;
        while(k >= 0 && k < SIZE) {
            int j = findPosition(k);
            if(j < 0) {
                queens[k] = -1;
                k--;
            } else {
                queens[k] = j;
                k++;
            }
        }
        if(k == -1)
            return false;
        else
            return true;
    }
    //寻找第k行放置棋子的位置
    public int findPosition(int k) {
        int start = queens[k] + 1;
        for(int j = start; j < SIZE; j++) {
            if(isValid(k, j)) {
                return j;
            }
        }
        return -1;
    }
    //判断棋子是否可以放置在[row, column]处
    public boolean isValid(int row, int column) {
        for(int i = 1; i <= row; i++) {
            if(queens[row - i] == column || queens[row - i] == column - i || queens[row - i] == column + i) {
                return false;
            }
        }
        return true;
    }
}