package application.Utils;

public class Comprobaciones {
    static public Boolean compruebaInt(String entero, String campo) {
        if (entero.isEmpty()) {
            AlertUtils.mostrarError("No has rellenado nada en el campo " + campo);
            return false;
        }
        if (!entero.matches("\\d+")) {
            AlertUtils.mostrarError("El campo de " + campo + " no son solo números");
            return false;
        }
        return true;
    }

    static public boolean compruebaDouble(String decimal, String campo) {
        if (decimal.isEmpty()) {
            AlertUtils.mostrarError("No has rellenado nada en el campo " + campo);
            return false;
        }
        if (!decimal.matches("\\d+") && !decimal.matches("\\d+\\.\\d+")) { // \\d+ es para numeros y el \\. es que es necesario el .
            AlertUtils.mostrarError("El campo " + campo + " no es decimal");
            return false;
        }
        return true;
    }
}
