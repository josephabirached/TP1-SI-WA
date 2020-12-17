/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

/**
 *
 * @author Charbel
 */
public class NewClass {
    public static void main(String[] args){
        Echiquier e = new Echiquier(8);
        e.placerReine(4, 4);
        System.out.println(e);
    }
}
