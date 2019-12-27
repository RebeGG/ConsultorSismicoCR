package consultorsismico.Modelo;

public class Sismo {

    private Coordenada latitud;
    private Coordenada longitud;
    private double magnitud;
    private double profundidad;
    private int registro;
    private String fecha;

    public Sismo(Coordenada latitud, Coordenada longitud, double magnitud, double profundidad, int registro, String fecha) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.registro = registro;
        this.fecha = fecha;
    }

    public Sismo() {
        this(null, null, 0.0, 0.0, 0, null);
    }

    public Coordenada getLatitud() {
        return latitud;
    }

    public void setLatitud(Coordenada latitud) {
        this.latitud = latitud;
    }

    public Coordenada getLongitud() {
        return longitud;
    }

    public void setLongitud(Coordenada longitud) {
        this.longitud = longitud;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        if (magnitud <= 10.0 && magnitud > 0.0) {
            this.magnitud = magnitud;
        }
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        if (profundidad <= 70.0 && profundidad >= 0.0) {
            this.profundidad = profundidad;
        }
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
