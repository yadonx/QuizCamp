package server;

import model.Answer;
import model.Question;
import model.QuestionOfCategory;
import model.Result;

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
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Answer sendQuestionGetAnswer(Question question) {
        try {
            outputStream.writeObject(question);
            return (Answer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;//TODO:add try again feature
    }

    public Answer chooseCategory(QuestionOfCategory category) {
        try {
            outputStream.writeObject(category);
            return (Answer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;//TODO:add feature to try again
    }

    public void sendScores(Result scores) {
        try {
            outputStream.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPaired() {
        try {
            outputStream.writeObject("paired");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isReady() {
        try {
            outputStream.writeObject("ready?");
            Object input = inputStream.readObject();
            if (input instanceof String) {
                return input.equals("Yes!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
