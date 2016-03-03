package com.oslab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Util {


    // sort an ArrayList of Process objects
    public static ArrayList<Process> sortProcessListByArrival(ArrayList<Process> processes){

        Collections.sort(processes, new Comparator<Process>() {

            public int compare(Process p1, Process p2) {
                if (p1.arrival_time == p2.arrival_time) {
                    return 0;
                }

                return p1.arrival_time < p2.arrival_time ? -1 : 1;
            }

        });

        return processes;
    }

    public static ArrayList<Process> sortProcessListByArrival_Desc(ArrayList<Process> processes){

        Collections.sort(processes, new Comparator<Process>() {

            public int compare(Process p1, Process p2) {
                if (p1.arrival_time == p2.arrival_time) {
                    return 0;
                }

                return p1.arrival_time > p2.arrival_time ? -1 : 1;
            }

        });

        return processes;
    }
    






    public static ArrayList<Process> sortProcessListById(ArrayList<Process> processes){

        Collections.sort(processes, new Comparator<Process>() {

            public int compare(Process p1, Process p2) {
                if (p1.id == p2.id) {
                    return 0;
                }

                return p1.id < p2.id ? -1 : 1;
            }

        });

        return processes;
    }


    public static ArrayList<Process> sortByArrivalThenID(ArrayList<Process> processes){

        Collections.sort(processes, new Comparator<Process>() {

            public int compare(Process p1, Process p2) {
                if (p1.arrival_time == p2.arrival_time) {
                    return p1.id < p2.id ? -1 : 1;
                }

                return p1.arrival_time < p2.arrival_time ? -1 : 1;
            }

        });

        return processes;
    }



}
