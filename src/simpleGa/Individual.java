package simpleGa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Individual implements Const {

    public final static int defaultGeneLength = 10;
    public final static int genePerChromosome = 2;
    private byte[] chromosome = new byte[defaultGeneLength * genePerChromosome];
    // Cache
    private double fitness = 0;
    private double a, b, c;

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            chromosome[i] = gene;
        }
        updateDiameters();
    }

    private void updateDiameters() {
        double d1 = getRaw(0);
        double d2 = getRaw(defaultGeneLength);
        double d3 = D - d2 - d1;

        List<Double> doubles = Arrays.asList(d1, d2, d3);
        Collections.sort(doubles);
        a = doubles.get(0);
        c = doubles.get(1);
        b = doubles.get(2);
    }

    public byte getGene(int index) {
        return chromosome[index];
    }

    public void setGene(int index, byte value) {
        chromosome[index] = value;
        fitness = 0;
        updateDiameters();
    }

    /* Public methods */
    public int size() {
        return chromosome.length;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    public double getRaw(int padding) {
        int raw = 0;
        for (int i = 0; i < defaultGeneLength; ++i) {
            raw |= this.getGene(i + padding) << i;
        }
        return raw * PRECISION;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
