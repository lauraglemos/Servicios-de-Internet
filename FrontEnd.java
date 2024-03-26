package p2;

import p2.*;
import java.io.*;
import java.util.ArrayList;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.*;

//Esta clase es para crear todas las pantallas.
public class FrontEnd {

    // La pantalla inicial que se llama cuando la fase es 0 o no hay fase.
    public void fase0(HttpServletRequest req, HttpServletResponse res, String fileName) {

        // Obtener datos del servidor
        String serverIP = req.getLocalAddr();
        String clientIP = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");

        // Obtener el nombre del fichero procesado.
        HttpSession session = req.getSession();

        // Configurar la respuesta HTTP con el contenido HTML
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        // Escribir el contenido HTML de la página de bienvenida
        try {
            PrintWriter out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<title>Inicio</title>");
            out.println("<link href='./WEB-INF/classes/p2.css' type='text/css' rel='stylesheet'>");
            out.println("</head><body>");
            out.println("<h1>Servicio de consulta de informaci&oacuten</h1>");
            out.println("<p>Fichero procesado: " + fileName + "</p>");
            out.println("<p>IP del cliente: " + clientIP + "</p>");
            out.println("<p>Navegador del cliente: " + userAgent + "</p>");
            out.println("<p>IP del servidor: " + serverIP + "</p>");

            out.println("<button type=\"button\" id='avanzar' onclick=\"location.href='?fase=1';\">Avanzar</button>");
            out.println("<hr>");

            out.println("<p>Autor: Laura Gonz&aacutelez Lemos</p>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {

        }
    }

    // La fase 1 que muestra la lista de países conocidos en los cuales se ha
    // publicado algún libro.

    public void fase1(HttpServletResponse res, ArrayList<Country> paises) {

        // Configurar la respuesta HTTP con el contenido HTML
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");

        // Escribir el contenido HTML de la página con la lista de países
        try {

            PrintWriter out = res.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<title>Fase 1</title>");
            out.println("<link href='./WEB-INF/classes/p2.css' rel='stylesheet'>");

            out.println("</head>");

            out.println("<body>");
            out.println("<h1>Servicio de consulta de informaci&oacuten</h1>");
            out.println("<h2>Fase 1</h2>");
            out.println("<p>Seleccione el pa&iacutes:</p>");

            out.println("<ol>");

            for (int i = 0; i < paises.size(); i++) {

                out.println("<li><a href='?fase=2&pais=" + paises.get(i).getIdentificador() + "'>"
                        + paises.get(i).getNombre() + "</a>");

            }

            out.println("</ol>");

            out.println("<button type=\"button\" id='anterior' onclick=\"location.href='?fase=0';\">Anterior</button>");
            out.println("<hr>");
            out.println("<p>Autor: Laura Gonz&aacutelez Lemos</p>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {

        }
    }

    // La fase 2 que muestra la lista de autores con libros publicados en un país
    // (recibido en el parámetro pais).

    public void fase2(HttpServletResponse res, String pais, ArrayList<Author> autores) {

        // Configurar la respuesta HTTP con el contenido HTML
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        // Escribir el contenido HTML de la página con la lista de autores de un país
        try {
            PrintWriter out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<title>Fase 2</title>");

            out.println("<link href='./WEB-INF/classes/p2.css' rel='stylesheet'>");

            out.println("</head><body>");

            out.println("<h1>Servicio de consulta de informaci&oacuten</h1>");

            out.println("<h2>Fase 2</h2>");

            out.println("<h3> Consultado informaci&oacuten de pa&iacutes: " + pais + " </h4>");

            out.println("<p>Seleccione el autor:</p>");

            out.println("<ol>");

            for (int i = 0; i < autores.size(); i++) {

                Author autor = autores.get(i);

                out.println("<li><p><a href='?fase=3&autor=" + autor.getIdentificador() + "'>" + autor.getNombre()
                        + "</a> Nacido en: " + autor.getNacimiento() + "</p>");

            }

            out.println("</ol>");

            out.println("<button type=\"button\" id='anterior' onclick=\"location.href='?fase=1';\">Anterior</button>");
            out.println("<hr>");
            out.println("<p>Autor: Laura Gonz&aacutelez Lemos</p>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {

        }

    }

    // La fase 3 que muestra la lista de libros de un cierto autor (se recibe su id
    // en el parámetro autor).

    public void fase3(HttpServletResponse res, Country pais, String autor, ArrayList<Book> libros) {

        // Configurar la respuesta HTTP con el contenido HTML
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        // Escribir el contenido HTML de la página con la lista de libros de un autor.
        // Si el libro no está disponible sale en rojo.
        try {
            PrintWriter out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<title>Fase 3</title>");

            out.println("<style> .no-disponible { color: red; } </style>");

            out.println("<link href='./WEB-INF/classes/p2.css' rel='stylesheet'>");

            out.println("</head><body>");

            out.println("<h1>Servicio de consulta de informaci&oacuten</h1>");

            out.println("<h2>Fase 3</h2>");

            out.println("<h3> Consultado informaci&oacuten de pa&iacutes: " + pais.getNombre() + " </h3>");
            out.println("<h3> Consultado informaci&oacuten de autor: " + autor + " </h3>");

            out.println("<p>Lista de libros</p>");

            out.println("<ol>");

            for (int i = 0; i < libros.size(); i++) {

                Book libro = libros.get(i);

                // Verificar si el libro está disponible
                boolean disponible = libro.getDisponible().equalsIgnoreCase("yes");

                // Configurar el color del enlace según la disponibilidad
                String color = disponible ? "blue" : "red";

                // Configurar el estilo del enlace
                String estilo = " style='color: " + color + ";'";

                // Agregar enlace al título del libro
                out.println("<li><a href=''" + estilo + ">" + libro.getTitulo() + "</a> ISBN: " + libro.getISBN()
                        + "</li>");

                        //String claseCss = disponible ? "" : "no-disponible";

                        // Agregar enlace al título del libro con la clase CSS
                       // out.println("<li><a href='' class='" + claseCss + "'>" + libro.getTitulo() + "</a> ISBN: " + libro.getISBN()
                               // + "</li>");

            }

            out.println("</ol>");

            out.println("<button type=\"button\" id='anterior' onclick=\"location.href='?fase=2&pais="
                    + pais.getIdentificador() + "';\">Anterior</button>");
            out.println("<hr>");
            out.println("<p>Autor: Laura Gonz&aacutelez Lemos</p>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {

        }
    }

    // Método para enviar la respuesta en formato JSON.
    public void mostrarJSONArray(HttpServletResponse res, JSONArray ja) {

        try {

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().println(ja.toString());
            System.out.println(ja.toString());
            res.setStatus(200);

        } catch (Exception e) {

        }
    }

    // Método para mostrar un 0 en pantalla cuando el documento no es válido.
    public void errorDoc(HttpServletResponse res) {

        try {
            PrintWriter out = res.getWriter();
            out.println("<html><body><h1>0</h1></body></html>");
        } catch (Exception e) {

        }
    }

    // Método para devolver un código de error 404 cuando el documento no es válido.
    public void error404(HttpServletResponse res, JSONArray ja) {

        try {

            res.getWriter().println(ja.toString());
            res.setStatus(404);
        } catch (Exception e) {

        }

    }

}
