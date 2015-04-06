package simpleGa;

public class Individual implements Const {

    public final static int defaultGeneLength = 10;
    public final static int genePerChromosome = 2;
    private byte[] chromosome = new byte[defaultGeneLength * genePerChromosome];
    // Cache
    private double fitness = 0.d;
    private double a, b, c;

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            chromosome[i] = gene;
        }
        updateDiameters();
    }

    /**
     * This method update some cache's properties.
     * <p/>
     * This method is called internally when genes are updated.
     */
    private void updateDiameters() {
        double d1 = decode(getRaw(0));
        double d2 = decode(getRaw(defaultGeneLength));
        double d3 = D - d2 - d1;

        /* enforce b >= c >= a */
        if (d1 >= d2) {
            if (d1 >= d3) {
                b = d1;
                if (d2 >= d3) {
                    c = d2;
                    a = d3;
                } else { // d3 > d2
                    c = d3;
                    a = d2;
                }
            } else { // d3 > d1
                b = d3;
                c = d1;
                a = d2;
            }
        } else { // d2 > d1
            if (d2 >= d3) {
                b = d2;
                if (d1 >= d3) {
                    c = d1;
                    a = d3;
                } else { // d3 > d1
                    c = d3;
                    a = d1;
                }
            } else { //d3 > d2
                b = d3;
                c = d2;
                a = d1;
            }
        }
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
        if (fitness == 0.d) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    /**
     * Extract a raw int value from individual's gene.
     *
     * @param startFrom the index where to start
     * @return a raw int value
     */
    public int getRaw(int startFrom) {
        int raw = 0;
        for (int i = 0; i < defaultGeneLength; ++i) {
            raw |= getGene(i + startFrom) << i;
        }
        return raw;
    }

    /**
     * Decode a raw int value into a diameter.
     *
     * @param raw the raw int value
     * @return a diameter
     * @see #getRaw(int)
     */
    public double decode(int raw) {
        return 10.d + ((70.d / 1023.d) * raw);
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
