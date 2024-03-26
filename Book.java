package p2;

import p2.*;
import java.io.*;

// Esta clase es para crear todos los libros con sus atributos.
public class Book implements Serializable, Comparable<Book> {
    private String identificador; // Identificador único del libro
    private String titulo; // Título del libro
    private String ISBN; // Código ISBN del libro
    private String autor; // Identificador del autor del libro
    private String disponible; // Indica si el libro está disponible o no

    // Constructor de la clase Book con todos sus atributos
    public Book(String identificador, String titulo, String ISBN, String autor, String disponible) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.autor = autor;
        this.disponible = disponible;
    }

    // Métodos getters y setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    // Para ordenar la lista de libros.
    @Override
    public int compareTo(Book otroLibro) {
        return this.identificador.compareTo(otroLibro.identificador);
    }

    /*
     * private void writeObject(ObjectOutputStream out) throws IOException {
     * // Personaliza el orden de escritura de los atributos
     * out.writeObject(titulo);
     * out.writeObject(identificador);
     * out.writeObject(autor);
     * out.writeObject(ISBN);
     * out.writeObject(disponible);
     * }
     */

    /*
     * private void readObject(ObjectInputStream in) throws IOException,
     * ClassNotFoundException {
     * // Personaliza el orden de lectura de los atributos
     * titulo = (String) in.readObject();
     * identificador = (String) in.readObject();
     * autor = (String) in.readObject();
     * ISBN = (String) in.readObject();
     * disponible = (String) in.readObject();
     * }
     * 
     * private void readObjectNoData() throws ObjectStreamException {
     * // Método requerido para Serializable, pero no se utiliza en este caso
     * }
     */
}
