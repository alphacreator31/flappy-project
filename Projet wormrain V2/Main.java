import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Main extends Application {
    private static SimpleDoubleProperty lifeX1 = new SimpleDoubleProperty();    
    private static SimpleDoubleProperty lifeX2 = new SimpleDoubleProperty();
    private static SimpleDoubleProperty lifeX3 = new SimpleDoubleProperty();
    
    private static ImageView menud;                                           
    private static ImageView menuf;
    
    private static ImageView personnage1;
    private static ImageView personnage2;
    
    private static ImageView life1;
    private static ImageView life2;
    private static ImageView life3;
    
    private static Affichage Affichage = new Affichage();                    
    
    private PNJ pnj; 
    private joueur joueur;
    
    private menu menu;
    private menu menufin;
    
    private static boolean droite1 = false;                                     
    private static boolean gauche1  = false;
    private static boolean restart = false;
    
    private double vMarche;
    private double g;
    
    private double X1 = 0;                                                    
    private double X2 = 0;
   
    private double Y1 = 0;
    private double Y2 = 0;
    
    private boolean state = true;   
    

    private boolean difficultyOne;
    private boolean difficultyTwo;
    private boolean difficultyThree;
                    
    
    private Label point;                                                       
    private int nbPoint = 0;
    private boolean pointStatu = false;
    private boolean life = true;
    private int tour;
    
    private boolean gaming = false;  
    private boolean start = false;
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Group root = new Group();                                                                            
            Scene scene = new Scene(root,1280,720);                                                          
            
            ImageView background = new ImageView(new Image("C:\\Users\\aheus\\Downloads\\Projet wormrain V2\\Projet wormrain V2\\background.png"));     
            Affichage.configBackground(background, scene, root);                                              
            
            joueur = new joueur();
            personnage1 = new ImageView(new Image(joueur.getSkin()));                   
            Affichage.configurer(personnage1, joueur.getDimension(), joueur.getDimension(), joueur.get("X"), joueur.get("Y"));                                    
            
            menu = new menu();

            menud = new ImageView(new Image(menu.getSkin("demarrage")));                        
            menuf = new ImageView(new Image(menu.getSkin("fin")));
            
            Affichage.configurer(menud, menu.getDimension("X"),  menu.getDimension("Y"), menu.get("X"), menu.get("Y"));              
            Affichage.configurer(menuf, menu.getDimension("X"),  menu.getDimension("Y"), menu.get("X"), menu.get("Y"));
            
            nbPoint = 0;                                                                                       
            
            point= new Label("Score :  " + Integer.toString(nbPoint));                                        
            
            Font fontLabel = new Font(50);                                                                         
            point.setTranslateX(10);
            point.setFont(fontLabel);

            AnimationTimer boucle = new AnimationTimer() {
                @Override
                public void handle(long arg0) {
                    if(gaming) {
                        if(life != false){
                            if(tour == 5 && vMarche < 0.002d){
                                vMarche += 0.05d;
                            }
                            if(state == true){ 
                                pnj = new PNJ();                                                           
                                personnage2.setImage(new Image(pnj.getImage()));
                                Affichage.configurer(personnage2, 0.75d, 2d, pnj.getX(), pnj.getY());
                                state = false;
                             }
                            X1 = Math.round(joueur.getValue("X")*100.0)/100.0;                                    
                            X2 = Math.round((pnj.getX()).get()*100.0)/100.0;
                            Y1 = Math.round(joueur.getValue("Y")*100.0)/100.0;
                            Y2 = Math.round((pnj.getY()).get()*100.0)/100.0;
                            if((gauche1) && Y1>-0.5d ){  
                                if(gauche1 && Y1 >-0.5d) {
                                    joueur.UP(g+0.01d);                                        
                                }
                            }else{
                                joueur.DOWN(g+0.005d);
                            }
                            pnj.LEFT(vMarche);                                                      
                            if(((X1+(0.15d)>=X2+(0.72d/2)&&(X1+(0.15d)<=X2+(0.72d))&&(Y1<=Y2+0.75d)||(X1+(0.15d)==X2+(0.72d/2))&&(Y1>=Y2+1d))&& X2+(0.72d/2)>=0.4d)||(Y1+0.15d)==1d){                                             
                                                                                 
                                life =false;                                                            
                            
                            }
                            if(X2+0.35d==0.5d && pointStatu == false){
                                nbPoint +=1; 
                                pointStatu = true;
                                point.setText("Score :  " +Integer.toString(nbPoint));            
                            }
                            if(X2+(0.72d/2)==0d){
                                state=true;
                                pointStatu = false;
                            }
                        }
                        if(life==false){                                              
                            root.getChildren().remove(personnage1);
                            root.getChildren().remove(personnage2);
                            root.getChildren().remove(point);
                            root.getChildren().add(menuf);
                            Font fontLabel = new Font(100);
                            point.setFont(fontLabel);
                            point.setText(Integer.toString(nbPoint));
                            point.setTextFill(Color.web("#FFFFFF"));
                            root.getChildren().add(point);
                            point.setTranslateX(600);
                            point.setTranslateY(250);
                            gaming = false;
                        } 
                    } else {
                        if(difficultyOne){
                            g = 0.0005d;
                            vMarche=0.001d;
                        }
                        if(difficultyTwo){
                            g = 0.00075d;
                            vMarche = 0.00075d;
                        }
                        if(difficultyThree){
                            g = 0.001d;  
                            vMarche = 0.0005d;
                        }
                        if (restart ) {
                            nbPoint = 0;                                                                    
                            root.getChildren().removeAll(menuf,point,menud);
                            Font fontLabel = new Font(50);
                            point.setFont(fontLabel);
                            point.setTranslateX(10);
                            point.setTranslateY(10);
                            
                            
                            
                            pnj = new PNJ();     
                            personnage2 = new ImageView(new Image(pnj.getImage()));
                            Affichage.configurer(personnage2, 0.75d, 2d, pnj.getX(), pnj.getY());
                            
                            joueur = new joueur();
                            personnage1 = new ImageView(new Image(joueur.getSkin()));                     
                            Affichage.configurer(personnage1, joueur.getDimension(), joueur.getDimension(), joueur.get("X"), joueur.get("Y"));                                                  // configuration de l'affichage du personnage1          
                            
                            root.getChildren().addAll(personnage1,personnage2,point);
                            point.setText("Score :  " +Integer.toString(nbPoint));
                            life=true;
                            System.out.println("life: "+life);
                            gaming = true;
                            
                        }
                    }
                }
            };
            boucle.start();                                                                                     
            scene.setOnKeyPressed(e -> {
                switch(e.getCode()){
                                                                                                           
                case UP :
                    gauche1 = true;
                    break;
                case ENTER :
                    restart = true;
                    break;
                case DIGIT1 :
                    difficultyOne = true;
                    break;
                case DIGIT2 :
                    difficultyTwo = true;
                    break;
                case DIGIT3 :
                    difficultyThree = true;
                    break;
                default:
                    break;

                }
            });
            scene.setOnKeyReleased(e -> {
                switch(e.getCode()){
                                                                                                           
                case UP :
                    gauche1 = false;
                    break;
                case ENTER :
                    restart = false;
                    break;
                case DIGIT1 :
                    difficultyOne = false;
                    break;
                case DIGIT2 :
                    difficultyTwo = false;
                    break;
                case DIGIT3 :
                    difficultyThree = false;
                    break;
                default:
                    break;
                    
                }
            });
            
            root.getChildren().addAll(background, menud);     
            scene.setFill(Color.BLACK);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
