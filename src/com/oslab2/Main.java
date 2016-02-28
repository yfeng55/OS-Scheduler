package com.oslab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static final String INPUT_FILE = "input.txt";
    private static final String RANDOM_NUMBERS_FILE = "random-numbers.txt";

    private static int number_of_processes;
    private static ArrayList<Process> processes = null;


    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(INPUT_FILE);
        processes = readInputFromFile(file);

//        printProcessList(processes);

        randomOS(1);
    }



    private static ArrayList readInputFromFile(File file) throws FileNotFoundException {
        ArrayList<Process> processes = new ArrayList<Process>();

        Scanner input = new Scanner(file);
        number_of_processes = input.nextInt();

        while(input.hasNext()){
            int a, b, c, io;

            a = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            b = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            c = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            io = Integer.parseInt(input.next().replaceAll("[\\D]", ""));

            processes.add(new Process(a, b, c, io));
        }

        return processes;
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
        System.out.println("RANDOM INT: " + randomint);

        return 1 + (randomint % u);
    }



    // print the list of all processes
    private static void printProcessList(ArrayList<Process> processes){
        for(Process process : processes){
            System.out.println(process.toString());
        }
    }





}

















