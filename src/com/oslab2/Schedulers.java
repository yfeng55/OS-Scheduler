package com.oslab2;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Schedulers {


    private static Scanner rand_scanner = null;


    // 1. First-Come-First-Serve Scheduling //
    public static void FCFS(ArrayList<Process> processes, int number_of_processes, Scanner scanner) throws FileNotFoundException {

        rand_scanner = scanner;

        Process running_process = null;
        LinkedBlockingQueue<Process> sorted_processes = new LinkedBlockingQueue<Process>();
        LinkedBlockingQueue<Process> ready_processes = new LinkedBlockingQueue<Process>();
        ArrayList<Process> finished_processes = new ArrayList<Process>();
        HashSet<Process> blocked_processes = new HashSet<Process>();

        int cycle = 0;
        int cpu_time = 0;
        int io_time = 0;

        //place processes in ready queue in order of arrival time
        processes = Util.sortProcessListByArrival(processes);
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


//            System.out.print("\n\n\nCYCLE: " + cycle);
//            System.out.println("\n\n----- SORTED PROCESSES -----");
//            System.out.println(sorted_processes);
//
//            System.out.println("\n----- READY PROCESSES -----");
//            System.out.println(ready_processes);
//
//            System.out.println("\n----- RUNNING PROCESSES -----\n");
//            System.out.println(running_process);


            // start a running process if there are no running processes
            if(running_process == null){
                running_process = ready_processes.poll();
                running_process.cpu_burst = randomOS(running_process.b);
                running_process.io_burst = randomOS(running_process.io);
            }

            // run the process
            running_process.cpu_burst--;
            running_process.cpu_time_left--;
            cpu_time++;

            // increase wait times of all ready processes
            for(Process process : ready_processes){
                process.waiting_time++;
            }


            // handle finished processes
            if(running_process.cpu_time_left == 0){
                running_process.finishing_time = cycle;
                finished_processes.add(running_process);

                // set running_process to null again since the currently running process has finished
                running_process = null;
            }


            cycle++;
        }


        //print summary of all processes (orderd by id)
        finished_processes = Util.sortProcessListById(finished_processes);
        for(Process process : finished_processes){
            System.out.println(process.summary() + "\n");
        }


    }




    // generate a random number
    private static int randomOS(int u) throws FileNotFoundException {
        int random_int = rand_scanner.nextInt();
//        System.out.println(random_int);

        return random_int;
    }

}
