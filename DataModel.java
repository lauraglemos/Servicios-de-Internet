package p2;

import p2.*;
import java.io.*;
import java.util.*;

import javax.print.Doc;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.json.*;

import java.net.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

//Esta clase se encarga de recopilar todos los datos necesarios (países, autores y libros).
public class DataModel {

    public Document doc = null;

    public String fileName = null;

    public boolean docCorrecto = true;

    // Este método analiza el archivo con los datos.
    public void parsearDoc() {

        String urlString = "https://manolo.webs.uvigo.gal/SINT/libreria.xml";

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            URL url = new URL(urlString);

            Document docURL = db.parse(urlString);

            String path = url.getPath();

            fileName = path.substring(path.lastIndexOf('/') + 1);
            Element libreria = docURL.getDocumentElement();

            doc = docURL;
            docCorrecto = true;

        } catch (Exception e) {

            docCorrecto = false;

        }

    }

    // Método utilizado para obtener todos los países que se encuentran en los
    // documentos.

    public ArrayList<Country> getCountries() throws ParserConfigurationException, SAXException, IOException {

        ArrayList<Country> paises = new ArrayList<Country>();

        try {

            NodeList nl = doc.getElementsByTagName("pais");
            Element elemPais;
            String nombrePais;
            String idPais;

            // se crea la lista de paises
            for (int i = 0; i < nl.getLength(); i++) {
                elemPais = (Element) nl.item(i);

                nombrePais = elemPais.getTextContent();
                idPais = elemPais.getAttribute("identificador");

                Country pais = new Country(nombrePais, idPais);

                if (!paises.contains(pais)) {
                    paises.add(pais);
                }
            }
            // se ordenan los paises en orden creciente según su identificador
            Collections.sort(paises);

        } catch (Exception e) {

        }

        return paises;
    }

    // Método utilizado para obtener todos los autores de un país dado que se
    // encuentran en los documentos.

    public ArrayList<Author> getAuthors(String countryID) {

        ArrayList<Author> listaAutores = new ArrayList<Author>();

        NodeList nl = doc.getElementsByTagName("autor");

        Element elemAutor;
        String nombreAutor;
        String idPaisAutor;
        String idAutor;
        int año;

        for (int i = 0; i < nl.getLength(); i++) {

            elemAutor = (Element) nl.item(i);

            nombreAutor = elemAutor.getTextContent();

            idPaisAutor = elemAutor.getAttribute("pais");

            // se crea la lista de autores

            if (idPaisAutor.equals(countryID)) {

                idAutor = elemAutor.getAttribute("identificador");
                año = Integer.parseInt(elemAutor.getAttribute("nacimiento"));

                Author autor = new Author(nombreAutor, idAutor, año, countryID);

                if (!listaAutores.contains(autor)) {

                    listaAutores.add(autor);

                }
            }

        }

        // se ordena la lista

        Collections.sort(listaAutores);

        return listaAutores;
    }

    // Método utilizado para obtener todos los libros de un autor dado que se
    // encuentran en los documentos.
    public ArrayList<Book> getBooks(String authorId) {

        ArrayList<Book> libros = new ArrayList<Book>();

        NodeList nl = doc.getElementsByTagName("libro");

        Element elemLibro;
        String tituloLibro;
        String idAutorLibro;
        String isbnLibro;
        String disponible;
        String idLibro;

        // se crea la lista de libros

        for (int i = 0; i < nl.getLength(); i++) {

            elemLibro = (Element) nl.item(i);

            tituloLibro = elemLibro.getTextContent();

            idAutorLibro = elemLibro.getAttribute("autor");

            if (idAutorLibro.equals(authorId)) {

                idLibro = elemLibro.getAttribute("identificador");
                isbnLibro = elemLibro.getAttribute("ISBN");
                disponible = elemLibro.getAttribute("disponible");

                Book libro = new Book(idLibro, tituloLibro, isbnLibro, idAutorLibro, disponible);

                if (!libros.contains(libro)) {

                    libros.add(libro);

                }
            }

        }

        // se ordena la lista
        Collections.sort(libros);

        return libros;

    }

    // Método para encontrar un país a partir de su identificador.

    public Country getCountry(String countryId) {

        try {
            ArrayList<Country> paisesLista = getCountries();

            for (Country pais : paisesLista) {
                if (pais.getIdentificador().equals(countryId)) {
                    return pais;
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    // Método para encontrar un autor a partir de su identificador.

    public Author getAuthor(String authorId) {

        ArrayList<Author> autoresLista = new ArrayList<Author>();

        try {
            ArrayList<Country> paisesLista = getCountries();

            for (Country country : paisesLista) {

                autoresLista.addAll(getAuthors(country.getIdentificador()));

            }

            for (Author autor : autoresLista) {
                if (autor.getIdentificador().equals(authorId)) {
                    return autor;
                }
            }

        } catch (Exception e) {
        }

        return null;
    }

    // Método para encontrar un libro a partir de su identificador.
    public Book getBook(String bookId) {

        try {
            NodeList nl = doc.getElementsByTagName("libro");

            for (int i = 0; i < nl.getLength(); i++) {
                Element elemLibro = (Element) nl.item(i);
                String idLibro = elemLibro.getAttribute("identificador");

                if (idLibro.equals(bookId)) {
                    String tituloLibro = elemLibro.getTextContent();
                    String idAutorLibro = elemLibro.getAttribute("autor");
                    String isbnLibro = elemLibro.getAttribute("ISBN");
                    String disponible = elemLibro.getAttribute("disponible");

                    return new Book(idLibro, tituloLibro, isbnLibro, idAutorLibro, disponible);
                }
            }
        } catch (Exception e) {

        }

        return null;
    }

}