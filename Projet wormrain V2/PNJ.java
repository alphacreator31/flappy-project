import javafx.beans.property.SimpleDoubleProperty;

public class PNJ {
    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();  
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    String skin = "C:\\Users\\aheus\\Downloads\\Projet wormrain V2\\Projet wormrain V2\\tuyau.png";  
    private double positionEnY = (((double)(1+(int)(Math.random()*((8-1)+1))))/10)-0.75; 
    public PNJ() {              
       HGY.set(positionEnY);                                                                             
       HGX.set(1d-(0.72d/2));
    }
    public void LEFT(double g) {
        HGX.set(HGX.get()-g);                                           
    }
    public String getImage() {
        return skin;
    }
    public SimpleDoubleProperty getX(){
        return HGX;                                                         
    }
    public SimpleDoubleProperty getY(){
        return HGY;                                                        
    }
}
