package br.com.util;

public class MatematicaUtil {
    public static Double retornarNumeroAleatorioEntreIntervalo(double min, double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

}
