//Main Thing to work is implimenting Annotation stuff
//Trying Lisp-esq syntax for closing braces
package cs671;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
//Testable is for classes
//@Test is for methods

/**
 *Test runner. Instances of this class are used to run the tests
 *methods found in classes that implement Testable.
 *@author Tucker DiNapoli using api by Michel Charpentier
 */
public class Tester implements Runnable{
  ArrayList<Class<? extends Testable>> classes;
  PrintWriter output=new PrintWriter(new OutputStreamWriter(System.err),true);
  //macro for my convience
  private static void println(String text){System.out.println(text);}
  /**
   *Creates a tester for the given classes
   */
  @SafeVarargs
  public Tester(Class<? extends Testable>...classes){
    //to test if Class is testable
    for (i : classes){
      assert(Testable.class.isAssignableFrom(i));}
    //maybe
  }
  /**
   *Creates a tester for the given classes
   */
  public Tester(Collection<Class<? extends Testable>> classes){
  }
  /**
   *SetPrint Writer Method
   *Sets the tester output. By default, the output is System.err.
   * It is valid to set the output to null,
   * in which case the tester is completely silent.
   *@param w - the output for the tester info; can be null
   */
  public void setPrintWriter(PrintWriter W){output=W;}
  //need to revise comment to fit my implimentation
  /**
   *Run Method
   *Runs the tests. All the classes are processed in the order in which
   *they were given. For each class, all the tests are run
   *on the same instance in order of the method names.
   *If no instance can be created, the class is skipped
   *with an error message. If method beforeMethod fails or
   *returns false, the corresponding test is skipped with
   *a warning message. After each test, method afterMethod
   *is run and a warning is displayed if it fails.
   *
   *In general, no-argument methods are valid test methods
   *if they are annotated, even when they are non-public or non-void.
   *Static method and methods that require arguments are ignored.
   *A warning is displayed if they are test-annotated.
   *@throws IllegalStateException - if this tester has already been run
   */
  public void run(){
    /**
     *Class Testpkg
     */
    private class Testpkg implements TestResult{
      //initalized in constructor
      Method method;double weight;String info;
      //initalized later
      boolean success;double duration;
      Object returned;Throwable error;
      /**
       *Testpkg Constructor
       */
      Testpkg(Method method,double weight,String info){
        this.method=method;this.weight=weight;this.info=info;}
      double getWeight(){return weight;}
      boolean success(){return success;}
      String getInfo(){return info;}
      double getDuration(){return duration;}
      Throwable error(){return error;}}
    ArrayList<Testpkg> tests=new ArrayList<Testpkg>;
    for(Class foo : classes){
      //do we need this test?
      if(foo.isAnnotationPresent(Testable.class)){
        for (Method i : foo.getDeclaredMethods()){
          //put these tests in constructor?
          if(!i.isAnnotationPresent(Test.class)){
            continue;}
          else if (i.getParameterTypes() !=0){
            output.println(String.format("Warning method %s is annotated with "
                                         +"@Test but takes parameters",
                                         i.toString()));
            continue;}
          else if (isStatic(i.getModifiers())){
            output.println(String.format("Waring static method %s is annotated"
                                         +" with @Test",i.toString()));
            continue;}
          else {
            try{
              Class annotate=i.getAnnotations(Test.class);
              ArrayList<Field> fields=new
                ArrayList<Field>(Arrays.asList(annotate.getDeclaredFields()));
              assert(fields.contains(weight));
              assert(fields.contains(info));
              tests.add(new Testpkg(i,fields.get(0).getDouble(weight),
                                    (String)fields.get(1).get(info)))}
            catch(IllegalAccessException | IllegalArgumentException |
                  NullPointerException | CastClassException ex){
            }}}
        try {
          Object temp=foo.newInstance();Testpkg testpkg;boolean check;
          for(testpkg : foo)
            if(testpkg.weight <=0){continue;}
          try{
            check=temp.beforeMethod(testpkg.method);
            if (!check){
              throw new Exception;}}
          catch (Exception ex){
            output.printLine(String.format("Warning:Before Method for method"
                                           +" %s has failed",
                                           funct.toString()));}
          try{
            testpkg.duration=(double)System.nanoTime;
            testpkg.method.invoke(temp);
            teskpkg.duration=testpkg.duration-(double)System.nanoTime;}
          catch(Object<? implements Throwable> ex){
            testpkg.duration=testpkg.duration-(double)System.nanoTime;
            testpkg.error=ex;}
          try{
            temp.afterMethod(funct);}
          catch(Exception ex){
            output.printLine(String.format("Warning:After Method for"
                                           +" method %s has failed",
                                           funct.toString()));}}
        catch(InstantiationException | IllegalAccessException |
              ExceptionInInitalizerError ex){
          output.println(String.format("Error Could not instantiate %s",
                                       foo.toString()));}}}}}
  /**
   *get Results Method
   *Test results. This method returns a list that contains
   * a TestResult object for each test that was run
   *(in the order the test method were actually run).
   *The returned list can be modified and modifications will
   *affect subsequent calls to getResults (i.e., this method
   *does not make copies of the list).
   *@throws IllegalStateException - if the tester has not yet been run
   */
  public List<TestResult> getResults(){
  }
  /**
   *Main Method
   *Starts a console-based application. 
   *Command line arguments are the names of the classes 
   *to be tested. The application produces a summary output 
   *of tests that succeeded and tests that failed.
   */
  public static void main(String[] args){
    //Get list of classes to test, check if they exist 
    //& print output to stdout

    //this is just a rough outline
    Tester testRun=new Tester(classes);
    List<TestRusult> results;
    testRun.run();
    testRun.getResults();
    return;}}