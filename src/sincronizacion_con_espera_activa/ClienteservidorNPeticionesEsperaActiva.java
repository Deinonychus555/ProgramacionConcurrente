/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacion_con_espera_activa;

/**
 *
 * @author juanan
 */
public class ClienteservidorNPeticionesEsperaActiva {
    
   // Las variables que van a compartir los threads han de ser 'volatile' 
   static volatile boolean requested;
   static volatile boolean responsed;
   static volatile double response;
    
    
    public static void servidor() {

        while (true){ // espera activa
            while(!requested);
            requested=false;
            response= Math.random();
            responsed=true;
        }
    }    
   
    public static void cliente() {

        
        while(true){
            requested=true;
            while (!responsed);
            responsed=false;
            System.out.println("La respuesta es: "+response);
        }
    }
    
    
     public static void main(String[] args) {

        // Creacion de threads
        responsed=false;
        requested=false;
        
        // Al crear los threads es conveniente darles nombre para identificarlos al depurar.
        Thread prod =new Thread(new Runnable() {
            @Override
            public void run() {servidor();}
            },"HiloServidor");
        Thread cons =new Thread(new Runnable() {
            @Override public void run() {cliente();}
            },"HiloCliente");
        prod.start();
        cons.start();
    }
}

    
    
    


