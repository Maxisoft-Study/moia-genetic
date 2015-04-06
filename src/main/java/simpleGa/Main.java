package simpleGa;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        /* First thing first, load configuration file. */
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("genetic.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "problem with configuration file, this program will use default values", e);
        }

        /* Parse configuration values. */
        int populationSize = Integer.parseInt(properties.getProperty("populationSize", "100"));
        int maxIter = Integer.parseInt(properties.getProperty("maxIter", "500"));
        double uniformRate = Double.parseDouble(properties.getProperty("uniformRate", "0.1"));
        double mutationRate = Double.parseDouble(properties.getProperty("mutationRate", "0.015"));
        int tournamentSize = Integer.parseInt(properties.getProperty("tournamentSize", "5"));
        boolean elitism = Boolean.parseBoolean(properties.getProperty("elitism", "true"));

        int iterStringLen = String.valueOf(maxIter).length();

        /* Init algorithm and population*/
        Algorithm algo = new Algorithm(uniformRate, mutationRate, tournamentSize, elitism);
        Population myPop = new Population(populationSize, true);

        /* Let's evolve our population. */
        int iter = 0;
        long timeBefore = System.nanoTime();
        while (iter < maxIter) {
            iter += 1;
            myPop = algo.evolvePopulation(myPop);
            Individual fittest = myPop.getFittest();
            System.out.printf("[%0" + iterStringLen + "d] Individual fittest genes=%s with fitness=%.5f. Population average fitness=%.2f\n",
                    iter,
                    fittest,
                    fittest.getFitness(),
                    myPop.avgFitness());
        }
        /* Population evolved. */
        long timeAfter = System.nanoTime();



        /* Display results. */
        Individual fittest = myPop.getFittest();
        System.out.println("--------------------------------");
        System.out.printf("Population evolved in %.3f sec \n",
                TimeUnit.MILLISECONDS.convert(timeAfter - timeBefore, TimeUnit.NANOSECONDS) / 1000.d);
        System.out.println("--------------------------------");
        System.out.println("Generation: " + iter);
        System.out.printf("Population average fitness: %.2f\n", myPop.avgFitness());
        System.out.print("Genes:");
        System.out.println(fittest);
        System.out.print("Fitness:");
        System.out.printf("%.5f\n", fittest.getFitness());
        System.out.printf("A=%.2f, B=%.2f, C=%.2f\n", fittest.getA(), fittest.getB(), fittest.getC());
    }
}
