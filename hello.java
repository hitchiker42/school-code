import java.io.*;
class Hello {
	public static void main(String[] args) throws IOException{
		PrintWriter output=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)),true);
		output.println("Hello World!");
}
}
