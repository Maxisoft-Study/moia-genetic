package simpleGa.bench;

import simpleGa.Algorithm;
import simpleGa.Individual;
import simpleGa.Population;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bench {
    public static void main(String... args) throws IOException {
        benchPopulation();
        benchIter();
        benchIterAndPopulation();
        benchTournament();
        benchMutation();
    }

    /**
     * launch a bench.
     *
     * @param params
     * @return
     */
    public static Result doBench(Parameters params) {
        /* Init algorithm and population*/
        Algorithm algo = new Algorithm(params.getUniformRate(), params.getMutationRate(), params.getTournamentSize(), params.isElitism());
        Population myPop = new Population(params.getPopulationSize(), true);

        /* Let's evolve our population. */
        int iter = 0;
        long timeBefore = System.nanoTime();
        while (iter < params.getMaxIter()) {
            iter += 1;
            myPop = algo.evolvePopulation(myPop);
        }
        /* Population evolved. */
        long timeAfter = System.nanoTime();
        return new Result(params, timeAfter - timeBefore, myPop);
    }

    /**
     * This bench is for population effect
     */
    public static void benchPopulation() throws IOException {
        List<Integer> populationsSizes = Arrays.asList(2, 64, 256, 1024);
        File f = new File("benchPopulation.csv");
        for (int popSize : populationsSizes) {
            final Parameters param = new Parameters();
            param.setPopulationSize(popSize);
            final Result result = doBench(param);
            toCSV(f, result);
        }
    }

    /**
     * This bench is for iterations effect
     */
    public static void benchIter() throws IOException {
        List<Integer> maxIters = Arrays.asList(10, 50, 100, 500, 10000);
        File f = new File("benchIter.csv");
        for (int maxiter : maxIters) {
            final Parameters param = new Parameters();
            param.setMaxIter(maxiter);
            final Result result = doBench(param);
            toCSV(f, result);
        }
    }

    /**
     * This bench is for iterations and population effect
     */
    public static void benchIterAndPopulation() throws IOException {
        List<Integer> populationsSizes = Arrays.asList(64, 256, 1024, 4096);
        List<Integer> maxIters = Arrays.asList(10, 50, 100, 500, 10000);
        File f = new File("benchIterAndPopulation.csv");
        for (int popSize : populationsSizes) {
            for (int maxiter : maxIters) {
                final Parameters param = new Parameters();
                param.setMaxIter(maxiter);
                param.setPopulationSize(popSize);
                final Result result = doBench(param);
                toCSV(f, result);
            }
        }

    }

    /**
     * This bench is for tournament size effect
     */
    public static void benchTournament() throws IOException {
        List<Integer> croisements = Arrays.asList(2, 5, 15, 50, 1024);
        File f = new File("benchTournament.csv");
        for (int croisement : croisements) {
            final Parameters param = new Parameters();
            param.setTournamentSize(croisement);
            final Result result = doBench(param);
            toCSV(f, result);
        }
    }


    /**
     * This bench is for mutation effect
     */
    public static void benchMutation() throws IOException {
        List<Double> mutations = Arrays.asList(0.001d, 0.01d, 0.15d, 0.5d);
        File f = new File("benchMutation.csv");
        for (double mutation : mutations) {
            final Parameters param = new Parameters();
            param.setMutationRate(mutation);
            final Result result = doBench(param);
            toCSV(f, result);
        }
    }

    /**
     * Simply write a result to a file.
     *
     * @param file
     * @param result
     * @throws IOException
     */
    static void toCSV(File file, Result result) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(String.valueOf(result.getMutationRate()));
        fileWriter.write(",");
        fileWriter.write(String.valueOf(result.getTournamentSize())); //taux de croisement
        fileWriter.write(",");
        fileWriter.write(String.valueOf(result.getPopulation().size())); //taille de la population
        fileWriter.write(",");
        fileWriter.write(String.valueOf(result.getIteration())); // nombre d’itérations
        fileWriter.write(",");
        fileWriter.write(String.valueOf(result.getBestIndividualFitness())); //fitness du meilleur individu
        fileWriter.write(",");
        fileWriter.write(String.valueOf(result.getAvgFitness())); //fitness moyen de la population
        Individual fittest = result.getPopulation().getFittest();
        fileWriter.write(",");
        fileWriter.write(String.valueOf(fittest.getA())); //valeur de A
        fileWriter.write(",");
        fileWriter.write(String.valueOf(fittest.getB())); //valeur de B
        fileWriter.write(",");
        fileWriter.write(String.valueOf(fittest.getC())); //valeur de C
        fileWriter.write(",");
        fileWriter.write(String.valueOf(TimeUnit.MILLISECONDS.convert(result.getTimeNano(), TimeUnit.NANOSECONDS))); //timespan
        fileWriter.write("\n");
        fileWriter.close();
    }
}
