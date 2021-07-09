/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean ;
    private final double sd;
    private final double highConfidence;
    private final double lowConfidence;

    public PercolationStats(int n , int trials)
    {
        if(n <= 0 || trials <= 0) throw new IllegalArgumentException();

        double[] results = new double[trials];
        int testX;
        int testY;

        for(int i = 0 ; i < trials;i ++){
            Percolation per = new Percolation(n);

            while(!per.percolates())
            {
                    testX = (int)((StdRandom.uniform() * n) + 1);
                    testY = (int)((StdRandom.uniform() * n) + 1);
                    if(!per.isOpen(testY,testX))
                    {
                        per.open(testX,testY);
                    }
            }
            results[i] = (double)(per.numberOfOpenSites()) / (n * n);
        }

        mean = StdStats.mean(results);
        sd = StdStats.stddev(results);

        lowConfidence =  mean - (1.96 * sd)/ Math.sqrt(trials);
        highConfidence =  mean + (1.96 * sd)/ Math.sqrt(trials);

    }

    public double mean(){
        return mean;
    }

    public double stddev()
    {
        return sd;
    }

    public double confidenceLo()
    {
        return lowConfidence;
    }

    public double confidenceHi(){
        return highConfidence;
    }
    public static void main(String[] args) {


    }
}
