package model;

import java.util.Random;

public class NoiseGenerator {
	public static double[][] GenerateWhiteNoise(int width,int height){

        Random random = new Random((Math.round(Math.random() * 100 * Math.random() * 10))); //Seed to 0 for testing
        double[][] noise = new double[width][height];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++){
                noise[i][j] = (Math.random() % 1);
            }
        }

        return noise;
    }

    static double[][] GenerateSmoothNoise(double[][] baseNoise, int octave){
       int width = baseNoise.length;
       int height = baseNoise.length;

       double[][] smoothNoise = new double[width][height];

       int samplePeriod = 1 << octave; // calculates 2 ^ k
       double sampleFrequency = 1.0 / samplePeriod;

       for (int i = 0; i < width; i++)
       {
          //calculate the horizontal sampling indices
          int sample_i0 = (i / samplePeriod) * samplePeriod;
          int sample_i1 = (sample_i0 + samplePeriod) % width; //wrap around
          double horizontal_blend = (i - sample_i0) * sampleFrequency;

          for (int j = 0; j < height; j++)
          {
             //calculate the vertical sampling indices
             int sample_j0 = (j / samplePeriod) * samplePeriod;
             int sample_j1 = (sample_j0 + samplePeriod) % height; //wrap around
             double vertical_blend = (j - sample_j0) * sampleFrequency;

             //blend the top two corners
             double top = Interpolate(baseNoise[sample_i0][sample_j0],
                baseNoise[sample_i1][sample_j0], horizontal_blend);

             //blend the bottom two corners
             double bottom = Interpolate(baseNoise[sample_i0][sample_j1],
                baseNoise[sample_i1][sample_j1], horizontal_blend);

             //final blend
             smoothNoise[i][j] = Interpolate(top, bottom, vertical_blend);
          }
       }

       return smoothNoise;
    }

    static double Interpolate(double x0, double x1, double alpha){
       return x0 * (1 - alpha) + alpha * x1;
    }
    
    /**
     * Maps the noise given to a smaller array of noise,
     * maybe getting better features.
     */
     static double[][] mapNoise(double[][] initial, int permap){
    	double[][] ans = new double[initial.length/permap][initial[0].length/permap];
    	int scale = permap*permap;
    	for (int x=0;x<initial.length;x+=permap){
    		for (int y=0;y<initial[x].length;y+=permap){
    			double sum=0;
    			for (int i=0;i<permap;i++){
    				for (int j=0;j<permap;j++){
    					sum+=initial[x+i][y+j];
    				}
    			}
    			ans[x/permap][y/permap] = sum/scale;
    			
    		}
    	}
    	
    	return ans;
    }
}
