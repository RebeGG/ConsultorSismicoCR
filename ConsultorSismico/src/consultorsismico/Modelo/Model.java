package consultorsismico.Modelo;

import java.awt.List;

public class Model {
    private Sismo sismo;
    private List sismos;

    public Model(Sismo sismo, List sismos) {
        this.sismo = sismo;
        this.sismos = sismos;
    }
    
    public Model(){
        //this(null, null);
    }
}
