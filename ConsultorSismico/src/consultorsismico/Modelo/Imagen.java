package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

public class Imagen {
    
    private String url;
    private Tamano dimension;
   
    public Imagen(String uml, Tamano dimension) {
        this.url = uml;
        this.dimension = dimension;
    }
    
    public Imagen(){
        this(null, null);
    }

    public String getUrl() {
        return url;
    }
    
    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public Tamano getDimension(){
        return dimension;
    }
    
    @XmlElement
    public void setDimension(Tamano dimension){
       this.dimension = dimension;
    }
    
    @Override
    public String toString(){
        return String.format("{%s, %s}", getUrl(), getDimension().toString());
    }
}
