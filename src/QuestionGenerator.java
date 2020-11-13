import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-13 <br>
 * Time: 11:15 <br>
 * Project: IntelliJ IDEA <br>
 */
public class QuestionGenerator implements Serializable {
    List<Category> categoryList = new ArrayList<>();



    private void addCategories(){
        Category djurOchNatur = new Category("Djur och Natur");
        categoryList.add(djurOchNatur);
        djurOchNatur.addQuestion(new Question("Vad är \"Vermisporium\" ett släkte av?",
                "Svampar", "Orkidéer", "Alger", "Skalbaggar"));
        djurOchNatur.addQuestion(new Question("Vilken är den normala kroppstemperaturen för en hund?",
                "38-39C", "42-43C", "40-41C", "36-37C"));
        djurOchNatur.addQuestion(new Question("Vilken av följande är INTE en växt?",
                "Impala", "Dvärghällebräken", "Dvärgspegel", "Marigold"));

        Category kroppOchKnopp = new Category("Kropp och Knopp");
        categoryList.add(kroppOchKnopp);
        kroppOchKnopp.addQuestion(new Question("Hur många gram natrium har en fullvuxen människa i sin kropp?",
                "100 g", "1 g", "1000 g", "10 g"));
        kroppOchKnopp.addQuestion(new Question("Vilka alternativ är INTE namnet på en typ av blodkärl?",
                "Alveol", "Kapillär", "Ven", "Artär"));
        kroppOchKnopp.addQuestion(new Question("Vad heter den del av blindtarmen som ibland kan bli inflammerad?",
                "Bihanget", "Tolvfingertarmen", "Tjockdelen", "Magmunnen"));

        Category musikOchHits = new Category("Musik och Hits");
        categoryList.add(musikOchHits);
        musikOchHits.addQuestion(new Question("Vilken är INTE en låt av The Who?",
                "Rocky Raccoon", "Boris the Spider", "Pinpall Wizard", "Baba O' Riley"));
        musikOchHits.addQuestion(new Question("Vad hette Wolfgang Mozarts violinist och tonsättare till far?",
                "Leopold", "Bach", "Amadeus", "Leonardo"));
        musikOchHits.addQuestion(new Question("Metalbandet Bathory sägs vara det band som skapade subgenren " +
                "'Black Metal'. Men vilken svensk filmregissör har varit med i bandet?",
                "Jonas Åkerlund", "JOhan Brisinger", "Lukas Moodyson", "Tomas Alfredson"));
    }

    QuestionGenerator(){
        addCategories();
    }
}

class Category {
    String categoryName;
    List<Question> questionList = new ArrayList<>();

    Category(String categoryName){
        this.categoryName = categoryName;
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

}

class Question{
    final String questionText;
    final String answer1;
    final String answer2;
    final String answer3;
    final String answer4;

    Question(String questionText, String answer1, String answer2, String answer3, String answer4){
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
}
