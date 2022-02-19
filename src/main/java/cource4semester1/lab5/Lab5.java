package cource4semester1.lab5;

import utils.Utils;

public class Lab5 {

    public static void main(String[] args) {
        int[][] matrix = Utils.readLab5Data();
        double matrix2[][] = new double[2][2];
        double table[][] = new double[2][3];

        double resultY[] = new double[2];
        double resultX[] = new double[2];
        double table_result[][];
        double g = 0, F = 0;
        double p[] = new double[2];
        double q[] = new double[2];

        Functions f = new Functions();
        f.sedlTochka(matrix);
        f.domRIDomS(matrix);
        f.newMatrix(matrix, matrix2);
        f.printFinalMatrix(matrix2);


        Simplex S = new Simplex();
        S.simplex(table);
        table_result = S.Calculate(resultY, resultX);

        System.out.println("\nSimplex table result:");
        for (int i = 0; i < table_result.length; i++) {
            for (int j = 0; j < table_result[0].length; j++) {
                String res = String.format("%.2f", table_result[i][j]);
                System.out.print(res + "\t\t");
            }
            System.out.println();
        }
//
        System.out.println("\nOptimal plan:");
        for (int i = 0; i < resultY.length; i++) {
            if (i < resultX.length) {
                System.out.print("y" + (i + 1) + " = ");
                System.out.printf("%.2f", resultY[i]);
                System.out.print("\t\t x" + (i + 1) + " = ");
                System.out.printf("%.2f", resultX[i]);
                F = 8.0 / 81.0;
                System.out.println();
            } else {
                System.out.print("y" + (i + 1) + " = ");
                System.out.printf("%.2f", resultY[i]);
            }
        }
//
        //System.out.println("\nFx="+F);
        g = 1 / F;
        f.printResultP(resultX, g, p);
        f.printResultQ(resultY, g, q);
        f.gry(g, resultY, resultX, p, q, matrix2);
        System.out.println();
        System.out.println("Game price is: " + g);
    }
}
