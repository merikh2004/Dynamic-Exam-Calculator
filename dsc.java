import java.util.Scanner;

public class dsc{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of sections
        System.out.print("Enter the number of sections: ");
        int input = scanner.nextInt();

        // Arrays
        int[] numStudentsInSection = new int[input];
        double[][] scores = new double[input][];
        double[] sectionAverages = new double[input];
        double average;
        double[] maxScores = new double[input];
        double[] minScores = new double[input];

        // Number of students in each section and their scores
        for (int i = 0; i < input; i++) {
            System.out.print("Enter the number of students in section " + (i + 1) + ": ");
            numStudentsInSection[i] = scanner.nextInt();
            scores[i] = new double[numStudentsInSection[i]];

            // Exam scores
            for (int j = 0; j < numStudentsInSection[i]; j++) {
                System.out.print("Enter exam score for student " + (j + 1) + " in section " + (i + 1) + ": ");
                scores[i][j] = scanner.nextDouble();

                // Update max and min scores
                if (j == 0) {
                    maxScores[i] = scores[i][j];
                    minScores[i] = scores[i][j];
                } else {
                    if (scores[i][j] > maxScores[i]) {
                        maxScores[i] = scores[i][j];
                    }
                    if (scores[i][j] < minScores[i]) {
                        minScores[i] = scores[i][j];
                    }
                }
            }
        }

        // Section averages and total average
        int totalStudents = 0;
        double totalSum = 0;
        for (int i = 0; i < input; i++) {
            double sum = 0;
            for (int j = 0; j < numStudentsInSection[i]; j++) {
                sum += scores[i][j];
            }
            sectionAverages[i] = sum / numStudentsInSection[i];
            totalSum += sum;
            totalStudents += numStudentsInSection[i];
        }
        average = totalSum / totalStudents;

        // Overall max and min scores
        double overallMax = maxScores[0];
        double overallMin = minScores[0];
        for (int i = 1; i < input; i++) {
            if (maxScores[i] > overallMax) {
                overallMax = maxScores[i];
            }
            if (minScores[i] < overallMin) {
                overallMin = minScores[i];
            }
        }

        // Mode and median
        double[] allScores = new double[totalStudents];
        int index = 0;
        for (int i = 0; i < input; i++) {
            for (int j = 0; j < numStudentsInSection[i]; j++) {
                allScores[index++] = scores[i][j];
            }
        }

        double mode = findMode(allScores);
        double median = findMedian(allScores);

        // Display all records in the tables filtered by section
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < input; i++) {
            System.out.println("Scores for section " + (i + 1) + ":");
            for (int j = 0; j < numStudentsInSection[i]; j++) {
                System.out.print(scores[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("SECTION-WISE ANALYSIS");
        System.out.println();
        for (int i = 0; i < input; i++) {
            System.out.println("Section " + (i + 1) + ":");
            System.out.println("Average score: " + sectionAverages[i]);
            System.out.println("Highest score: " + maxScores[i]);
            System.out.println("Lowest score: " + minScores[i]);
        }
        System.out.println("-----------------------------------------------------------");

        System.out.println("OVERALL ANALYSIS");
        System.out.println();
        System.out.println("Average score for all sections: " + average);
        System.out.println("Highest score among all sections: " + overallMax);
        System.out.println("Lowest score among all sections: " + overallMin);
        System.out.println("Mode: " + mode);
        System.out.println("Median: " + median);
        System.out.println("-----------------------------------------------------------");

        // Deletion option
        System.out.println("Do you want to delete a specific section, all sections, or keep all sections?");
        System.out.println("Press [1] to delete a specific section, [2] to delete all sections, or [3] to keep all sections.");
        int deleteChoice = scanner.nextInt();

        // Delete specific section or all sections based on user's choice
        if (deleteChoice == 1) {
            System.out.print("Enter the index of the section you want to delete: ");
            int sectionIndex = scanner.nextInt();
            // Shift elements to remove the section
            for (int i = sectionIndex; i < input - 1; i++) {
                numStudentsInSection[i] = numStudentsInSection[i + 1];
                scores[i] = scores[i + 1];
                sectionAverages[i] = sectionAverages[i + 1];
                maxScores[i] = maxScores[i + 1];
                minScores[i] = minScores[i + 1];
            }
            input--; // Decrement the total number of sections
        } else if (deleteChoice == 2) {
            input = 0; 
        }
        // Display updated results after deletion or no deletion
        if (input > 0) {
            System.out.println("-----------------------------------------------------------");
            for (int i = 0; i < input; i++) {
                System.out.println("Scores for section " + (i + 1) + ":");
                for (int j = 0; j < numStudentsInSection[i]; j++) {
                    System.out.print(scores[i][j] + ", ");
                }
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("Section-wise Analysis:");
            for (int i = 0; i < input; i++) {
                System.out.println("Section " + (i + 1) + ":");
                System.out.println("Average score: " + sectionAverages[i]);
                System.out.println("Highest score: " + maxScores[i]);
                System.out.println("Lowest score: " + minScores[i]);
            }
            System.out.println("-----------------------------------------------------------");

            // Recalculate overall statistics after deletion
            totalStudents = 0;
            totalSum = 0;
            for (int i = 0; i < input; i++) {
                totalStudents += numStudentsInSection[i];
                totalSum += sectionAverages[i] * numStudentsInSection[i];
            }
            average = totalSum / totalStudents;

            // Find overall max and min scores after deletion
            overallMax = maxScores[0];
            overallMin = minScores[0];
            for (int i = 1; i < input; i++) {
                if (maxScores[i] > overallMax) {
                    overallMax = maxScores[i];
                }
                if (minScores[i] < overallMin) {
                    overallMin = minScores[i];
                }
            }

            // Find mode and median after deletion
            allScores = new double[totalStudents];
            index = 0;
            for (int i = 0; i < input; i++) {
                for (int j = 0; j < numStudentsInSection[i]; j++) {
                    allScores[index++] = scores[i][j];
                }
            }

            mode = findMode(allScores);
            median = findMedian(allScores);

            // Display updated overall statistics after deletion
            System.out.println("OVERALL ANALYSIS AFTER DELETION");
            System.out.println("Average score for all sections: " + average);
            System.out.println("Highest score among all sections: " + overallMax);
            System.out.println("Lowest score among all sections: " + overallMin);
            System.out.println("Mode: " + mode);
            System.out.println("Median: " + median);
        } else {
            System.out.println("No sections remaining.");
        }

        // Close the scanner
        scanner.close();
    }

    // Method to find the mode of an array of doubles
    static double findMode(double[] arr) {
        int maxCount = 0;
        double mode = Double.NaN;
    /*Double,NaN para walang value ang declaration ng variable, dahil sa mode.
    (for example, if all elements in the array are the same, resulting in no mode)
    kaya stay lang ang value ng variable na NaN*/
        for (double num : arr) {
            int count = 0;
            for (double num2 : arr) {
                if (num2 == num) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mode = num;
            }
        }
        return mode;
    }

    // Method to find the median of an array of doubles
    static double findMedian(double[] arr) {
        int n = arr.length;
        if (n % 2 != 0) {
            return arr[n / 2];
        } else {
            return (arr[(n - 1) / 2] + arr[n / 2]) / 2.0;
        }
    }
}
