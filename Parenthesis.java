import java.util.Scanner;
import java.util.Stack;


public class Parenthesis {

	public static void main(String[] args) {
		System.out.println("Enter a sentence");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		System.out.println(CheckParenthesis(sentence));

	}
	public static boolean CheckParenthesis(String str)
	{
	    if (str.isEmpty())
	        return true;

	    Stack<Character> stack = new Stack<Character>();
	    for (int i = 0; i < str.length(); i++)
	    {
	        char current = str.charAt(i);
	        if (current == '{' || current == '(' || current == '[')
	        {
	            stack.push(current);
	        }
	        if (current == '}' || current == ')' || current == ']')
	        {
	            if (stack.isEmpty())
	                return false;
	            char last = stack.peek();
	            if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
	                stack.pop();
	            else 
	                return false;
	        }
	    }
	    return stack.isEmpty();
	}

}
