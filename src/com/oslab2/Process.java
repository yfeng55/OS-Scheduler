package com.oslab2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Process {

    private static final String RANDOM_NUMBERS_FILE = "random-numbers.txt";

    public int arrival_time;
    public int cpu_burst_time;
    public int cpu_time_needed;
    public int io_time;

    public int waiting_time = 0;
    public int finishing_time = 0;



    public Process(int a, int b, int c, int io) throws FileNotFoundException {
        this.arrival_time = a;
        this.cpu_burst_time = randomOS(b);
        this.io_time = randomOS(io);
        this.cpu_time_needed = c;
    }


    public String toString(){

        String outputstring = "";

        outputstring += String.format("%-23s", "Arrival Time:" + Integer.toString(arrival_time));
        outputstring += String.format("%-23s", "CPU Burst Time:" + Integer.toString(cpu_burst_time));
        outputstring += String.format("%-23s", "CPU Time Needed:" + Integer.toString(cpu_time_needed));
        outputstring += String.format("%-23s", "IO Time:" + Integer.toString(io_time));

        outputstring += String.format("%-23s", "Waiting Time:" + Integer.toString(waiting_time));

        return outputstring;
    }



    // generate a random number
    private static int randomOS(int u) throws FileNotFoundException {

        ArrayList<Integer> randomintegers = new ArrayList<Integer>();

        File file = new File(RANDOM_NUMBERS_FILE);
        Scanner input =  new Scanner(file);

        while(input.hasNextLine()){
            try{
                randomintegers.add(Integer.parseInt(input.next()));
            }catch(Exception e){
            }
        }

        //get a random element from the arraylist of integers
        Random randomGen = new Random();
        int randomint = randomintegers.get(randomGen.nextInt(randomintegers.size()));
//        System.out.println("RANDOM INT: " + randomint);

        return 1 + (randomint % u);
    }

}
