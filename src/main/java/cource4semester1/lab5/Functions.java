package cource4semester1.lab5;

public class Functions {

    public int sedlTochka(int matrix[][]) {
        int[] a_min = {20, 20, 20, 20, 20};
        int[] b_max = {0, 0, 0, 0, 0};
        int a_max = 0, b_min = 20;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] <= a_min[i]) {
                    a_min[i] = matrix[i][j];
                }
                if (matrix[i][j] >= b_max[j]) {
                    b_max[j] = matrix[i][j];
                }
            }
        }

        System.out.println("\nMin rows");
        for (int i = 0; i < 5; i++) {
            System.out.print(a_min[i] + "\t");
            if (a_max <= a_min[i]) {
                a_max = a_min[i];
            }
        }
        System.out.println("\n");

        System.out.println("Max columns");
        for (int i = 0; i < 5; i++) {
            System.out.print(b_max[i] + "\t");
            if (b_min >= b_max[i]) {
                b_min = b_max[i];
            }
        }
        System.out.println("\n");

        if (a_max != b_min) {
            System.out.println("Game price is bounded: " + a_max + " <= y <= " + b_min + "\n");
        }

        return 0;
    }


    public int domRIDomS(int matrix[][]) {
        int[][] count =
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };
        int[] sum_r = {0, 0, 0, 0, 0};
        int max = 0;

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                for (int k = 0; k < 5; k++) {
                    if (matrix[i][j] >= matrix[k][j] && i != k) {
                        count[i][j] = count[i][j] + 1;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sum_r[i] += count[i][j];
            }
        }

        for (int i = 0; i < 5; i++) {
            if (max < sum_r[i]) {
                max = sum_r[i];
                if (i == 3) {
                    System.out.println("Рядок " + (i + 1) + " - домiнуючий, а рядок " + i + " - домiнантний\n");
                }
            }
        }

        return 0;
    }

    public double[][] newMatrix(int matrix[][], double matrix2[][]) {

        System.out.println("New matrix");
        int x = 0;
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i >= 1 && i < 2) {
                    if (j >= 3 && j < 4) {
                        matrix2[0][0] = matrix[i][j];
                    } else if (j > 3 && j <= 4) {
                        matrix2[0][1] = matrix[i][j];
                    }
                }
                if (i > 1 && i <= 2) {
                    if (j >= 3 && j < 4) {
                        matrix2[1][0] = matrix[i][j];
                    } else if (j > 3 && j <= 4) {
                        matrix2[1][1] = matrix[i][j];
                    }
                }
            }
        }
        return matrix2;
    }

    public double[][] simplex_matrix(double matrix2[][], double table[][]) {

        for (int i = 0; i < 5; i++) {
            table[i][0] = 1;
        }

        //System.out.println("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                /*if(j==0){
                    table[i][j]=1;
                    table[i][j+1]=matrix2[i][j];
                }
                else {*/
                if (j > 0) {
                    if (i >= 4) {
                        table[i][j] = -1;
                    } else {
                        table[i][j] = matrix2[i][j - 1];
                    }
                }
                //System.out.printf("%10s", table[i][j]);
            }
            //System.out.println();
        }

        /*for (int i=0;i<5;i++){
            for(int j=0;j<6;j++) {
                System.out.printf("%10s", table[i][j]);
            }
            System.out.println();
            }*/

        return table;
    }

    public double[] printResultP(double result_x[], double g, double p[]) {
        //double g;
        //double p[] = new double[4];
        //double q[] = new double[5];

        System.out.println();
        //g = 1/F;
        for (int i = 0; i < result_x.length; i++) {
            p[i] = g * result_x[i];
        }
        return p;
    }

    public double[] printResultQ(double result_y[], double g, double q[]) {
        //double g;
        //double p[] = new double[4];
        //double q[] = new double[5];

        System.out.println();
        //g = 1/F;
        for (int i = 0; i < result_y.length; i++) {
            q[i] = g * result_y[i];
        }
        return q;
    }

    public double gry(double g, double result_y[], double result_x[], double p[], double q[], double matrix2[][]) {
        double P[] = new double[2]; // q
        //double Q[] = new double[4]; // p
        double M;

//       }

        System.out.println();

        for (int j = 0; j <2; j++) {
            M = 0;
            for (int i = 0; i < 2; i++) {
                //System.out.println(M + " = " + matrix2[i][j] + " * " + p[i]);
                M = M + matrix2[i][j] * p[i];

                if (M >= g && i == 3) {
                    System.out.println("M(P,Q[" + (j + 1) + "]) >= g\t\t" + M + " >= " + g);
                }
            }
        }

        System.out.print("\nSolution: \n P (");
        for (int i = 0; i < q.length; i++) {
            if (i < 1) {
                P[i] = p[i];
                System.out.print(P[i] + ", ");
            } else {
                P[i] = p[i];
                System.out.print(P[i]);
            }
        }
        System.out.print(")\n Q (");
        for (int i = 0; i < q.length; i++) {
            if (i < 1) {
                System.out.print(q[i] + ", ");
            } else {
                System.out.print(q[i] + ")");
            }
        }
        System.out.println("\n");


        return 0;
    }

    public void printFinalMatrix(double[][] matrix) {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
