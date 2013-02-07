package cs671;
import java.util.*;
import java.util.Collections.*;
import java.util.EnumSet.*;
import java.lang.reflect.*;
  /**
     Liar Class
   */
public class Liar<T> implements Guesser<Liar.Secret<T>>{
  boolean init=false;
  boolean qmade=false;
  boolean solved=false;
  //This is just a macro for myself
  //So its not documented
  static void println(String arg){
    System.out.println(arg);
  }
  LiarTable liarTable;
  ArrayList<T> candidates;
  int items;
  /**
     MaxLies variable
   */
  public final int maxLies;
  /**
     Name variable
   */
  public final String name;
    /**
     Has Solved Method
   */
  public boolean hasSolved(){
    if(init != true){
      throw(new IllegalStateException());
    }
    return liarTable.hasSolved();
  }
    /**
     Progress Method
   */
  public double progress(){
    return(1-(liarTable.progress()/liarTable.total));
  }


  //Type of Secrets, just used an interface to outside
  //All internal manipulations done w/LiarTable
    /**
     Secret Class
   */
  public static class Secret<E> {
    int lies;
    E secret;
    Secret(int lies,E secret){
      this.lies=lies;
      this.secret=secret;
    }
      /**
     Get Lies method
   */
    public int getLies(){
      return lies;
    }
  /**
     Get Secret method
   */
    public E getSecret(){
      return secret;
    }
  /**
     to String method
   */
    public String toString(){
      return String.format("Your object was %s and you told %d lies",secret,lies);//not sure how to format for generics, and should object be name?
    }
  }


  /**
     LiarTable class
     Data structure to hold information about items and lies
     Does all the manipulating and sorting of objects
  */
  class LiarTable {
    TreeMap<Integer,ArrayList<T>> top;
    TreeMap<Integer,ArrayList<T>> bottom;
    TreeMap<Integer,ArrayList<T>> liarList;
    double total;//# of total lies at star
    /**
       LiarTable Constructor
    */
    LiarTable(ArrayList<T> candidates,int lies){
      this.top=new TreeMap<>();
      this.bottom=new TreeMap<>();
      this.top.put(0,new ArrayList<T>(candidates.subList(0,items/2)));
      this.bottom.put(0,new ArrayList<T>(candidates.subList(items/2,items)));
      for (int i=1;i<=lies;i++){
        this.top.put(i,new ArrayList<T>(items/2+items%2));
        this.bottom.put(i,new ArrayList<T>(items/2+items%2));
      }
      this.total=items*lies;
      //println(this.top.toString()+this.bottom.toString()+'\n');
    }

    /**
       Increment lies on top/bottom
    */
    @SuppressWarnings("unchecked")
    void increment(boolean inTop){
      //println("increment\n");
      //might use teritary operator here, but removed for debugging
      TreeMap<Integer,ArrayList<T>> liarList;
      if (inTop==true){
        liarList=bottom;
      } else {
        liarList=top;
      }
      //println(liarList.toString());
      ArrayList<T> temp;
      //do stuff
      for (int i=maxLies;i>0;i--){
        try{
          //warnings suppressed for this,we're casting a clone of
          //an ArrayList<T> into an ArrayList<T>, so it should
          //always work, not sure why its necessary but clone
          //just returns a plain object
          temp=(ArrayList<T>)liarList.get(i-1).clone();
          liarList.put(i,temp);
        } catch(ClassCastException ex){
        }
      }
      //temp=liarList.get(0);...keeping just in case
      liarList.get(0).clear();
      //println(top.toString());
      //println(bottom.toString());
    }

    /**
       Swap part of top and bottom w/each other
    */
    void swap(){
      //move the last n/2 items from top to temp array
      //move the first n/2+n%2 items from bottom to temp array
      //merge top and bottom, this is new top
      //temp array is new bottom
      //println("swap\n");
      //println("before\n"+top.toString()+'\n'+bottom.toString());
      ArrayList<T> up;
      ArrayList<T> down;
      ArrayList<T> temp;
      boolean up_put=false;
      for (int i=0;i<=maxLies;i++){
        up=top.get(i);
        down=bottom.get(i);
        temp=new ArrayList<>(up.size());
        //with temp variables, Should this have a new namespace?
        if (down.size()==1){
          up.addAll(down);
          down.clear();
        } else if (up.size()==1 && up_put==false){
          up_put=true;
          continue;
        } else {
          temp.addAll(up.subList(up.size()/2,up.size()));
          up.subList(up.size()/2,up.size()).clear();
          temp.addAll(down.subList(0,(down.size()/2+down.size()%2)));
          down.subList(0,(down.size()/2+down.size()%2)).clear();
          up.addAll(down);
        }
        //change real variables
        top.put(i,up);
        bottom.put(i,temp);
      }
      //println("After\n"+top.toString()+'\n'+bottom.toString());
    }

    /**
       return progress as a double giving the total number
       of possible lies left, this is compaired w/the value
       total ,which gives total lies ever possible, to get
       the actual progress value.
    */
    double progress(){
      //sum lies left per object over all objects
      double sum=0;
      for(int i=0;i<=maxLies;i++){
        sum += top.get(i).size()*(maxLies-i);
        sum += bottom.get(i).size()*(maxLies-i);
      }
      return sum;
    }

    /**
       if only 1 item left than we've solved the problem
       if not keep going
    */
    boolean hasSolved(){
      //println("hasSolved\n");
      int sum=top.get(0).size()+bottom.get(0).size();
      //println(String.format("sum:%d",sum));
      for (int i=1;i<=maxLies;i++){
        if (sum>1){
          return false;
        }
        sum+=(top.get(i).size()+bottom.get(i).size());
      }
      if (sum>1){
        return false;
      } else if (sum==1){
        solved=true;
        return true;
      } else {
        println("What the hell happened");
        System.exit(89);
        return true;
      }
    }
  }

  //Constructor
  /**
     Liar Constructor
   */
  public Liar(Set<? extends T> candidates, int lies, String name){
    items=candidates.size();
    try{
      //check if candidates is empty
      //throw new IllegalArgumentException("empty candidates list");
      //check if lies <0
      //throw new IllegalArgumentException("Can't Have negitive lies");
      //check if name == null
      //throw new IllegalArgumentException("Unspecified Name");
      } catch (IllegalArgumentException ex){
      //println(ex.getMessage());
    }
    this.maxLies=lies;
    this.name=name;
    this.candidates=new ArrayList<>(candidates);
    this.liarTable=new LiarTable(this.candidates,lies);
  }
  //Get Secret
    /**
     Get Secret Method
   */
  public Secret<T> getSecret(){
    if (init!=true || solved!=true){
      throw(new IllegalStateException());
    }
    Secret<T> ans=null;
    for (int i=0;i<=maxLies;i++){
      if (!liarTable.top.get(i).isEmpty()){
        ans=new Secret<>(i,liarTable.top.get(i).get(0));
        break;
        }
      if (!liarTable.bottom.get(i).isEmpty()){
        ans=new Secret<>(i,liarTable.bottom.get(i).get(0));
        break;
        }
    }
    return(ans);
  }

  /**
     Initialize method
   */
  public String initialize(){
    //reset liarTable
    this.liarTable=new LiarTable(this.candidates,maxLies);
    init=true;
    return "";
  }

  /**
     make Question method
   */
  public String makeQuestion(){
    if (init!=true || qmade == true){
      throw(new IllegalStateException());
    }
    StringBuilder question = new StringBuilder(String.format("Is your %s one of: ",name));
    for (ArrayList<T> i:liarTable.top.values()){
      Iterator<T> q = i.iterator();
      while (q.hasNext()){
        question.append(q.next());
        question.append(' ');
      }
    }
    question.deleteCharAt(question.length()-1);//length-1?
    question.append('?');
    qmade=true;
    return question.toString();
  }
  /**
     no method
   */
  public void no(){
    if (init!=true || qmade!=true){
      throw(new IllegalStateException());
    }
    //add 1 lie to each entry in top and remove any with lies>maxlies
    qmade=false;
    liarTable.increment(false);
    liarTable.swap();
  }

  /**
     yes method
   */
  public void yes(){
    if (init!=true || qmade!=true){
      throw(new IllegalStateException());
    }
    //add 1 lie to each entry in bottom and remove any with lies>maxlies
    liarTable.increment(true);
    liarTable.swap();
    qmade=false;
  }
  /**
     select Candidates method
   */
  public int selectCandidates(int n){
    if (n<=0){
      return -1;
    } else if (n>=candidates.size()){
      return candidates.size();
    }
    //Random rand=new Random();
    Collections.shuffle(candidates);
    candidates=new ArrayList<T>(candidates.subList(0,n));
    items=n;
    return n;
  }
}
