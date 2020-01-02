package consultorsismico.Modelo;

public class Conversion {

    Coordenada primera;
    Coordenada segunda;
    //Tamano tam;

    public Conversion(Coordenada primera, Coordenada segunda) {
        this.primera = primera;
        this.segunda = segunda;
    }

    public Coordenada getPrimera() {
        return primera;
    }

    public void setPrimera(Coordenada primera) {
        this.primera = primera;
    }

    public Coordenada getSegunda() {
        return segunda;
    }

    public void setSegunda(Coordenada segunda) {
        this.segunda = segunda;
    }

    //Falta el constructor, setters y getters
    public int anchoPixeles() {
        return segunda.getPosI().getX() - primera.getPosI().getX();
    }

    public int anchoGrados() {
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "W") {
            return -segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados());
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return -segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados());
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLongitud().getDireccion() == "W") {
            return segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados());
        }
        return segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados());
    }

    public int alturaPixeles() {
        return segunda.getPosI().getY() - primera.getPosI().getY();
    }

    public int alturaGrados() {
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "S") {
            return -segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados());
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return -segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados());
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados());
        }
        return segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados());
    }

    // t = x - a / b - a
    public double latitudT(Latitud aux) {
        double lat = aux.latitudToDecimal();
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "S") {
            return (lat - (primera.getPosM().getLatitud().getGrados()) / (segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados())));
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "S" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return (lat - (-primera.getPosM().getLatitud().getGrados()) / (segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados())));
        }
        if (segunda.getPosM().getLatitud().getDireccion() == "N" && primera.getPosM().getLatitud().getDireccion() == "N") {
            return ((-lat - (-primera.getPosM().getLatitud().getGrados())) / (-segunda.getPosM().getLatitud().getGrados() - (-primera.getPosM().getLatitud().getGrados())));

        }
        return (-lat - (primera.getPosM().getLatitud().getGrados()) / (-segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados())));
    }

    // t = x - a / b - a
    public double longitudT(Longitud aux) {
        double log = aux.longitudToDecimal();

        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return (log - (primera.getPosM().getLongitud().getGrados()) / (segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados())));
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "E" && primera.getPosM().getLatitud().getDireccion() == "W") {
            return (log - (-primera.getPosM().getLongitud().getGrados()) / (segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados())));
        }
        if (segunda.getPosM().getLongitud().getDireccion() == "W" && primera.getPosM().getLongitud().getDireccion() == "E") {
            return (-log - (primera.getPosM().getLongitud().getGrados()) / (-segunda.getPosM().getLongitud().getGrados() - (primera.getPosM().getLongitud().getGrados())));
        }

//        System.out.println(log);
//         System.out.println(primera.getPosM().getLongitud().getGrados());
//        System.out.println((-log - (-primera.getPosM().getLongitud().getGrados())));
//        System.out.println((-segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados())));
        return ((-log + (primera.getPosM().getLongitud().getGrados())) / (-segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados())));
    }

    public double longitudPix(Longitud aux) {
        return primera.getPosI().getX() + longitudT(aux) * (segunda.getPosI().getX() - primera.getPosI().getX());
    }

    public double latitudPix(Latitud aux) {
        return primera.getPosI().getY() + latitudT(aux) * (segunda.getPosI().getY() - primera.getPosI().getY());
    }

    //Longitud WEST EAST
    public double longitudTPix(int x) {
        return (x - primera.getPosI().getX()) / anchoPixeles();
    }

    public double longitudGrados(int x) {
        if (primera.getPosM().getLongitud().getDireccion() == "E") {
            return primera.getPosM().getLongitud().getGrados() + longitudTPix(x) * anchoGrados();
        }
        return -primera.getPosM().getLongitud().getGrados() + longitudTPix(x) * anchoGrados();
    }

    //m = integer((dd - d) × 60) = 15'
    public int longitudMinutos(int x) {
        return (int) (Math.abs(longitudGrados(x)) - (int) Math.abs(longitudGrados(x))) * 60;
    }

    //s = (dd - d - m/60) × 3600 = 50"
    public int longitudSegundos(int x) {
        return (int) (Math.abs(longitudGrados(x)) - (int) Math.abs(longitudGrados(x)) - (longitudMinutos(x) / 60)) * 3600;
    }
    
    public double latitudTPix(int y){
        return (y - primera.getPosI().getY()) / alturaPixeles();
    }
    
    public double latitudGrados(int x) {
        if (primera.getPosM().getLatitud().getDireccion() == "N") {
            return primera.getPosM().getLatitud().getGrados() + longitudTPix(x) * anchoGrados();
        }
        return -primera.getPosM().getLatitud().getGrados() + longitudTPix(x) * anchoGrados();
    }

    //m = integer((dd - d) × 60) = 15'
    public int latitudMinutos(int x) {
        return (int) (Math.abs(latitudGrados(x)) - (int) Math.abs(latitudGrados(x))) * 60;
    }

    //s = (dd - d - m/60) × 3600 = 50"
    public int latitudSegundos(int x) {
        return (int) (Math.abs(latitudGrados(x)) - (int) Math.abs(latitudGrados(x)) - (latitudMinutos(x) / 60)) * 3600;
    }
}
