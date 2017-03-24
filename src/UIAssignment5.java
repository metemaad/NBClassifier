import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

/**
 * This class responsible for creating the user interface.
 * it has functions for getting the file name and selecting the target attribute.
 *
 * Created by Mohammad Etemad on 3/10/17.
 */
public class UIAssignment5 {

    /**
     * this is the target attribute.
     */
    private Attribute _target;

    String getInputFile() {
        String filename = "";
        boolean Invalid = false;
        while (!Invalid) {
            Scanner scan2 = new Scanner(System.in);
            System.out
                    .print("What is the name of the file containing your data?(train dataset or whole dataset)");
            filename = scan2.next();
            String DBFileName = FileHandler.getFilePath(filename);
            Path path = Paths.get(DBFileName);
            if (Files.isReadable(path)) {
                Invalid = true;
            } else {
                System.out.print("\r\nCannot open file:" + DBFileName + "\r\n");
            }

        }
        return filename;
    }

    /**
     * user interface for getting the target attribute.
     * @param attributes list of all attributes than can be selected as a target attribue.
     * @return target attribute.
     */
    Attribute selectTargetAttribute(Vector<Attribute> attributes) {
        int target = 0;
        boolean Invalid = false;
        while (!Invalid) {
            Scanner scan2 = new Scanner(System.in);

            System.out.print("Please choose an attribute (by number): \n");
            for (Attribute s: attributes) {
                System.out.print("\t" + attributes.indexOf(s) + "-" + s.AttributeName + " \n");

            }
            System.out.print("Attribute:");


            target = scan2.nextInt();

            try {
                Attribute a = attributes.get(target);
                System.out.print("\nTarget attribute is :" + a.AttributeName + "\n");
                Invalid = true;
            } catch (Exception e) {
                Invalid = false;
                System.out.print("\nPlease select a valid attribute.");

            }



        }
        _target=attributes.get(target);
        return _target;


    }

    /**
     * user interface for getting the degree of verbosity.
     * this function help user to track the process by looking at the intermediate results.
     * @return user choice of verbosness
     */
    public int verboseness() {


        int target;

            Scanner scan2 = new Scanner(System.in);

            System.out.print("Please choose a verbosity level for results:(0=concise 2=verbose) \n");
            System.out.print("0=concise just print the normal results.) \n");
            System.out.print("2=verbose for getting a detailed results step by step.) \n");

            System.out.print("Verbosity:");

            try {

            target = scan2.nextInt();


            } catch (Exception e) {
                target=0;
                System.out.print("\nNo verbose.\n");

            }




        return target;


    }

    boolean Doyouhaveatestdataset() {


        int target;

        Scanner scan2 = new Scanner(System.in);

        System.out.print("Do you have a test dataset (0=yes 1=no): \n");



        try {

            target = scan2.nextInt();
            if (target==0){
                return true;
            }
            if (target==1){
                return false;
            }
            return Doyouhaveatestdataset();


        } catch (Exception e) {
            System.out.print("\nnot valid.\n");

        }




        return false;


    }

    public int numberoffoldsincrossvalidation() {
        int fold=1;
        boolean Invalid = false;
        while (!Invalid) {
            Scanner scan2 = new Scanner(System.in);
            System.out
                    .print("please enter the number of folds in cross validation:");
            fold = scan2.nextInt();

            if ((fold >1)&(fold<100)) {
                Invalid = true;
            } else {
                System.out.print("\r\nplease select a valid number of folds between 2 and 19:\r\n");
            }

        }
        return fold;
    }
}