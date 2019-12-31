package consultorsismico.Modelo;

public class Conversion {

    Coordenada primera;
    Coordenada segunda;
    Tamano tam;

    //Falta el constructor, setters y getters
    public int anchoPixeles() {
        return segunda.getPosI().getX() - primera.getPosI().getX();
    }

    public int anchoGrados() {
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "W") {
            return -segunda.getPosM().getLongitud().getGrados() - (-segunda.getPosM().getLongitud().getGrados());
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return -segunda.getPosM().getLongitud().getGrados() - (segunda.getPosM().getLongitud().getGrados());
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLongitud().getDireccion() == "W") {
            return segunda.getPosM().getLongitud().getGrados() - (-segunda.getPosM().getLongitud().getGrados());
        }
        return segunda.getPosM().getLongitud().getGrados() - (segunda.getPosM().getLongitud().getGrados());
    }

    public int alturaPixeles() {
        return segunda.getPosI().getY() - primera.getPosI().getY();
    }

    public int alturaGrados() {
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "S") {
            return -segunda.getPosM().getLatitud().getGrados() - (-segunda.getPosM().getLatitud().getGrados());
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return -segunda.getPosM().getLatitud().getGrados() - (segunda.getPosM().getLatitud().getGrados());
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return segunda.getPosM().getLatitud().getGrados() - (-segunda.getPosM().getLatitud().getGrados());
        }
        return segunda.getPosM().getLatitud().getGrados() - (segunda.getPosM().getLatitud().getGrados());
    }

    // t = x - a / b - a
    public double latitudT(Coordenada aux) {
        double lat = aux.getPosM().getLatitud().latitudToDecimal();
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "S") {
            return (lat - (primera.getPosM().getLatitud().getGrados()) / segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados()));
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return (lat - (-primera.getPosM().getLatitud().getGrados()) / segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados()));
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "N" && primera.getPosM().getLatitud().getDireccion() == "S") {
            return (lat - (primera.getPosM().getLatitud().getGrados()) / -segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados()));
        }
        return (lat - (-primera.getPosM().getLatitud().getGrados()) / -segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados()));
    }

    // t = x - a / b - a
    public double longitudT(Coordenada aux) {
        double log = aux.getPosM().getLongitud().longitudToDecimal();
        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return (log - (primera.getPosM().getLongitud().getGrados()) / segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados()));
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLatitud().getDireccion() == "W") {
            return (log - (-primera.getPosM().getLongitud().getGrados()) / segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados()));
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return (log - (primera.getPosM().getLongitud().getGrados()) / -segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados()));
        }
        return (log - (-primera.getPosM().getLongitud().getGrados()) / -segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados()));
    }
}
