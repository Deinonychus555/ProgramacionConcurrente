/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exclusion_mutua_con_espera_activa;


/**
 *
 * @author juanan
 */
public class AlternanciaObligatoria {
    
   // Las variables que van a compartir los threads han de ser 'volatile' 
   static volatile int turno;
   
   
    
    
    public static void p1() {

        while (true){ // espera activa
            
            // Preprotocolo
            while(turno!=1);
            
            // Sección crítica
            System.out.println("P1 sección crítica");
            try{Thread.sleep(5);} catch(Exception e){};
            System.out.println("P1 sección crítica");
            
            // Postprotocolo
            turno=2;
            
            // Sección NO crítica
            System.out.println("P1 sección NO crítica");
            System.out.println("P1 sección NO crítica");
            
        }
    }    
   
    public static void p2() {

        while (true){
            
         // Preprotocolo   
         while(turno!=2);
            
            // Sección crítica
            System.out.println("P2 sección crítica");
            System.out.println("P2 sección crítica");
            
            // Postprotocolo
            turno=1;
            
            // sección No crítica
            System.out.println("P2 sección NO crítica");
            try{Thread.sleep(5);} catch(Exception e){};
            System.out.println("P2 sección NO crítica");
        }
    }
    
    
     public static void main(String[] args) {

        // Creacion de threads
        turno=1;
        
        // Al crear los threads es conveniente darles nombre para identificarlos al depurar.
        Thread hilo1 =new Thread(new Runnable() {
            @Override
            public void run() {p1();}
            },"Hilop1");
        Thread hilo2 =new Thread(new Runnable() {
            @Override public void run() {p2();}
            },"Hilop2");
        hilo1.start();
        hilo2.start();
    }
}

    
    
    


