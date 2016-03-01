package com.oslab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


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
    public static void FCFS(ArrayList<Process> processes) throws FileNotFoundException {

        Process running_process = null;
        LinkedBlockingQueue<Process> sorted_processes = new LinkedBlockingQueue<Process>();
        LinkedBlockingQueue<Process> ready_processes = new LinkedBlockingQueue<Process>();
        HashSet<Process> finished_processes = new HashSet<Process>();
        HashSet<Process> blocked_processes = new HashSet<Process>();

        int cycle = 0;
        int cpu_time = 0;
        int io_time = 0;

        //place processes in ready queue in order of arrival time
        processes = Util.sortProcessList(processes);
        for(Process process : processes){
            sorted_processes.add(process);
        }

        // dequeue all elements
//        while(!sorted_processes.isEmpty()){
//            System.out.println(sorted_processes.poll().toString());
//        }


        while(finished_processes.size() < number_of_processes){

            // add all processes with current arrival_time to running
            while (!sorted_processes.isEmpty() && sorted_processes.peek().arrival_time == cycle){

                if(sorted_processes.size() > 1){
                    ready_processes.add(sorted_processes.poll());
                }else{
                    ready_processes.add(sorted_processes.poll());
                }

            }


            System.out.print("\n\n\nCYCLE: " + cycle);
            System.out.println("\n\n----- SORTED PROCESSES -----");
            System.out.println(sorted_processes);

            System.out.println("\n----- READY PROCESSES -----");
            System.out.println(ready_processes);

            System.out.println("\n----- RUNNING PROCESSES -----\n");
            System.out.println(running_process);


            // start a running process if there are no running processes
            if(running_process == null){
                running_process = ready_processes.poll();
                running_process.cpu_burst = randomOS(running_process.b);
                running_process.io_burst = randomOS(running_process.io);
            }

            // run the process
            running_process.cpu_burst--;
            running_process.cpu_time_needed--;
            cpu_time++;

            // increase wait times of all ready processes
            for(Process process : ready_processes){
                process.waiting_time++;
            }


            // handle finished processes
            if(running_process.cpu_time_needed == 0){
                running_process.finishing_time = cycle;
                finished_processes.add(running_process);

                // set running_process to null again since the currently running process has finished
                running_process = null;
            }


            cycle++;
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
//        System.out.println(random_int);

        return random_int;
    }



}

















