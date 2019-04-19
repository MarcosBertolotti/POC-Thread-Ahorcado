package utn;

import java.sql.*;

public class ConexionBD {

    private Connection connection;
    private Statement st;
    private PreparedStatement PrepareSt;
    private String query;

    public void crearBD(){

        verificarConexion();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
            st = connection.createStatement();

            query = "create database if not exists ahorcado;";
            st.execute(query);

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ahorcado","root","");
            st = connection.createStatement();

            query = "create table if not exists palabras(" +
                    "   id_palabra int auto_increment," +
                    "   palabra varchar (50) unique," +
                    "   primary key (id_palabra)" +
                    ");";
            st.execute(query);

            query = "create table if not exists jugadores(" +
                    "   id_jugador int auto_increment," +
                    "   nombre_jugador varchar(50)," +
                    "   primary key(id_jugador)" +
                    ");";
            st.execute(query);

            query = "create table if not exists resultados(" +
                    "    id_resultado int auto_increment," +
                    "    nombre varchar(50)," +
                    "    palabra varchar(50)," +
                    "    fecha date," +
                    "    primary key(id_resultado)" +
                    ");";
            st.execute(query);

            query = "insert ignore into palabras (palabra) values " +
                    "('arbol'), ('delfin')," +
                    "('sombrero'),('barra'), ('hervir')," +
                    "('mision'), ('piscis'), ('pantufla')," +
                    "('palta'),('yogurt'), ('estuche')," +
                    "('remera'), ('cuaderno'), ('pizarron')," +
                    "('matambre'),('estanteria'), ('semaforo')," +
                    "('cuarentena'),('murcielago'), ('hilo')," +
                    "('transportista'), ('loro');";
            st.execute(query);

            connection.close();

        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la Base de Datos");
            e.printStackTrace();
        }
    }

    public String getPalabraBD(){

        String palabra = "";
        verificarConexion();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ahorcado","root","");
            st = connection.createStatement();

            query = "select palabra from palabras order by rand() limit 1;";

            ResultSet resulSet = st.executeQuery(query);

            while(resulSet.next()) {
                palabra = resulSet.getString(1);
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la Base de Datos");
            e.printStackTrace();
        }
        return palabra;
    }

    public void guardarResultadoBD(String nombre, String palabra){

        verificarConexion();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ahorcado","root","");

            query = "insert into resultados (nombre,palabra,fecha) values (?,?,now())";

            PrepareSt = connection.prepareStatement(query);

            PrepareSt.setString(1,nombre);
            PrepareSt.setString(2,palabra);
            PrepareSt.execute();

            System.out.println("\nGanador almacenado en la Base de Datos correctamente");

            connection.close();

        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la Base de Datos");
            e.printStackTrace();
        }
    }

    public void mostrarGanadores(){

        verificarConexion();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ahorcado","root","");
            st = connection.createStatement();

            query = "select * from resultados;";

            ResultSet resulSet = st.executeQuery(query);

            while(resulSet.next()) {
                System.out.println(resulSet.getString("nombre") + " " +
                                    resulSet.getString("palabra") + " " +
                                    resulSet.getDate("fecha"));
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la Base de Datos");
            e.printStackTrace();
        }
    }

    public void verificarConexion(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la libreria Mysql, no se puede seguir");
            e.printStackTrace();
        }
    }
}
