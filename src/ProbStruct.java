import java.util.Vector;

/**
 * Created by mohammad on 3/20/17.
 */
public class ProbStruct {
    Attribute attribute;
    String classname;
    double probability;
    ProbStruct parent;
    Vector<ProbStruct> child;

    ProbStruct(){
        attribute=new Attribute();
        classname="";
        probability=0;
        parent=null;
        child=new Vector<>();
    }

    @Override
    public String toString() {
        return "P["+attribute.AttributeName+'='+classname+"]="+Math.pow(Math.E, probability);
    }
}
