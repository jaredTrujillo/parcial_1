package modelos;

public abstract class Envio {

    protected String cliente;
    protected String codigo;
    protected double pesoKg;
    protected double distanciaKm;

    public Envio(String cliente, String codigo, double pesoKg, double distanciaKm) {
        this.cliente = cliente;
        this.codigo = codigo;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    public abstract double calcularTarifa();

    public String getCliente() {
        return cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
}
