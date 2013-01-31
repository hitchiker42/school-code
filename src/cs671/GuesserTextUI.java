//For the guesser, might need to use g, and might need to call initalizer while parsing
//command line arguements
package cs671;
import java.io.*;//For now, but simplify to only classes I need
/*Main Text Interfact to guesser program*/
class GuesserTextUI {
  BufferedReader input;
  PrintWriter output;
  Guesser<?> guesser;
  String question,answer;
  GuesserTextUI(Guesser<?> g){
    this.output=new PrintWriter(new OutputStreamWriter(System.out),true);
    this.input=new BufferedReader(new InputStreamReader(System.in));
    this.guesser=g;
  }
  GuesserTextUI(Guesser<?> g, Reader input, Writer output){
    this.output=new PrintWriter(output);
    this.input=new BufferedReader(input);
    this.guesser=g;
  }
  public int play(){
    boolean play=true;
    int games=0;
    //Main Loop Goes here
    while (play==true){
      guesser.initalize();
      while (guesser.hasSolved() != true ){
        question=guesser.makeQuestion();
        output.println(question);
        try{
          answer=input.readLine();
        } catch (IOException ex){
        }
        //insure input is cast to a string
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
      }
      String number=guesser.getSecret().toString(); //need to make generic
      output.println(String.format("Your number was %s",number));
      games++;
      output.println("Play Again?(y/n)");
      try{
        answer=input.readLine();
      } catch (IOException ex){
      }
      if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")){
        play=true;
      } else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")){
        play=false;
      }
    }
    return games;
  }
  static void help() {
    //How to use the Program, prints on -h, --help or no args
    String usage="Usage: TextUI -hilo min max\n"
      +"or: TextUI -liar #lies name1 [name2 ...]\n"
      +"or: TextUI -liar #lies -file filename #names\n"
      +"Options: -h,--help print this help and exit\n";
    System.out.println(usage);
    return;
    }
  public static void main(String [] args) throws IOException{
    //Test this bit, I need to be surce this is the right way to do this
    if (args.length <= 0 || args[0].equals("-h") || args[0].equals("--help")){
      help();
    } else if (args[0].equals("-liar")){
    //liar cases
    //something= args[1]
    //something= args[2]
    //test for some option
    //GuesserTextUI game=new GuesserTextUI(new Liar());
    // game.play();
    return; //temp till I make Liar
    } else if (args[0].equals("-hilo")){
      if(args.length < 2){
        help();
      } else try{
          int min= new Integer(args[1]);
          int max= new Integer(args[2]);
          GuesserTextUI game=new GuesserTextUI(new HiLo(min,max));
          game.play();
        } catch(NumberFormatException ex){
          System.out.println("Arguments were not ints");
        }
    }
    else {
      System.out.println("ERROR");
      System.exit(1);
    }
  }
}