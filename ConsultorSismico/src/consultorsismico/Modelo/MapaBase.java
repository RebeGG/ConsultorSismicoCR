package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "base-map")
public class MapaBase {

    private Imagen imagen;
    @XmlElement(name = "coordinates")
    private final ListaCoordenadas coordenadas;
    
    public MapaBase(Imagen imagen){
        this.imagen = imagen;
        this.coordenadas = new ListaCoordenadas();
    }

    public MapaBase() {
        this(null);
    }

    public Imagen getImagen() {
        return imagen;
    }

    @XmlElement(name = "image")
    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
    
    public ListaCoordenadas getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(String.format("---------- Base del Mapa ----------%n"));
        s.append(String.format("----- Image -----%n"));
        s.append(String.format("Url: %s%n", imagen.getUrl()));
        s.append(String.format("Height: %s%n", imagen.getDimension().getAlto()));
        s.append(String.format("Width: %s%n", imagen.getDimension().getAncho()));
        s.append(String.format("----- Coordinates  -----%n"));
        s.append(coordenadas.toString());
        return s.toString();
    }

}
