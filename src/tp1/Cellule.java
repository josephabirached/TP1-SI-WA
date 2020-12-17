package tp1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Charbel
 */
public class Cellule {
    private int x;
    private int y;
    
    private int typeOccupation;// libre, reine, menacé par une reine
    final static int LIBRE = 0;
    final static int REINE = 1;
    final static int MENACEE = 2;
    
    public Cellule(int x, int y){
        this.x = x;
        this.y = y;
        this.typeOccupation = LIBRE;
    }
    
    public static Cellule copy(Cellule c){
        Cellule c2 = new Cellule(c.getX(), c.getY());
        c2.setTypeOccupation(c.getTypeOccupation());
        return c2;
    }
    
   
    @Override
    public boolean equals(Object o){
        if(o instanceof Cellule){
            Cellule c = (Cellule)o;
            return c.getX() == this.x && c.getY() == this.y;
        }
        return false;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the typeOccupation
     */
    public int getTypeOccupation() {
        return typeOccupation;
    }

    /**
     * @param typeOccupation the typeOccupation to set
     */
    public void setTypeOccupation(int typeOccupation) {
        this.typeOccupation = typeOccupation;
    }
    
}
