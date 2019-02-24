/**
 * Created by Rishabh Nigam on 2/26/2018.
 *
 */
import java.util.*;

public class BlackBox {
    public static char box[][]; // The matrix for the game.
    public static char guess[][];// The matrix for the guesses.
    public static int size;       // to store the number of rows and columns.
    public static int numball;
    public static int numlink;
    public static boolean end;
    public static int score;
    public static int high_score=100;
    public static boolean goOn;

    /**
     * The default constructor which places default values to the class variables
     */
    public BlackBox()
    {
        this.box=new char[0][0];
        this.guess=new char[0][0];
        this.size=0;
        this.numball=0;
        this.numlink=0;
        this.end=false;
        this.score=0;
        this.goOn=true;
    }
    /**
     * The parameterized constructor which places values to the class variables
     */
    public BlackBox(int size, int numball, int numlink, boolean end, int score)
    {
        this.box=new char[size][size];
        this.guess=new char[size][size];
        this.size=size;
        this.numball=numball;
        this.numlink=numlink;
        this.end=end;
        this.score=score;
        this.goOn=true;
    }
    /**
     * The main function takes input for the difficulty level and call the functions initialize() and
     * playgame()
     */
    public static void main(String[] args) {
        //Todo:start the game print the welcome message and ask for correct difficulty level.
        //Todo: end the game if the user says quit.
        //Todo:call the functions initialize and playgame()
        // Todo: take care of high score
        BlackBox box = new BlackBox();
        Scanner s=new Scanner(System.in);
        int game=0;

        while(goOn){
            System.out.println("Welcome to the Blackbox Game. Please choose the difficulty level:easy/medium/hard or quit to end the game:");
            String choice=s.nextLine();

            if (!choice.toLowerCase().equals("quit")){
                if(choice.toLowerCase().equals("easy")){
                    box.size=7;
                    box.initialize();
                    box.playgame();
                }
                else if (choice.toLowerCase().equals("medium")){
                    box.size=9;
                    box.initialize();
                    box.playgame();
                }
                else if (choice.toLowerCase().equals("hard")){
                    box.size=10;
                    box.initialize();
                    box.playgame();
                }
            }
            else{
                if(game>0){
                    if(box.high_score>box.score){
                        box.high_score=box.score;
                    }
                    System.out.println("High-Score-->"+Integer.toString(box.high_score));
                }
                else{
                    System.out.println("No High-Score.");
                }
                System.out.println("Thank you for playing!");
                goOn=false;
            }
            game++;

        }
    }
    /**
     * The initialize funtion
     */
    public void initialize() {
        //Todo:initialise the Box[][]
        //Todo: place the 'X' and the '#'
        // Todo: place 3 '0''s randomly.

        Random r = new Random();
        this.box = new char[this.size][this.size];
        this.guess= new char[this.size][this.size];
        this.goOn=true;
        this.end=false;
        this.numlink=0;
        this.numball=0;
        this.score=0;

        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                this.box[i][j]=' ';
                this.guess[i][j]=' ';
            }
        }
        for(int i=0;i<size;i++){                  // assigning X and #
            if (i!=0 && i!=size-1){
                this.box[0][i]='#';
                this.box[size-1][i]='#';
                this.box[i][0]='#';
                this.box[i][size-1]='#';
                this.guess[0][i]='#';
                this.guess[size-1][i]='#';
                this.guess[i][0]='#';
                this.guess[i][size-1]='#';
            }
            else{
                this.box[0][i]='X';
                this.box[size-1][i]='X';
                this.guess[0][i]='X';
                this.guess[size-1][i]='X';
            }
        }

        for (int i=0;i<3;i++){
            int x=r.nextInt(size-1);
            int y=r.nextInt(size-1);
            if(x==0||y==0){
                i--;
            }
            else if(this.box[x][y]=='0'){
                i--;
            }
            else{
                this.box[x][y]='0';
                this.guess[x][y]='0';
            }
        }
    }
    /**
     * The printbox funtion prints out the matrix in a particular format as given in the handout.
     */
    public static void printbox() {
        //Todo:print the box in the correct order

        for (int i=0;i<size;i++){
            System.out.printf("  %d",i+1);
        }
        System.out.println(" ");

        for (int i=0;i<size;i++){
            System.out.print("===");
        }
        System.out.println(" ");

        for (int i=0;i<size;i++){
            System.out.printf("%d"+"|",i+1);
            for (int j=0;j<size;j++){
                if (!end && guess[i][j]=='0'){
                    System.out.print("*"+" |");
                }
                else{
                    System.out.print(guess[i][j]+" |");
                }
            }
            System.out.println(" ");
        }

        for (int i=0;i<size;i++) {
            System.out.print("===");
        }
        System.out.println(" ");
        // for  5*5 example
        /* 1  2  3  4  5  6  7
         ======================
        1|X |# |# |# |# |# |X |
        2|# |  |  |  |  |  |# |
        3|# |  |  |  |  |  |# |
        4|# |  |  |  |  |  |# |
        5|# |  |  |  |  |  |# |
        6|# |  |  |  |  |  |# |
        7|X |# |# |# |# |# |X |
         ======================*/
        //place the guesses as the come and print the balls when the player enter sumbit.
    }
    /**
     * The playgame funtion opens the first cell and is the main controller for the game. It calls various function when needed.
     */
    public static void playgame() {
        //Todo:Take input of a guess or hint from the user.
        //Todo:Check for valid input
        //Todo:call required functions
        //Todo:keep tab on score.

        Scanner s = new Scanner(System.in);

        while (!end){
            printbox();
            System.out.println("Type the new coordinates (row,column) to play the next step or type submit/quit to end the game:");
            String input=s.nextLine();
            if(!input.toLowerCase().equals("submit") && !input.toLowerCase().equals("quit")){
                int coordX=Integer.parseInt(input.substring(0,input.indexOf(',')))-1;
                int coordY=Integer.parseInt(input.substring(input.indexOf(',')+1,input.length()))-1;
                check(coordX,coordY);
                score++;

            }

            if(input.toLowerCase().equals("submit")&&numball<3){
                System.out.println("Place all guesses to submit!");
            }
            else if(input.toLowerCase().equals("submit")&&numball==3){

                int success=2;
                for (int i=1;i<size-1;i++) {
                    for (int j = 1; j < size-1; j++) {
                        if(guess[i][j]!=box[i][j]){
                            success=0;
                            break;
                        }
                        else{
                            success=1;
                        }
                    }
                    if(success==0){
                        break;
                    }
                }
                if(success==1){
                    printbox();
                    System.out.println("Success!\nScore-->"+Integer.toString(score));
                }
                else{
                    printbox();
                    for(int i=0;i<size;i++){
                        for(int j=0;j<size;j++){
                            if(box[i][j]=='0'){
                                System.out.println("Location of ball: ("+Integer.toString(i)+","+Integer.toString(j)+")");
                            }
                        }
                    }
                    System.out.println("AWW almost there!");
                }
                end=true;
            }

            if(input.toLowerCase().equals("quit")){
                if(high_score>score){
                    high_score=score;
                }
                System.out.println("High-Score-->"+Integer.toString(high_score));
                System.out.println("Thank you for playing!");
                goOn=false;
                end=true;
            }

        }




    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Hit (H), Reflection (R) or Divergence(#num)
     *
     */
    public static void check(int i,int j) {
        //Todo:place a guess when the input of i and j are valid
        //Todo:Check for a Hit
        //Todo:Check for a reflection
        //Todo:Check for a bounce
        //Todo:Print a statement telling the user they cannot place a fourth ball.

        if(i!=0 &&i!=size-1 &&j!=0 &&j!=size-1){
            guess[i][j]='0';
            if(numball<3){
                numball++;
            }
        }
        else if((i==0&&j!=0&&j!=size-1) ||(i==size-1&&j!=0&&j!=size-1) ||(j==0&&i!=0&&i!=size-1) ||(j==size-1&&i!=0&&i!=size-1)){
            if(reflectionCheck(i,j)){
                guess[i][j]='R';

            }

            else if(deflectionCheck(i,j)){
                guess[i][j]=(char)(numlink+49);
                numlink+=1;
            }

            else if(hitcheck(i,j)){
                guess[i][j]='H';
            }
            else{
                guess[i][j]=(char)(numlink+49);
                if(i==0){
                    guess[size-1][j]=(char)(numlink+49);
                }
                else if(i==size-1){
                    guess[0][j]=(char)(numlink+49);
                }
                else if(j==0){
                    guess[i][size-1]=(char)(numlink+49);
                }
                else if(j==size-1){
                    guess[i][0]=(char)(numlink+49);
                }
                numlink+=1;
            }
        }
        else{
            System.out.println("Invalid input! You just gained a point");
        }

        if(numball>3){
            guess[i][j]=' ';
            System.out.println("Cannot place more than three markers! You just gained a point! Type submit or quit.");
        }


    }
    /**
     * The hitcheck funtion takes in the row and column in the matrix, checks for Hit (H)
     *
     */
    public static boolean hitcheck(int i, int j) {
        //todo: check if the ray causes a HIT as defined in the handout

        if((i==0)&&j!=0&&j!=size-1){
            for(int run=0;run<size;run++){
                if(box[run][j]=='0'){
                    return true;
                }
            }
        }
        else if((i==size-1)&&j!=0&&j!=size-1){
            for(int run=size-1;run>=0;run--){
                if(box[run][j]=='0'){
                    return true;
                }
            }
        }
        else if((j==0)&&i!=0&&i!=size-1) {
            for (int run = 0; run < size; run++) {
                if (box[i][run] == '0') {
                    return true;
                }
            }
        }
        else if((j==size-1)&&i!=0&&i!=size-1){
            for(int run=size-1;run>=0;run--){
                if(box[i][run]=='0'){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Reflection (R)
     *
     */
    public static boolean reflectionCheck(int i,int j) {
        //todo: check if the ray causes a Reflection as defined in the handout

        if((i==0)&&j!=0&&j!=size-1){
            if(box[1][j+1]=='0'||box[1][j-1]=='0'){
                return true;
            }
            else{
                for(int run=0;run<size;run++){
                    if(box[run][j+1]=='0'&&box[run][j-1]=='0'){
                        return true;
                    }
                }
            }
        }
        else if((i==size-1)&&j!=0&&j!=size-1){
            if(box[size-2][j+1]=='0'||box[size-2][j-1]=='0'){
                return true;
            }
            else{
                for(int run=size-1;run>=0;run--){
                    if(box[run][j+1]=='0'&&box[run][j-1]=='0'){
                        return true;
                    }
                }
            }
        }
        else if((j==0)&&i!=0&&i!=size-1) {
            if(box[i+1][1]=='0'||box[i-1][1]=='0'){
                return true;
            }
            else{
                for (int run = 0; run < size; run++) {
                    if (box[i+1][run] == '0' && box[i-1][run]=='0') {
                        return true;
                    }
                }
            }
        }
        else if((j==size-1)&&i!=0&&i!=size-1){
            if(box[i+1][size-2]=='0'||box[i-1][size-2]=='0'){
                return true;
            }
            else{
                for(int run=size-1;run>=0;run--){
                    if(box[i+1][run]=='0' && box[i-1][run]=='0'){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Divergence(#num)
     *
     */
    public static boolean deflectionCheck(int i,int j) {
        //todo: check if the ray causes a Deflection as defined in the handout
        if((i==0)&&j!=0&&j!=size-1){
            for(int run=0;run<size;run++){
                if(box[run][j+1]=='0'){
                    guess[run-1][0]=(char)(numlink+49);
                    return true;
                }
                else if(box[run][j-1]=='0'){
                    guess[run-1][size-1]=(char)(numlink+49);
                    return true;
                }
            }
        }
        else if((i==size-1)&&j!=0&&j!=size-1){
            for(int run=size-1;run>=0;run--){
                if(box[run][j+1]=='0'){
                    guess[run+1][0]=(char)(numlink+49);
                    return true;
                }
                else if(box[run][j-1]=='0'){
                    guess[run+1][size-1]=(char)(numlink+49);
                    return true;
                }
            }
        }
        else if((j==0)&&i!=0&&i!=size-1) {
            for (int run = 0; run < size; run++) {
                if (box[i+1][run] == '0') {
                    guess[0][run-1]=(char)(numlink+49);
                    return true;
                }
                else if(box[i-1][run]=='0'){
                    guess[size-1][run-1]=(char)(numlink+49);
                    return true;
                }
            }
        }
        else if((j==size-1)&&i!=0&&i!=size-1){
            for(int run=size-1;run>=0;run--){
                if(box[i+1][run]=='0'){
                    guess[0][run+1]=(char)(numlink+49);
                    return true;
                }
                else if(box[i-1][run]=='0'){
                    guess[size-1][run+1]=(char)(numlink+49);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * The straightRay funtion takes in the row and column in the matrix, checks for Straight ray
     *
     */
    public static boolean straightRay(int i,int j){
        //todo: check if the ray is a straight ray as defined in the handout
        return false;
    }
    /**
     * The following definitions are the getters and setter functions which have to be implemented
     *
     */
    public char[][] getbox() {
        return this.box;
    }
    public int getscore() {
        return this.score;
    }
    public int getNumball() {
        return this.numball;
    }
    public int getNumlink() {
        return this.numlink;
    }
    public boolean getend() {
        return this.end;
    }
    public void setbox(char box[][]) {
        this.box=box;
    }
    public void setSize(int size){
        this.size=size;
    }
    public void setNumball(int Numball) {
        this.numball=Numball;
    }
    public void setNumlink(char Numlink) {
        this.numlink=Numlink;
    }
    public void setEnd(boolean end) {
        this.end=end;
    }
    public void setScore(int score) {
        this.score=score;
    }
}