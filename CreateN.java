package cli;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class CreateN {
    private static boolean createFile(String filename){
        Path p=Paths.get(filename);
        if(Files.exists(p)){
            System.out.println("file already exists.");
            return false;
        }
        else{
            try{
                Path k=Files.createFile(p);
                System.out.println("File created successfully");
                System.out.println(k.toAbsolutePath());
                return true;
            }
            catch(IOException e){
                System.out.println("An error occured while executing...");
                e.printStackTrace();
                return false;
            }
        }
    }
    private static boolean createDirectory(String s){
        Path p=Path.of(s);
        try{
            Path k=Files.createDirectories(p);
            System.out.println("Directory created successfully");
            System.out.println(k);
            return true;
        }
        catch(Exception e){
            System.out.println("An error occured while executing");
            e.printStackTrace();
            return false;
        }
    }


    public static void create(Scanner sc){
        System.out.println("Enter a choice");
        System.out.println("1.Create a new file\n2.Create a directory");
        boolean check=true;
        while(check){
        int ch=accept(sc);
        switch(ch){
            case 1:
                System.out.println("Enter file name (include the file type as well)");
                while(true){
                    String s=sc.nextLine().trim();
                    if(s.isEmpty()){
                        System.out.println("Filename cannot be empty. Enter a proper filename.");
                        continue;
                    }
                    if(s.contains(".")){
                        if(!createFile(s)){
                            System.out.println("cannot create file");
                        } else {
                            check=false;
                        }
                        break;
                    }
                    else{
                        System.out.println("Enter a proper filename");
                    }
                }
                break;
            case 2:
                System.out.println("Enter directory name (complete directory if required)");
                while(true){
                    String s=sc.nextLine().trim();
                    if(s.isEmpty()){
                        System.out.println("Directory name cannot be empty. Enter the directory name again.");
                        continue;
                    }
                    if(!createDirectory(s)){
                        System.out.println("cannot create directory");
                    }
                    check=false;
                    break;
                }
                break;
            
            default: System.out.println("Invalid choice\nenter your choice again");
            }            
        }
    }


    private static int accept(Scanner sc){
        while(true){
            try{
                int ch=sc.nextInt();
                sc.nextLine();
                return ch;
            }
            catch(InputMismatchException e){
                System.out.println("Enter an integer value");
                sc.nextLine();
            }
        }
    }
}
