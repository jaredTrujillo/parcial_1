package modelos;

public class EnvioAereo extends Envio {

    private final double TARIFA_KM = 5000;
    private final double RECARGO_KG = 4000;

    public EnvioAereo(String cliente, String codigo, double pesoKg, double distanciaKm) {
        super(cliente, codigo, pesoKg, distanciaKm);
    }

    @Override
    public double calcularTarifa() {
        return (distanciaKm * TARIFA_KM) + (pesoKg * RECARGO_KG);
    }
}
