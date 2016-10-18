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
    private static final int version = 11;
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
            AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + " STRING, " +
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
                "'" + "Pensión Completa y Hotel 4* en la Santander" + "'" + "," +
                "2" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "¡Ven a la Costa en Hotel 4* Sup. en Cantabria!" + "'" + "," +
                "3" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Escápate con Pensión Completa en Lloret de Mar" + "'" + "," +
                "4" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Escapada a Cantabria con Spa y Desayuno (No Data)" + "'" + "," +
                "5" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "2 Noches en Hotel 5* con Spa y Patinaje en Andorra (No Data)" + "'" + "," +
                "6" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Crucero TODO INCLUIDO Leyendas del Mediterráneo (No Data)" + "'" + "," +
                "7" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "¡Última Hora! Disfruta de Cadiz en Pensión Completa! (No Data)" + "'" + "," +
                "8" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Pensión Completa en 1ª línea de playa (Costa Dorada) (No Data)" + "'" + "," +
                "9" + ");";
        db.execSQL(sqlAux1);
        sqlAux1 = cSql + "null," +
                "'" + "Disfruta de Blanes en Media Pensión (No Data)" + "'" + "," +
                "10" + ");";
        db.execSQL(sqlAux1);
    }

    private void insercionDatosAlojamientos(SQLiteDatabase db) {
        String cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
                //idChollo
                "1" + "," +
                // Titulo principal
                "'" + "TODO INCLUIDO en Hotel 4* en Tossa de Mar" + "'" + "," +
                // Precio
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

        //Inserción datos 1º alojamiento entrada 2
        cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
        sqlAux1 = cSql + "null," +
                //idChollo
                "1" + "," +
                // Titulo principal
                "'" + "5 Noches: Hotel Tossamar 4*, Tossa de Mar (Costa Brava)" + "'" + "," +
                // Precio
                "176" + "," +
                // Informacion resumida
                "'" + "6 días y 5 noches en el Hotel TossaMar 4* situado en Tossa de Mar (Costa Brava) en régimen de TODO INCLUIDO + entrada al Casino con una consumición (1 por persona y estancia) con ticket de parking." + "'" + "," +
                // Si incluye
                "'" + " - 6 días y 5 noches en el Hotel TossaMar 4* de Tossa de Mar (Costa Brava).\n - Régimen de TODO INCLUIDO por persona.\n - Entrada al Casino de Lloret de Mar con una consumición (1 por persona y estancia) con ticket de parking.\n" + "'" + "," +
                // No incluye
                "'" + " - Transporte hasta el alojamiento y extras no especificados anteriormente.\n - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n - El Todo Incluido NO incluye: parking, nevera, caja de seguridad, Wi-Fi en las habitaciones.\n" + "'" + "," +
                // Notas importantes
                "'" + " - Este chollo también está disponible en más noches de estancia.\n - También es posible reservar este chollo para 1 noche de estancia con un pequeño suplemento que puedes consultar en el proceso de reserva.\n - A tener en cuenta: el día 1 de noviembre el hotel cierra, de modo que el último servicio que se dará es el desayuno, por lo tanto, el primer servicio que se realizará el día de entrada es la comida de mediodía.\n" + "'" + "," +
                // Viaje
                "'" + " - En el casco antiguo de Tossa de Mar se encuentra la Vila Vella Tossa, así como a lo largo del paseo marítimo se encuentra Playa Gran. Además de esta playa, el municipio cuenta con 11 calas con mucho encanto y 6 pequeñas playas más, ideales para disfrutar de la tranquilidad del mar. \n - Y tras el día de playa, en Tossa de Mar encontrarás gran variedad de restaurantes, mientras disfrutas de entornos privilegiados, como por ejemplo al abrigo de las murallas medievales de la Vila Vella Tossa o, al caer la noche, observar una bella panorámica de las murallas iluminadas de Tossa de Mar desde el paseo marítimo. \n - ¡Conoce el enigmático municipio costero de Tossa de Mar y disfruta!\n" + "'" + "," +
                // Descuentos
                "'" + " - Bebé de 0 a 1 año: viajan GRATIS compartiendo cama con los padres.\n - Niños de 2 a 4 años: 1º viaja GRATIS y 2º tiene un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños hasta 2 años tendrán que compartir cama con los adultos o dormir en cuna de viaje. \n - Niños de 5 a 11 años: 1º y 2º viajan con un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños dispondrán de cama.\n - Ocupación máxima: 4 personas (4 adultos, 3 adultos + 1 niño, 2 adultos + 2 niños o bebés).\n - Suplemento de habitación individual: puedes consultarlo en el proceso de reserva. Para calcular el precio total de una habitación individual, deberás sumar este suplemento al precio por persona visible en la web.\n" + "'" + "," +
                // Condiciones
                "'" + "¡Genial! El alojamiento de este chollo SÍ admite mascotas." + "'" + "," +
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
                "6.9" + "," +
                // Estrellas
                "4" + "," +
                // numeroEncuestas
                "1339" + "," +
                // Valoraciones:
                // Habitaciones
                "7.7" + "," +
                // Servicios
                "6.9" + "," +
                // Limpieza
                "7.2" + "," +
                // Comida
                "5.6" + "," +
                // Personal
                "7.2" + "," +
                // Relacion  calidad/precio
                "7.1" + ");";
        db.execSQL(sqlAux1);

        //Inserción datos 1º alojamiento entrada 3
        cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
        sqlAux1 = cSql + "null," +
                //idChollo
                "1" + "," +
                // Titulo principal
                "'" + "7 Noches: Hotel Tossamar 4*, Tossa de Mar (Costa Brava)" + "'" + "," +
                // Precio
                "247" + "," +
                // Informacion resumida
                "'" + "8 días y 7 noches en el Hotel TossaMar 4* situado en Tossa de Mar (Costa Brava) en régimen de TODO INCLUIDO + entrada al Casino con una consumición (1 por persona y estancia) con ticket de parking." + "'" + "," +
                // Si incluye
                "'" + " - 8 días y 7 noches en el Hotel TossaMar 4* de Tossa de Mar (Costa Brava).\n - Régimen de TODO INCLUIDO por persona.\n - Entrada al Casino de Lloret de Mar con una consumición (1 por persona y estancia) con ticket de parking.\n" + "'" + "," +
                // No incluye
                "'" + " - Transporte hasta el alojamiento y extras no especificados anteriormente.\n - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n - El Todo Incluido NO incluye: parking, nevera, caja de seguridad, Wi-Fi en las habitaciones.\n" + "'" + "," +
                // Notas importantes
                "'" + " - Este chollo también está disponible en más noches de estancia.\n - También es posible reservar este chollo para 1 noche de estancia con un pequeño suplemento que puedes consultar en el proceso de reserva.\n - A tener en cuenta: el día 1 de noviembre el hotel cierra, de modo que el último servicio que se dará es el desayuno, por lo tanto, el primer servicio que se realizará el día de entrada es la comida de mediodía.\n" + "'" + "," +
                // Viaje
                "'" + " - En el casco antiguo de Tossa de Mar se encuentra la Vila Vella Tossa, así como a lo largo del paseo marítimo se encuentra Playa Gran. Además de esta playa, el municipio cuenta con 11 calas con mucho encanto y 6 pequeñas playas más, ideales para disfrutar de la tranquilidad del mar. \n - Y tras el día de playa, en Tossa de Mar encontrarás gran variedad de restaurantes, mientras disfrutas de entornos privilegiados, como por ejemplo al abrigo de las murallas medievales de la Vila Vella Tossa o, al caer la noche, observar una bella panorámica de las murallas iluminadas de Tossa de Mar desde el paseo marítimo. \n - ¡Conoce el enigmático municipio costero de Tossa de Mar y disfruta!\n" + "'" + "," +
                // Descuentos
                "'" + " - Bebé de 0 a 1 año: viajan GRATIS compartiendo cama con los padres.\n - Niños de 2 a 4 años: 1º viaja GRATIS y 2º tiene un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños hasta 2 años tendrán que compartir cama con los adultos o dormir en cuna de viaje. \n - Niños de 5 a 11 años: 1º y 2º viajan con un 50% de descuento. Compartiendo habitación con dos adultos o con 2 personas que paguen la tarifa de adulto. Los niños dispondrán de cama.\n - Ocupación máxima: 4 personas (4 adultos, 3 adultos + 1 niño, 2 adultos + 2 niños o bebés).\n - Suplemento de habitación individual: puedes consultarlo en el proceso de reserva. Para calcular el precio total de una habitación individual, deberás sumar este suplemento al precio por persona visible en la web.\n" + "'" + "," +
                // Condiciones
                "'" + "¡Genial! El alojamiento de este chollo SÍ admite mascotas." + "'" + "," +
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
                "6.9" + "," +
                // Estrellas
                "5" + "," +
                // numeroEncuestas
                "1456" + "," +
                // Valoraciones:
                // Habitaciones
                "8.7" + "," +
                // Servicios
                "7" + "," +
                // Limpieza
                "8" + "," +
                // Comida
                "6" + "," +
                // Personal
                "7.6" + "," +
                // Relacion  calidad/precio
                "7.6" + ");";
        db.execSQL(sqlAux1);

        //Inserción datos 2º alojamiento
        cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
        sqlAux1 = cSql + "null," +
                //idChollo
                "2" + "," +
                // Titulo principal
                "'" + "Escápate a Cantabria con Desayuno" + "'" + "," +
                // Precio
                "29" + "," +
                // Informacion resumida
                "'" + "3 días y 2 noches en el Hotel Bezana Lago 3* ubicado en Santa Cruz de Bezana, a 15 min de Santander (Cantabria) + 2 desayunos por persona." + "'" + "," +
                // Si incluye
                "'" + " - 3 días y 2 noches en el Hotel Bezana Lago 3* (Santa Cruz de Bezana).\n - 2 desayunos por persona.\n" + "'" + "," +
                // No incluye
                "'" + " - Transporte hasta el alojamiento y extras no especificados anteriormente.\n" + " - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n" + "'" + "," +
                // Notas importantes
                "'" + " - Fechas disponibles para viajar: del 18 de octubre al 16 de diciembre.\n" + " - Este chollo también está disponible en paquetes de más noches de estancia.\n" + "  - Para Halloween (28-31 de octubre) hay un pequeño suplemento.\n" + "'" + "," +
                // Viaje
                "'" + " - Marcado por su proximidad a Santander, que lo ha convertido en zona residencial, Santa Cruz de Bezana es un municipio costero de llano litoral y suaves pendientes.\n" + " - La casona solariega de La Canal, en Soto de la Marina, es uno de los monumentos más significativos del municipio, junto a las iglesias de Mompía y Prezanes y el seminario de Corbán.\n" + " - Entre sus playas, Los Covachos, de 500 metros, está rodeada por admirables paisajes y San Juan de la Canal, más pequeña con 200 metros de longitud, es de las más concurridas por los turistas.\n" + " - El municipio cuenta además con una marcada vocación hípica. La Sociedad Hípica de Santander posee en Prezanes cuadras e instalaciones cubiertas para la práctica de la equitación.\n" + "'" + "," +
                // Descuentos
                "'" + " - Bebé de 0 a 2 años: podrá alojarse gratis, en régimen de sólo alojamiento compartiendo cama con dos adultos. Recuerda incluir a tu bebé en la reserva, el descuento se aplicará automáticamente.\n" + " - Niños de 3 a 12 años: viajan con el 30% de descuento, siempre compartiendo habitación con dos personas que abonen la tarifa de adulto. \n" + " - Ocupación máxima: 2 adultos + 1 niño por habitación.\n" + " - Suplemento de habitación individual: el precio de habitación individual para este chollo tiene un suplemento del 100%. Es decir, es el mismo precio que para dos personas en habitación doble.\n" + "'" + "," +
                // Condiciones
                "'" + "Te informamos que en el alojamiento de este chollo NO se admiten mascotas." + "'" + "," +
                // Localidad
                "'" + "Santa Cruz de Bezana" + "'" + "," +
                //Descripcion general
                "'" + "Este hotel está situado en el centro de Santa Cruz de Bezana, a solo 10 minutos en coche de las playas de Liencres y San Juan de la Canal y a 10 km de Santander.\n" +
                "\n" +
                "Las habitaciones del Hotel Bezana Lago disponen de conexión Wi-Fi gratuita, TV, minibar y baño privado con artículos de aseo gratuitos.\n" +
                "\n" +
                "El hotel alberga un restaurante que sirve platos cántabros tradicionales, así como cocina internacional. También cuenta con recepción abierta las 24 horas.\n" +
                "\n" +
                "Las encantadoras localidades de Suances y Santillana del Mar están a 20 minutos en coche del hotel." + "'" + "," +
                // Servicios alojamiento
                "'" + "24h recepción;Restaurante;Bar;Calefacción;Aire acondicionado en las zonas comunes;Jacuzzi;Piscina (verano);Sala de juegos;" +
                "Servicio de lavandería;Ascensor;Zona de relax;Terraza;Hamacas;Desayuno tipo Buffet;Comida tipo Buffet;Cena tipo Buffet;" +
                "Parking Exterior de pago;Se admiten animales bajo petición y de pago directo en hotel;Alimentos sin gluten bajo petición;Leche sin lactosa bajo petición;Acceso para discapacitados" + "'" + "," +
                // Servicios Habitacion
                "'" + "Televisión;Teléfono;WC;Ducha o Bañera;Secador de pelo;Caja fuerte (de pago);Nevera (de pago);Wifi (de pago)" + "'" + "," +
                // HorariosHabitacion
                "'" + " - Check in: a partir de la 13:00h\n" +
                " - Check out:  antes de las 12:00h \n" +
                " - Horario Desayuno: de 7:30h a 10:30h\n" +
                " - Horario Comida: de 13:00h a 15:00h\n" +
                " - Horario Cena: de 21:00h a 23:00h\n" + "'" + "," +
                // Nota
                "6.6" + "," +
                // Estrellas
                "3" + "," +
                // numeroEncuestas
                "592" + "," +
                // Valoraciones:
                // Habitaciones
                "6.6" + "," +
                // Servicios
                "6.4" + "," +
                // Limpieza
                "6.9" + "," +
                // Comida
                "5.1" + "," +
                // Personal
                "7.4" + "," +
                // Relacion  calidad/precio
                "7.4" + ");";
        db.execSQL(sqlAux1);

        //Inserción datos 3º alojamiento
        cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
        sqlAux1 = cSql + "null," +
                //idChollo
                "3" + "," +
                // Titulo principal
                "'" + "Disfruta de Suances en Media Pensión" + "'" + "," +
                // Precio
                "45" + "," +
                // Informacion resumida
                "'" + "3 días y 2 noches en el Hotel Vivero II 3* situado en Suances (Cantabria) muy cerca de la playa, en régimen de Media Pensión con agua y vino de la casa (2 desayunos + 2 cenas por persona). Disponible el mismo paquete en Pensión completa ¡pro sólo 51€/pers.!" + "'" + "," +
                // Si incluye
                "'" + " - 3 días y 2 noches en el Hotel Vivero II 3* situado en Suances.\n - Régimen de Media Pensión (2 desayunos + 2 cenas por persona).\n - Agua y vino de la casa incluidos en las cenas.\n" + "'" + "," +
                // No incluye
                "'" + " - Transporte hasta el alojamiento y extras no especificados anteriormente.\n - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n" + "'" + "," +
                // Notas importantes
                "'" + " - Fechas disponibles para viajar: del 22 de octubre al 16 de noviembre y del 3 al 7 de diciembre.\n - Para este chollo, las reservas en régimen de Pensión Completa deberás entrar con la cena y salir con la comida y las reservas en régimen de Media Pensión deberás entrar con cena y salir con desayuno.\n - Las comidas y cenas son un menú cerrado, que consta siempre de un primer plato, un segundo plato y postre, tienen incluido también el agua y vino en las comidas pero no el café. En caso de que alguno de los platos no sea de tu agrado, ¡no te preocupes! Siempre se te ofrecerá una segunda opción ;-)\n" + "'" + "," +
                // Viaje
                "'" + " - Suances es una hermosa villa marinera que ofrece uno de los paisajes más bellos de la costa de Cantabria. \n - Su excepcional entorno natural y sus playas de arena fina seguro que te encantarán. En sus restaurantes podrás deleitar tu paladar con la fantástica gastronomía costera de Cantabria. \n - Esta localización también ofrece actividades náuticas, deportivas y un animado ambiente estival, ¿en serio vas a faltar? ;-P.\n" + "'" + "," +
                // Descuentos
                "'" + " - Este chollo no admite niños ni bebés alojados en la misma habitación que 2 adultos, ya que el hotel sólo dispone de habitaciones dobles.\n - No es posible reservar habitaciones triples ni cuádruples.\n - Suplemento de habitación individual: el precio de habitación individual para este chollo tiene un suplemento del 100%, es decir, es el mismo precio que para dos personas en habitación doble.\n" + "'" + "," +
                // Condiciones
                "'" + "¡Genial! El alojamiento de este chollo SÍ admite mascotas." + "'" + "," +
                // Localidad
                "'" + "Suances" + "'" + "," +
                //Descripcion general
                "'" + "Este hotel disfruta de una situación magnífica en Suances. Está ubicado en la zona de la playa de La Concha, a tan solo 200 metros de una gran cantidad de restaurantes, bares y locales de ocio nocturno. La playa de los Locos está a solo 200 m del hotel y es famosa por sus campeonatos de surf. El puerto está a 500 m del hotel, mientras que los huéspedes podrán disfrutar de una amplia variedad de actividades en las cercanías. El aeropuerto de Santander se halla a solo 29 km. Este hotel lleno de encanto sabrá cómo sorprender a cada tipo de cliente. Las habitaciones han sido elegantemente decoradas y equipadas con modernas comodidades, y los clientes que se hospeden en el establecimiento tendrán la posibilidad de disfrutar de cada uno de los servicios e instalaciones que este hotel ofrece." + "'" + "," +
                // Servicios alojamiento
                "'" + "24h recepción;Restaurante;Bar;Calefacción;Aire acondicionado en las zonas comunes;Jacuzzi;Piscina (verano);Sala de juegos;" +
                "Servicio de lavandería;Ascensor;Zona de relax;Terraza;Hamacas;Desayuno tipo Buffet;Comida tipo Buffet;Cena tipo Buffet;" +
                "Parking Exterior de pago;Se admiten animales bajo petición y de pago directo en hotel;Alimentos sin gluten bajo petición;Leche sin lactosa bajo petición;Acceso para discapacitados" + "'" + "," +
                // Servicios Habitacion
                "'" + "Televisión;Teléfono;WC;Ducha o Bañera;Secador de pelo;Caja fuerte (de pago);Nevera (de pago);Wifi (de pago)" + "'" + "," +
                // HorariosHabitacion
                "'" + " - Check in: a partir de la 15:00h\n" +
                " - Check out:  antes de las 12:00h \n" +
                " - Mascotas: 10€ por noche (máximo 15 kg).\n" +
                " - Cunas: 13€ por noche (bajo aviso y disponibilidad).\n" + "'" + "," +
                // Nota
                "6.8" + "," +
                // Estrellas
                "3" + "," +
                // numeroEncuestas
                "408" + "," +
                // Valoraciones:
                // Habitaciones
                "5.7" + "," +
                // Servicios
                "6.5" + "," +
                // Limpieza
                "6.9" + "," +
                // Comida
                "6.3" + "," +
                // Personal
                "8.2" + "," +
                // Relacion  calidad/precio
                "7.3" + ");";
        db.execSQL(sqlAux1);

        //Inserción datos 4º alojamiento
        cSql = "INSERT INTO " + AlojamientoDAO.C_TABLA + "(" +
                AlojamientoDAO.C_COLUMNA_ID + "," +
                AlojamientoDAO.C_COLUMNA_IDCHOLLO + "," +
                AlojamientoDAO.C_COLUMNA_TITULOPRINCIPAL + "," +
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
        sqlAux1 = cSql + "null," +
                //idChollo
                "4" + "," +
                // Titulo principal
                "'" + "¡Visita Lloret de Mar con Media Pensión!" + "'" + "," +
                // Precio
                "18" + "," +
                // Informacion resumida
                "'" + "2 días y 1 noche en el Hotel Golden Sand 3* (Lloret de Mar) en régimen de Media Pensión (1 cena + 1 desayuno por persona, bebidas no incluidas). ¡Incluye Puente de Halloween! Fechas disponibles para viajar hasta el 31 de octubre." + "'" + "," +
                // Si incluye
                "'" + " - 2 días y 1 noche en el Hotel Golden Sand 3* (Lloret de Mar).\n - Régimen de Media Pensión (1 cena + 1 desayuno por persona, bebidas no incluidas).\n - Niños de 2 a 12 años: el 1º viaja GRATIS y el 2º viaja con un 50% de descuento.\n" + "'" + "," +
                // No incluye
                "'" + " - Transporte hasta el destino y extras no especificados anteriormente.\n - Seguro de cancelación (lo puedes contratar en el proceso de reserva).\n - Bebidas en la cena.\n" + "'" + "," +
                // Notas importantes
                "'" + " - Fechas disponibles para viajar: del 18 al 31 de octubre.\n - Oferta disponible para paquetes de más noches. \n - Puedes reservar del 28 de octubre al 2 de noviembre a partir de 2 noches.\n - Halloween: los días 29 y 30 de Octubre se realiza una cena tematizada y DJ.\n" + "'" + "," +
                // Viaje
                "'" + " - Si quieres disfrutar de algunas de las mejores playas de la Costa Brava, pasar tus vacaciones en Lloret de Mar es una buena elección. Arena fina y arena gruesa, ¡hay para todos los gustos! ¿Cuál prefieres tú?\n - Desde Los Jardines de Santa Clotilde tendrás una panorámica de la costa del Mediterráneo, rodeado de un entorno encantador, natural y esculturas de sirenas. Si vas a escaparte a Lloret de Mar, la iglesia de Sant Romà es una visita que no te puedes perder. Es una iglesia de estilo modernista muy curiosa por sus colores. ¡Te va a encantar!\n - Además, Lloret de Mar también es conocida por su gran variedad de pubs y discotecas, ¡donde todas las noches encontrarás marcha y buen ambiente! :-)\n" + "'" + "," +
                // Descuentos
                "'" + " - Bebés de 0 a 1 años: pueden alojarse GRATIS compartiendo cama con dos adultos o en cuna de viaje. Recuerda incluir a tu bebé en la reserva, el descuento se aplica automáticamente.\n - Niños de 2 a 12 años: el 1º viaja GRATIS y el 2º viaja con un 50% de descuento, acompañados por 2 adultos en la misma habitación.\n - Ocupación máxima: 4 personas en la misma habitación. \n - Suplemento de habitación individual: +18€ por noche y persona. Para calcular el precio total de una habitación individual, deberás sumar este suplemento al precio por persona de este chollo.\n" + "'" + "," +
                // Condiciones
                "'" + "¡Genial! El alojamiento de este chollo SÍ admite mascotas." + "'" + "," +
                // Localidad
                "'" + "Lloret de Mar" + "'" + "," +
                //Descripcion general
                "'" + "El Hotel Golden Sand se encuentra a sólo 400 metros de la playa de Lloret de Mar y ofrece habitaciones renovadas con TV y balcón. \n" +
                "\n" +
                "El hotel también alberga una piscina al aire libre y un restaurante tipo buffet con show cooking.\n" +
                "\n" +
                "Las habitaciones disponen de aire acondicionado y baño privado con artículos de aseo gratuitos y secador de pelo. Hay conexión Wi-Fi gratuita en las zonas comunes.\n" +
                "\n" +
                "El hotel alberga un bar cafetería grande, donde hay una zona de juegos con mesa de billar. Además, hay sala de TV y recepción abierta las 24 horas con caja fuerte.\n" +
                "\n" +
                "El Hotel Golden Sand está situado en el centro de Lloret, en la Costa Brava. Hay una parada de autobús a 50 metros, y el aeropuerto de Girona está a 30 km. " + "'" + "," +
                // Servicios alojamiento
                "'" + "24h recepción;Restaurante;Bar;Calefacción;Aire acondicionado en las zonas comunes;Jacuzzi;Piscina (verano);Sala de juegos;" +
                "Servicio de lavandería;Ascensor;Zona de relax;Terraza;Hamacas;Desayuno tipo Buffet;Comida tipo Buffet;Cena tipo Buffet;" +
                "Parking Exterior de pago;Se admiten animales bajo petición y de pago directo en hotel;Alimentos sin gluten bajo petición;Leche sin lactosa bajo petición;Acceso para discapacitados" + "'" + "," +
                // Servicios Habitacion
                "'" + "Televisión;Teléfono;WC;Ducha o Bañera;Secador de pelo;Caja fuerte (de pago);Nevera (de pago);Wifi (de pago)" + "'" + "," +
                // HorariosHabitacion
                "'" +
                " - Check in: a partir de las 14:00h\n" +
                " - Check out: antes de las 10:00h\n" +
                " - Horario Desayuno: de 8h a 9:30h\n" +
                " - Horario Comida: de 13h a 14:30h\n" +
                " - Horario Cena: de 19:30h a 21:30h\n" +
                " - Cunas: gratis (bajo petición y disponibilidad)\n" +
                " - Mascotas: 5€ por noche (máximo 5 kg)\n" + "'" + "," +
                // Nota
                "5.8" + "," +
                // Estrellas
                "3" + "," +
                // numeroEncuestas
                "118" + "," +
                // Valoraciones:
                // Habitaciones
                "5.5" + "," +
                // Servicios
                "5.4" + "," +
                // Limpieza
                "5.6" + "," +
                // Comida
                "5.5" + "," +
                // Personal
                "6.1" + "," +
                // Relacion  calidad/precio
                "6.8" + ");";
        db.execSQL(sqlAux1);

    }
}