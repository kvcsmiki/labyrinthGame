package results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * A class representing the result of a game played by a specific player.
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class GameResult {

    /**
     * Indicates the name of the player.
     */
    private String name;

    /**
     * The exact date when the player completed the puzzle.
     */
    private String date;

    /**
     * The number of steps made by the player.
     */
    private int steps;

    @Override
    public String toString() {
        return name+": steps: "+steps+", date of victory: "+date;
    }
}
