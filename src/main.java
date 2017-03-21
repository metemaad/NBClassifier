import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * Created by mohammad on 3/9/17.
 */
public class main {


    public static void main(String[] args) {
        System.out.print("\n============================================================================\n");
        System.out.print("NB Classifier\n");
        System.out.print("============================================================================\n");


        UIAssignment5 uiAssignment5 = new UIAssignment5();

        FileHandler fl = new FileHandler();
        fl.setFilename(uiAssignment5.getInputFile());
        Vector <Vector< item >> train =fl.loadData();
        Vector<Attribute> attributes=fl.getAttributes();

        Attribute target=uiAssignment5.selectTargetAttribute(attributes);
        NBClassifier nbClassifier=new NBClassifier();
        nbClassifier.train(train,attributes,target);



        UIAssignment5 uiAssignment50 = new UIAssignment5();

        FileHandler fl0 = new FileHandler();
        fl0.setFilename(uiAssignment50.getInputFile());
        Vector <Vector< item >> test =fl0.loadData();
        try
        {
            PrintWriter writer = new PrintWriter("Result.txt", "UTF-8");
        System.out.print("\n");
            writer.printf("\n");
        for (Attribute attribute:attributes) {
            System.out.print(" "+attribute.AttributeName+" ");
            writer.printf(" "+attribute.AttributeName+" ");

        }
        System.out.print(" Classification");
            writer.printf(" Classification");
        int corr=0,all=0;
        for (Vector<item> record:test){
            String tar="";
            System.out.print( " \n");
            writer.printf(" \n");
            for (item i :record ) {
                System.out.print(" "+i.getvalue()+ " ");
                writer.printf(" "+i.getvalue()+ " ");
                if (i.getAttributeName().equals(target.AttributeName))
                {
                    tar=i.getvalue();
                }
            }



            Vector<NBPredResult> res = nbClassifier.prediction(record);
            double sum=0,max=0;
            for (NBPredResult r:res) {sum+=r.prob;}
            String label="";
            for (NBPredResult r:res) {

                if (r.prob/sum>max){
                label=r.classname;
                max=r.prob/sum;
                }
            }

            if (!tar.equals(label)){corr++;}
            all++;
            System.out.print(" "+label);
            writer.printf(" "+label);


        }

        System.out.printf("\nAccuracy: %d / %d ",all-corr,all);
            writer.printf("\nAccuracy: %d / %d ",all-corr,all);


            writer.close();
            System.out.print("\nResults are ready in Result.txt");
        } catch (IOException e) {
            System.out.print("Error in writing to file!");
        }

    }
}