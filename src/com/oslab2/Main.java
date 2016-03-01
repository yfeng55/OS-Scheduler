package com.oslab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    private static final String INPUT_FILE = "input.txt";
    private static final String RANDOM_NUMBERS_FILE = "random-numbers.txt";

    private static int number_of_processes;
    private static ArrayList<Process> processes = null;

    private static Scanner rand_scanner = null;



    public static void main(String[] args) throws FileNotFoundException {

        rand_scanner = new Scanner(new File(RANDOM_NUMBERS_FILE));
        File file = new File(INPUT_FILE);

        processes = readInputFromFile(file, rand_scanner);

        printProcessList(processes);


        FCFS(processes);

    }



    // 1. First-Come-First-Serve Scheduling //
    public static void FCFS(ArrayList<Process> processes){

        Queue<Process> ready_processes = new LinkedList<Process>();

        //place processes in queue in order of arrival time
        processes = Util.sortProcessList(processes);

        for(Process process : processes){
            ready_processes.add(process);
        }

        // dequeue all elements
        while(!ready_processes.isEmpty()){
//            System.out.println(ready_processes.poll().toString());


        }






    }



    private static ArrayList readInputFromFile(File file, Scanner random_scanner) throws FileNotFoundException {
        ArrayList<Process> processes = new ArrayList<Process>();

        Scanner input = new Scanner(file);
        number_of_processes = input.nextInt();

        int i=0;
        while(input.hasNext()){
            int a, b, c, io;

            a = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            b = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            c = Integer.parseInt(input.next().replaceAll("[\\D]", ""));
            io = Integer.parseInt(input.next().replaceAll("[\\D]", ""));

            processes.add(new Process(i, a, b, c, io));
            i++;
        }

        return processes;
    }

    // print the list of all processes
    private static void printProcessList(ArrayList<Process> processes){
        for(Process process : processes){
            System.out.println(process.toString());
        }
        System.out.println("\n");
    }


    // generate a random number
    private static int randomOS(int u) throws FileNotFoundException {
        int random_int = Integer.parseInt(rand_scanner.next());
        System.out.println(random_int);

        return random_int;
    }



}

















