La práctica consiste en implementar un servicio de consultas sobre la información de los ficheros LML.
A través de interfaz HTML, se generarán pantallas navegables desde el browser. Esta incluirá los elementos
necesarios para que el usuario prosiga la consulta enviando, en cada fase, un parámetro de las siguientes que guiará
la evolución de la consulta. En estas pantallas se implementará la siguiente sucesión de consultas a través de fases (indicada mediante el
parámetro fase):

• 0: Pide la pantalla inicial en la que se presentará un mensaje de bienvenida. Será el valor por defecto si no hay
parámetro fase. Se mostrará el nombre del fichero procesado, así como la IP del cliente y del servidor y el
navegador desde el que se hace la petición. Estos datos serán generados en el servidor, es decir, no pueden ser
averiguados por el cliente usando JavaScript.

• 1: Se muestra la lista de países conocidos en los cuales se ha publicado algún libro.

• 2: Se muestra la lista de autores con libros publicados en un país (recibido en el parámetro pais).

• 3: Se muestra la lista de libros de un cierto autor (se recibe su id en el parámetro autor). Para cada libro se
mostrará su título y código ISBN. Se mostrarán en color rojo aquellos libros que no estén disponibles según el
atributo correspondiente.

Cada página debe informar de las selecciones previas que han conducido a ella, y debe incluir un botón para
retroceder en la consulta. Cada página irá firmada al pie con el nombre del autor.
Esta consulta también deberá implementarse a través de una interfaz REST.

Se escribirá una serie de métodos que deben recibir los parámetros indicados, buscar la información
y devolver (ya ordenados por identificador en sentido creciente, es decir, primero los menores) los datos de la
respuesta a cada fase de la consulta, independientemente del tipo de consulta que sea. Los métodos para la consulta
son:
• ArrayList<Author> getAuthors(String countryId)
• ArrayList<Book> getBooks (String authorId)
• ArrayList<Country> getCountries ()
• Author getAuthor(String authorId)
• Country getCountry(String countryId)
• Book getBook(String bookId)
Los nombres y parámetros son los indicados, todos obligatorios, y no se permite ninguno adicional.
Los tipos de datos Author, Book y Country serán clases definidas en la práctica para agrupar toda la necesaria que se
usará posteriormente para crear la respuesta. Deberán contener, al menos, una variable miembro llamada identificador
que actuará como clave primaria.
