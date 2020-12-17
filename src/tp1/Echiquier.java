package tp1;


import java.util.ArrayList;

/**
 *
 * @author Charbel
 */
public class Echiquier {
    private Cellule[][] echiquier;
    ArrayList<Cellule> celluleLibres;
    private int taille;
    public Echiquier(int taille){
        this.taille = taille;
        echiquier = new Cellule[taille][taille];
        celluleLibres = new ArrayList<>();
        initialiserEchequier();
    }

    public void initialiserEchequier() {
        for(int x = 0; x<taille;x++){
            for(int y = 0; y<taille;y++){
                echiquier[x][y] = new Cellule(x, y);
                celluleLibres.add(echiquier[x][y]);
            }
        }
    }
    
    public void modifierCellule(int x, int y, int valeur){
        echiquier[x][y].setTypeOccupation(valeur);
    }
    
    public int calculerMenacePotenciel(int x, int y){
        int somme = 0;
        int b1 = y-x; // y=x+b1 (// à la premiere bissectrice)
        int b2 = y+x; // y=-x+b2 (// à la deuxième bissectrice)
        for(int i=0;i<taille;i++){
            if(echiquier[i][y].getTypeOccupation() == Cellule.LIBRE){
                somme++;
            }
            if(b1 + i >=0 && b1 + i < taille && echiquier[i][b1 + i].getTypeOccupation() == Cellule.LIBRE){
                somme++;
            }
            if(b2 - i >=0 && b2-i < taille && echiquier[i][b2 - i].getTypeOccupation() == Cellule.LIBRE){
                somme++;
            }
            if(echiquier[x][i].getTypeOccupation() == Cellule.LIBRE){
                somme++;   
            }   
        }
        return somme;
    }
    
    public Cellule trouverMoinsMenacente(){
        Cellule reine = null;
        int minimum = taille*taille, current;
        
        for(Cellule cellule : celluleLibres){
            current = calculerMenacePotenciel(cellule.getX(), cellule.getY());
            if(current<minimum){
                minimum = current;
                reine = cellule;
            }
        }
        
        return reine;
    } 
    
    // Algorithme Glouton
    public ArrayList<Cellule> algoGlouton(){
        ArrayList<Cellule> reines = new ArrayList<>();
        while(celluleLibres.size()>0){
            Cellule reine = trouverMoinsMenacente();
            placerReine(reine.getX(), reine.getY());
            reines.add(reine);
        }
        return reines;
    }
    
    
    public Cellule[][] copyEchiquier(Cellule[][] echiquier){
        Cellule[][] copy = new Cellule[taille][taille];
        for(int x = 0;x<taille;x++){
            for(int y = 0;y<taille;y++){
                copy[x][y] = Cellule.copy(echiquier[x][y]);
            }
        }
        return copy;
    }
    
    //Deuxieme algorithme
    public boolean algoArbre(ArrayList<Cellule> reines, int x){
        if(x>=taille){
            return true;
        }
        
        Cellule[][] localEchiquier = copyEchiquier(echiquier);
        for(int y=0;y<taille;y++){
            if(echiquier[x][y].getTypeOccupation() == Cellule.LIBRE){
                placerReine(x, y);
                reines.add(echiquier[x][y]);
                
                if(algoArbre(reines, x+1)){
                    return true;
                }
                reines.remove(echiquier[x][y]);
                this.echiquier = copyEchiquier(localEchiquier);
            }
            
        }
        return false;
    }
    
    public void placerReine(int x, int y){
        if(echiquier[x][y].getTypeOccupation() == Cellule.LIBRE){
            echiquier[x][y].setTypeOccupation(Cellule.REINE);
            celluleLibres.remove(echiquier[x][y]);
            int b1 = y-x; // y=x+b1 (// à la premiere bissectrice)
            int b2 = y+x; // y=-x+b2 (// à la deuxième bissectrice)
            for(int i=0;i<taille;i++){
                if(echiquier[i][y].getTypeOccupation() == Cellule.LIBRE){
                    echiquier[i][y].setTypeOccupation(Cellule.MENACEE);
                    celluleLibres.remove(echiquier[i][y]);
                }
                
                if(b1 + i >=0 && b1 + i < taille && echiquier[i][b1 + i].getTypeOccupation() == Cellule.LIBRE){
                    echiquier[i][b1 + i].setTypeOccupation(Cellule.MENACEE);
                    celluleLibres.remove(echiquier[i][b1 + i]);
                }
                if(b2 - i >=0 && b2-i < taille && echiquier[i][b2 - i].getTypeOccupation() == Cellule.LIBRE){
                    echiquier[i][b2 - i].setTypeOccupation(Cellule.MENACEE);
                    celluleLibres.remove(echiquier[i][b2 - i]);
                }
                
                if(echiquier[x][i].getTypeOccupation() == Cellule.LIBRE){
                    echiquier[x][i].setTypeOccupation(Cellule.MENACEE);  
                    celluleLibres.remove(echiquier[x][i]);
                }   
            }
        }
    }
    
    @Override
    public String toString(){
        String display="";

        for(int x = 0; x<taille;x++){
            for(int y = 0; y<taille;y++){
                if(y!=0){
                     display+="";
                }
                display+="|";
                int type = echiquier[x][y].getTypeOccupation();
                switch (type){
                    case Cellule.LIBRE: display+=" ";break;
                    case Cellule.MENACEE: display+="X";break;
                    default: display+="Q";break;
                }
            }
            display+="|\n";
         
           
        }
        return display;
    }
    
    
}
