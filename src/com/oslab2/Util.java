package com.oslab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Util {


    // sort an ArrayList of Process objects
    public static ArrayList<Process> sortProcessList(ArrayList<Process> processes){

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



}
