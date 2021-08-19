package gameProjectGui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameTitle extends Application{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Application.launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		VBox mainpane = new VBox(15);
		mainpane.setPadding(new Insets(20,10,20,10));
		mainpane.setAlignment(Pos.CENTER);
		
		GridPane titlepane = new GridPane();
		Label title = new Label("小 黑 屋");
		title.setStyle("-fx-font-size:50; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		titlepane.getChildren().add(title);
		titlepane.setAlignment(Pos.TOP_CENTER);
		mainpane.getChildren().add(titlepane);
		
		Button startButton = new Button("开始游戏");
		startButton.setStyle("-fx-font-size:20; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		startButton.setOnAction(event ->{
			try {
				new GameScene().start(primaryStage);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		});
		
		Button continueButton = new Button("继续游戏");
		continueButton.setStyle(" -fx-font-size:20; -fx-font-family:'SongTi';-fx-font-weight:900;");
		
		Button quitButton = new Button("离开游戏");
		quitButton.setStyle("-fx-font-size:20; -fx-font-family:'SongTi';-fx-font-weight:900;");
		quitButton.setOnAction(event->{
			primaryStage.close();
		});
		
		mainpane.getChildren().addAll(startButton,continueButton,quitButton);
		
		Scene scene = new Scene(mainpane,800,500);
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.setTitle("小黑屋");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
