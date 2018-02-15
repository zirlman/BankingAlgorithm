package org.unibl.etf;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingAlgorithm {
    private static int[][] maxMatrix;
    private static int[][] allocatonMatrix;
    private static int[][] needMatrix;
    private static int[] availableVector;
    private static boolean[] visit;
    private static ArrayList<Integer> safeSequence = new ArrayList<>();
    private static int safeSeqCounter;
    private static int R;
    private static int P;

    private static void load(int r, int p, String[] mm, String[] am, String[] av) {
        maxMatrix = new int[p][];
        allocatonMatrix = new int[p][];
        needMatrix = new int[p][];
        availableVector = new int[r];
        visit = new boolean[p];
        R = r;
        P = p;
        int k = 0;
        for (int i = 0; i < P; i++) {
            maxMatrix[i] = new int[R];
            allocatonMatrix[i] = new int[R];
            needMatrix[i] = new int[R];
            for (int j = 0; j < R; ++j) {
                maxMatrix[i][j] = Integer.valueOf(mm[k]);
                allocatonMatrix[i][j] = Integer.valueOf(am[k++]);
                needMatrix[i][j] = maxMatrix[i][j] - allocatonMatrix[i][j];
            }
        }
        for (int i = 0; i < av.length; i++)
            availableVector[i] = Integer.valueOf(av[i]);
    }

    private static void startSimulation() {
        for (int i = 0; i < P; ++i)
            if (!visit[i] && isAvailable(i)) {
                visit[i] = true;
                for (int j = 0; j < R; ++j)
                    availableVector[j] += maxMatrix[i][j];
                safeSequence.add(i);
                startSimulation();
                safeSequence.remove(safeSequence.size() - 1);
                visit[i] = false;
                for (int j = 0; j < R; ++j)
                    availableVector[j] -= maxMatrix[i][j];
            }
        if (safeSequence.size() == P) {
            ++safeSeqCounter;
            for (int i = 0; i < P; i++) {
                System.out.print("P" + safeSequence.get(i));
                if (i != (P - 1))
                    System.out.print("-->");
            }
            System.out.println();
        }
    }

    private static boolean isAvailable(int i) {
        for (int j = 0; j < R; ++j)
            if (availableVector[j] < needMatrix[i][j])
                return false;
        return true;
    }

//    private static void printMatrix(int[][] matrix) {
//        for (int i = 0; i < P; i++) {
//            for (int j = 0; j < R; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    // input: 3 5 7,5,3,3,2,2,9,0,2,2,2,2,4,3,3 0,1,0,2,0,0,3,0,2,2,1,1,0,0,2 3,3,2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of resources: ");
        int r = scanner.nextInt();
        System.out.print("Enter number of processes: ");
        int p = scanner.nextInt();
        System.out.print("Enter " + r * p + " resources for max matrix: ");
        String[] maxMatrix = scanner.next().split(",");
        System.out.print("Enter " + r * p + " resources for allocation matrix: ");
        String[] alloactionMatrix = scanner.next().split(",");
        System.out.print("Enter " + r + " resources for available vector: ");
        String[] availableVector = scanner.next().split(",");

        load(r, p, maxMatrix, alloactionMatrix, availableVector);
        startSimulation();
        if (safeSeqCounter == 0)
            System.out.println("Couldn't allocate resources :(");
    }
}
