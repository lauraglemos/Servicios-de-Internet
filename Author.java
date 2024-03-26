package p2;

import p2.*;
import java.io.*;

// Esta clase es para crear todos los autores con sus atributos.
public class Author implements Serializable, Comparable<Author> {
    private String identificador; // Identificador único del autor
    private String nombre; // Nombre del autor
    private int nacimiento; // Año de nacimiento del autor
    private String pais; // Identificador del país del autor

    // Constructor
    public Author(String nombre, String identificador, int nacimiento, String pais) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.pais = pais;

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

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // Para ordenar la lista de países.
    @Override
    public int compareTo(Author otroAutor) {
        return this.identificador.compareTo(otroAutor.identificador);
    }

    /*
     * private void writeObject(ObjectOutputStream out) throws IOException {
     * // Personaliza el orden de escritura de los atributos
     * out.writeObject(nombre);
     * out.writeObject(identificador);
     * out.writeInt(nacimiento);
     * out.writeObject(pais);
     * }
     * 
     * private void readObject(ObjectInputStream in) throws IOException,
     * ClassNotFoundException {
     * // Personaliza el orden de lectura de los atributos
     * nombre = (String) in.readObject();
     * identificador = (String) in.readObject();
     * nacimiento = in.readInt();
     * pais = (String) in.readObject();
     * }
     * 
     * private void readObjectNoData() throws ObjectStreamException {
     * // Método requerido para Serializable, pero no se utiliza en este caso
     * }
     */

}