import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * this class responsible for reading the file and handling the path of the file. it converts our file to a vector structure.
 * Created by Mohammad Etemad on 3/10/17.
 */
public class FileHandler {

    FileHandler(){
        all_Tuples=new Vector<>();
    }

    /**
     * all_Tuples is a structure to save all the dataset.
     * the dataset has some records each of which is a vector.
     * the record itself is a vector of items
     * and each item has is an attribute and its value
     */
    private  Vector < Vector < item >> all_Tuples;



    /**
     * list of all attributes in the dataset.
     */
    private static Vector<Attribute> attributes;
    /**
     * dataset file name
     */
    private String _filename;

    /**
     * this function sets our dataset file name
     * @param filename dataset file name
     */
    public void setFilename(String filename) {   _filename=filename;   }

    /**
     * this function class a function to read the dataset and convert it to vector structure.
     */
    public Vector < Vector < item >> loadData()
    {
        return loadDataSet(_filename);
    }

    /**
     * this function returns all the attributes in the dataset.
     * @return list of all attributes or you can say the columns names.
     */
    public Vector<Attribute> getAttributes()
    {
        return attributes;
    }

    /**
     * processing the first line of dataset and fetching the attributes.
     * @param line is the first line of dataset.
     * @return is a list of attributes(columns names) in the input line.
     */
    private static Vector < Attribute > GetItemsinoneLine(String line) {
        String[] tmpln = line.replaceAll("  +|   +|\t|\r|\n", " ").split(" ");
        Vector < Attribute > Res = new Vector < > ();
        for (String s: tmpln) {
            Attribute a1 = new Attribute();
            a1.AttributeName = s;
            Res.add(a1);
        }
        return Res;
    }

    /**
     * processing each lines of dataset and dividing the items and removing characters such as \r \n
     * @param line is a line of dataset.
     * @return is a list of items in the input line.
     */
    public static Vector < String > GetItemsValuesinoneLine(String line) {
        String[] tempLn = line.replaceAll("  +|   +|\t|\r|\n", " ").split(" ");
        Vector < String > Res = new Vector < > ();
        Collections.addAll(Res, tempLn);
        return Res;
    }

    /**
     * find the path of current directory and make the file path
     * @param filename the dataset file name.
     * @return full path of the dataset file.
     */
    public static String getFilePath(String filename) {
        String CurrDir = System.getProperty("user.dir");
        return CurrDir + "/" + filename;
    }

    /**
     * this function process the dataset file.
     * First it process the first line of our dataset to fetch the attributes.
     * then it reads the rest of file line by line and add the records of dataset to the vector structure of dataset.
     * @param dbFileName dataset file name.
     * @return data set in vector structure.
     */
     Vector < Vector < item >> loadDataSet(String dbFileName) {
        List < String > lines = new ArrayList < > ();
        dbFileName = getFilePath(dbFileName);
        Path path = Paths.get(dbFileName);
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attributes = GetItemsinoneLine(lines.get(0));

        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).isEmpty()) {
                Vector < String > values = GetItemsValuesinoneLine(lines.get(i));
                Vector < item > record = new Vector < > ();
                for (int j = 0; j < values.size(); j++) {
                    attributes.get(j).addClass(values.get(j));
                    item instance = new item(attributes.get(j).AttributeName, values.get(j));
                    record.add(instance);
                }
                all_Tuples.add(record);
            }
        }
        return all_Tuples;
    }


}