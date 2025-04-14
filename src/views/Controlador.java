package views;

import data.Persistencia;
import domain.*;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Objects;

public class Controlador {
    public static TipoAlimentacion[] getTiposAlimentacion(){
        return  TipoAlimentacion.values();
    }
    public static ArrayList<Especie> getEspecies(){
        return Persistencia.getEspecies();
    }
    public static ArrayList<Sector> getSectores(){
        return Persistencia.getSectores();
    }
    public static ArrayList<Pais> getPaises(){
        return Persistencia.getPaises();
    }
    
    public static boolean CorrespondeValorFijo(String seleccion){
        if (seleccion == null) {
        return false;
        }

        for (Especie especie : Persistencia.getEspecies()) {
            if (especie.getNombre().equalsIgnoreCase(seleccion)) {
                return especie.getTipoAlimentacion().esHerbivoro();
            }
        }
    return false;
    }
    
    public static void cargarAnimal(int edad, double peso, String especie, String sector, String pais, double valorFijo) throws InvalidPropertiesFormatException{
       
        Especie es = Persistencia.buscarEspeciePorNombre(especie);
        Pais p = Persistencia.buscarPaisPorNombre(pais);
        Sector s = Persistencia.buscarSectorPorNumero(sector);
           
       if(valorFijo == 0) {         
        Carnivoro carnivoro = new Carnivoro(edad,peso,es,s,p);
        Persistencia.agregarAnimal(carnivoro);
    } else {
        Herbivoro herbivoro = new Herbivoro(edad,peso,es,s,valorFijo,p);
        Persistencia.agregarAnimal(herbivoro);
    }
    }
        
    public static ArrayList<AnimalViewModel> getAnimales(){
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for(Mamifero animal : Persistencia.getAnimales()){
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }
    
    public static ComidaViewModel  calcularComida(){
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }
    
}