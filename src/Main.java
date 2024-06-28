import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 8
 * Members  :
 * 1. 5026231222 - Khayel Josh Yunadi Sitorus
 * 2. 5026231224 - Razza Ibrahmwibowo Muktiadi
 * 3. 5026231043 - Sandfree Mozera Marpaung
 * ------------------------------------------------------
 */

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        SnL g1 = new SnL(100);
        g1.play();

    }
}
