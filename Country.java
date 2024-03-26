package p2;

import p2.*;
import java.io.*;

//Esta clase sirve para crear todos los paises con sus atributos.
public class Country implements Serializable, Comparable<Country> {
    private String identificador; // Identificador único del país
    private String nombre; // Nombre del país

    // Constructor
    public Country(String nombre, String identificador) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    // Métodos getters y setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Para ordenar la lista de países.
    @Override
    public int compareTo(Country otroPais) {

        return this.identificador.compareTo(otroPais.getIdentificador());
    }

    // private void writeObject(ObjectOutputStream out) throws IOException {
    // // Personaliza el orden de escritura de los atributos
    // out.writeObject(nombre);
    // out.writeObject(identificador);
    // }

    // private void readObject(ObjectInputStream in) throws IOException,
    // ClassNotFoundException {
    // Personaliza el orden de lectura de los atributos
    // nombre = (String) in.readObject();
    // identificador = (String) in.readObject();
    // }

    // private void readObjectNoData() throws ObjectStreamException {
    // Método requerido para Serializable, pero no se utiliza en este caso
    // }
}