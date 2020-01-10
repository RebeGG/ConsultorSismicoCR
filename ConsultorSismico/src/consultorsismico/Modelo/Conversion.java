package consultorsismico.Modelo;

public class Conversion {

    Coordenada primera;
    Coordenada segunda;
    

    public Conversion(Coordenada primera, Coordenada segunda) {
        this.primera = primera;
        this.segunda = segunda;
    }
    
    public Conversion() {
        this(new Coordenada(new PosicionImagen(86, 67), new PosicionMapa(new Latitud(11, 0, 0, "N"), new Longitud(86, 0, 0, "W"))),
                new Coordenada(new PosicionImagen(663, 636), new PosicionMapa(new Latitud(8, 0, 0, "N"), new Longitud(83, 0, 0, "W"))));
    }

    //Métodos básicos setters & getters
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

    //Métodos de cálculo
    //La altura en grados
    public int alturaGrados() {
        return segunda.getPosM().getLatitud().getGrados() - (primera.getPosM().getLatitud().getGrados());
    }

    //La altura en pixeles
    public int alturaPixeles() {
        return segunda.getPosI().getY() - primera.getPosI().getY();
    }

    //La altura en grados
    public int anchoGrados() {
        return -segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados());
    }

    //La altura en pixeles
    public int anchoPixeles() {
        return segunda.getPosI().getX() - primera.getPosI().getX();
    }

    // ------BUSCAR LA POSICIÓN GEOGRÁFICA (X, Y)------
    //Buscar la posición y según una latitud
    // t = x - a / b - a
    public double latitudT(Latitud aux) {
        return ((aux.latitudToDecimal() + primera.getPosM().getLatitud().getGrados()) / (-segunda.getPosM().getLatitud().getGrados() + primera.getPosM().getLatitud().getGrados()));
    }

    //Buscar la latitud en pixeles
    //f(t) = a + t(b-a)
    public double latitudPix(Latitud aux) {
        return primera.getPosI().getY() + latitudT(aux) * (segunda.getPosI().getY() - primera.getPosI().getY());
    }

    //Buscar la posición x según una longitud
    // t = x - a / b - a
    public double longitudT(Longitud aux) {
        double log = aux.longitudToDecimal();
        return ((log + (primera.getPosM().getLongitud().getGrados())) / (-segunda.getPosM().getLongitud().getGrados() - (-primera.getPosM().getLongitud().getGrados())));
    }

    //f(t) = a + t(b-a)
    public double longitudPix(Longitud aux) {
        return primera.getPosI().getX() + longitudT(aux) * (segunda.getPosI().getX() - primera.getPosI().getX());
    }

    public PosicionImagen coordenadatoPixeles(Longitud x, Latitud y) {
        return new PosicionImagen((int) longitudPix(x), (int) latitudPix(y));
    }

    // ------BUSCAR LA COORDENADA GEOGRÁFICA (LONGITUD, LATITUD)------
    
    //Encontrar t de pixeles a coordenadas
    public double longitudTPix(int x) {
        return (x - (double) primera.getPosI().getX()) / anchoPixeles();
    }

    //Encontrar los grados f(t) = a + t(b-a)
    public double longitudGrados(int x) {
        return -primera.getPosM().getLongitud().getGrados() + longitudTPix(x) * anchoGrados();
    }

    //m' = integer((dd - d) × 60) 
    public int longitudMinutos(int x) {
        return (int) ((Math.abs(longitudGrados(x)) - (int)Math.abs(longitudGrados(x))) * 60);
    }

    //s" = (dd - d - m/60) × 3600 
    public int longitudSegundos(int x) {
        return (int) ((Math.abs((double)longitudGrados(x)) - (int) Math.abs(longitudGrados(x)) - ((double)longitudMinutos(x) / 60)) * 3600);
    }

    public Longitud longPix(int y) {
        return new Longitud((int) Math.abs(longitudGrados(y)), longitudMinutos(y), longitudSegundos(y), "W");
    }
    
    //Encontrar t de pixeles a coordenadas
    public double latitudTPix(int y) {
        return (y - (double)primera.getPosI().getY()) / (double)alturaPixeles();
    }
    
    //Encontrar los grados f(t) = a + t(b-a)
    public double latitudGrados(int x) {
        return primera.getPosM().getLatitud().getGrados() + (double)latitudTPix(x) * (double)-anchoGrados();
    }

    //m' = integer((dd - d) × 60)
    public int latitudMinutos(int x) {
        return (int) ((Math.abs((double)latitudGrados(x)) - (int) Math.abs(latitudGrados(x))) * 60);
    }

    //s" = (dd - d - m/60) × 3600 
    public int latitudSegundos(int x) {
        return (int) ((Math.abs((double)latitudGrados(x)) - (int) Math.abs(latitudGrados(x)) - ((double)latitudMinutos(x) / 60)) * 3600);
    }

    public Latitud latPix(int x) {
        return new Latitud((int) Math.abs(latitudGrados(x)), latitudMinutos(x), latitudSegundos(x), "N");
    }
    
    //Se crea una nueva latitud y longitud a partir de los x y y
    public PosicionMapa nuevaCoordenada(int x, int y) {
        return new PosicionMapa(latPix(y), longPix(x));
    }
    
    // ------BUSCAR LA COORDENADA GEOGRÁFICA (DOUBLE)------
    
    public int latitudMinutos(double x) {
        return (int) ((Math.abs(x) - (int) Math.abs(x)) * 60);
    }

    //s" = (dd - d - m/60) × 3600 
    public int latitudSegundos(double x) {
        return (int) (((Math.abs(x) - (int) Math.abs(x)) - (latitudMinutos(x) / 60)) * 3600);
    }

    public Latitud latPix(double x) {
        return new Latitud((int) x, latitudMinutos(x), latitudSegundos(x), "N");
    }
    
    public int longitudMinutos(double x) {
        return (int) ((Math.abs(x) - (int) Math.abs(x)) * 60);
    }

    //s" = (dd - d - m/60) × 3600 
    public int longitudSegundos(double x) {
        return (int) (((Math.abs(x) - (int) Math.abs(x)) - (longitudMinutos(x) / 60)) * 3600);
    }

    public Longitud longPix(double x) {
        return new Longitud((int) Math.abs(x), longitudMinutos(x), longitudSegundos(x), "W");
    }
    
    public PosicionMapa nuevaCoordenada(double x, double y) {
        return new PosicionMapa(latPix(y), longPix(x));
    }
    
}
