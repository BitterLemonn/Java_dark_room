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
	Label err = new Label("��ׯ�ڿ�����������5�ˣ��޷�׷��");
	Stage stage = new Stage();
	Stage primarystage = new Stage();
	boolean isclose = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO �Զ����ɵķ������
		
		final Stage stage = new Stage();
		
		VBox pane = new VBox(10);
		Label msg = new Label("      ɭ���ﴫ�����궯\nǰ�������Щ����������\n  �����ϱ鲼�ž޴�Ľ�ӡ");
		HBox choice = new HBox(15);
		Button go = new Button("׷��");
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
					Label safe = new Label("�ڽ�ӡ�ľ�ͷ��ʹ�������һͷҰ��\n     ������ս������" + add + "����Ƭ");
					meat += add;
					Button back = new Button("����");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(safe,back);
				};break;
				case 2:{
					pane.getChildren().remove(0, pane.getChildren().size());
					if(kill == 0) kill = 1;
					Label hurt = new Label("�ڽ�ӡ�ľ�ͷ��ʹ�������һͷҰ��\n        ������ս������" + add + "����Ƭ\n             ����" + kill + "������������");
					meat += add;
					relaxn -= kill;
					Button back = new Button("����");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(hurt,back);
				};break;
				default:{
					pane.getChildren().remove(0, pane.getChildren().size());
					Label ace = new Label("�ڽ�ӡ�ľ�ͷ��ʹ�������һͷҰ��\n   ��Ұ��̫ǿ��ֻ����������˻���");
					relaxn = 0;
					Button back = new Button("����");
					back.setOnAction(v ->{
						stage.close();
					});
					pane.getChildren().addAll(ace,back);
				}
				}
			}
		});
		Button back = new Button("����");
		back.setOnAction(e ->{
			stage.close();
		});
		choice.getChildren().addAll(go,back);
		choice.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(msg,choice);
		pane.setAlignment(Pos.CENTER);
		
		stage.setOnCloseRequest(e ->{
			System.out.println("�����������˳�");
		});
		Scene scene = new Scene(pane,400,200);
		stage.setScene(scene);
		stage.setTitle("����¼�");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(primaryStage);
		stage.show();
	} 

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
