package simpleGa.bench;

import simpleGa.Population;

public class Result {
    final Parameters parameters;
    final long timeNano;
    final Population population;

    public Result(Parameters parameters, long timeNano, Population population) {
        this.parameters = parameters;
        this.timeNano = timeNano;
        this.population = population;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public long getTimeNano() {
        return timeNano;
    }

    public Population getPopulation() {
        return population;
    }

    // taux de mutation
    public double getMutationRate() {
        return parameters.getMutationRate();
    }

    // taux de croisement
    public double getTournamentSize() {
        return parameters.getTournamentSize();
    }

    //nombre d’itérations
    public double getIteration() {
        return parameters.getMaxIter();
    }

    //fitness du meilleur individu
    public double getBestIndividualFitness() {
        return population.getFittest().getFitness();
    }

    //fitness moyen de la population
    public double getAvgFitness() {
        return population.avgFitness();
    }


}
