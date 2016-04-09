/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystemsimulation;

import java.io.*;
import java.nio.file.Files;

/**
 *
 * @author kaustabh
 */
public class container {
    public static void main(String[] args){
       Writer writer = null;
    try {
    writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream("container.txt"), "utf-8")); //create new text file named container
    FileWriter fw = new FileWriter("container.txt", true); //tell it to append to the file 
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter out = new PrintWriter(bw);
    //boot sector 
    for(float bytes=0; bytes<=530000; bytes++){
        out.print("$");
        writer.close();
    }
    float bytes = new File("container.txt").length();
    out.println("");
    System.out.println("boot sector: "+bytes);
    //meta deta sector
    for(float i=0; i<=530000; i++){
        out.print("&");
        writer.close();
    }
    bytes = new File("container.txt").length();
    out.println("");
    System.out.println("meta data sector appended: "+bytes);
    //data file sector
    for(float i=0; i<=3150000; i++){
        out.print("#");
        writer.close();
    }
    bytes = new File("container.txt").length();
    out.println("");
    System.out.println("data file sector appended: "+bytes);
    
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    }
