package cli;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class ExtractN {

    private static void extractdata(String filename){
        Path path=Paths.get(filename);
        try{
            List<String> filelines=Files.readAllLines(path);
            for(String lines:filelines){System.out.println(lines);}
            System.out.println("\nReached end of the file");
            
        }
        catch(IOException e){
            System.out.println("Error while executing!");
            e.printStackTrace();
        }
    }
    
    public static void extract(Scanner sc){
        System.out.println("Enter the filename you want to extract data from");
        boolean check=true;
        while(check){
            String filename=sc.nextLine().trim();
            if(filename.isEmpty()){
                System.out.println("Filename cannot be empty. Enter the filename again.");
                continue;
            }
            Path path=Paths.get(filename);
            if(!Files.exists(path) || !Files.isRegularFile(path)){
                System.out.println("File does not exist or is not a regular file.");
                System.out.println("Do you want to reenter name?\n'y' to re enter");
                String choice=sc.nextLine().trim().toLowerCase();
                if(choice.isEmpty() || choice.charAt(0)!='y'){
                    System.out.println("File doesn't exist! Data cannot be extracted!");
                    return;
                }
                else{
                    System.out.println("Enter correct filename again");
                }
            }
            else{
                extractdata(filename);
                return;
            }
        }
    }
}
