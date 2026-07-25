package cli;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class TransferN {

    private static boolean transfer(Path sf,Path st,char c){
        if(c=='y'){
        try{
            Files.move(sf, st, StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
            return true;
        }
        catch(Exception e){
            System.out.println("An error occured while tarnsfering");
            e.printStackTrace();
            return false;
        }
        }
        else{
            try{
            Files.move(sf, st,StandardCopyOption.COPY_ATTRIBUTES);
            return true;
            }
            catch(Exception e){
                System.out.println("An error occured while tarnsfering");
                e.printStackTrace();
                return false;
            }
        }
    }
    
    public static void relocate(Scanner sc){
        System.out.println("Enter source file name");
        boolean check=true;
        Path sf = null, st = null;
        while(check){
            String sfname=sc.nextLine().trim();
            if (sfname.isEmpty()) {
                System.out.println("Source filename cannot be empty. Enter source file name again.");
                continue;
            }
            sf=Path.of(sfname);

            if(!Files.exists(sf)){
                    System.out.println("File does not exist.");
                    System.out.println("Do you want to re-enter name?\n'y' to re enter");
                    String choice=sc.nextLine().trim().toLowerCase();
                    if (choice.isEmpty() || choice.charAt(0)!='y'){
                        System.out.println("File doesn't exist! Data cannot be relocated !");
                        return;
                    }
                    else{
                        System.out.println("Enter correct filename again");
                    }
            }
            else{
                check=false;
            }
        }
        System.out.println("Enter target file name");
        check=true;
        while(check){
            String stname=sc.nextLine().trim();
            if (stname.isEmpty()) {
                System.out.println("Target filename cannot be empty. Enter target file name again.");
                continue;
            }
            st=Path.of(stname);

            if(!Files.exists(st)){
                    System.out.println("File does not exist.");
                    System.out.println("Do you want to re-enter name or create a file and transfer?\n'r' to re enter or\n'c' to create and transfer\n'n' to cancel transfer");
                    String choice=sc.nextLine().trim().toLowerCase();
                    if (choice.isEmpty()) {
                        System.out.println("invalid choice");
                        continue;
                    }
                    switch(choice.charAt(0)){
                        case 'r':
                            System.out.println("Enter target filename again");
                            break;
                        case 'c':
                            try{
                                Files.createFile(st);
                                check=false;
                            }
                            catch(Exception e){
                                System.out.println("Error occurred while creating file");
                                e.printStackTrace();
                                return;
                            }
                            break;
                        case 'n':
                            System.out.println("file transfer cancelled succesfully");
                            return;
                        default:
                            System.out.println("invalid choice");
                    }
            }
            else{
                check=false;
            }
        }
        System.out.println("do you want to replace existing file?(y/n)");
        check=true;
        while(check){
            String value=sc.nextLine().trim().toLowerCase();
            if (value.isEmpty()) {
                System.out.println("Enter a valid choice (y/n)");
                continue;
            }
            if(value.charAt(0)=='y' || value.charAt(0)=='n'){
                check=false;
                boolean confirm=transfer(sf, st, value.charAt(0));
                if(confirm==true){
                    System.out.println("file transfered succesfully");
                    return;
                }
                else{
                    return;
                }
            }
            else{
                System.out.println("Enter a valid choice (y/n)");
            }
        }
    }   
}