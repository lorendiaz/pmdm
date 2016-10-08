package practicas.fpmislata.com.mischollosbd.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import practicas.fpmislata.com.mischollosbd.dao.AlojamientoDAO;
import practicas.fpmislata.com.mischollosbd.dao.CholloDAO;
import practicas.fpmislata.com.mischollosbd.dao.UsuarioDAO;


/**
 * Created by loren on 07/10/15.
 */
public class MiBD extends SQLiteOpenHelper {

    private static SQLiteDatabase db;
    //nombre de la base de datos
    private static final String database = "MisChollosBD";
    //versión de la base de datos
    private static final int version = 9;
    //Instrucción SQL para crear la tabla de Usuarios
    private String sqlCreacionUsuarios = "CREATE TABLE " + UsuarioDAO.C_TABLA + " ( " +
            UsuarioDAO.C_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UsuarioDAO.C_COLUMNA_NIF + " STRING, " +
            UsuarioDAO.C_COLUMNA_NOMBRE + " STRING, " +
            UsuarioDAO.C_COLUMNA_APELLIDOS + " STRING, " +
            UsuarioDAO.C_COLUMNA_EDAD + " INTEGER, " +
            UsuarioDAO.C_COLUMNA_LOCALIDAD + " STRING, " +
            UsuarioDAO.C_COLUMNA_CLAVESEGURIDAD + " STRING, " +
            UsuarioDAO.C_COLUMNA_EMAIL + " STRING " +
            " );";
    //Instruccion SQL para crear la tabla de Chollos
    private String sqlCreacionChollos = "CREATE TABLE " + CholloDAO.C_TABLA + " ( " +
            CholloDAO.C_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CholloDAO.C_COLUMNA_TITULO + " STRING, " +
            CholloDAO.C_COLUMNA_ORDEN + " INTEGER " +
            " );";
    //Instruccion SQL para crear la tabla de Alojamientos
    private String sqlCreacionAlojamientos = "CREATE TABLE " + AlojamientoDAO.C_TABLA + " ( " +
            AlojamientoDAO.C_COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AlojamientoDAO.C_COLUMNA_IDCHOLLO + " INTEGER, " +
            AlojamientoDAO.C_COLUMNA_PRECIOPERSONA + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_INFORMACIONRESUMIDA + " STRING, " +
            AlojamientoDAO.C_COLUMNA_SIINCLUYE + " STRING, " +
            AlojamientoDAO.C_COLUMNA_NOINCLUYE + " STRING, " +
            AlojamientoDAO.C_COLUMNA_NOTASIMPORTANTES + " STRING, " +
            AlojamientoDAO.C_COLUMNA_VIAJE + " STRING, " +
            AlojamientoDAO.C_COLUMNA_DESCUENTOS + " STRING, " +
            AlojamientoDAO.C_COLUMNA_CONDICIONES + " STRING, " +
            AlojamientoDAO.C_COLUMNA_LOCALIDAD + " STRING, " +
            AlojamientoDAO.C_COLUMNA_DESCRIPCIONGENERAL + " STRING, " +
            AlojamientoDAO.C_COLUMNA_SERVICIOSALOJAMIENTO + " STRING, " +
            AlojamientoDAO.C_COLUMNA_SERVICIOSHABITACION + " STRING, " +
            AlojamientoDAO.C_COLUMNA_HORARIOSHABITACION + " STRING, " +
            AlojamientoDAO.C_COLUMNA_NOTA + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_ESTRELLAS + " INTEGER, " +
            AlojamientoDAO.C_COLUMNA_NUMEROENCUESTAS + " INTEGER, " +
            AlojamientoDAO.C_COLUMNA_HABITACIONES + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_SERVICIOS + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_LIMPIEZA + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_COMIDA + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_PERSONAL + " FLOAT, " +
            AlojamientoDAO.C_COLUMNA_CALIDADPRECIO + " FLOAT " +
            " );";

    private static MiBD instance = null;

    private static AlojamientoDAO alojamientoDAO;
    private static CholloDAO cholloDAO;
    private static UsuarioDAO usuarioDAO;
    private static Context contextoAplicacion;

    public static Context getContextoAplicacion() {
        return contextoAplicacion;
    }

    public static void setContextoAplicacion(Context contextoAplicacion) {
        MiBD.contextoAplicacion = contextoAplicacion;
    }

    public static AlojamientoDAO getAlojamientoDAO() {
        return alojamientoDAO;
    }

    public static CholloDAO getCholloDAO() {
        return cholloDAO;
    }

    public static UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public static MiBD getInstanceBD() {
        if(instance == null) {
            instance = new MiBD(contextoAplicacion);
            db = instance.getWritableDatabase();
            cholloDAO = new CholloDAO();
            alojamientoDAO = new AlojamientoDAO();
            usuarioDAO = new UsuarioDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB(){
        return db;
    }
    public static void closeDB(){db.close();};

    /**
     * Constructor de clase
     * */
    protected MiBD(Context context) {
        super( context, database, null, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando tabla Usuarios");
        db.execSQL(sqlCreacionUsuarios);
        Log.i(this.getClass().toString(), "Tabla Usuarios creada");
        Log.i(this.getClass().toString(), "Creando tabla Chollos");
        db.execSQL(sqlCreacionChollos);
        Log.i(this.getClass().toString(), "Tabla Chollos creada");
        Log.i(this.getClass().toString(), "Creando tabla Alojamientos");
        db.execSQL(sqlCreacionAlojamientos);
        Log.i(this.getClass().toString(), "Tabla Alojamientos creada");
        //db.execSQL(sqlCreacionValoraciones);

        // Insertar datos de usuarios
        Log.i(this.getClass().toString(), "Insertando datos iniciales Usuarios");
        insercionDatosUsuarios(db);
        Log.i(this.getClass().toString(), "Datos iniciales Usuarios insertados");

        // Insertar datos de chollos
        Log.i(this.getClass().toString(), "Insertando datos iniciales Chollos");
        insercionDatosChollos(db);
        Log.i(this.getClass().toString(), "Datos iniciales Chollos insertados");

        // Insertar datos de alojamientos
        Log.i(this.getClass().toString(), "Insertando datos iniciales Alojamientos");
        insercionDatosAlojamientos(db);
        Log.i(this.getClass().toString(), "Datos iniciales Alojamientos insertados");

        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS " + AlojamientoDAO.C_TABLA );
            db.execSQL( "DROP TABLE IF EXISTS " + CholloDAO.C_TABLA );
            db.execSQL( "DROP TABLE IF EXISTS " + UsuarioDAO.C_TABLA );
            //db.execSQL( "DROP TABLE IF EXISTS " + ValoracionDAO.C_TABLA );
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionUsuarios);
            db.execSQL(sqlCreacionChollos);
            db.execSQL(sqlCreacionAlojamientos);
           // db.execSQL(sqlCreacionValoraciones);

            // Insertar datos de usuarios
            Log.i(this.getClass().toString(), "Insertando datos iniciales Usuarios");
            insercionDatosUsuarios(db);
            Log.i(this.getClass().toString(), "Datos iniciales Usuarios insertados");

            // Insertar datos de chollos
            Log.i(this.getClass().toString(), "Insertando datos iniciales Chollos");
            insercionDatosChollos(db);
            Log.i(this.getClass().toString(), "Datos iniciales Chollos insertados");

            // Insertar datos de alojamientos
            Log.i(this.getClass().toString(), "Insertando datos iniciales Alojamientos");
            insercionDatosAlojamientos(db);
            Log.i(this.getClass().toString(), "Datos iniciales Alojamientos insertados");

            Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);

            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }
    private void insercionDatosUsuarios(SQLiteDatabase db) {
        String cSql = "INSERT INTO " + UsuarioDAO.C_TABLA + "(" +
                UsuarioDAO.C_COLUMNA_ID + "," +
                UsuarioDAO.C_COLUMNA_NIF + "," +
                UsuarioDAO.C_COLUMNA_NOMBRE + "," +
                UsuarioDAO.C_COLUMNA_APELLIDOS + "," +
                UsuarioDAO.C_COLUMNA_EDAD + "," +
                UsuarioDAO.C_COLUMNA_LOCALIDAD + "," +
                UsuarioDAO.C_COLUMNA_CLAVESEGURIDAD + "," +
                UsuarioDAO.C_COLUMNA_EMAIL +") VALUES (";
        String sqlAux1 = cSql + "null," +
                "'" + "11111111A" + "'" + "," +
                "'" + "PEPITO" + "'" + "," +
                "'" + "PEREZ" + "'" + "," +
                "35" + "," +
                "'" + "VALENCIA" + "'" + "," +
                "'" + "1234" + "'" + "," +
                "'" + "pperez@gmail.com" + "'" + ");";
        db.execSQL(sqlAux1);

        sqlAux1 = cSql + "null," +
                "'" + "22222222A" + "'" + "," +
                "'" + "MORTADELO" + "'" + "," +
                "'" + "GIL" + "'" + "," +
                "40" + "," +
                "'" + "CORDOBA" + "'" + "," +
                "'" + "1111" + "'" + "," +
                "'" + "mgil@gmail.com" + "'" + ");";
        db.execSQL(sqlAux1);
    }

    private void insercionDatosChollos(SQLiteDatabase db) {
        String cSql = "INSERT INTO " + CholloDAO.C_TABLA + "(" +
                CholloDAO.C_COLUMNA_ID + "," +
                CholloDAO.C_COLUMNA_TITULO + "," +
                CholloDAO.C_COLUMNA_ORDEN + ") VALUES (";
        String sqlAux1 = cSql + "null," +
                "'" + "TODO INCLUIDO en Hotel 4* en Tossa de Mar" + "'" + "," +
                "1" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Pensión Completa y Hotel 4* en la Costa Brava" + "'" + "," +
                "2" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "¡Ven a la Costa Brava en Hotel 4* Sup. en Blanes!" + "'" + "," +
                "3" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Escápate con Pensión Completa en Lloret de Mar" + "'" + "," +
                "4" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Escapada a Cantabria con Spa y Desayuno" + "'" + "," +
                "5" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "2 Noches en Hotel 5* con Spa y Patinaje en Andorra" + "'" + "," +
                "6" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Crucero TODO INCLUIDO Leyendas del Mediterráneo" + "'" + "," +
                "7" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "¡Última Hora! Disfruta de Cadiz en Pensión Completa!" + "'" + "," +
                "8" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Pensión Completa en 1ª línea de playa (Costa Dorada)" + "'" + "," +
                "9" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Disfruta de Blanes en Media Pensión" + "'" + "," +
                "10" + ");";
        db.execSQL(sqlAux1);
    }

    private void insercionDatosAlojamientos(SQLiteDatabase db) {
        String cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_PRECIOPERSONA + "," +
                AlojamientoDAO.C_COLUMNA_INFORMACIONRESUMIDA + "," +
                AlojamientoDAO.C_COLUMNA_SIINCLUYE + "," +
                AlojamientoDAO.C_COLUMNA_NOINCLUYE + "," +
                AlojamientoDAO.C_COLUMNA_NOTASIMPORTANTES + "," +
                AlojamientoDAO.C_COLUMNA_VIAJE + "," +
                AlojamientoDAO.C_COLUMNA_DESCUENTOS + "," +
                AlojamientoDAO.C_COLUMNA_CONDICIONES + "," +
                AlojamientoDAO.C_COLUMNA_LOCALIDAD + "," +
                AlojamientoDAO.C_COLUMNA_DESCRIPCIONGENERAL + "," +
                AlojamientoDAO.C_COLUMNA_SERVICIOSALOJAMIENTO + "," +
                AlojamientoDAO.C_COLUMNA_SERVICIOSHABITACION + "," +
                AlojamientoDAO.C_COLUMNA_HORARIOSHABITACION + "," +
                AlojamientoDAO.C_COLUMNA_NOTA + "," +
                AlojamientoDAO.C_COLUMNA_ESTRELLAS + "," +
                AlojamientoDAO.C_COLUMNA_NUMEROENCUESTAS + "," +
                AlojamientoDAO.C_COLUMNA_HABITACIONES + "," +
                AlojamientoDAO.C_COLUMNA_SERVICIOS + "," +
                AlojamientoDAO.C_COLUMNA_LIMPIEZA + "," +
                AlojamientoDAO.C_COLUMNA_COMIDA + "," +
                AlojamientoDAO.C_COLUMNA_PERSONAL + "," +
                AlojamientoDAO.C_COLUMNA_CALIDADPRECIO + ") VALUES (";
        String sqlAux1 = cSql + "null," +
                "1" + "," +
                "50.23" + "," +
                "'" + "3 días y 2 noches en el Hotel TossaMar 4* situado en Tossa de Mar (Costa Brava) en régimen de TODO INCLUIDO + entrada al Casino con una consumición (1 por persona y estancia) con ticket de parking." + "'" + "," +
                "'" + " - 3 días y 2 noches en el Hotel TossaMar 4* de Tossa de Mar (Costa Brava).\n" + " - Régimen de TODO INCLUIDO por persona.\n" + " - Entrada al Casino de Lloret de Mar con una consumición (1 por persona y estancia) con ticket de parking.\n" + "'" + "," +
                "'" + " - Transporte hasta el alojamiento y extras no especificados anteriormente.\n" + " - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n" + " - El Todo Incluido NO incluye: parking, nevera, caja de seguridad, Wi-Fi en las habitaciones.\n" + "'" + "," +
                "'" + " - Fechas disponibles para viajar: del 5 al 31 de octubre.\n" + " - Este chollo también está disponible en más noches de estancia.\n" + "  - También es posible reservar este chollo para 1 noche de estancia con un pequeño suplemento que puedes consultar en el proceso de reserva.\n" + "'" + "," +
                "'" + " - En el casco antiguo de Tossa de Mar se encuentra la Vila Vella Tossa, así como a lo largo del paseo marítimo se encuentra Playa Gran. Además de esta playa, el municipio cuenta con 11 calas con mucho encanto y 6 pequeñas playas más, ideales para disfrutar de la tranquilidad del mar. \n" + " - Y tras el día de playa, en Tossa de Mar encontrarás gran variedad de restaurantes, mientras disfrutas de entornos privilegiados, como por ejemplo al abrigo de las murallas medievales de la Vila Vella Tossa o, al caer la noche, observar una bella panorámica de las murallas iluminadas de Tossa de Mar desde el paseo marítimo. \n" + " - ¡Conoce el enigmático municipio costero de Tossa de Mar y disfruta!\n" + "'" + "," +
                "'" + " - Bebé de 0 a 1 año: viajan GRATIS compartiendo cama con los padres.\n" + " - Niños de 2 a 4 años: 1º viaja GRATIS y 2º tiene un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños hasta 2 años tendrán que compartir cama con los adultos o dormir en cuna de viaje. \n" + " - Niños de 5 a 11 años: 1º y 2º viajan con un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños dispondrán de cama.\n" + " - Ocupación máxima: 4 personas (4 adultos, 3 adultos + 1 niño, 2 adultos + 2 niños o bebés).\n" + " - Suplemento de habitación individual: puedes consultarlo en el proceso de reserva. Para calcular el precio total de una habitación individual, deberás sumar este suplemento al precio por persona visible en la web.\n" + "'" + "," +
                "'" + "¡Genial! El alojamiento de este chollo SÍ admite mascotas. Puedes ver las condiciones y el precio en la pestaña de alojamiento. Una vez hecha tu reserva, es muy importante que hagas llegar tu petición al alojamiento a través de nuestro apartado de contacto, escogiendo la opción \"Peticiones al alojamiento\". " + "'" + "," +
                // Localidad
                "'" + "Tossa de Mar" + "'" + "," +
                //Descripcion general
                "'" + "Este hotel se encuentra cerca de la playa en el centro de la ciudad, cerca de lugares de interés como Playa de Tossa de Mar, Castillo de Tossa de Mar y Faro de Tossa de Mar. Las atracciones de esta área incluyen Cala Canyelles.\n" +
                " \n" +
                "Además de un restaurante, Hotel TossaMar ofrece piscina descubierta en verano. También brinda a sus clientes bar junto a la piscina y lounge.\n" +
                " \n" +
                "El Hotel TossaMar ofrece una relajante y agradable atmósfera y se compone de un edificio principal y otro anexo en los que se reparten las modernas habitaciones. \n" +
                " \n" +
                "A su vez, el hotel cuenta con una recepción, ascensores y sala de televisión. Además, tiene un bar muy acogedor, un comedor y un restaurante. Hay disponibles habitaciones para discapacitados, pero es necesario solicitar disponibilidad.\n" +
                " \n" +
                "Tanto el desayuno, comida y cena se ofrecen en forma de buffet." + "'" + "," +
                // Servicios alojamiento
                "'" + "24h recepción;Restaurante;Bar;Calefacción;Aire acondicionado en las zonas comunes;Jacuzzi;Piscina (verano);Sala de juegos;" +
                "Servicio de lavandería;Ascensor;Zona de relax;Terraza;Hamacas;Desayuno tipo Buffet;Comida tipo Buffet;Cena tipo Buffet;" +
                "Parking Exterior de pago;Se admiten animales bajo petición y de pago directo en hotel;Alimentos sin gluten bajo petición;Leche sin lactosa bajo petición;Acceso para discapacitados" + "'" + "," +
                // Servicios Habitacion
                "'" + "Televisión;Teléfono;WC;Ducha o Bañera;Secador de pelo;Caja fuerte (de pago);Nevera (de pago);Wifi (de pago)" + "'" + "," +
                // HorariosHabitacion
                "'" + " - Check in: a partir de las 14:00h\n" +
                " - Check out: antes de las 10:00h\n" +
                " - Parking (según disponibilidad): 25€ / noche, varía según días de estancia (de pago directo en el hotel).\n" +
                " - Mascotas: el precio varía según los días de estancia de 15 a 20€ por noche y mascota. Mascotas de máximo 20-25 kg.\n" +
                " - Horario Desayuno: de 8:15h a 10:00h\n" +
                " - Horario Comida: de 13:15h a 15:00h\n" +
                " - Horario Cena: de 19:45h a 21:45h\n" + "'" + "," +
                // Nota
                "7.98" + "," +
                // Estrellas
                "4" + "," +
                // numeroEncuestas
                "397" + "," +
                // Valoraciones:
                // Habitaciones
                "7.56" + "," +
                // Servicios
                "6.97" + "," +
                // Limpieza
                "9.32" + "," +
                // Comida
                "8.34" + "," +
                // Personal
                "7.86" + "," +
                // Relacion  calidad/precio
                "8.05" + ");";
        db.execSQL(sqlAux1);
    }
}