import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MultipleBounceBall extends Application {
    @Override
    public void start(Stage primaryStage){
        MultipleBallPane ballPane = new MultipleBallPane(100);
        ballPane.setStyle("-fx-border-color: yellow");

        Button btAdd = new Button("+");
        Button btSubtract = new Button("-");
        Button btPlay = new Button("Play");
        Button btPause = new Button("Pause");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btAdd, btSubtract, btPlay, btPause);
        hBox.setAlignment(Pos.CENTER);

        btAdd.setOnAction(e -> ballPane.add());
        btSubtract.setOnAction(e -> ballPane.subtract());
        btPlay.setOnAction(e -> ballPane.play());
        btPause.setOnAction(e -> ballPane.pause());


//        ballPane.setOnMousePressed(e -> ballPane.pause());
//        ballPane.setOnMouseReleased(e -> ballPane.play());

        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bind(sbSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setTop(sbSpeed);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setTitle("MultipleBounceBall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class MultipleBallPane extends Pane {
        private Timeline animation;

        public MultipleBallPane(){
            this(50);
        }

        public MultipleBallPane(double duration) {
            animation = new Timeline(new KeyFrame(Duration.millis(duration), e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }

        public void add() {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
            getChildren().add(new Ball(30, 30, 20, color, Math.random(), Math.random()));
        }

        public void subtract() {
            if(getChildren().size() > 0) {
                getChildren().remove(getChildren().size() -1);
            }
        }

        public void play() {
            animation.play();
        }

        public void pause() {
            animation.pause();
        }

//        public void increaseSpeed() {
//            animation.setRate(animation.getRate() + 0.1);
//        }
//
//        public void decreaseSpeed() {
//            animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
//        }

        public DoubleProperty rateProperty() {
            return animation.rateProperty();
        }

        protected void moveBall() {
            for(Node node : this.getChildren()) {
                Ball ball = (Ball)node;

                if(ball.getCenterX() < ball.getRadius() ||
                        ball.getCenterX() > getWidth() - ball.getRadius()) {
                    ball.dx *= -1;
                }
                if(ball.getCenterY() < ball.getRadius() ||
                        ball.getCenterY() > getHeight() - ball.getRadius()) {
                    ball.dy *= -1;
                }

                ball.setCenterX(ball.dx * ball.getHRate() + ball.getCenterX());
                ball.setCenterY(ball.dy * ball.getVRate() + ball.getCenterY());
            }
        }
    }

    class Ball extends Circle {
        private double dx = 1, dy = 1;
        private double hRate = 1, vRate = 1;

        public double getHRate() {
            return this.hRate;
        }

        public double getVRate() {
            return this.vRate;
        }

        Ball(double x, double y, double radius, Color color) {
            this(x, y, radius, color, 1, 1);
        }

        Ball(double x, double y, double radius, Color color, double hRate, double vRate) {
            super(x, y, radius);
            setFill(color);
            this.hRate = hRate > 0 ? hRate : 1;
            this.vRate = vRate > 0 ? vRate : 1;
        }
    }
}