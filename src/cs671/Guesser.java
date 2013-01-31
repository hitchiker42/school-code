package cs671.j1;
public interface Guesser<T>{
    String initalize();
    T getSecret();
    boolean hasSolved() throws IllegalStateException;
    String makeQuestion();
    void no();
    double progress();
    void yes();
}