import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Console;

public class Project {
    public static String[] list={"tehran", "pizza", "banana", "new york", "advanced programming", "michael jordan",
            "lionel messi", "apple", "macaroni", "university", "intel", "kitten", "python", "java",
            "data structures", "algorithm", "assembly", "basketball", "hockey", "leader", "javascript",
            "toronto", "united states of america", "psychology", "chemistry", "breaking bad", "physics",
            "abstract classes", "linux kernel", "january", "march", "time travel", "twitter", "instagram",
            "dog breeds", "strawberry", "snow", "game of thrones", "batman", "ronaldo", "soccer",
            "hamburger", "italy", "greece", "albert einstein", "hangman", "clubhouse", "call of duty",
            "science", "theory of languages and automata"};
    //
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void clearScr(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static class user{
        String username;
        String pass;
        int score=0;
        user(){
        }
        public void setUsername (String username){
            this.username = username;
        }
        public void setPass (String pass){
            this.pass = pass;
        }
    }
   static int counter(String str) {
        int count = str.length();
        for(int i = 0 ; i< str.length() ; ++i){
            if(str.charAt(i)== ' ')
                --count;
        }
        return count;
   }
   static void board(user[] users){
      int n = users.length;
      user u= new user();
      for(int i = 0 ; i < n ; ++i){
          for(int j = 1; j < n-i;++j){
              if(users[j-1].score<users[j].score){
                  u=users[j-1];
                  users[j-1]=users[j];
                  users[j]=u;
              }
          }
      }
      for(int i = 0;i<n;++i){
        System.out.println(ANSI_BLUE+users[i].username+ANSI_RESET+ANSI_PURPLE+"____________"+ANSI_RESET+ANSI_BLUE+users[i].score+ANSI_RESET);
      }
    }

    static void Signup(user u){
       Scanner input = new Scanner(System.in);
        System.out.println(ANSI_YELLOW+"username:"+ANSI_RESET);
        String uname=input.nextLine();
        u.setUsername(uname);
        Console co=System.console();
        System.out.println(ANSI_YELLOW+"password:"+ANSI_RESET);
        char[] cha=co.readPassword();
        String pas = String.valueOf(cha);
        String regex = "[\\w!@#$%&*]{6}+[\\w!@#$%&*]*";
        Pattern pat = Pattern.compile(regex);
        Matcher match = pat.matcher(pas);
        boolean bpas=match.matches();
        clearScr();
        if(bpas){
            int ch=0;
            for(int i = 0;i<pas.length();++i){
                if(pas.charAt(i)=='!'||pas.charAt(i)=='@'||pas.charAt(i)=='#'||pas.charAt(i)=='$'||pas.charAt(i)=='%'||pas.charAt(i)=='&'||pas.charAt(i)=='*')
                    ++ch;
            }
            if(ch>1||ch==0)
                bpas=false;
        }
        if(bpas){
            u.setPass(pas);
        }
        else{
            System.out.println(ANSI_RED+"Invalid password! try again."+ANSI_RESET);
            //clearScr();
            Signup(u);
        }
    }
    public static user[] eq(user[] arr1,int size){
        user[] arr = new user[size + 1];
        System.arraycopy(arr1, 0, arr,0, size);
        return arr;
    }
    static user Login(user[] arr){
        clearScr();
        Scanner input = new Scanner(System.in);
        boolean exist = false;
        System.out.println(ANSI_YELLOW+"username:"+ANSI_RESET);
        String un= input.nextLine();
        int nom=0;
        for(int i = 0 ; i < arr.length; ++i){
              if(arr[i].username.endsWith(un)){
                exist=true;
                nom=i;
            }
        }
        if(exist)
           return arr[nom];
        else{
            System.out.println(ANSI_RED+"Enter correct username!"+ANSI_RESET);
            return Login(arr);
        }
    }
    static class Game{
        user gamer;
        int wrong=0;
        String guesses="";
        boolean usehelp=false;
        Game(){
        }
        public String help(String word,String hidden1){
            System.out.println(ANSI_BLUE+"Do you want to use help? 1. Yes / 2. No"+ANSI_RESET);
            Scanner input=new Scanner(System.in);
            int h=input.nextInt();
            if(h==1) {
                int r = (int) (Math.random() * (word.length() - 1));
                String hidden = "";
                for (int j = 0; j < word.length(); ++j) {
                    if (word.charAt(j) == ' ')
                        hidden += " ";
                    else if (hidden1.charAt(j) != '-' && hidden1.charAt(j) != ' ')
                        hidden += word.charAt(j);
                    else if (word.charAt(h) == word.charAt(j))
                        hidden += word.charAt(h);
                    else
                        hidden += "-";
                }
                clearScr();
                System.out.println(ANSI_WHITE+hidden+ANSI_RESET);
                hidden1 = hidden;
                this.gamer.score-=10;
                clearScr();
                return hidden1;
            }
            else{
                clearScr();
                return hidden1;
            }
        }
        public void print(String gs){
            System.out.print(ANSI_YELLOW+"All entered letters:"+ANSI_RESET);
            for(int i = 0 ; i < gs.length();++i)
                System.out.print(ANSI_WHITE+gs.charAt(i)+" "+ANSI_RESET);
            System.out.println();
        }
        public void game(user gamer){
            this.gamer=gamer;
            String rand=list[(int)(Math.random()*(list.length-1))];
            Scanner input=new Scanner(System.in);
            String word = rand;
            String hidden1=word;
            for(int i=0 ; i<word.length() ;++i){
                if(word.charAt(i)==' '){}
                else
                   hidden1= hidden1.replace(hidden1.charAt(i),'-');
            }
            while((this.wrong<7 && hidden1.contains("-"))||(counter(word)>9 && this.wrong<14 && hidden1.contains("-"))){
                if(!this.usehelp&&gamer.score>=10)
                    hidden1=this.help(word,hidden1);

                System.out.println(ANSI_BLUE+"Guess a letter"+ANSI_RESET);
                System.out.println(ANSI_WHITE+hidden1+ANSI_RESET);
                String guess=input.nextLine();
                //clearScr();
                if(this.guesses.contains(guess)){
                    System.out.println(ANSI_RED+"This letter has already guessed"+ANSI_RESET);
                    System.out.println(ANSI_BLUE+"Guess a letter"+ANSI_RESET);
                    System.out.println(ANSI_WHITE+hidden1+ANSI_RESET);
                    this.print(this.guesses);
                    guess=input.nextLine();
                    clearScr();
                }
                if(word.contains(guess)){
                      String hidden = "";
                        for(int j = 0 ; j < word.length();++j){
                            if(word.charAt(j)==' ')
                                hidden+=" ";
                            else if(hidden1.charAt(j)!='-'&& hidden1.charAt(j)!=' ')
                                hidden+=word.charAt(j);
                            else if(guess.charAt(0)==word.charAt(j))
                                hidden+=guess.charAt(0);
                            else
                                hidden+="-";
                        }
                        clearScr();
                        System.out.println(ANSI_WHITE+hidden+ANSI_RESET);
                        hidden1=hidden;
                        if(hidden1.equals(word)){
                        gamer.score+=5;
                        System.out.println(ANSI_PURPLE+"Well, you guessed the word"+ANSI_RESET);
                    }
                }
                else{
                    clearScr();
                    ++this.wrong;
                    System.out.println(ANSI_RED+"Wrong guess"+ANSI_RESET);
                    if(counter(word)>9 && this.wrong%2==0)
                        manimage((this.wrong)/2);
                    else if(counter(word)<=9)
                        manimage(this.wrong);
                }
                this.guesses+=guess;
                this.print(this.guesses);

            }
        }
    }

    static void afterlogin(user[] arr,user un){
        System.out.println(ANSI_GREEN+"1. start game / 2. show leaderboard "+ANSI_RESET);
        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();
        if (choose == 2) {
            clearScr();
            board(arr);
        } 
        else if (choose == 1) {
            clearScr();
            Game bazi=new Game();
            bazi.game(un);
        }
    }

    public static void manimage(int miss){
        switch (miss){
            case 1:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                for (int i = 0 ; i<4;++i)
                    System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 2:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                for (int i = 0 ; i<3;++i)
                    System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 3:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  /"+ANSI_RESET);
                for (int i = 0 ; i<2;++i)
                    System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 4:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  /|"+ANSI_RESET);
                for (int i = 0 ; i<2;++i)
                    System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 5:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  /|\\"+ANSI_RESET);
                for (int i = 0 ; i<2;++i)
                    System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 6:{
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  /|\\"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  / "+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
                break;
            }
            case 7:{
                System.out.println(ANSI_RED+"GAME OVER!"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"----"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   |"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|   O"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  /|\\"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|  / \\"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET);
            }
        }
    }
    public static void main (String args[]){
        int size=0;
        user[] arr=new user[0];
        boolean r=true;
        while(r){
            System.out.println(ANSI_GREEN+"1.signup / 2.login / 3.close"+ANSI_RESET);
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            clearScr();
            if(n==1){
                user u = new user();
                Signup(u);
                arr=eq(arr,size);
                ++size;
                arr[size-1]=u;
            }
            else if(n==2) {
                user un = Login(arr);//bazikon
                afterlogin(arr, un);
            }
            else if(n==3)
                r=false;
        }
    }
}
