package app;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class StandardDev {

    public static void main(String[] args) throws IOException {
        // Create a list to store the values read from the file
        List<Double> values = new ArrayList<>();

        // Define the file name
        String file = "data2.txt";
        // Displaying file name and initiating reading
        System.out.println("What is the name of the data file? " + file);
        System.out.println("Reading data from " + file);
        
        Path path = Paths.get(file);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            // Reading each line from the file and adding to the values list
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                values.add(Double.parseDouble(line.trim()));
            }
        } catch (IOException ex) {
            // Handle file reading errors
            ex.printStackTrace();
        }
        
        // Display the original values read from the file
        System.out.println("\nThe list is originally: " + values);

        // Compute the average of the values
        double sum = 0;
        for (double value : values) sum += value;
        double average = sum / values.size();

        // Compute the standard deviation of the values
        double varianceSum = 0;
        for (double value : values) varianceSum += Math.pow(value - average, 2);
        double standardDeviation = Math.sqrt(varianceSum / values.size());

        System.out.println("\nThe average is: " + average);
        System.out.println("The standard Deviation is: " + standardDeviation);

        // Define bounds for value removal based on average and standard deviation
        double lowerBound = average - 2 * standardDeviation;
        double upperBound = average + 2 * standardDeviation;
        System.out.println("\nRemoving values outside the range " + lowerBound + " to " + upperBound);

        // Remove values outside of the specified range
        Iterator<Double> iterator = values.iterator();
        while (iterator.hasNext()) {
            double value = iterator.next();
            if (Math.abs(value - average) > 2 * standardDeviation) {
                System.out.println("Removed value " + value);
                iterator.remove();
            }
        }

        // Display the remaining values
        System.out.println("\nThe list is now: " + values);

        // Recompute average and standard deviation after removal of extreme values
        sum = 0;
        for (double value : values) sum += value;
        average = sum / values.size();

        varianceSum = 0;
        for (double value : values) varianceSum += Math.pow(value - average, 2);
        standardDeviation = Math.sqrt(varianceSum / values.size());

        System.out.println("\nThe average with extreme values removed is: " + average);
        System.out.println("The standard Deviation with extreme values removed is: " + standardDeviation);
    }
}
