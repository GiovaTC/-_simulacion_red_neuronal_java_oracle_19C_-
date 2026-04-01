public class RedNeuronal {

    // simulacion de neurona ( suma ponderada ) .
    public static double calcularSuma(double[] entradas) {
        double suma = 0;
        for (double valor : entradas) {
            suma += valor; // peso=1 .
        }
        return suma;
    }

    public static double calcularPromedio(double suma) {
        return suma / 5.0;
    }
}
