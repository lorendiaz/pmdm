package com.fpmislata.practicas.aplicacion38;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HipotecasSQLiteHelper extends SQLiteOpenHelper {

    private static int version = 3;
    private static String nombreBD = "HipotecasDB" ;
    private static CursorFactory factory = null;

    // Sentencia de creacion de tabla hipotecas
    private String sqlCreacionTablaHipotecas = "CREATE TABLE hipotecas(" +
            " _id INTEGER PRIMARY KEY," +
            " nombre TEXT NOT NULL, " +
            " condiciones TEXT, " +
            " contacto TEXT," +
            " email TEXT," +
            " telefono TEXT," +
            " observaciones TEXT)";

    private String sqlIndiceNombreHipotecas = "CREATE UNIQUE INDEX nombrehipotecas ON hipotecas(nombre ASC)";

    // Sentencia de insercion de datos iniciales en la tabla hipotecas
    private String[] sqlInsertHipotecas = { "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(1,'Santander', 'Sin condiciones', " +
                                                "'Julián Gómez Martínez', 'jgmartinez@gmail.com', '634827472','Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(2,'BBVA', 'Sin condiciones', " +
                                                    "'Antonio Díaz Gómez', 'adiaz@gmail.com', '628753726','Tiene toda la documentación y se ha aprobado la operación')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(3,'La Caixa', 'Condiciones especiales', " +
                                                    "'Agustin Luque Quintana', 'aluque@gmail.com', '639664736','Tiene toda la documentación y se ha aprobado la operación')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(4,'Cajamar', 'Sin condiciones', " +
                                                    "'Maria Ponce Salcedo', 'mponce@gmail.com', '633896537','Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(5,'Bankia', 'Condiciones especiales', " +
                                                    "'Maria Amparo Aparicio Ruiz', 'maruiz@gmail.com', '628763410','Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(6,'Banco Sabadell', 'Condiciones especiales', " +
                                                    "'Carla Porriño Tomas', 'cporrino@gmail.com', '638557728','Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones')",
                                            "INSERT INTO hipotecas(_id, nombre, condiciones, contacto, email, telefono, observaciones) VALUES(7,'Banco Popular', 'Condiciones especiales', " +
                                                    "'Ana Murcia Begoña', 'amurcia@gmail.com', '637997624','Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones')"};

    public HipotecasSQLiteHelper(Context contexto) {
        super(contexto, nombreBD, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        Log.i(this.getClass().toString(), "Creando tabla hipotecas");

        db.execSQL(sqlCreacionTablaHipotecas);
        db.execSQL(sqlIndiceNombreHipotecas);

        Log.i(this.getClass().toString(), "Tabla hipotecas creada");

        // Ejecutamos la carga de datos iniciales en la tabla
        Log.i(this.getClass().toString(), "Insertando datos iniciales");

        for(int i=0;i<sqlInsertHipotecas.length;i++){
            db.execSQL(sqlInsertHipotecas[i]);
        }

        // Aplicamos las sucesivas actualizaciones
        upgrade_2(db);
        upgrade_3(db);

        Log.i(this.getClass().toString(), "Datos iniciales cargados");

        Log.i(this.getClass().toString(), "Base de datos inicial creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Actualización a versión 2
        if (oldVersion < 2)
        {
            upgrade_2(db);
        }
        // Actualización a versión 3
        if (oldVersion < 3)
        {
            upgrade_3(db);
        }
    }

    private void upgrade_2(SQLiteDatabase db)
    {
        //
        // Upgrade versión 2: definir algunos datos de ejemplo
        //
        db.execSQL("UPDATE hipotecas SET contacto = 'Julián Gómez Martínez'," +
                "             email = 'jgmartinez@gmail.com'," +
                "             observaciones = 'Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones'" +
                " WHERE _id = 1");

        Log.i(this.getClass().toString(), "Actualización versión 2 finalizada");
    }

    private void upgrade_3(SQLiteDatabase db)
    {
        //
        // Upgrade versión 3: Incluir pasivo_sn
        //
        db.execSQL("ALTER TABLE hipotecas ADD pasivo_sn   VARCHAR2(1) NOT NULL DEFAULT 'N'");

        Log.i(this.getClass().toString(), "Actualización versión 3 finalizada");
    }

}
