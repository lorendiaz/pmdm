package mibancooperacional.bd;

import android.content.Context;

import java.util.ArrayList;

import mibancooperacional.pojo.Cliente;
import mibancooperacional.pojo.Cuenta;
import mibancooperacional.pojo.Movimiento;

/**
 * Created by loren on 08/10/15.
 */
public class MiBancoOperacional {


    private MiBD miBD;

    protected MiBancoOperacional(Context context){
        miBD = MiBD.getInstance(context);
    }

    private static MiBancoOperacional instance = null;

    //***************************************
    // Interfaz publica de la API del banco
    //***************************************

    // Constructor del banco. Obtiene una instancia del mismo para operar
    public static MiBancoOperacional getInstance(Context context) {
        if(instance == null) {
            instance = new MiBancoOperacional(context);
        }
        return instance;
    }

    // Operacion Login: Verifica que el cliente existe y que su contraseña es correcta. Recibira un cliente
    // que solo contendrá el nif y la password.
    public Cliente login(Cliente c){
        Cliente aux = (Cliente) miBD.getClienteDAO().search(c);
        if (aux.getClaveSeguridad().equals(c.getClaveSeguridad())){
            return aux;
        }else{
            return null;
        }
    }

    // Operacion changePassword: Cambia la password del cliente. Recibirá el cliente de la aplicación con la password cambiada.
    public int changePassword(Cliente c){
        if (miBD.getClienteDAO().update(c)==-1){
            return -1;
        }else{
            return 0;
        }

    }

    // Operacion getCuentas: Obtiene un ArrayList de las cuentas de un cliente que recibe como parámetro
    public ArrayList<Cuenta> getCuentas(Cliente c){
        return miBD.getCuentaDAO().getCuentas(c);
    }

    // Operacion getMovimientos: Obtiene un ArrayList de los movimientos de una cuenta que recibe como parámetro
    public ArrayList<Movimiento> getMovimientos(Cuenta c){
        return miBD.getMovimientoDAO().getMovimientos(c);
    }
}
