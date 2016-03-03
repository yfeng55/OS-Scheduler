package com.oslab2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class FCFS {


    private static Scanner rand_scanner = null;


    // 1. First-Come-First-Serve Scheduling //
    public static void FCFS(ArrayList<Process> processes, int number_of_processes, Scanner scanner, boolean verbose_flag) throws FileNotFoundException {

        rand_scanner = scanner;

        Process running_process = null;
        LinkedBlockingQueue<Process> sorted_processes = new LinkedBlockingQueue<Process>();
        LinkedBlockingQueue<Process> ready_processes = new LinkedBlockingQueue<Process>();
        ArrayList<Process> blocked_processes = new ArrayList<Process>();
        ArrayList<Process> finished_processes = new ArrayList<Process>();


        int cycle = 0;
        int cpu_time = 0;
        int io_time = 0;


        //place processes in ready queue in order of arrival time
        processes = Util.sortProcessListByArrival(processes);

        //add processes to sorted_processes and correct the initial ID fields
        for(int i=0; i<processes.size(); i++){
            processes.get(i).id = i;
            sorted_processes.add(processes.get(i));
        }


        while(finished_processes.size() < number_of_processes){

            // print process states before starting cycle if verbose flag is set to true
            if(verbose_flag){
                System.out.print(String.format("%-23s", "Before cycle " + cycle + ":"));

                for(Process p : processes){
                    int burst;
                    if(p.state.equals("blocked")){
                        burst = p.io_burst;
                    }
                    else if(p.state.equals("running")){
                        burst = p.cpu_burst;
                    }
                    else{
                        burst = 0;
                    }
                    System.out.print(String.format("%-16s", p.state + " " + Integer.toString(burst)));
                }
                System.out.println();
            }



            ///// DO_BLOCKED /////
            if(!blocked_processes.isEmpty()){

                ArrayList<Process> finished_io = new ArrayList<Process>();

                for(Process p : blocked_processes) {
                    // decrease IO burst
                    p.io_burst--;
                    p.io_time++;

                    if(p.io_burst == 0){

                        //TODO: implement tiebreaker


                        p.state = "ready";
                        finished_io.add(p);

//                        ready_processes.add(p);



                    }
                }

                finished_io = Util.sortByArrivalThenID(finished_io);

                ready_processes.addAll(finished_io);

                blocked_processes.removeAll(finished_io);
            }




            ////// DO_RUNNING //////
            if(running_process != null){

                running_process.cpu_time_left--;
                running_process.cpu_burst--;
                cpu_time++;


                // handle finished processes
                if(running_process.cpu_time_left == 0){
                    running_process.state = "terminated";
                    running_process.finishing_time = cycle;
                    finished_processes.add(running_process);

                    // set running_process to null again since the currently running process has finished
                    running_process = null;
                }
                // if the running process has used up its burst time, move it to blocked state
                else if(running_process.cpu_burst == 0){
                    running_process.state = "blocked";
                    blocked_processes.add(running_process);
                    running_process.io_burst = randomOS(running_process.io);

                    running_process = null;
                }

            }



            ////// DO_ARRIVING //////
            while (!sorted_processes.isEmpty() && sorted_processes.peek().arrival_time == cycle){
                Process p = sorted_processes.poll();
                p.state = "ready";
                ready_processes.add(p);
            }



            ///// DO_READY /////
            if(running_process == null && !ready_processes.isEmpty()){

                // start a running process if there are no running processes
                if(ready_processes.peek().arrival_time != cycle || cycle == 0){
                    Process p = ready_processes.poll();
                    running_process = p;
                    p.state = "running";

                    // give the running process a new cpu_burst value
                    running_process.cpu_burst = randomOS(running_process.b);
                }

            }




            cycle++;
        }
        ///// END FCFS LOOP /////




        //print summary of all processes (orderd by id)
        finished_processes = Util.sortProcessListById(finished_processes);
        System.out.println();
        for(Process p : finished_processes){
            p.turnaround_time = p.finishing_time - p.arrival_time;
            p.waiting_time = p.finishing_time - p.c - p.io_time - p.arrival_time;
            System.out.println(p.summary() + "\n");
        }





    }



    // generate a random number
    private static int randomOS(int u) throws FileNotFoundException {
        int random_int = rand_scanner.nextInt();

//        System.out.println("Find burst " + random_int);

        return 1 + (random_int % u);
    }


}
