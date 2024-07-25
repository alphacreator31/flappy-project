import javafx.beans.property.SimpleDoubleProperty;

public class menu
{
    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();   // creation des postion X et Y 
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    private String skin;
    private double dimensionX;
    private double dimensionY;
    public menu(){
      HGX.set(0.25d);
      HGY.set(0.1d);
      dimensionX = 0.5d;
      dimensionY = 0.75d;
    }
    public double getDimension(String Dim){
        if(Dim == "X"){
            return dimensionX;
        }else{
            return dimensionY;
        }
    }
    public SimpleDoubleProperty get(String Dim){
        if(Dim == "X"){
            return HGX;
        }else{
            return HGY;
        }
    }
    public String getSkin(String type){
        if(type == "demarrage"){
            return "C:\\Users\\aheus\\Downloads\\Projet wormrain V2\\Projet wormrain V2\\menudemarrage.png";
        }else{
            return "C:\\Users\\aheus\\Downloads\\Projet wormrain V2\\Projet wormrain V2\\menufin.png";
        }
    }
}
