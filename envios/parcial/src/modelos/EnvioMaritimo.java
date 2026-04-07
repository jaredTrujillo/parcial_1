package modelos;

public class EnvioMaritimo extends Envio {

    private final double TARIFA_KM = 800;
    private final double RECARGO_KG = 1000;

    public EnvioMaritimo(String cliente, String codigo, double pesoKg, double distanciaKm) {
        super(cliente, codigo, pesoKg, distanciaKm);
    }

    @Override
    public double calcularTarifa() {
        return (distanciaKm * TARIFA_KM) + (pesoKg * RECARGO_KG);
    }
}
