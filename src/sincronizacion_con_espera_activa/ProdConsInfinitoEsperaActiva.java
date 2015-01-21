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
public class ProdConsInfinitoEsperaActiva {
    
    // Las variables que van a compartir los threads han de ser 'volatile'.
    // Los atributos y métodos serán 'static'.
    static volatile double producto ;
    static volatile boolean produciendo;
    
    
    public static void productor() {

        while (true){ // espera activa
            producto= Math.random();
            // Cuando produce se para hasta que se consuma lo producido.
            produciendo=false; 
            while(!produciendo); // espera activa
                
            
        }    
    }
    
    public static void consumidor() {

        while (true){    
            while (produciendo);
                System.out.println("El producto es: "+producto);
                produciendo=true;
            }
    }
    
    
     public static void main(String[] args) {

        // Creacion de threads
         
        produciendo=true;
        
        // Al crear los threads es conveniente darles nombre para identificarlos al depurar.
        // Los threads tienen que sobreescribir el método run
        Thread prod =new Thread(new Runnable() {
            @Override
            public void
            run() {productor();}
            },"HiloProductor");
        Thread cons =new Thread(new Runnable() {
            @Override 
            public void run() {consumidor();}
            },"HiloConsumidor");
        prod.start();
        cons.start();
    }
}

    
    
    

