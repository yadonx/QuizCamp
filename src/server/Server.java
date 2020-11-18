package server;

import model.Answer;
import model.Question;
import model.QuestionOfCategory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Emil Johansson
 * Date: 2020-11-13
 * Time: 12:17
 * Project: QuizCamp
 * Package: server
 */

public class Server {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;


    public Server(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }

    public Answer sendQuestionGetAnswer(Question question) {
        try {
            out.writeObject(question);
            return (Answer) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;//TODO:add try again feature
    }

    public Answer chooseCategory(QuestionOfCategory category) {

        try {
            out.writeObject(category);
            return (Answer) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;//TODO:add feature to try again
    }

    public void sendScores(Result scores) {

        try {
            out.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
