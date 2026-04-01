import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection conn = ConexionOracle.getConnection()) {

            for (int i = 1; i <= 4; i++) {

                System.out.println("\nAlumno #" + i);
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                double[] notas = new double[10];

                // CAPTURA de 10 NOTAS .

                for (int j = 0; j < 10; j++) {
                    System.out.print("Nota: " + (j + 1) + ": ");
                    notas[j] = sc.nextDouble();
                }
                sc.nextLine(); // limpiar buffer

                // "RED neuronal "
                double suma = RedNeuronal.calcularSuma(notas);
                double promedio = RedNeuronal.calcularPromedio(suma, 10);

                System.out.println("SUMA: " + suma);
                System.out.println("PROMEDIO: " + promedio);

                // LLAMADA a procedimiento ALMACENADO / .
                CallableStatement cs = conn.prepareCall("{call INSERTAR_NOTAS(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, nombre);

                for (int k = 0; k < 10; k++) {
                    cs.setDouble(k + 2, notas[k]);
                }
                cs.setDouble(12, suma);
                cs.setDouble(13, promedio);

                cs.execute();

                System.out.println("✔ Registro guardado en Oracle");

                cs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}