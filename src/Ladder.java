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
public class Ladder{
    int fromPosition;
    int toPosition;

    Ladder(int from, int to){
        this.fromPosition = from;
        this.toPosition = to;
    }

    void setFromPosition(int from){
        this.fromPosition =  from;

    }

    void setToPosition(int to){
        this.toPosition = to;

    }

    int getFromPosition(){
        return fromPosition;

    }
    int getToPosition(){
        return toPosition;

    }
}