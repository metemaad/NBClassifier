import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * This class is responsiple for divide dataset into random subsets for selecting the test and train dataset. It also responsible for folding dataset
 * to use subsets in cross validation.
 * Created by mohammad on 3/20/17.
 */
public class DataSetHandler {

    /**
     * dataset that the handler will work on it.
     */
    Vector<Vector<item>> data;

    /**
     * divided dataset into test and training sets
     */
    private Vector<Vector<Vector<item>>> DividedDataset=new Vector<>();
    /**
     * Folded dataset into N parts
     */
    private Vector<Vector<Vector<item>>> FoldedDataset=new Vector<>();
    private List<Object> attributes;

    /**
     * fold the dataset into N folds
     * @param data dataset
     * @param fold number of folds
     */
    public void FoldTrainAndTest(Vector<Vector<item>> data, int fold) {
        FoldedDataset=new Vector<>();
        Vector<Vector<item>> tmpfold=new Vector<>();
        int n=data.size()/fold;
        for (int j=0;j<fold-1;j++){
            tmpfold=new Vector<>();
            for (int i = 0; i<=n - 1; i++)
            {
                Vector<item> select = data.get(random(data.size()));
                tmpfold.add(select);
                data.remove(select);
            }
            FoldedDataset.add(j,tmpfold);
        }
        FoldedDataset.add(fold-1,data);
    }

    /**
     * this function returns the test dataset after division
     * @return test dataset
     */
    public Vector<Vector<item>> getTestDataset() {

        return DividedDataset.get(1);
    }

    /**
     * return the Nth part of dataset that has been folded
     * @param fold number of fold
     * @return dataset related to that number
     */
    public Vector<Vector<item>> getFoldTest(int fold) {

        return FoldedDataset.get(fold);
    }

    /**
     * combine all dataset folds except test fold (fold) and generate the Train dataset
     * @param fold number of test fold
     * @return traing test dataset for fold
     */
    public Vector<Vector<item>> getFoldTrain(int fold) {

        Vector<Vector<item>> ret=new Vector<>();
        int h=FoldedDataset.size()-1;
        for (int j=0;j<h;j++)
        {
            Vector<Vector<item>> tmp = FoldedDataset.get(j);
            ret.addAll(tmp);
        }




        ret.removeAll(FoldedDataset.get(fold));
        return ret;
    }
    /**
     * devide the dataset based on an attribute to its classes
     * @param set the dataset to be divided
     * @param attribute the attribute that dataset will be divided on
     * @return the list of datasets that is generated
     *
     */
    Vector<subSetclass> subSet(Vector<Vector<item>> set, Attribute attribute) {


        Vector<subSetclass> res2 = new Vector<>();
        for (String classname : attribute.AttributeClasses) {


            subSetclass res = new subSetclass();
            for (Vector<item> i : set) {
                boolean chk = false;
                for (item tmp : i) {
                    if ((tmp.getAttributeName().equals(attribute.AttributeName)) & (tmp.getvalue().equals(classname))) {
                        chk = true;
                    }

                }
                if (chk) {
                    res.classname = classname;
                    res.attribute=attribute;
                    res.subSet.add(i);
                }
            }
            res2.add(res);
        }
        return res2;
    }

    void UpdateCounts(Vector<Vector<item>> data,Vector<Attribute> attributes)  {
        for (Attribute a:attributes){
a.reset();}

        for (Vector<item > record :data) {
            for (item i:record)
            {

                Attribute a=new Attribute();
                a.AttributeName=i.getAttributeName();
                int x= attributes.indexOf(a);
                Attribute aa=attributes.get(x);
                aa.addClass(i.getvalue());

            }

        }
    }

    /**
     * claculate the cardinality of each class in the candidate attribute
     * @param attribute attribute to be counted
     * @return updated attribute
     */

    private Attribute UpdateCounts(Attribute attribute,Attribute target) {

        int index = this.attributes.indexOf(attribute);
        int index2 = this.attributes.indexOf(target);

        Attribute newatt = new  Attribute();
        newatt.AttributeName = attribute.AttributeName;
        item itemi2 = new item();
        for (Vector<item> items : this.data) {
            item itemi = items.get(index);
            itemi2 = items.get(index2);
            newatt.addClass(itemi.getvalue());
        }
//
//        for (String classname : newatt.AttributeClasses) {
//            if ((newatt.classCount(classname) == 0) | (this.data.size() == 0) | ((newatt.classCount(classname) == this.data.size()))) {
//                this.pure = true;
//                this.targetValueInLeaf = itemi2.getvalue();
//                break;
//
//            }
//
//        }
        return newatt;


    }

    /**
     * generate random number
     * @param n input number to be generated
     * @return random number
     */
    private int random(int n){ Random rand = new Random(); return rand.nextInt(n) ;}

    /**
     * implemementation of DivideTrainAndTest of classifier interface
     * @param data Dataset in vector structure
     * @param percent Percent of Dataset that should be in the training set.
     */

    public void DivideTrainAndTest(Vector<Vector<item>> data, int percent) {
        DividedDataset=new Vector<>();
        Vector<Vector<item>> train=new Vector<>();
        int n=data.size()*percent/100;
        for (int i = 0; i<=n - 1; i++)
        {
            Vector<item> select = data.get(random(data.size()));
            train.add(select);
            data.remove(select);
        }
        DividedDataset.add(0,train);
        DividedDataset.add(1,data);
    }

}
