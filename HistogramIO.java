import java.io.*;
import java.util.*;

/**
 * Creates a histogram based on a file input. Takes the file name as a string and then reads the file to initialize the 
 * instance variables. Values are then used to compute frequencies and create a histogram with "+" symbols representing each data 
 * point.
 * 
 * @author Yuneydy and Lily 
 * @version 10/23/2023
 */
public class HistogramIO
{
    private int maxInt;
    private int size;
    private int[] data;
    private int numOfBuckets; //Ranges (0-9 as you increase)
    private int[] frequencies;
    private String fileName;

    /**
     * Takes the input file and initializes the instance variables to help create a histogram of the data
     * 
     * @param Takes string representing the file name
     */
    public HistogramIO(String inputFile)
    {
        fileName = inputFile;
        
        try {
            Scanner fileScan = new Scanner(new File(inputFile));
            
            size = Integer.parseInt(fileScan.next());
            maxInt = Integer.parseInt(fileScan.next());
            
            
            // Always rounds up to account for a value in a higher bucket
            numOfBuckets = (int) Math.ceil(maxInt/10.0);
            
            frequencies = new int[numOfBuckets];
            
            data = new int[size];
            int i = 0;
            
            
            while (i < size){
                 int num = Integer.parseInt(fileScan.next());
                 data[i] = num;
                 i++;
            }

            fileScan.close();
            
        } catch (IOException e) {
            System.out.println("Problem with reading from " + inputFile);
        }
    
    }
    
    /**
     * Computes the frequencies based on ranges increasing by 10 each time (representing buckets)
     */
    public void computeFrequencies() {
        int count = 0; 
        for (int i = 0; i < numOfBuckets; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((data[j] >= i*10)&& (data[j] <= ((i*10)+9))) {
                    count++;
                }
            }
            
            frequencies[i] = count;
            count = 0;
        }
    }
    
    /**
     * Creates string representation of the HistogramIO object
     * 
     * @returns a String representation of the data (looks like a histogram)
     */
    public String toString() {
        String s = "Printing the Histogram from file '" + fileName + "'";
            try { 
            s += "\nInitial Data (" + size + " integers):\n";
            
            for (int num:data){
                s += num + " ";
            }
        
            s+= "\n******* Results *******";
        
            computeFrequencies();
            for (int i = 0; i < numOfBuckets; i++) {
                s += "\nRange " + (i*10) + "-" + ((i*10)+9) + "(" +frequencies[i] +")  |";
                
                for (int j = 0; j < frequencies[i] ; j++) {
                    s += "+";
                }
                s+= "\n";
            }
        } catch (NullPointerException n) {
            System.out.println("Cannot print from non-existent file.");
        }
        return s;
    }
    
    
    public static void main(String[] args) {
        // Creates histogram, prints it using toString(), and then computes the frequencies
        HistogramIO LilysH = new HistogramIO("integerData1.txt");
        System.out.println(LilysH);
        LilysH.computeFrequencies();
        
        // Another test case from a file we created to test again
        HistogramIO YuneydysH = new HistogramIO("randomValues.txt");
        System.out.println(YuneydysH);
        
        //Throws an Exception:
        System.out.println("Testing Exceptions:");
        HistogramIO rando = new HistogramIO("loser.txt");
        System.out.println(rando);
        
        //Produces expected results! :)
    }
}
