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
	
	//主布局
	Stage primaryStage = new Stage();
	HBox mainpane = new HBox(50);//主界面
	VBox message = new VBox(15);//消息栏
	HBox tp = new HBox(15);//传送栏
	VBox adrui = new VBox(10);//小屋界面
	VBox cityui = new VBox(10);//村落界面
	FlowPane craft = new FlowPane();//制造面板
	VBox ctr = new VBox(10); //控制栏
	VBox item = new VBox(10);//物品区
	VBox storge = new VBox(10);//仓库;
	AnchorPane stgmsg = new AnchorPane();//仓库说明
	FlowPane stgcontain = new FlowPane();//仓库内容
	VBox tools = new VBox(10);//工具
	AnchorPane toolmsg = new AnchorPane();//工具说明
	FlowPane toolscontain = new FlowPane();//工具内容
	AnchorPane craftmsg = new AnchorPane();//制作说明
	AnchorPane valley = new AnchorPane();//村庄信息
	VBox manager = new VBox(10);//分配任务栏
	HBox citizen = new HBox(10);//居民
	HBox setcutter = new HBox(10);//伐木工
	VBox control = new VBox();//控制符
	
	//制作相关
	VBox crafttable = new VBox(20);
	Label msg1 = new Label();
	Label msg2 = new Label();
	Button ok = new Button("制作");
	Button cancel = new Button("取消");
	HBox choice = new HBox(30);
	
	//地点按钮
	Button room = new Button("小屋");
	Button city = new Button("寂静的森林");
	
	//事件控制符
	boolean start = true;    //控制变冷进程开关
	int firen = 0;           //当前火焰强度
	int fx = -1;             //上次火焰强度
	boolean stagetwo = false;//第二阶段开关
	int architect = 0;       //建筑师
	boolean traprandom = false;//陷阱随机损坏开关
	boolean tips = false;      //建造木屋提示
	boolean stagethree = false;//第三阶段开关
	
	//物品
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
	
	//功能指示控件
	Button setfire = new Button("生火");
	ProgressBar firepro = new ProgressBar(0);
	Button cutwood = new Button("砍树");
	ProgressBar cutwoodpro = new ProgressBar(0);
	Label crafttext = new Label("制造:");
	Button crafttrunk = new Button("小推车");
	Button crafttrap = new Button("陷阱");
	Button crafthub = new Button("小木屋");
	Button checktrap = new Button("检查陷阱");
	ProgressBar checktrappro = new ProgressBar(0); 
	
	//库存标签
	Label woodn = new Label("木头: " + wood);
	Label meatn = new Label("肉片: " + meat);
	Label furn = new Label("毛皮: " + fur);
	Label skinn = new Label("古怪鳞片: " + skin);
	Label hubn = new Label("小木屋: " + hub);
	Label warps = new Label("陷阱: " + trap);
	
	//职业标签
	Label citizenmsg = new Label("居民:" + citizenn + " / " + allpeople);
	Label relax = new Label("空闲居民: " + relaxn);
	Label cuttermsg = new Label("\n伐木工: " + cutter);
	
	//职业按钮
	Button cadd = new Button("▲");       //伐木工
	Button cdecrease = new Button("▼");
	
	public static void main(String args[]) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		
		//布局
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
		
		//职业分配布局
		control.setMaxSize(25, 25);
		citizen.getChildren().addAll(citizenmsg);
		manager.getChildren().addAll(citizen,relax,setcutter);
		manager.setMinWidth(120);
		relax.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		citizenmsg.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		cuttermsg.setStyle("-fx-font-size:15; -fx-font-family:'SongTi';");
		Tooltip.install(cuttermsg, new Tooltip("每位伐木工提供1.5的自动伐木数值"));
		
		//制作台布局
		cancel.setOnAction(exit -> {
			primaryStage.setScene(scene);
		});
		crafttable.getChildren().addAll(msg1,msg2,choice);
		choice.setAlignment(Pos.CENTER);
		choice.getChildren().addAll(ok,cancel);
		crafttable.setAlignment(Pos.CENTER);
		
		//adrui.getChildren().add(craft);              //制作台测试
		
		//开局讯息
		changemessage("房间寒冷刺骨");
		changemessage("火堆熄灭了");
		
		//检测火堆线程
		Thread checkfire = new Thread(checkfire());
		checkfire.setDaemon(true);
		checkfire.start();
		//变冷机制线程
		Thread cold = new Thread(cold());
		cold.setDaemon(true);
		cold.start();
		//检测游戏进度线程
		Thread checkgameprogress = new Thread(gameprogress());
		checkgameprogress.setDaemon(true);
		checkgameprogress.start();
		
		//小屋子地点
		room.setDisable(true);
		room.setStyle("-fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900; -fx-background-color:#fafafa");
		room.setOnAction(JumpToHome ->{
			ctr.getChildren().remove(cityui);
			city.setDisable(false);
			room.setDisable(true);
			ctr.getChildren().add(adrui);
		});
		
		//村落地点
		city.setStyle("-fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900; -fx-background-color:#fafafa");
		city.setOnAction(JumpToFrest ->{
			ctr.getChildren().remove(adrui);
			city.setDisable(true);
			room.setDisable(false);
			ctr.getChildren().add(cityui);
		});
//		Button x = new Button("新窗口");
//		x.setOnAction(e ->{
//			try {
//				TrapRandomEvent t = new TrapRandomEvent();
//				t.showstage(trap, relaxn, meat, primaryStage);
//				Task helpfix = helpfix(t);
//				Thread help = new Thread(helpfix);
//				help.start();
//			} catch (Exception e1) {
//				// TODO 自动生成的 catch 块
//				e1.printStackTrace();
//			}
////			Thread damage = new Thread(traprandom());
////			damage.setDaemon(true);
////			damage.start();
//		});
//		tp.getChildren().addAll(room,x);
		tp.getChildren().addAll(room);
		
		//砍树按钮(村落界面)
		cutwood.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		cutwood.setOnAction(cut ->{
			changemessage("森林里枯叶满地");
			Task cutwood = cutwood();
			cutwoodpro.progressProperty().bind(cutwood.progressProperty());
			this.cutwood.setDisable(true);
			new Thread(cutwood).start();
		});
		
		//生火按钮(小屋界面)
		setfire.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		setfire.setOnAction(e ->{
			
			if(!stagetwo) {
				//一阶段生火
				switch(firen) {
				case 0:changemessage("一点火光划破了漆黑的夜空");break;
				case 1:changemessage("一间破旧的小屋映入眼帘");break;
				case 2:changemessage("冒着火光的小木屋在森林里格外醒目");break;
				default: changemessage("火堆熊熊燃烧");
				}
				firen++;
				Task fire = fire();
				firepro.progressProperty().bind(fire.progressProperty());
				setfire.setDisable(true);
				new Thread(fire).start();
			}
			else {
				if(wood > 0) {
					//二三阶段生火
					wood--;
					Task fire = fire();
					changemessage("火堆熊熊燃烧");
					firen++;
					firepro.progressProperty().bind(fire.progressProperty());
					setfire.setDisable(true);
					new Thread(fire).start();
				}
				else {
					changemessage("木头不够了");
				}
			}
			
		});
		
		//制造界面
		crafttrunk.setOnAction(crafttrunk ->{
			setcraft("制作小推车需要 木头 * 100","小推车可以从森林运回更多木材");
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
					changemessage("木头不够了");
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
			setcraft("制作陷阱需要 木头 * " + needwood, "陷阱可以获取一些资源");
			int realneed = needwood;
			primaryStage.setScene(craftUI);
			ok.setOnAction(ok ->{
				if(wood - realneed > 0) {
					trap++;
					wood -= realneed;
					primaryStage.setScene(scene);
					changemessage("更多的陷阱可以捕获更多的资源");
					if(trap > 9) {
						changemessage("更多的陷阱已经毫无帮助了");
						this.crafttrap.setDisable(true);
					}
				}
				else {
					changemessage("木头不够了");
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
			setcraft("制作小木屋需要 木头 * " + needwood, "小木屋可以招来更多的人");
			int realneed = needwood;
			primaryStage.setScene(craftUI);
			
			ok.setOnAction(ok ->{
				if(wood >= realneed) {
					wood -= realneed;
					hub++;
					primaryStage.setScene(scene);
					changemessage("村落热闹了起来");
					if(hub == 1) {
						mainpane.getChildren().add(2,manager);
					}
					if(hub > 9) {
						changemessage("已经没有空间建造小木屋了");
						this.crafthub.setDisable(true);
					}
				}
				else {
					changemessage("木头不够了");
					primaryStage.setScene(scene);
				}
			});
			
			cancel.setOnAction(cancel ->{
				primaryStage.setScene(scene);
			});
		});
		
		//职业分配界面
		cadd.setDisable(true);
		cadd.setOnAction(e ->{
			cutter++;
			relaxn--;
			Label fix = (Label) manager.getChildren().get(manager.getChildren().indexOf(relax));
			fix.setText("空闲居民: " + relaxn);
			relax = fix;
			Label fix2 = (Label) setcutter.getChildren().get(setcutter.getChildren().indexOf(cuttermsg));
			fix2.setText("\n伐木工: " + cutter);
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
			fix.setText("空闲居民: " + relaxn);
			relax = fix;
			Label fix2 = (Label) setcutter.getChildren().get(setcutter.getChildren().indexOf(cuttermsg));
			fix2.setText("\n伐木工: " + cutter);
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
		primaryStage.setTitle("小黑屋");
		primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	//改变消息栏
	public void changemessage(String message) {
		if(this.message.getChildren().size() > 5) {
			this.message.getChildren().remove(this.message.getChildren().size() - 1);
		}
		Label m = new Label(message);
		m.setWrapText(true);
		m.setStyle(" -fx-font-size:15; -fx-font-family:'SongTi'; -fx-font-weight:900;");
		this.message.getChildren().add(0, m);
	}
	
	//更改制作目标
	public void setcraft(String object,String describe) {
		Platform.runLater(() ->{
			msg1.setText(object);
			msg2.setText(describe);
		});
	}
	
	//生火
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
	
	//砍树
	public Task cutwood() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
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
	
	//检查陷阱
	public Task trapcheck() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
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
					String mes = "仔细检查了森林里的陷阱,收获了";
					if(meatmsg > 0)mes += " 肉片 " + meatmsg;
					if(furmsg > 0)mes += " 毛皮 " + furmsg;
					if(skinmsg > 0)mes += " 古怪鳞片 " + skinmsg;
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
	
	//建筑师剧情
	public Task wake() {
		return new Task(){
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
				Platform.runLater(() ->{
					changemessage("一位衣衫褴褛的人步入了屋子");
				});
				Thread.sleep(1000);
				Platform.runLater(() ->{
					changemessage("他瑟瑟发抖蜷缩在屋子的角落");
				});
				int cold = 5;
				int flag = 0;
				for(int i = 0; i < 2000000; i++) {
					if(cold == 3 && flag == 0) {
						flag = 1;
						Platform.runLater(() ->{
							changemessage("陌生人不再颤抖了");
						});
					}
					else if(cold == 0 && flag == 1) {
						flag = 2;
						Platform.runLater(() ->{
							changemessage("陌生人缓缓站了起来");
						});
						Thread.sleep(1000);
						Platform.runLater(() ->{
							changemessage("陌生人说他是一名建筑师，可以帮忙伐木和制造物品");
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
				//启用自动砍树
				Thread autocut = new Thread(autocut());
				autocut.setDaemon(true);
				autocut.start();
				
				//增加制作界面
				Platform.runLater(()->{
					adrui.getChildren().add(craft);
				});
				return true;
			}
			
		};
	}
	
	//变冷
	public Task cold() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
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
	
	//检查火焰强度
	public Task checkfire() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
				for(int i = 0; i < 10000000; i++) {
					Platform.runLater(() ->{
						if(fx != firen) {
							switch(firen) {
							case -1:
							case 0:
							case 1:
							case 2:break;
							case 3:changemessage("屋子十分冰冷");break;
							case 4:changemessage("屋子有些寒冷");break;
							case 5:changemessage("屋子暖和宜人");break;
							case 6:changemessage("屋子有些热了");break;
							default: changemessage("屋子快要烧起来了");
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
	
	//检测游戏进度
	public Task gameprogress() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				for(int i = 0; i < 20000000; i++) {
					if(firen > 3 && !stagetwo) {
						stagetwo = true;
						Platform.runLater(()->{
							//仓库布局
							mainpane.getChildren().addAll(item);
							Label msg = new Label("库存: ");
							stgmsg.getChildren().add(msg);
							AnchorPane.setRightAnchor(msg, 20.0);
							Label msg2 = new Label("工具: ");
							toolmsg.getChildren().add(msg2);
							AnchorPane.setRightAnchor(msg2, 20.0);
							tools.getChildren().addAll(toolmsg,toolscontain);
							AnchorPane.setRightAnchor(msg2, 20.0);
							
							//检测仓库进程开始
							Thread checkcontain = new Thread(checkcontain());
							checkcontain.setDaemon(true);
							checkcontain.start();
							
							//添加新地点
							tp.getChildren().add(city);
						});
					}
					//建筑师剧情
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
	
	//检查库存
	public Task checkcontain() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
				for(int i = 0; i < 500000000; i++) {
					Platform.runLater(() ->{
						//修改木头库存
						if(wood >= 0) {
							if(stgcontain.getChildren().contains(woodn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(woodn));
								fix.setText("木头: " + wood);
								woodn = fix;
							}
							else stgcontain.getChildren().add(woodn);
							woodn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//修改肉片库存
						if(meat > 0) {
							if(stgcontain.getChildren().contains(meatn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(meatn));
								fix.setText("肉片: " + meat);
								meatn = fix;
							}
							else stgcontain.getChildren().add(meatn);
							meatn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//肉片剧情
						if(meat > 20 && !tips) {
							tips = true;
							try {
								Platform.runLater(()->{
									changemessage("建筑工说已经有足够多的肉了，可以建造小木屋了");
								});
								Thread.sleep(200);
								Platform.runLater(()->{
									changemessage("小木屋可以吸引更多的人入住");
									craft.getChildren().add(crafthub);
								});
							} catch (InterruptedException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							
						}
						//更改皮毛
						if(fur > 0) {
							if(stgcontain.getChildren().contains(furn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(furn));
								fix.setText("毛皮: " + fur);
								furn = fix;
							}
							else stgcontain.getChildren().add(furn);
							furn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//修改鳞片
						if(skin > 0) {
							if(stgcontain.getChildren().contains(skinn)) {
								Label fix = (Label) stgcontain.getChildren().get(stgcontain.getChildren().indexOf(skinn));
								fix.setText("古怪鳞片: " + skin);
								skinn = fix;
							}
							else stgcontain.getChildren().add(skinn);
							skinn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//修改木屋
						if(hub > 0) {
							if(stagethree == false) {
								Thread move = new Thread(move());
								move.setDaemon(true);
								move.start();
								stagethree = true;
							}
							if(toolscontain.getChildren().contains(hubn)) {
								Label fix = (Label) toolscontain.getChildren().get(toolscontain.getChildren().indexOf(hubn));
								fix.setText("小木屋: " + hub);
								hubn = fix;
							}
							else toolscontain.getChildren().add(hubn);
							allpeople = 4 * hub;
							Label fix = (Label) citizen.getChildren().get(citizen.getChildren().indexOf(citizenmsg));
							fix.setText("居民:" + citizenn + " / " + allpeople);
							citizenmsg = fix;
							hubn.setStyle("-fx-font-size: 10; -fx-font-family: SongTi");
						}
						//修改陷阱
						if(trap >= 0) {
							if(toolscontain.getChildren().contains(warps)) {
								Label fix = (Label) toolscontain.getChildren().get(toolscontain.getChildren().indexOf(warps));
								fix.setText("陷阱: " + trap);
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
							//置0移除
							if(trap == 0) {
								if(cityui.getChildren().contains(checktrap)) {
									cityui.getChildren().removeAll(checktrap,checktrappro);
								}
								toolscontain.getChildren().remove(warps);
							}
							//控制建造
							if(trap < 10 && crafttrap.isDisable()) {
								crafttrap.setDisable(false);
							}
							//损坏事件
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
							Label trunkn = new Label("小推车");
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
	
	//自动伐木
	public Task autocut(){
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
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

	//陷阱随机事件
	public Task traprandom() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
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
	
	//时间辅助修改
	public Task helpfix(TrapRandomEvent t) {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
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
	
	//木屋随机入住
	public Task move() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				// TODO 自动生成的方法存根
				
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
								case 1: changemessage("一位流浪汉搬进了村庄");break;
								case 2: changemessage("一对情侣搬进了村庄");break;
								case 3: changemessage("一家人搬进了村庄");break;
								}
								citizenn += amount;
								relaxn += amount;
								cadd.setDisable(false);
								Label fix = (Label) manager.getChildren().get(manager.getChildren().indexOf(relax));
								fix.setText("空闲居民: " + relaxn);
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
