import java.util.Vector;

/**
 * This interface has the functions are shared between different classifiers. it works same as a handler to use for any classifier.

 */
public interface Classifier {

    /**
     * this function is responsible for train a classifier.
     * @param trainingData this is the traing dataset
     * @param attributes this is the list of attributes in the dataset
     * @param target this is the target dataset
     */
    void train(final Vector<Vector<item>> trainingData,final Vector<Attribute> attributes, final Attribute target);

    /**
     * this function get new evidence and pridict the target attribute.
     * This is part of bounce part of the question.
     * @param data this is the evidence that the target value of it should be predicted.
     * @return pridicted value for target attribute.
     */
    Vector<NBPredResult> prediction(Vector<item> data);

}