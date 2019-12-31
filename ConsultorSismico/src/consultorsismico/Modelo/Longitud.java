/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author rebecca
 */
public class Longitud {
    private int grados;
    private int minutos;
    private int segundos;
    private String direccion;

    public Longitud(int grados, int minutos, int segundos, String direccion) {
        this.grados = grados;
        this.minutos = minutos;
        this.segundos = segundos;
        this.direccion = direccion;
    }
    
    public Longitud(){
        this(0,0,0,"");
    }

    public int getGrados() {
        return grados;
    }

    @XmlElement(name = "longitude-degrees")
    public void setGrados(int grados) {
        this.grados = grados;
    }

    public int getMinutos() {
        return minutos;
    }

    @XmlElement(name = "minutes")
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    @XmlElement(name = "seconds")
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public String getDireccion() {
        return direccion;
    }

    @XmlElement(name="longitude-direction")
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString(){
      return String.format("%d° %d' %d'' %s", getGrados(), getMinutos(), getSegundos(), getDireccion());  
    }
    
    public double longitudToDecimal() {
        if ("E".equals(getDireccion())) {
            return getGrados() + (getMinutos() / 60) + (getSegundos() / 3600);
        }
        return getGrados() + (getMinutos() / 60) + (getSegundos() / 3600) * -1;
    }
}
