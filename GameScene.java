package gameProjectGui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScene extends Application {
	
	//������
	Stage primaryStage = new Stage();
	HBox mainpane = new HBox(50);//������
	VBox message = new VBox(15);//��Ϣ��
	HBox tp = new HBox(15);//������
	VBox adrui = new VBox(10);//С�ݽ���
	VBox cityui = new VBox(10);//�������
	FlowPane craft = new FlowPane();//�������
	VBox ctr = new VBox(10); //������
	VBox item = new VBox(10);//��Ʒ��
	VBox storge = new VBox(10);//�ֿ�;
	AnchorPane stgmsg = new AnchorPane();//�ֿ�˵��
	FlowPane stgcontain = new FlowPane();//�ֿ�����
	VBox tools = new VBox(10);//����
	AnchorPane toolmsg = new AnchorPane();//����˵��
	FlowPane toolscontain = new FlowPane();//��������
	AnchorPane craftmsg = new AnchorPane();//����˵��
	AnchorPane valley = new AnchorPane();//��ׯ��Ϣ
	VBox manager = new VBox(10);//����������
	HBox citizen = new HBox(10);//����
	HBox setcutter = new HBox(10);//��ľ��
	VBox control = new VBox();//���Ʒ�
	
	//�������
	VBox crafttable = new VBox(20);
	Label msg1 = new Label();
	Label msg2 = new Label();
	Button ok = new Button("����");
	Button cancel = new Button("ȡ��");
	HBox choice = new HBox(30);
	
	//�ص㰴ť
	Button room = new Button("С��");
	Button city = new Button("�ž���ɭ��");
	
	//�¼����Ʒ�
	boolean start = true;    //���Ʊ�����̿���
	int firen = 0;           //��ǰ����ǿ��
	int fx = -1;             //�ϴλ���ǿ��
	boolean stagetwo = false;//�ڶ��׶ο���
	int architect = 0;       //����ʦ
	boolean traprandom = false;//��������𻵿���
	boolean tips = false;      //����ľ����ʾ
	boolean stagethree = false;//�����׶ο���
	
	//��Ʒ
	int wood = 15;
	int trunk = 0;
	int trap = 0;
	int meat = 0;
	int fur = 0;
	int skin = 0;
	int hub = 0;
	int citizenn = 1;
	int allpeople = 4 * hub;
	int relaxn = 0;
	int cutter = 1;
	
	//����ָʾ�ؼ�
	Button setfire = new Button("����");
	ProgressBar firepro = new ProgressBar(0);
	Button cutwood = new Button("����");
	ProgressBar cutwoodpro = new ProgressBar(0);
	Label crafttext = new Label("����:");
	Button crafttrunk = new Button("С�Ƴ�");
	Button crafttrap = new Button("����");
	Button crafthub = new Button("Сľ��");
	Button checktrap = new Button("�������");
	ProgressBar checktrappro = new ProgressBar(0); 
	
	//����ǩ
	Label woodn = new Label("ľͷ: " + wood);
	Label meatn = new Label("��Ƭ: " + meat);
	Label furn = new Label("ëƤ: " + fur);
	Label skinn = new Label("�Ź���Ƭ: " + skin);
	Label hubn = new Label("Сľ��: " + hub);
	Label warps = new Label("����: " + trap);
	
	//ְҵ��ǩ
	Label citizenmsg = new Label("����:" + citizenn + " / " + allpeople);
	Label relax = new Label("���о���: " + relaxn);
	Label cuttermsg = new Label("\n��ľ��: " + cutter);
	
	//ְҵ��ť
	Button cadd = new Button("��");       //��ľ��
	Button cdecrease = new Button("��");
	
	public static void main(String args[]) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO �Զ����ɵķ������
		
		//����
		Scene scene = new Scene(mainpane,800,500);
		Scene craftUI = new Scene(crafttable,300,150);
		mainpane.setPadding(new Insets(20,20,20,20));
		mainpane.getChildren().add(message);
		message.setMinWidth(180);
		message.setMaxWidth(180);
		adrui.getChildren().addAll(setfire,firepro);
		cityui.getChildren().addAll(cutwood,cutwoodpro);
		ctr.getChildren().addAll(tp,adrui);
		ctr.setMinWidth(400);
		item.getChildren().addAll(storge,tools);
		craftmsg.getChildren().add(crafttext);
		AnchorPane.setLeftAnchor(crafttext, 10.0);
		craftmsg.setMinWidth(400);
		craft.getChildren().addAll(craftmsg,crafttrunk,crafttrap);
		craft.setHgap(10);
		craft.setVgap(10);
		stgcontain.setHgap(30);
		toolscontain.setHgap(30);
		storge.getChildren().addAll(stgmsg,stgcontain);
		mainpane.getChildren().add(ctr);
		mainpane.setStyle("-fx-background-color:white");
		setcutter.getChildren().addAll(cuttermsg,control);
		
		//ְҵ���䲼��
		control.setMaxSize(25, 25);
		citizen.getChildren().addAll(citizenmsg);
		manager.getChildren().addAll(citizen,relax,setcutter);
		manager.setMinWidth(120);
		relax.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		citizenmsg.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		cuttermsg.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		Tooltip.install(cuttermsg, new Tooltip("ÿλ��ľ���ṩ1.5���Զ���ľ��ֵ"));
		
		//����̨����
		cancel.setOnAction(exit -> {
			primaryStage.setScene(scene);
		});
		crafttable.getChildren().addAll(msg1,msg2,choice);
		choice.setAlignment(Pos.CENTER);
		choice.getChildren().addAll(ok,cancel);
		crafttable.setAlignment(Pos.CENTER);
		
		//adrui.getChildren().add(craft);              //����̨����
		
		//����ѶϢ
		changemessage("���亮��̹�");
		changemessage("���Ϩ����");
		
		//������߳�
		Thread checkfire = new Thread(checkfire());
		checkfire.setDaemon(true);
		checkfire.start();
		//��������߳�
		Thread cold = new Thread(cold());
		cold.setDaemon(true);
		cold.start();
		//�����Ϸ�����߳�
		Thread checkgameprogress = new Thread(gameprogress());
		checkgameprogress.setDaemon(true);
		checkgameprogress.start();
		
		//С���ӵص�
		room.setDisable(true);
		room.setStyle("-fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900; -fx-background-color:#fafafa");
		room.setOnAction(JumpToHome ->{
			ctr.getChildren().remove(cityui);
			city.setDisable(false);
			room.setDisable(true);
			ctr.getChildren().add(adrui);
		});
		
		//����ص�
		city.setStyle("-fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900; -fx-background-color:#fafafa");
		city.setOnAction(JumpToFrest ->{
			ctr.getChildren().remove(adrui);
			city.setDisable(true);
			room.setDisable(false);
			ctr.getChildren().add(cityui);
		});
//		Button x = new Button("�´���");
//		x.setOnAction(e ->{
//			try {
//				TrapRandomEvent t = new TrapRandomEvent();
//				t.showstage(trap, relaxn, meat, primaryStage);
//				Task helpfix = helpfix(t);
//				Thread help = new Thread(helpfix);
//				help.start();
//			} catch (Exception e1) {
//				// TODO �Զ����ɵ� catch ��
//				e1.printStackTrace();
//			}
////			Thread damage = new Thread(traprandom());
////			damage.setDaemon(true);
////			damage.start();
//		});
//		tp.getChildren().addAll(room,x);
		tp.getChildren().addAll(room);
		
		//������ť(�������)
		cutwood.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		cutwood.setOnAction(cut ->{
			changemessage("ɭ�����Ҷ����");
			Task cutwood = cutwood();
			cutwoodpro.progressProperty().bind(cutwood.progressProperty());
			this.cutwood.setDisable(true);
			new Thread(cutwood).start();
		});
		
		//����ť(С�ݽ���)
		setfire.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		setfire.setOnAction(e ->{
			
			if(!stagetwo) {
				//һ�׶�����
				switch(firen) {
				case 0:changemessage("һ���⻮������ڵ�ҹ��");break;
				case 1:changemessage("һ���ƾɵ�С��ӳ������");break;
				case 2:changemessage("ð�Ż���Сľ����ɭ���������Ŀ");break;
				default: changemessage("�������ȼ��");
				}
				firen++;
				Task fire = fire();
				firepro.progressProperty().bind(fire.progressProperty());
				setfire.setDisable(true);
				new Thread(fire).start();
			}
			else {
				if(wood > 0) {
					//�����׶�����
					wood--;
					Task fire = fire();
					changemessage("�������ȼ��");
					firen++;
					firepro.progressProperty().bind(fire.progressProperty());
					setfire.setDisable(true);
					new Thread(fire).start();
				}
				else {
					changemessage("ľͷ������");
				}
			}
			
		});
		
		//�������
		crafttrunk.setOnAction(crafttrunk ->{
			setcraft("����С�Ƴ���Ҫ ľͷ * 100","С�Ƴ����Դ�ɭ���˻ظ���ľ��");
			primaryStage.setScene(craftUI);
			ok.setOnAction(ok ->{
				if(wood > 99) {
					wood -= 100;
					trunk = 1;
					primaryStage.setScene(scene);
					this.crafttrunk.setDisable(true);
				}
				else {
					primaryStage.setScene(scene);
					changemessage("ľͷ������");
				}
			});
			cancel.setOnAction(cancel ->{
				primaryStage.setScene(scene);
			});
		});
		
		crafttrap.setOnAction(craftwarp ->{
			int needwood = 0;;
			switch(trap) {
			case 0:needwood = 10;break;
			case 1:needwood = 20;break;
			case 2:needwood = 30;break;
			case 3:needwood = 40;break;
			default:needwood = 50;break;
			}
			setcraft("����������Ҫ ľͷ * " + needwood, "������Ի�ȡһЩ��Դ");
			int realneed = needwood;
			primaryStage.setScene(craftUI);
			ok.setOnAction(ok ->{
				if(wood - realneed > 0) {
					trap++;
					wood -= realneed;
					primaryStage.setScene(scene);
					changemessage("�����������Բ���������Դ");
					if(trap > 9) {
						changemessage("����������Ѿ����ް�����");
						this.crafttrap.setDisable(true);
					}
				}
				else {
					changemessage("ľͷ������");
					primaryStage.setScene(scene);
				}
			});
			cancel.setOnAction(cancel ->{
				primaryStage.setScene(scene);
			});
		});
		
		crafthub.setOnAction(e ->{
			int needwood = 0;;
			switch(hub) {
			case 0:needwood = 100;break;
			case 1:needwood = 150;break;
			case 2:needwood = 200;break;
			case 3:needwood = 250;break;
			default:needwood = 300;break;
			}
			setcraft("����Сľ����Ҫ ľͷ * " + needwood, "Сľ�ݿ��������������");
			int realneed = needwood;
			primaryStage.setScene(craftUI);
			
			ok.setOnAction(ok ->{
				if(wood >= realneed) {
					wood -= realneed;
					hub++;
					primaryStage.setScene(scene);
					changemessage("��������������");
					if(hub == 1) {
						mainpane.getChildren().add(2,manager);
					}
					if(hub > 9) {
						changemessage("�Ѿ�û�пռ佨��Сľ����");
						this.crafthub.setDisable(true);
					}
				}
				else {
					changemessage("ľͷ������");
					primaryStage.setScene(scene);
				}
			});
			
			cancel.setOnAction(cancel ->{
				primaryStage.setScene(scene);
			});
		});
		
		//ְҵ�������
		cadd.setDisable(true);
		cadd.setOnAction(e ->{
			cutter++;
			relaxn--;
			Label fix = (Label) manager.getChildren().get(manager.getChildren().indexOf(relax));
			fix.setText("���о���: " + relaxn);
			relax = fix;
			Label fix2 = (Label) setcutter.getChildren().get(setcutter.getChildren().indexOf(cuttermsg));
			fix2.setText("\n��ľ��: " + cutter);
			cuttermsg = fix2;
			if(relaxn == 0) {
				cadd.setDisable(true);
			}
			if(cutter > 0) {
				cdecrease.setDisable(false);
			}
		});
		cdecrease.setOnAction(e ->{
			cutter--;
			relaxn++;
			Label fix = (Label) manager.getChildren().get(manager.getChildren().indexOf(relax));
			fix.setText("���о���: " + relaxn);
			relax = fix;
			Label fix2 = (Label) setcutter.getChildren().get(setcutter.getChildren().indexOf(cuttermsg));
			fix2.setText("\n��ľ��: " + cutter);
			cuttermsg = fix2;
			if(cutter == 0) {
				cdecrease.setDisable(true);
			}
			if(relaxn > 0) {
				cadd.setDisable(false);
			}
		});
		cadd.setStyle("-fx-font-size:10; -fx-font-family:'SongTi'; -fx-background-color:#fafafa");
		cdecrease.setStyle("-fx-font-size:10; -fx-font-family:'SongTi'; -fx-background-color:#fafafa");
		control.getChildren().addAll(cadd,cdecrease);
		
		this.primaryStage = primaryStage;
		primaryStage.setMinWidth(1000);
		primaryStage.setMinHeight(500);
		primaryStage.setTitle("С����");
		primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	//�ı���Ϣ��
	public void changemessage(String message) {
		if(this.message.getChildren().size() > 5) {
			this.message.getChildren().remove(this.message.getChildren().size() - 1);
		}
		Label m = new Label(message);
		m.setWrapText(true);
		m.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		this.message.getChildren().add(0, m);
	}
	
	//��������Ŀ��
	public void setcraft(String object,String describe) {
		Platform.runLater(() ->{
			msg1.setText(object);
			msg2.setText(describe);
		});
	}
	
	//����
	public Task fire() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for(int i = 0; i < 10; i++) {
					updateProgress(i + 1, 10);
					Thread.sleep(500);
				}
				if(setfire.isDisable()) {
					setfire.setDisable(false);
				}
				firepro.progressProperty().unbind();
				firepro.setProgress(0);
				
				return true;
			}
		};
	}
	
	//����
	public Task cutwood() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				int woodcut = (int) ( (Math.random() * 10) + 10);
				switch(firen) {
				case 5: break;
				case 4:
				case 6:woodcut *= 0.75;break;
				case 3:woodcut *= 0.65;break;
				default: woodcut *= 0.5;
				}
				if(trunk == 2)woodcut += (int)((Math.random() * 10) * 3);
				wood += woodcut;
				
				for(int i = 0; i < 10; i++) {
					updateProgress(i + 1, 10);
					Thread.sleep(2500);
				}
				if(cutwood.isDisable()) {
					cutwood.setDisable(false);
				}
				cutwoodpro.progressProperty().unbind();
				cutwoodpro.setProgress(0);
				
				return true;
			}
			
		};
	}
	
	//�������
	public Task trapcheck() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				int meatcatch = (int)(Math.random() * 3 * trap + 1);
				int furcatch = (int)(Math.random() * 2 * trap);
				int skincatch = (int)(Math.random() * 0.25 * trap);
				
				if(meatcatch > 2 * trap) meatcatch = 2 * trap;
				if(meatcatch > 3) {
					furcatch -= (int) (Math.random() * 1.25 * trap);
					if(furcatch < 0) furcatch = 0;
				}
				if(meatcatch > 3 && furcatch > 0) {
					skincatch -= (int) (Math.random() * trap * 0.7);
					if(skincatch < 0) skincatch = 0;
				}
				
				final int meatmsg = meatcatch;
				final int furmsg = furcatch;
				final int skinmsg = skincatch;
				
				Platform.runLater(()->{
					String mes = "��ϸ�����ɭ���������,�ջ���";
					if(meatmsg > 0)mes += " ��Ƭ " + meatmsg;
					if(furmsg > 0)mes += " ëƤ " + furmsg;
					if(skinmsg > 0)mes += " �Ź���Ƭ " + skinmsg;
					changemessage(mes);
				});
				
				meat += meatcatch;
				fur += furcatch;
				skin += skincatch;
				
				for(int i = 0; i < 10; i++) {
					updateProgress(i + 1, 10);
					Thread.sleep(6000);
				}
				
				checktrappro.progressProperty().unbind();
				checktrappro.setProgress(0);
				checktrap.setDisable(false);
				
				return true;
			}
		};
	}
	
	//����ʦ����
	public Task wake() {
		return new Task(){
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				Platform.runLater(() ->{
					changemessage("һλ�������ڵ��˲���������");
				});
				Thread.sleep(1000);
				Platform.runLater(() ->{
					changemessage("��ɪɪ�������������ӵĽ���");
				});
				int cold = 5;
				int flag = 0;
				for(int i = 0; i < 2000000; i++) {
					if(cold == 3 && flag == 0) {
						flag = 1;
						Platform.runLater(() ->{
							changemessage("İ���˲��ٲ�����");
						});
					}
					else if(cold == 0 && flag == 1) {
						flag = 2;
						Platform.runLater(() ->{
							changemessage("İ���˻���վ������");
						});
						Thread.sleep(1000);
						Platform.runLater(() ->{
							changemessage("İ����˵����һ������ʦ�����԰�æ��ľ��������Ʒ");
						});
						architect = 2;
						break;
					}
					else if(firen == 5) {
						long time = System.currentTimeMillis();
						while(true) {
							if(System.currentTimeMillis() - time > 10000) {
								cold--;
								break;
							}
							if(firen != 5) break;
						}
					}
					else {
						Thread.sleep(3000);
						continue;
					}
				}
				//�����Զ�����
				Thread autocut = new Thread(autocut());
				autocut.setDaemon(true);
				autocut.start();
				
				//������������
				Platform.runLater(()->{
					adrui.getChildren().add(craft);
				});
				return true;
			}
			
		};
	}
	
	//����
	public Task cold() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				for(int i = 0; i < 10000000; i++) {
					boolean flag = false;
					if(firen > 3) {
						long time = System.currentTimeMillis();
						while(true) {
							if(System.currentTimeMillis() - time > 20000 && start) {
								flag = true;
								firen--;
								break;
							}
							else if(!start) break;
						}
					}
					if(!flag) {
						Thread.sleep(10000);
						continue;
					}
				}
				System.out.print("over");
				return true;
			}
			
		};
	}
	
	//������ǿ��
	public Task checkfire() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				for(int i = 0; i < 10000000; i++) {
					Platform.runLater(() ->{
						if(fx != firen) {
							switch(firen) {
							case -1:
							case 0:
							case 1:
							case 2:break;
							case 3:changemessage("����ʮ�ֱ���");break;
							case 4:changemessage("������Щ����");break;
							case 5:changemessage("����ů������");break;
							case 6:changemessage("������Щ����");break;
							default: changemessage("���ӿ�Ҫ��������");
							}
							start = false;
							fx = firen;
						}
						else {
							start = true;
						}
					});
					Thread.sleep(3000);
				}
			return true;
			}
		};
	}
	
	//�����Ϸ����
	public Task gameprogress() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				for(int i = 0; i < 20000000; i++) {
					if(firen > 3 && !stagetwo) {
						stagetwo = true;
						Platform.runLater(()->{
							//�ֿⲼ��
							mainpane.getChildren().addAll(item);
							Label msg = new Label("���: ");
							stgmsg.getChildren().add(msg);
							AnchorPane.setRightAnchor(msg, 20.0);
							Label msg2 = new Label("����: ");
							toolmsg.getChildren().add(msg2);
							AnchorPane.setRightAnchor(msg2, 20.0);
							tools.getChildren().addAll(toolmsg,toolscontain);
							AnchorPane.setRightAnchor(msg2, 20.0);
							
							//���ֿ���̿�ʼ
							Thread checkcontain = new Thread(checkcontain());
							checkcontain.setDaemon(true);
							checkcontain.start();
							
							//����µص�
							tp.getChildren().add(city);
						});
					}
					//����ʦ����
					if(wood > 49 && architect == 0) {
						architect = 1;
						Thread wake = new Thread(wake());
						wake.setDaemon(true);
						wake.start();
					}
					
					Thread.sleep(2000);
				}
				return true;
			}
		};
	}
	
	//�����
	public Task checkcontain() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				for(int i = 0; i < 500000000; i++) {
					Platform.runLater(() ->{
						//�޸�ľͷ���
						if(wood >= 0) {
							if(stgcontain.getChildren().contains(woodn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(woodn));
								fix.setText("ľͷ: " + wood);
								woodn = fix;
							}
							else stgcontain.getChildren().add(woodn);
							woodn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//�޸���Ƭ���
						if(meat > 0) {
							if(stgcontain.getChildren().contains(meatn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(meatn));
								fix.setText("��Ƭ: " + meat);
								meatn = fix;
							}
							else stgcontain.getChildren().add(meatn);
							meatn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//��Ƭ����
						if(meat > 20 && !tips) {
							tips = true;
							try {
								Platform.runLater(()->{
									changemessage("������˵�Ѿ����㹻������ˣ����Խ���Сľ����");
								});
								Thread.sleep(200);
								Platform.runLater(()->{
									changemessage("Сľ�ݿ����������������ס");
									craft.getChildren().add(crafthub);
								});
							} catch (InterruptedException e) {
								// TODO �Զ����ɵ� catch ��
								e.printStackTrace();
							}
							
						}
						//����Ƥë
						if(fur > 0) {
							if(stgcontain.getChildren().contains(furn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(furn));
								fix.setText("ëƤ: " + fur);
								furn = fix;
							}
							else stgcontain.getChildren().add(furn);
							furn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//�޸���Ƭ
						if(skin > 0) {
							if(stgcontain.getChildren().contains(skinn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(skinn));
								fix.setText("�Ź���Ƭ: " + skin);
								skinn = fix;
							}
							else stgcontain.getChildren().add(skinn);
							skinn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//�޸�ľ��
						if(hub > 0) {
							if(stagethree == false) {
								Thread move = new Thread(move());
								move.setDaemon(true);
								move.start();
								stagethree = true;
							}
							if(toolscontain.getChildren().contains(hubn)) {
								Label fix = (Label) toolscontain.getChildren().get(toolscontain.getChildren().indexOf(hubn));
								fix.setText("Сľ��: " + hub);
								hubn = fix;
							}
							else toolscontain.getChildren().add(hubn);
							allpeople = 4 * hub;
							Label fix = (Label) citizen.getChildren().get(citizen.getChildren().indexOf(citizenmsg));
							fix.setText("����:" + citizenn + " / " + allpeople);
							citizenmsg = fix;
							hubn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//�޸�����
						if(trap >= 0) {
							if(toolscontain.getChildren().contains(warps)) {
								Label fix = (Label) toolscontain.getChildren().get(toolscontain.getChildren().indexOf(warps));
								fix.setText("����: " + trap);
								warps = fix;
							}
							else toolscontain.getChildren().add(warps);
							warps.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
							if(!cityui.getChildren().contains(checktrap)) {
								cityui.getChildren().addAll(checktrap,checktrappro);
								checktrap.setOnAction(check ->{
									Task checking = trapcheck();
									checktrappro.progressProperty().bind(checking.progressProperty());
									new Thread(checking).start();
									checktrap.setDisable(true);
								});
							}
							//��0�Ƴ�
							if(trap == 0) {
								if(cityui.getChildren().contains(checktrap)) {
									cityui.getChildren().removeAll(checktrap,checktrappro);
								}
								toolscontain.getChildren().remove(warps);
							}
							//���ƽ���
							if(trap < 10 && crafttrap.isDisable()) {
								crafttrap.setDisable(false);
							}
							//���¼�
							if(trap > 0 && !traprandom) {
//								changemessage("random start");
								traprandom = true;
								Thread damage = new Thread(traprandom());
								damage.setDaemon(true);
								damage.start();
							}
						}
						//
						if(trunk == 1) {
							trunk = 2;
							Label trunkn = new Label("С�Ƴ�");
							trunkn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
							toolscontain.getChildren().add(trunkn);
						}
					});
					Thread.sleep(1000);
				}
				return true;
			}
			
		};
	}
	
	//�Զ���ľ
	public Task autocut(){
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				for(int i = 0; i < 500000000; i++) {
					if(!stagethree) {
						int autocut = 0;
						if(architect == 2) {
							autocut += 2;
						}
						wood += autocut;
					}
					else {
						int autocut = 0;
						autocut = (int)(cutter * 1.5);
						wood += autocut;
					}
					Thread.sleep(10000);
				}
				Platform.runLater(() ->{
					changemessage("over");
				});
				return true;
			}
		};
	}

	//��������¼�
	public Task traprandom() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				for(int i = 0; i < 5000000; i++) {
					if(trap == 0) {
						traprandom = false;
//						Platform.runLater(() ->{
//							changemessage("over");
//						});
						return true;
					}
					Platform.runLater(() ->{
						changemessage("random run");
					});
					Thread.sleep(30000);
					int is = (int)((Math.random() + 0.5) * 4);
					if(is == 3) {
						
						Platform.runLater(() ->{
							changemessage("random get");
						});
						
						TrapRandomEvent t = new TrapRandomEvent();
						t.showstage(trap,relaxn,meat,primaryStage);
						Task helpfix = helpfix(t);
						
						if(trap == 0) {
							traprandom = false;
//							Platform.runLater(() ->{
//								changemessage("over");
//							});
							return true;
						}
					}
//					else {
//						Platform.runLater(() ->{
//							changemessage("none");
//						});
//					}
				}
				return true;
			}
			
		};
	}
	
	//ʱ�丨���޸�
	public Task helpfix(TrapRandomEvent t) {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				while(!t.isclose);
				Platform.runLater(() ->{
					changemessage("getcha");
					trap = t.damage();
					relaxn = t.kill();
					meat = t.gmeat();
					changemessage("fin");
				});
				return true;
			}
		};
		
	}
	
	//ľ�������ס
	public Task move() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO �Զ����ɵķ������
				
				for(int i = 0; i < 50000000; i++) {
					int flag = (int)((Math.random() * 3) + 0.5);
					Thread.sleep(30000);
					switch(flag) {
					case 2: while(true) {
						int amount = (int)((Math.random() * 2) + 1.5);
						if(amount + citizenn > allpeople) continue;
						else {
							Platform.runLater(() ->{
								switch(amount) {
								case 1: changemessage("һλ���˺�����˴�ׯ");break;
								case 2: changemessage("һ�����°���˴�ׯ");break;
								case 3: changemessage("һ���˰���˴�ׯ");break;
								}
								citizenn += amount;
								relaxn += amount;
								cadd.setDisable(false);
								Label fix = (Label) manager.getChildren().get(manager.getChildren().indexOf(relax));
								fix.setText("���о���: " + relaxn);
								relax = fix;
							});
							break;
						}
					}break;
					}
				}
				
				return true;
			}
		};
		
	}
	
}
