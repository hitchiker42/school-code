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
        this.output=new PrintWriter(System.out);
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
            while (guesser.hasSolved() != true){
                question=guesser.makeQuestion();
                try{
                    answer=input.readLine();
                } catch (IOException ex){
                }
                //assume input is a defined String
                //need to put in checks or somehow insure it is
                if (answer == "y" || answer.toLowerCase() == "yes"){
                    guesser.yes();
                } else if (answer == "n" || answer.toLowerCase() == "no"){
                    guesser.no();
                }
                else {
                    //prompt for another answer
                }
            }
            games++;
            output.println("Play Again?(y/n)");
            try{
                answer=input.readLine();
            } catch (IOException ex){
            }
            if (answer == "y" || answer.toLowerCase() == "yes"){
                play=true;
            } else if (answer == "n" || answer.toLowerCase() == "no"){
                play=false;
            }
        }
        return games;
    }
    public static void main(String [] args) throws IOException{
        //Test this bit, I need to be surce this is the right way to do this
        //if we can, use getopt java port
        if (args[0] == "-h" || args[0] == "--help" || args[0] == null) {
            new Object() {
                    {
                        //How to use the Program, prints on -h, --help or no args
                        String usage="Usage: TextUI -hilo min max\n"
                            +"or: TextUI -liar #lies name1 [name2 ...]\n"
                            +"or: TextUI -liar #lies -file filename #names\n"
                            +"Options: -h,--help print this help and exit\n";
                        System.out.println(usage);
                        System.exit(0);
                    }
                };
        } else if (args[0] == "-liar"){
            //liar cases
            //something= args[1]
            //something= args[2]
            //test for some option
            //GuesserTextUI game=new GuesserTextUI(new Liar());
            // game.play();
            System.exit(0); //temp till I make Liar
        } else if (args[0] == "-hilo"){
            //HiLo cases, test for args[1],args[2]
            int min= new Integer(args[1]);
            int max= new Integer(args[2]);
            GuesserTextUI game=new GuesserTextUI(new HiLo(min,max));
            game.play();
        } else {
            //Some Error
        }
        return;
    }
}