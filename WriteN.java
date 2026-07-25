package cli;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class WriteN {

    private static boolean writenew(String filename,List<String> holdstring){
        Path path=Paths.get(filename);
        try{
            Files.write(path,holdstring,StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        }
        catch(IOException e){
            System.out.println("An error occurred while executing");
            e.printStackTrace();
            return false;
        } 
    }

    private static boolean writeappend(String filename,List<String> holdstring){
        Path path=Paths.get(filename);
        try{
            Files.write(path,holdstring,StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            return true;
        }
        catch(IOException e){
            System.out.println("An error occurred while executing");
            e.printStackTrace();
            return false;
        } 
    }
    
    public static void write(Scanner sc){
        String filename;
        while(true){
            System.out.println("Enter your filename.");
            filename=sc.nextLine().trim();
            if(filename.isEmpty()){
                System.out.println("Filename cannot be empty.");
                continue;
            }
            break;
        }

        List<String> holdstring=acceptL(sc);
        boolean check=true;
        while(check){
            Path path=Paths.get(filename);
            if(!Files.exists(path) || !Files.isRegularFile(path)){
                if (Files.exists(path) && !Files.isRegularFile(path)) {
                    System.out.println("The path exists but is not a file.");
                } else {
                    System.out.println("File does not exist.");
                }
                System.out.println("Do you want to reenter name or create anyway?\n'C' to create\n'R' to re-enter ");
                String choice=sc.nextLine().trim().toLowerCase();
                if(!choice.isEmpty()){
                    switch(choice.charAt(0)){
                        case 'c':
                            boolean b= writenew(filename, holdstring);
                            check=false;
                            if(!b){ System.out.println("Could not write the data"); return; }
                            else{System.out.println("File created and data updated successfully");return;}
                        case 'r':
                            while(true){
                                System.out.println("Enter your filename.");
                                filename=sc.nextLine().trim();
                                if(filename.isEmpty()){
                                    System.out.println("Filename cannot be empty.");
                                    continue;
                                }
                                break;
                            }
                            break;
                        default: System.out.println("Invalid choice");
                    }
                }
                else{
                    System.out.println("Choice can't be empty");
                }
            }
            else{
                System.out.println("Enter your choice\n1.Overwrite the old file\n2.Append into the file");
                check=true;
                while(check){
                    int ch2=accept(sc);
                    switch(ch2){
                        case 1: {
                            boolean b= writenew(filename,holdstring);
                            check=false;
                            if(!b){ System.out.println("Could not write the data"); }
                            else{System.out.println("Data updated successfully");}
                        } break;
                        case 2: {
                            boolean b= writeappend(filename,holdstring);
                            check=false;
                            if(!b){ System.out.println("Could not write the data"); }
                            else{System.out.println("Data updated successfully");}
                        } break;
                        default: System.out.println("Invalid choice");
                                System.out.println("enter your choice again 1>overwrite   2>append");
                    }
                }
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

    private static List<String> acceptL(Scanner sc){
        System.out.println("Enter how many lines do you want to write");
        int n=accept(sc);
        List<String> holdstring=new ArrayList<String>();
        System.out.println("Enter your lines");
        for(int i=0;i<n;i++){
            String s=sc.nextLine();
            holdstring.add(s);
        }
        return holdstring;
    }
}
