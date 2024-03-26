package p2;

import p2.*;
import java.io.*;

import javax.xml.parsers.*;

import java.net.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.*;

import org.w3c.dom.*;

//Esta clase tiene dos métodos. El método init que llama al método de la clase DataModel que analiza el archivo.
//El segundo método es el doGet() que llama a todos los métodos del FrontEnd con el objetivo de mostrar todas las pantallas.

public class Sint11P2 extends HttpServlet {

    // Para llamar a los métodos que se encuentran en las clases DataModel y
    // FrontEnd.

    FrontEnd frontEnd = new FrontEnd();
    DataModel dataModel = new DataModel();

    // Llama al método que analiza el archivo.
    public void init() {

        try {

            dataModel.parsearDoc();
        } catch (Exception e) {

        }
    }

    // Llama a todos los métodos del FrontEnd con el objetivo de mostrar todas las
    // pantallas.
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            String fase = req.getParameter("fase");
            String pais = req.getParameter("pais");
            String autor = req.getParameter("autor");

            ArrayList<Country> listaPaises = new ArrayList<Country>();
            ArrayList<Author> listaAutores = new ArrayList<Author>();
            ArrayList<Book> listaLibros = new ArrayList<Book>();

            JSONArray ja = new JSONArray();

            String requestUrl = req.getRequestURI();

            // Acceso a la API que da soporte a los métodos REST, ya que la URL tiene que
            // ser .../P2Lib/v1/...
            if (requestUrl.contains("/v1/")) {

                // si el documento no es válido se envía un código de error 404.
                if (!dataModel.docCorrecto) {

                    res.setStatus(404);

                }

                // muestra todos los paises registrados, si no hay países se envía un código de
                // error 404
                if (requestUrl.endsWith("/v1/paises")) {

                    listaPaises = dataModel.getCountries();

                    if (listaPaises.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        ja = new JSONArray(listaPaises);

                        frontEnd.mostrarJSONArray(res, ja);
                    }

                }

                // muestra todos los autores registrados, si no hay autores se envía un código
                // de error 404
                if (requestUrl.endsWith("/v1/autores")) {

                    listaPaises = dataModel.getCountries();

                    for (Country country : listaPaises) {

                        listaAutores.addAll(dataModel.getAuthors(country.getIdentificador()));

                    }

                    if (listaAutores.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        Collections.sort(listaAutores);

                        ja = new JSONArray(listaAutores);

                        frontEnd.mostrarJSONArray(res, ja);
                    }

                }

                // muestra el pais del que hemos pasado el identificador, si no existe se envía
                // un código de error 404

                if (requestUrl.contains("/v1/pais/")) {

                    int ultimaBarra = requestUrl.lastIndexOf('/');

                    String paisID = requestUrl.substring(ultimaBarra + 1);

                    Country paisJSON = dataModel.getCountry(paisID);

                    ArrayList<Country> paisSolo = new ArrayList<Country>();

                    paisSolo.add(paisJSON);

                    if (paisSolo.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        ja = new JSONArray(paisSolo);

                        frontEnd.mostrarJSONArray(res, ja);
                    }

                }

                // muestra el autor del que hemos pasado el identificador, si no existe se envía
                // un código de error 404

                if (requestUrl.contains("/v1/autor/")) {

                    int ultimaBarra = requestUrl.lastIndexOf('/');

                    String autorID = requestUrl.substring(ultimaBarra + 1);

                    Author autorJSON = dataModel.getAuthor(autorID);

                    ArrayList<Author> autorSolo = new ArrayList<Author>();

                    autorSolo.add(autorJSON);

                    if (autorSolo.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        ja = new JSONArray(autorSolo);

                        frontEnd.mostrarJSONArray(res, ja);
                    }

                }

                // muestra los autores del pais del que hemos pasado el identificador, si no hay
                // se envía un código de error 404

                if (requestUrl.contains("v1/autores/pais/")) {

                    int ultimaBarra = requestUrl.lastIndexOf('/');

                    String paisID = requestUrl.substring(ultimaBarra + 1);

                    listaAutores = dataModel.getAuthors(paisID);

                    if (listaAutores.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        Collections.sort(listaAutores);

                        ja = new JSONArray(listaAutores);

                        frontEnd.mostrarJSONArray(res, ja);
                    }

                }

                // muestra los libros del autor del que hemos pasado el identificador, si no
                // existe se envía un código de error 404

                if (requestUrl.contains("v1/libros/autor/")) {

                    int ultimaBarra = requestUrl.lastIndexOf('/');

                    String autorID = requestUrl.substring(ultimaBarra + 1);

                    listaLibros = dataModel.getBooks(autorID);

                    if (listaLibros.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        Collections.sort(listaLibros);

                        ja = new JSONArray(listaLibros);

                        frontEnd.mostrarJSONArray(res, ja);

                    }

                }

                // muestra el libro del que hemos pasado el identificador, si no existe se envía
                // un código de error 404

                if (requestUrl.contains("/v1/libro/")) {

                    int ultimaBarra = requestUrl.lastIndexOf('/');

                    String libroID = requestUrl.substring(ultimaBarra + 1);

                    Book libroJSON = dataModel.getBook(libroID);

                    ArrayList<Book> libroSolo = new ArrayList<>();

                    libroSolo.add(libroJSON);

                    if (libroSolo.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    else {

                        ja = new JSONArray(libroSolo);

                        frontEnd.mostrarJSONArray(res, ja);

                    }

                }

                // muestra todos los libros registrados, si no hay se envía un código de error
                // 404

                if (requestUrl.endsWith("/v1/libros")) {

                    listaPaises = dataModel.getCountries();

                    for (Country country : listaPaises) {

                        listaAutores.addAll(dataModel.getAuthors(country.getIdentificador()));

                    }

                    for (Author author : listaAutores) {

                        listaLibros.addAll(dataModel.getBooks(author.getIdentificador()));
                    }

                    if (listaLibros.isEmpty()) {

                        frontEnd.error404(res, ja);

                    }

                    Collections.sort(listaLibros);

                    ja = new JSONArray(listaLibros);

                    frontEnd.mostrarJSONArray(res, ja);

                }

            }
            // sin ser API REST
            else {

                // si el documento de datos no es válido, la pantalla mostrará un 0
                if (!dataModel.docCorrecto) {

                    frontEnd.errorDoc(res);
                }

                // si no hay fase se muestra el mensaje de bienvenida, que es la fase 0
                if (fase == null) {

                    frontEnd.fase0(req, res, dataModel.fileName);
                }

                else {

                    switch (fase) {

                        case "0": // se pide la pantalla inicial
                            frontEnd.fase0(req, res, dataModel.fileName);
                            break;

                        case "1": // se pide el listado de paises conocidos en los cuales se ha publicado un libro

                            listaPaises = dataModel.getCountries();

                            frontEnd.fase1(res, listaPaises);

                            break;

                        case "2": // se pide el listado de autores con libros publicados en un pais
                            pais = req.getParameter("pais");

                            Country nombrePais2 = dataModel.getCountry(pais);
                            String nPais2 = nombrePais2.getNombre();

                            listaAutores = dataModel.getAuthors(pais);

                            frontEnd.fase2(res, nPais2, listaAutores);
                            break;

                        case "3":// se muestra la lista de libros de un cierto autor. Los libros que no estén
                                 // disponibles se marcarán en rojo.

                            autor = req.getParameter("autor");
                            listaLibros = dataModel.getBooks(autor);

                            Author nombreAutor = dataModel.getAuthor(autor);
                            String nAutor = nombreAutor.getNombre();

                            Country nombrePais3 = dataModel.getCountry(nombreAutor.getPais());
                            // String nPais3 = nombrePais3.getNombre();

                            frontEnd.fase3(res, nombrePais3, nAutor, listaLibros);
                            break;

                        default:
                            // Manejar otras fases si es necesario
                            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fase no válida");
                            break;
                    }
                }
            }

        } catch (Exception e) {

        }
    }
}
