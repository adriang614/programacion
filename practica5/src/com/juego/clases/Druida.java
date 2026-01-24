package com.juego.clases;

import com.juego.habilidades.CuraCC;
import com.juego.habilidades.DanioCC;
import com.juego.habilidades.DanioLD;
import com.juego.habilidades.Habilidad;
import com.juego.modelo.Personaje;

import java.util.ArrayList;

public class Druida extends Clase implements IClase {

    //----------CONSTRUCTOR----------
    public Druida() {
        super(2, 0, 1, 100, 100, 0, 0);

    }

    //----------OBTENER HABILIDADES----------
    @Override
    public ArrayList<Habilidad> getHabilidades() {
        ArrayList result = new ArrayList<Habilidad>();
        //----------HABILIDADES DRUIDA----------
        DanioCC ZarpazoVerde = new DanioCC("ZarpazoVerde", 5, "La druida ataca con garras llenas de energia natural, desgarrando al enemigo", 22, "fuerza");
        DanioLD PitiHumeante = new DanioLD("PitiHumeante", 1, "Lanza una nube de humo concentrado que confunde y daña al enemigo desde la distancia", 40, "destreza");
        CuraCC InfusionDeHierbas = new CuraCC("InfusionDeHierbas", 3, "Prepara una mezcla de hierbas que restaura la vida y la calma", 28, "defensa");

        result.add(ZarpazoVerde);
        result.add(PitiHumeante);
        result.add(InfusionDeHierbas);

        return result;
    }


    //----------APLICAR BONIFICADOR DE CLASE----------
    @Override
    public void aplicarbonificador(Personaje p) {
        p.setInteligencia(getInteligencia() + this.getInteligencia());
        p.setFuerza(getFuerza() + this.getFuerza());
    }

}
