import java.util.Vector;

/**
 * Created by mohammad on 3/20/17.
 */
public class NBClassifier implements Classifier {

    @Override
    public void train(Vector<Vector<item>> trainingData, Vector<Attribute> attributes, Attribute target) {



        Vector<Attribute> otherattributes=new Vector<>();
        otherattributes.addAll(attributes);
        otherattributes.remove(target);




        DataSetHandler dsh=new DataSetHandler();
        Vector<subSetclass> subsets = dsh.subSet(trainingData, target);
        for (subSetclass subset:subsets ) {
            dsh.UpdateCounts(trainingData,attributes);
            ProbStruct probStruct1=new ProbStruct();
            probStruct.child.add(probStruct1);
            probStruct1.parent=probStruct;
            probStruct1.classname=subset.classname;
            probStruct1.attribute=target;
            probStruct1.probability=probability(trainingData,target,subset.classname);


            dsh.UpdateCounts(subset.subSet,attributes);
                for (Attribute attribute:otherattributes) {

                    for (String inclassname:attribute.AttributeClasses ) {
                        ProbStruct probStruct2=new ProbStruct();
                        probStruct2.parent=probStruct1;
                        probStruct2.classname=inclassname;
                        probStruct2.attribute=attribute;
                        probStruct2.probability=probability(subset.subSet,attribute,inclassname);
                        probStruct1.child.add(probStruct2);

                    }

                }

            }



    }
    ProbStruct probStruct=new ProbStruct();
    private double probability(Vector<Vector<item>> dataset, Attribute X, String attributeClass){
        double m=1;
        double k=X.AttributeClasses.size()+1;
        double p=1/(k);
        double n=dataset.size();
        double nc=X.classCount(attributeClass);
        return (Math.log(nc+(m*p))-Math.log(n+m));
    }

    double getitemprob(item it, ProbStruct p)
    {
        for (ProbStruct d :p.child  ) {
            if ((d.classname.equals(it.getvalue()))&(d.attribute.AttributeName.equals(it.getAttributeName())))
            {
                return d.probability;
            }
        }

        return 0;
    }
    @Override
    public Vector<NBPredResult> prediction(Vector<item> data)
    {
        double x=0;
        Vector<NBPredResult> ret=new Vector<>();
        for (ProbStruct c:probStruct.child  ) {
            x=c.probability;
            for (item d :data ) {
                if (d.getAttributeName()!=probStruct.attribute.AttributeName)
                {
                    double l=getitemprob(d,c);
                    x+=l;
                }

            }
            NBPredResult rst=new NBPredResult();

            rst.classname=c.classname;
            rst.prob=Math.pow(Math.E, x);
            ret.add(rst);

        }
        return ret;
    }

}
