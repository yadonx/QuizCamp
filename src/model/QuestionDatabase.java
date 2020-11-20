package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Viktor Kodet <br>
 * Date: 2020-11-13 <br>
 * Time: 11:15 <br>
 * Project: IntelliJ IDEA <br>
 */
public class QuestionDatabase implements Serializable {
    List<Category> categoryList = new ArrayList<>();


    // answer1 är det korrekta svaret.
    private void addCategories() {
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
                "Jonas Åkerlund", "Johan Brisinger", "Lukas Moodyson", "Tomas Alfredson"));

        Category dataOchTVSpel = new Category("Data- och TV-spel");
        categoryList.add(dataOchTVSpel);
        dataOchTVSpel.addQuestion(new Question("Vem av följande är INTE en medlem i Koopa-familjen i Super Mario's värld?",
                "Mandy Koopa", "Ludqig von Koopa", "Bowser Jr", "Lemmy Koopa"));
        dataOchTVSpel.addQuestion(new Question("Vilket företag står bakom \"Street Fighter\"-serien?",
                "Capcom", "Tecmo", "Konami", "Namco"));
        dataOchTVSpel.addQuestion(new Question("Vilken typ av spel är Sonic the Hedgehog 2 som släpptes " +
                "till Sega Mega Drive 1992?", "Plattformsspel", "Fightingspel", "Strategispel", "Pusselspel"));

        Category bockerOchOrd = new Category("Böcker och ord");
        categoryList.add(bockerOchOrd);
        bockerOchOrd.addQuestion(new Question("Vad kallas formen \"rödare\" i förhållande till \"röd\"?",
                "Komparativ", "Genitiv", "Superlativ", "Dimunitiv"));
        bockerOchOrd.addQuestion(new Question("Vem skrev \"Flickan i frack\"?",
                "Hjalmar Bergman", "Viktoria Benedictsson", "Selma Lagerlöf", "Augist Strindberg"));
        bockerOchOrd.addQuestion(new Question("Vilken av följande böcker är INTE en bok av David Pelzer?",
                "Pojken med hålet i handen", "Pojken som överlevde", "Pojken som kallades Det",
                "Pojken som inte fanns"));
    }

    public List<Category> getShuffledCategories(int amount){
        List<Category> tempList = new ArrayList<>(categoryList);
        tempList.remove(0);
        Collections.shuffle(tempList);
        List<Category> outList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            outList.add(tempList.get(i));
        }
        return outList;
    }

    public QuestionDatabase() {
        addCategories();
    }

}



