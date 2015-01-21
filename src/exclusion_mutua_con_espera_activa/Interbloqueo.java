// SOLUCIÓN NO VÁLIDA
// Se pueden producir intercalaciones en las secciones críticas.
package exclusion_mutua_con_espera_activa;

// Indicamos antes que el proceso quiere entrar en la sección crítica.


public class Interbloqueo {
    
   // Las variables que van a compartir los threads han de ser 'volatile' 
   static volatile boolean p1sc;
   static volatile boolean p2sc;
   
   
    
    
    public static void p1() {

        while (true){ // espera activa
            
            // Preprotocolo
            p1sc=true; // quiero entrar
            while(p2sc); // ¿está ocupada?
            
            
            // Sección crítica
            System.out.println("P1 sección crítica");
            try{Thread.sleep(5);} catch(Exception e){};
            System.out.println("P1 sección crítica");
            
            // Postprotocolo
            p1sc=false; // he salido
            
            // Sección NO crítica
            System.out.println("P1 sección NO crítica");
            System.out.println("P1 sección NO crítica");
            
        }
    }    
   
    public static void p2() {

        while (true){
            
         // Preprotocolo   
         p2sc=true;  // quiero entrar 
         while(p1sc); // ¿está ocupada?
            
            
            // Sección crítica
            System.out.println("P2 sección crítica");
            System.out.println("P2 sección crítica");
            
            // Postprotocolo
            p2sc=false; // he salido
            
            // sección No crítica
            System.out.println("P2 sección NO crítica");
            try{Thread.sleep(5);} catch(Exception e){};
            System.out.println("P2 sección NO crítica");
        }
    }
    
    
     public static void main(String[] args) {

        // Creacion de threads
        p1sc=false;
        p2sc=false;
        
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

    
    
    


