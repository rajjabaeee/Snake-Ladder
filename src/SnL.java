/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 8
 *
 * Members  :
 * 1. 5026231222 - Khayel Josh Yunadi Sitorus
 * 2. 5026231224 - Razza Ibrahmwibowo Muktiadi
 * 3. 5026231043 - Sandfree Mozera Marpaung
 * ------------------------------------------------------
 */

import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class SnL {

    //states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;

    //constructor
    public SnL (int size){
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players= new ArrayList<Player>();
        this.gameStatus = 0;
        this.currentTurn = -1;

    }

    public void initiateGame(){
        int [][] ladders =
                {    {2, 23},
                        {8, 34},
                        {20, 77},
                        {32,68},
                        {41, 79},
                        {74, 88},
                        {82, 100},
                        {85, 95}
                };
        setLadders(ladders);
        int [][] snakes =
                {    {47, 5},
                        {29, 9},
                        {38, 15},
                        {97,25},
                        {53, 33},
                        {92, 70},
                        {86, 54},
                        {97, 25}
                };
        setSnakes(snakes);

    }

    public Player getTurn() {
        this.currentTurn = (this.currentTurn + 1) % this.players.size();
        return this.players.get(this.currentTurn);
    }
    //setter methods
    public void setSizeBoard(int size){
        this.boardSize = size;
    }
    public void addPlayer(Player p){
        this.players.add(p);
    }
    public void setLadders(int[][] ladders){
        int s = ladders.length;
        for(int i = 0; i < s; i++){
            this.ladders.add(new Ladder(ladders[i][0],ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes)
    {
        int s = snakes.length;
        for(int i = 0; i < s; i++){
            this.snakes.add(new Snake(snakes[i][0],snakes[i][1]));
        }
    }

    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }

    public int getGameStatus(){
        return this.gameStatus;
    }
    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        initiateGame();

        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 4; i++) {
            System.out.println("Enter player" + i + " Name:");
            String playerName = sc.nextLine();
            addPlayer(new Player(playerName));
        }


        Player nowPlaying;
        do{
            File file1 = new File("SuaraDadu.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file1);
            Clip clip1 = AudioSystem.getClip();

            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: "+ nowPlaying.getName()+" the current position is "+nowPlaying.getPosition());
            System.out.println(nowPlaying.getName()+" it's your turn, please press enter to roll dice");

            String input= sc.nextLine();
            clip1.open(audioStream);
            clip1.start();
            Thread.sleep(3000);

            int x = 0;
            if (input.isEmpty()){
                x = nowPlaying.rollDice();
                System.out.println(nowPlaying.getName()+ " is rolling dice and get number: "+x);
                movePlayer(nowPlaying, x);
                System.out.println(nowPlaying.getName()+ " new position is "+ nowPlaying.getPosition());
                if(x == 6){
                    Scanner scan = new Scanner(System.in);
                    File file2 = new File("double.wav");
                    AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
                    Clip clip2 = AudioSystem.getClip();
                    System.out.print("Double! Roll the dice once again!");
                    clip2.open(audioStream2);
                    clip2.start();
                    Thread.sleep(1000);
                    String enter = scan.nextLine();
                    if (enter.equals("")){
                        clip1.setFramePosition(0);
                        clip1.start();
                        Thread.sleep(3000);
                        x = nowPlaying.rollDice();

                        System.out.println(nowPlaying.getName()+ " is rolling dice and get number: "+x);
                        movePlayer(nowPlaying, x);
                        System.out.println(nowPlaying.getName()+ " new position is "+ nowPlaying.getPosition());
                    }

                }
            }


        } while(getGameStatus()!=2);

        System.out.println("The Game is Over, the winner is: "+nowPlaying.getName());
    }

    public void movePlayer(Player p, int x){
        this.gameStatus=1;
        p.moveAround(x, this.boardSize);
        for(Ladder l: this.ladders){
            if(l.getFromPosition() == p.getPosition()){
                p.setPosition(l.getToPosition());
                System.out.println(p.getName()+" got ladder so jumps to "+p.getPosition());
            }
        }

        for(Snake s: this.snakes){
            if(s.getHead() == p.getPosition()){
                p.setPosition(s.getTail());
                System.out.println(p.getName()+" got snake so slide down to "+p.getPosition());
            }
        }

        if (p.getPosition()==this.boardSize){
            this.gameStatus=2;
        }
    }
}



