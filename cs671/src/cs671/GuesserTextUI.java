//For the guesser, might need to use g, and might need to call initalizer while parsing
//command line arguements
package cs671;
import java.io.*;
import java.util.*;//For now, but simplify to only classes I need
/*Main Text Interfact to guesser program*/
/**
   GuesserTextUI Class
 */
public class GuesserTextUI {
  //I'm lazy and this makes printing things eaiser
  static void println(String arg){
    System.out.println(arg);
  }

  BufferedReader input;
  PrintWriter output;
  Guesser<?> guesser;
  String question,answer;
  /**
     GuesserTextUI initalizer
   */
  public GuesserTextUI(Guesser<?> g){
    this.output=new PrintWriter(new OutputStreamWriter(System.out),true);
    this.input=new BufferedReader(new InputStreamReader(System.in));
    this.guesser=g;
  }
  public GuesserTextUI(Guesser<?> g, Reader input, Writer output){
    this.output=new PrintWriter(output);
    this.input=new BufferedReader(input);//may change
    this.guesser=g;
  }
  /**
     Play method
   */
  public int play(){
    boolean play=true;
    int games=0;
    //Main Loop Goes here
    while (play==true){
      do {
        guesser.initialize();
        question=guesser.makeQuestion();
        output.println(question);
        try{
          answer=input.readLine();
        } catch (IOException ex){
          println("Input somehow failed");
          return 3;
        }
        if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")){
          guesser.yes();
        } else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")){
          guesser.no();
        }
        else {
          //prompt for another answer
          continue;
        }
        double progress=guesser.progress();
        output.println(String.format("I am %.0f %% complete",100*progress));
      }       while (guesser.hasSolved() != true );
      String number=guesser.getSecret().toString(); //need to make generic
      output.println(String.format("Your number was %s",number));
      games++;
      output.println("Play Again?(y/n)");
      try{
        answer=input.readLine();
      } catch (IOException ex){
        println("Input somehow failed");
        return 3;
      }
      if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")){
        play=true;
      } else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")){
        play=false;
      }
    }
    //check API if i have to return games like this do so
    //but if i can print games and return 0
    output.println(String.format("You played %d games",games));
    return games;
  }
  /**
     help method 
     How to use the Program, prints on -h, --help or no args
  */
  static void help() {
    String usage="Usage: TextUI -hilo min max\n"
      +"or: TextUI -liar #lies name1 [name2 ...]\n"
      +"or: TextUI -liar #lies -file filename #names\n"
      +"Options: -h,--help print this help and exit\n";
    System.out.println(usage);
    return;
  }
  /**
     Main method
   */
  public static void main(String [] args) throws IOException{
    //Test this bit, I need to be surce this is the right way to do this
    if (args.length <= 0 || args[0].equals("-h") || args[0].equals("--help")){
      help();
    } else if (args[0].equals("-liar")){
      //liar cases
      if (args.length<3){
        System.out.println("Not Enough Arguements");
        return;
      }
      HashSet<String> candidates = new HashSet<>();
      /*I can think of no reasonably implimentable means of finding
        the type of the lies, so they are all going to be strings
      */
      String name="String";
      //using a file
      if (args[2].equals("-file")){
        if (args.length != 5){
          println("If using a file you need 5 arguments of the form:\n"+" -liar #lies -file filename #names");
          return;
        }
        try{
          //println("using A file");
          int lies= new Integer(args[1]);
          File file=new File(args[3]);
          assert(file.exists()==true);
          //println(file.toString());
          BufferedReader fileRead=new BufferedReader(new FileReader(file));
          String word=fileRead.readLine();
          while (word!=null){
            candidates.add(word);
            word=fileRead.readLine();
          }
          Liar liar=new Liar<>(candidates,lies,name);
          try{
            int n=liar.selectCandidates(new Integer(args[4]));
            if(n<0){
              return;
            }
          } catch(NumberFormatException ex){
            println("# of canditates to use not an int");
            return;
          }
          GuesserTextUI game=new GuesserTextUI(liar);
          game.play();
        }  catch(FileNotFoundException ex){
          println("File not found");
          return;
        } catch(IOException ex){
          println("IO exception");
          return;
        }
      } else{
        //using stdout
        try{
          int lies= new Integer(args[1]);
          for (int i=2;i<args.length;i++){
            candidates.add(args[i]);
          }
          //test for some option
          println(candidates.toString());
          GuesserTextUI game=new GuesserTextUI(new Liar<>(candidates,lies,name));
          assert(!game.equals(null));
          game.play();
        } catch(NumberFormatException ex){
          println("# of lies not an int");
          return;
        }
      }
    } else if (args[0].equals("-hilo")){
      if(args.length < 2){
        help();
      } else try{
          int min= new Integer(args[1]);
          int max= new Integer(args[2]);
          GuesserTextUI game=new GuesserTextUI(new HiLo(min,max));
          game.play();
        } catch(NumberFormatException ex){
          println("Arguments were not ints");
          return;
        }
    }
    else {
      println("ERROR");
      return;
    }
  }
}