//source algorithm: https://algs4.cs.princeton.edu/51radix/LSD.java.html
//using IntelliJ IDEA 2020.2.3
//This program will sort string array using LSD radix sort
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LSD {
    static final int BITS_PER_BYTE = 8;

    public static void sort(String[] a, int w) {
        int n = a.length; //size of an array
        int R = 256; //extended ASCII character set (Radix)
        String[] aux = new String[n];//create string array

        for (int d = w-1; d >= 0; d--) { //start sort by dth character as key-indexed counting

            int[] count = new int[R+1];

            for (int i = 0; i < n; i++) //count frequency
                count[a[i].charAt(d) + 1]++;0

            for (int r = 0; r < R; r++) //compute cumulates
                count[r+1] += count[r];

            for (int i = 0; i < n; i++) //move data
                aux[count[a[i].charAt(d)]++] = a[i];

            for (int i = 0; i < n; i++) //copy back
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {

        //read textfile
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("sgb-words.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //save the textfile data in input array
        String[] input = lines.toArray(new String[]{});

        //initialised variables
        Calendar calendar = Calendar.getInstance();
        Date start = calendar.getTime(); //count start time
        int n = input.length;
        int w = input[0].length();//fixed length w

        //check whether string have fixed length
        for (int i = 0; i < n; i++)
            assert input[i].length() == w : "Strings must have fixed length";

        //sort the array
        sort(input, w);

        //print results
        System.out.println("Result " + Arrays.toString(input));

        //calculate execution time
        calendar = Calendar.getInstance();
        Date end = calendar.getTime(); //count end time
        long sum = end.getTime() - start.getTime(); //get execution time
        System.out.println("Execution time: "+sum+" ms"); //print execution time
    }
}
