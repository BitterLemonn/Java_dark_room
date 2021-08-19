package gameProjectGui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TrapRandomEvent extends Application {
	
	int trap = 0;
	int relaxn = 5;
	int meat = 0;
	int damage = (int) ((Math.random() + 0.5) * 3);
	int kill = (int)((Math.random() + 0.5) * (Math.random() * 4));
	Label err = new Label("村庄内空闲人数不足5人，无法追击");
	Stage stage = new Stage();
	Stage primarystage = new Stage();
	boolean isclose = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		
		final Stage stage = new Stage();
		
		VBox pane = new VBox(10);
		Label msg = new Label("      森林里传来了躁动\n前往检查有些许陷阱损坏了\n  地面上遍布着巨大的脚印");
		HBox choice = new HBox(15);
		Button go = new Button("追击");
		go.setOnAction(e ->{
			if(relaxn < 5) {
				if(!pane.getChildren().contains(err)) pane.getChildren().add(err);
			}
			else {
				int flag = (int)((Math.random() + 0.5) * 3);
				int add = (int)((Math.random() + 0.5) * 50);
				switch(flag) {
				case 1:{
					pane.getChildren().remove(0, pane.getChildren().size());
					Label safe = new Label("在脚印的尽头你和村民发现了一头野兽\n     经过奋战你获得了" + add + "块肉片");
					meat += add;
					Button back = new Button("返回");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(safe,back);
				};break;
				case 2:{
					pane.getChildren().remove(0, pane.getChildren().size());
					if(kill == 0) kill = 1;
					Label hurt = new Label("在脚印的尽头你和村民发现了一头野兽\n        经过鏖战你获得了" + add + "块肉片\n             但有" + kill + "名村民阵亡了");
					meat += add;
					relaxn -= kill;
					Button back = new Button("返回");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(hurt,back);
				};break;
				default:{
					pane.getChildren().remove(0, pane.getChildren().size());
					Label ace = new Label("在脚印的尽头你和村民发现了一头野兽\n   但野兽太强大只有你侥幸逃了回来");
					relaxn = 0;
					Button back = new Button("返回");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(ace,back);
				}
				}
			}
		});
		Button back = new Button("返回");
		back.setOnAction(e ->{
			stage.close();
		});
		choice.getChildren().addAll(go,back);
		choice.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(msg,choice);
		pane.setAlignment(Pos.CENTER);
		
		stage.setOnCloseRequest(e ->{
			System.out.println("监听到窗口退出");
		});
		Scene scene = new Scene(pane,400,200);
		stage.setScene(scene);
		stage.setTitle("随机事件");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(primaryStage);
		stage.show();
	} 

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Application.launch(args);
	}
	
	public void showstage(int trap, int relaxn, int meat,Stage primarystage) throws Exception {
		this.trap = trap;
		this.relaxn = relaxn;
		this.meat = meat;
		this.primarystage = primarystage;
		start(this.primarystage);
	}
	public int damage() {
		trap -= damage;
		if(trap < 0) trap = 0;
		return trap;
	}
	public int kill() {
		return relaxn;
	}
	public int gmeat() {
		return meat;
	}
}
