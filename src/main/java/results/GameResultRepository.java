package results;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.tinylog.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for the {@link GameResult} entity.
 */
public class GameResultRepository{

    /**
     * An object mapper to write and read json files
     */
    private final ObjectMapper oj = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * A list where the game results will be stored
     */
    private List<GameResult> gameResultRepository;

    public GameResultRepository() {

        try {
            gameResultRepository = oj.readValue(new FileReader("player.json"), new TypeReference<List<GameResult>>() {
            });
        }catch (JsonProcessingException e) {
            Logger.error(e.getMessage());
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    /**
     *  Adds one result to the repository and rewrites the json file
     * @param element a result a player got
     */
    public void addOne(GameResult element){
        try(var writer = new FileWriter("player.json")){
            gameResultRepository.add(element);
            oj.writeValue(writer,gameResultRepository);
            Logger.info("element added");
        } catch (StreamWriteException e) {
            Logger.error(e.getMessage());
        } catch (DatabindException e) {
            Logger.error(e.getMessage());
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    /** Returns a list of game results of the repository
     *
     * @return a list of game results of the repository
     */
    public List<GameResult> toList(){
        List<GameResult> gameResultList = new ArrayList<>();
        gameResultList.addAll(gameResultRepository);
        return gameResultList;
    }

}