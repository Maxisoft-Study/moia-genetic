package simpleGa.bench;

public class Parameters {


    public final static int DEFAULT_POPULATION_SIZE = 1024;
    public static int DEFAULT_MAX_ITER = 10;
    public static double DEFAULT_UNIFORM_RATE = 0.1d;
    public static double DEFAULT_MUTATION_RATE = 0.015d;
    public static int DEFAULT_TOURNAMENT_SIZE = 5;

    private int populationSize = DEFAULT_POPULATION_SIZE;
    private int maxIter = DEFAULT_MAX_ITER;
    private double uniformRate = DEFAULT_UNIFORM_RATE;
    private double mutationRate = DEFAULT_MUTATION_RATE;
    private int tournamentSize = DEFAULT_TOURNAMENT_SIZE;
    private boolean elitism = true;

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }

    public double getUniformRate() {
        return uniformRate;
    }

    public void setUniformRate(double uniformRate) {
        this.uniformRate = uniformRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public boolean isElitism() {
        return elitism;
    }

    public void setElitism(boolean elitism) {
        this.elitism = elitism;
    }

}
