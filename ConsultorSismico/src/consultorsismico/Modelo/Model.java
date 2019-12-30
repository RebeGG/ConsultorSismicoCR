package consultorsismico.Modelo;

import java.util.Observable;

public class Model extends Observable {
    private Coordenada coordena;
    
    public void update(){
        setChanged();
        notifyObservers();
    }
    
    //Esta parte debe ir en el Modelo, creo
    //Es para cargar la imagen y las coordenadas principales, pero esto solo debe hacerse una vez en toda la corrida del programa
//    try {
//            JAXBContext ctx = JAXBContext.newInstance(BaseMapa.class);
//            Unmarshaller mrs = ctx.createUnmarshaller();
//            MapaBase base = (MapaBase) mrs.unmarshal(new File("../map.xml"));
//            System.out.println(base); ////esto solo prueba que esté funcionando todo, se quita despues
//        } catch (JAXBException ex) {
//            System.err.printf("Excepción: '%s'%n", ex.getMessage());
//        }
    
}
