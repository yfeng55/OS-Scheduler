package com.oslab2;


public class Process {

    private int arrival_time;       // A
    private int max_cpu_burst;      // B
    private int cpu_time_needed;    // C
    private int max_io_time;        // IO


    public Process(int a, int b, int c, int io){
        this.arrival_time = a;
        this.max_cpu_burst = b;
        this.cpu_time_needed = c;
        this.max_io_time = io;
    }


    public String toString(){

        String outputstring = "";

        outputstring += String.format("%-8s", "A:" + Integer.toString(arrival_time));
        outputstring += String.format("%-8s", "B:" + Integer.toString(max_cpu_burst));
        outputstring += String.format("%-8s", "C:" + Integer.toString(cpu_time_needed));
        outputstring += String.format("%-8s", "IO:" + Integer.toString(max_io_time));

        return outputstring;
    }




    // Getters / Setters //

    public int getA(){
        return this.arrival_time;
    }

    public int getB(){
        return this.max_cpu_burst;
    }

    public int getC(){
        return this.cpu_time_needed;
    }

    public int getIO(){
        return this.max_io_time;
    }



}
