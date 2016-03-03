package com.oslab2;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;




public class LCFS {

    private static Scanner rand_scanner = null;

    // 1. Last-Come-First-Serve Scheduling //
    public static void LCFS(ArrayList<Process> processes, int number_of_processes, Scanner scanner, boolean verbose_flag) throws FileNotFoundException {

        rand_scanner = scanner;

        Process running_process = null;
        LinkedList<Process> sorted_processes = new LinkedList<Process>();
        Stack<Process> ready_processes = new Stack<Process>();
        ArrayList<Process> blocked_processes = new ArrayList<Process>();
        ArrayList<Process> finished_processes = new ArrayList<Process>();


        int cycle = 0;
        int cpu_cycles = 0;
        int io_cycles = 0;


        //print original input
        System.out.print("The original input was: ");
        for(Process p : processes){
            System.out.print(p.toString() + " ");
        }

        processes = Util.sortProcessListByArrival(processes);
        //print sorted input
        System.out.print("\nThe (sorted) input is: ");
        for(Process p : processes){
            System.out.print(" " + p.toString());
        }



        //place processes in ready queue in order of arrival time and print
//        ArrayList<Process> reversedList = new ArrayList<Process>(processes);
//        Collections.reverse(reversedList);
       processes = Util.sortProcessListByArrival_Desc(processes);


        System.out.println("\n");
        if(verbose_flag){
            System.out.println("This detailed printout gives the state and remaining burst for each process\n");
        }


        //add processes to sorted_processes
        for(int i=0; i<processes.size(); i++){
            sorted_processes.add(processes.get(i));
        }
        //correct the initial ID fields
        int j=0;
        for(int i=processes.size()-1; i>=0; i--){
            processes.get(i).id = j;
            j++;
        }


        while(finished_processes.size() < number_of_processes){

            // print process states before starting cycle if verbose flag is set to true
            if(verbose_flag){
                System.out.print(String.format("%-23s", "Before cycle " + cycle + ":"));

                for(int i=processes.size()-1; i>=0; i--){
                    int burst;
                    if(processes.get(i).state.equals("blocked")){
                        burst = processes.get(i).io_burst;
                    }
                    else if(processes.get(i).state.equals("running")){
                        burst = processes.get(i).cpu_burst;
                    }
                    else{
                        burst = 0;
                    }
                    System.out.print(String.format("%-16s", processes.get(i).state + " " + Integer.toString(burst)));
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
                    io_cycles++;

                    if(p.io_burst == 0){

                        p.state = "ready";
                        finished_io.add(p);

                    }
                }

                finished_io = Util.sortByArrivalThenID_Desc(finished_io);

                ready_processes.addAll(finished_io);

                blocked_processes.removeAll(finished_io);
            }




            ////// DO_RUNNING //////
            if(running_process != null){

                running_process.cpu_time_left--;
                running_process.cpu_burst--;
                cpu_cycles++;


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
            if (!sorted_processes.isEmpty() && sorted_processes.peekLast().arrival_time == cycle){

                if(sorted_processes.size() > 1){
                    Iterator iterator = sorted_processes.listIterator(sorted_processes.indexOf(sorted_processes.getLast())-1);
                    ArrayList<Process> to_remove = new ArrayList<Process>();

                    while(iterator.hasNext()){
                        Process p = (Process) iterator.next();
                        to_remove.add(p);

                        p.state = "ready";
                        ready_processes.add(p);
                    }

                    sorted_processes.removeAll(to_remove);
                }
                else{
                    Process p = sorted_processes.getLast();
                    p.state = "ready";

                    sorted_processes.remove(p);
                    ready_processes.add(p);
                }

            }



            ///// DO_READY /////
            if(running_process == null && !ready_processes.isEmpty()){

                // start a running process if there are no running processes
                if(ready_processes.peek().arrival_time != cycle || cycle == 0){
                    Process p = ready_processes.pop();
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
        System.out.println("The scheduling algorithm used was LastCome First Served\n");
        for(Process p : finished_processes){
            p.turnaround_time = p.finishing_time - p.arrival_time;
            p.waiting_time = p.finishing_time - p.c - p.io_time - p.arrival_time;
            System.out.println(p.summary() + "\n");
        }


        //print a summary of the entire scheduling process

        float avg_turnaround = 0;
        float avg_waiting = 0;
        for(Process p : finished_processes){
            avg_turnaround += p.turnaround_time;
            avg_waiting += p.waiting_time;
        }
        avg_turnaround = avg_turnaround / number_of_processes;
        avg_waiting = avg_waiting / number_of_processes;

        System.out.println("Summary Data: ");
        System.out.println("\tFinishing time: " + (cycle-1));
        System.out.println("\tCPU Utilization: " + (float)cpu_cycles/(cycle-1));
        System.out.println("\tI/O Utilization: " + (float)io_cycles/(cycle-1));
        System.out.println("\tThroughput: " + (float)number_of_processes/(cycle-1) * 100 + " processes per hundred cycles");
        System.out.println("\tAverage turnaround time: " + avg_turnaround);
        System.out.println("\tAverage waiting time: " + avg_waiting);


    }


    // generate a random number
    private static int randomOS(int u) throws FileNotFoundException {
        int random_int = rand_scanner.nextInt();

//        System.out.println("Find burst " + random_int);

        return 1 + (random_int % u);
    }


}
