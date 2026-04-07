package modelos;

public class EnvioTerrestre extends Envio {

    private final double TARIFA_KM = 1500;
    private final double RECARGO_KG = 2000;

    public EnvioTerrestre(String cliente, String codigo, double pesoKg, double distanciaKm) {
        super(cliente, codigo, pesoKg, distanciaKm);
    }

    @Override
    public double calcularTarifa() {
        return (distanciaKm * TARIFA_KM) + (pesoKg * RECARGO_KG);
    }
}
