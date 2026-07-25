package cli;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;

public class DeleteN {
    private static boolean deleteit(String filename){
        Path path=Path.of(filename);
        try{
            return Files.deleteIfExists(path);
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void delete(Scanner sc){
        System.out.println("Enter the filename you want to delete");
        while(true){
            String filename=sc.nextLine().trim();
            if(filename.isEmpty()){
                System.out.println("Filename cannot be empty. Enter the filename again.");
                continue;
            }
            Path path=Path.of(filename);
            if(!Files.exists(path) || !Files.isRegularFile(path)){
                System.out.println("File does not exist or is not a regular file.");
                System.out.println("Do you want to reenter the name?\n'y' to reenter");
                String choice=sc.nextLine().trim().toLowerCase();
                if(choice.isEmpty() || choice.charAt(0)!='y'){
                    System.out.println("Delete operation cancelled.");
                    return;
                }
                System.out.println("Enter the filename again");
                continue;
            }

            boolean deleted = deleteit(filename);
            if(deleted){
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Could not delete the file.");
            }
            return;
        }
    }
}

