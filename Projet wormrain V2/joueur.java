import javafx.beans.property.SimpleDoubleProperty;

public class joueur
{
    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();  
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    private String skin;
    private double dimension;

    public joueur(){
        dimension = 0.10d;
        HGX.set(0.5d-(dimension/2));                                                                                
        skin = "C:\\Users\\aheus\\Downloads\\Projet wormrain V2\\Projet wormrain V2\\Bird.png";
    }
    public void UP(double g) {
        HGY.set(HGY.get()-g);                                              
    }
    public void DOWN(double g) {
        HGY.set(HGY.get()+g);                                              
    }
    public SimpleDoubleProperty get(String Dim){
        if(Dim == "X"){
            return HGX;
        }else{
            return HGY;
        }
    }
    public double getValue(String Dim){
        if(Dim == "X"){
            return HGX.get();
        }else{
            return HGY.get();
        }
    }
    public String getSkin(){
        return skin;
    }
    public double getDimension(){
        return dimension;
    }
}
