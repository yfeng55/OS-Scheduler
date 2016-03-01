package com.oslab2;


import java.io.FileNotFoundException;

public class Process{

    public int id;

    //upper bounds for randOS()
    public int b;
    public int io;

    public int arrival_time;
    public int cpu_time_needed;

    public int cpu_burst;
    public int io_burst;

    public int waiting_time;
    public int finishing_time;
    public int turnaround_time;
    public int io_time;



    public Process(int id, int a, int b, int c, int io) throws FileNotFoundException {

        this.id = id;

        this.arrival_time = a;
        this.b = b;
        this.cpu_time_needed = c;
        this.io = io;

    }


    public String toString(){

        String outputstring = "";

        outputstring += String.format("%-23s", "PID: " + Integer.toString(id));
        outputstring += String.format("%-23s", "Arrival Time: " + Integer.toString(arrival_time));
        outputstring += String.format("%-23s", "B: " + Integer.toString(b));
        outputstring += String.format("%-23s", "CPU Time Needed: " + Integer.toString(cpu_time_needed));
        outputstring += String.format("%-23s", "IO: " + Integer.toString(io));

        return outputstring;
    }











}













