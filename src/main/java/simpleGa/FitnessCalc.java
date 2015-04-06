package simpleGa;

public class FitnessCalc implements Const {
    // Calculate inidividuals fittness by comparing it to our candidate solution
    static double getFitness(Individual individual) {
        double a = individual.getA();
        double b = individual.getB();
        double c = individual.getC();
        if (a + b + c - D > 0.001 || a < 10.d || b < 10.d || c < 10.d) { // filter bad chromozome
            return Double.POSITIVE_INFINITY;
        }
        return aire(a) + aire(b) + aire(c);
    }

    static double aire(double diametre) {
        double r = diametre / 2.d;
        return Math.PI * r * r;
    }
}