package com.oslab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static final String INPUT_FILE = "input.txt";

    private static int number_of_processes;
    private static ArrayList<Process> processes = null;


    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(INPUT_FILE);
        processes = readInputFromFile(file);

        FCFS(processes);

        printProcessList(processes);




    }




    // 1. First-Come-First-Serve Scheduling //
    public static void FCFS(ArrayList<Process> processes){

        //place processes in queue in order of arrival time
        

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

    // print the list of all processes
    private static void printProcessList(ArrayList<Process> processes){
        for(Process process : processes){
            System.out.println(process.toString());
        }
    }





}

















