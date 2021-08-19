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
		// TODO �Զ����ɵķ������
		Application.launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO �Զ����ɵķ������
		VBox mainpane = new VBox(15);
		mainpane.setPadding(new Insets(20,10,20,10));
		mainpane.setAlignment(Pos.CENTER);
		
		GridPane titlepane = new GridPane();
		Label title = new Label("С �� ��");
		title.setStyle("-fx-font-size:50; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		titlepane.getChildren().add(title);
		titlepane.setAlignment(Pos.TOP_CENTER);
		mainpane.getChildren().add(titlepane);
		
		Button startButton = new Button("��ʼ��Ϸ");
		startButton.setStyle("-fx-font-size:20; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		startButton.setOnAction(event ->{
			try {
				new GameScene().start(primaryStage);
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		});
		
		Button continueButton = new Button("������Ϸ");
		continueButton.setStyle(" -fx-font-size:20; -fx-font-family:'SongTi';-fx-font-weight:900;");
		
		Button quitButton = new Button("�뿪��Ϸ");
		quitButton.setStyle("-fx-font-size:20; -fx-font-family:'SongTi';-fx-font-weight:900;");
		quitButton.setOnAction(event->{
			primaryStage.close();
		});
		
		mainpane.getChildren().addAll(startButton,continueButton,quitButton);
		
		Scene scene = new Scene(mainpane,800,500);
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.setTitle("С����");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
