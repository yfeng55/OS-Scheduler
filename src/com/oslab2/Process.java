package com.oslab2;


import java.io.FileNotFoundException;

public class Process{

    public int id;

    //track the process' state
    public String state;

    public float penalty_ratio;

    //upper bounds for randOS()
    public int b;
    public int io;
    public int c;

    public int arrival_time;
    public int cpu_time_left;

    public int cpu_burst;
    public int io_burst = -99999;

    public int waiting_time = 0;
    public int finishing_time;
    public int turnaround_time;
    public int io_time;



    public Process(int id, int a, int b, int c, int io) throws FileNotFoundException {

        this.id = id;

        this.arrival_time = a;
        this.b = b;
        this.c = c;
        this.cpu_time_left = c;
        this.io = io;

        this.state = "unstarted";

    }


    public String toDetailedString(){

        String outputstring = "";

        outputstring += String.format("%s", "{id: " + Integer.toString(id));
        outputstring += String.format("%s", ", arrival_time: " + Integer.toString(arrival_time));
        outputstring += String.format("%s", ", b: " + Integer.toString(b));
        outputstring += String.format("%s", ", cpu_time_left: " + Integer.toString(cpu_time_left));
        outputstring += String.format("%s", ", io: " + Integer.toString(io)) + "}";

        return outputstring;
    }


    public String toString(){

        return Integer.toString(arrival_time) + " " + Integer.toString(b) + " " + Integer.toString(c) + " " + Integer.toString(io);
    }



    public String summary(){

        String outputstring = "Process " + id;

        outputstring += "\n\t (A,B,C,IO) = " + "(" + arrival_time + "," + b + "," + c + "," + io + ")";
        outputstring += "\n\t Finishing time: " + finishing_time;
        outputstring += "\n\t Turnaround time: " + turnaround_time;
        outputstring += "\n\t I/O time: " + io_time;
        outputstring += "\n\t Waiting time: " + waiting_time;

        return outputstring;
    }









}













