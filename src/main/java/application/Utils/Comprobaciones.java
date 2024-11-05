package application.Utils;

public class Comprobaciones {
    static public Boolean compruebaInt(String entero, String campo) {
        if (compruebaVacio(entero, campo))
            if (!entero.matches("\\d+")) {
                AlertUtils.mostrarError("El campo de " + campo + " no son solo números");
                return false;
            }
        return true;
    }

    static public boolean compruebaDouble(String decimal, String campo) {
        if (compruebaVacio(decimal, campo))
            if (!decimal.matches("\\d+") && !decimal.matches("\\d+\\.\\d+")) { // \\d+ es para numeros y el \\. es que es necesario el .
                AlertUtils.mostrarError("El campo " + campo + " no es decimal");
                return false;
            }
        return true;
    }

    static public boolean compruebaVacio(String str, String campo) {
        if (str.isEmpty()) {
            AlertUtils.mostrarError("No has rellenado nada en el campo " + campo);
            return false;
        }
        return true;
    }
}
