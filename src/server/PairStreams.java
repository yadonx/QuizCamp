package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:50
 * Project: QuizCamp
 * Package: server
 */
public class PairStreams {

    private static List<ObjectOutputStream> pair = new ArrayList<>();

    public List<ObjectOutputStream> getPair() {
        return pair;
    }

    public void add(ObjectOutputStream stream){
        pair.add(stream);
    }

}
