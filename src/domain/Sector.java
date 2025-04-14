package domain;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import javax.swing.JOptionPane;

public class Sector {
    private int numero;
    private double latitud;
    private double longitud;
    private int limite;

    private TipoAlimentacion tipoAlimentacion;
    private Empleado empleado;
    private ArrayList<Mamifero> animales = new ArrayList<>();

    public Sector(int numero, double latitud, double longitud, int limite, TipoAlimentacion tipoAlimentacion, Empleado empleado) throws IllegalArgumentException {
        if(empleado == null) throw new IllegalArgumentException();
        this.numero = numero;
        this.latitud = latitud;
        this.longitud = longitud;
        this.limite = limite;
        this.tipoAlimentacion = tipoAlimentacion;
        this.empleado = empleado;
    }


    public void agregarAnimal(Mamifero mamifero) throws IllegalArgumentException, InvalidPropertiesFormatException {
        if(mamifero == null) throw new IllegalArgumentException();
        
        if(!mamifero.getTipoAlimentacion().equals(this.tipoAlimentacion)){
            throw new InvalidPropertiesFormatException(" Error: El sector actual no admite este tipo de alimentación.\n"
                                                        + " Sectores para herbívoros: 1 y 3.\n"
                                                        + " Sectores para carnívoros: 2 y 4.");                                                         
        }
        if(animales.size() == limite) throw new InvalidPropertiesFormatException("El sector no admite más animales");
        animales.add(mamifero);
    }

    public int getNumero() {
        return numero;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getLimite() {
        return limite;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
    public TipoAlimentacion getTipoAlimentacion() {return tipoAlimentacion;}

    @Override
    public String toString(){
        return String.format("Sector N°: %s [%s]", numero, tipoAlimentacion);
    }
}
