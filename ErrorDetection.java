import java.util.Stack;

public class ErrorDetection {
	public static boolean CheckParenthesis(String str)
	{
	    if (str.isEmpty())
	        return true;

	    Stack<Character> stack = new Stack<Character>();
	    for (int i = 0; i < str.length(); i++)
	    {
	        char current = str.charAt(i);
	        if ( current == '(')
	        {
	            stack.push(current);
	        }
	        if ( current == ')' )
	        {
	            if (stack.isEmpty())
	                return false;
	            char last = stack.peek();
	            if ( current == ')' && last == '(' )
	                stack.pop();
	            else 
	                return false;
	        }
	    }
	    return stack.isEmpty();
	}
	public static boolean checkOperator(String str)
	{
		char op1 = ' ';
		char op2= ' ';
		for(int i = 0; i < str.length() -1; i++)
		{
			op1= str.charAt(i);
			op2 = str.charAt(i+1);
			boolean sOp1 = true;
			boolean sOp2 = true;
			if(!(op1 == '+' || op1 == '-' || op1 == '/' || op1 == '*' || op1 ==  '(' || op1 == ')')) 
			{
				sOp1 = false;
			}
			if(!(op2 == '+' || op2 == '-' || op2 == '/' || op2 == '*' || op2 ==  '(' || op2 == ')')) 
			{
				sOp2 = false;
			}
			if(isOperand(str.charAt(i) + "") && sOp2)
			{
				return false;
			}
		}
		int signCount = 0;
		int numCount = 0;
		for(int j = 0; j < str.length(); j ++)
		{
			char current = str.charAt(j);
			boolean sign;
			
			if(current == '+' || current == '-' || current == '/' || current == '*' ) 
			{
				sign = true;
				signCount++;
			}
			else sign = false;
			
			if(!sign && current != '(' && current != ')' )
			{
				numCount++;
			}

		}
		if(numCount  > signCount + 1)
			return false;
		else
			return true;
		
	}
}
