/**
 * Created by mohammad on 3/20/17.
 */
public class NBPredResult {
    double prob;
    String classname;

    @Override
    public String toString() {
        return "["+classname+"="+prob+"]";
    }
}
