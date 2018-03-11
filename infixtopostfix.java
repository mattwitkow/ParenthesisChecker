//https://vermashubhang.wordpress.com/2013/06/30/infix-to-postfix-conversion-and-evaluation-code-java/
//https://github.com/PaulVaroutsos/Scripts-Algorithms-and-Other-Projects/blob/master/BinaryTrees/PostFix.java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class infixtopostfix 
{
 private int count=0;
 private static Scanner scan;
 public static void main(String []args) throws ParenthesisException, OperatorException, OperandException
 {
    System.out.println("Infix Expression:");
    scan = new Scanner(System.in);
    String infix = scan.nextLine();
    infix = infix.replaceAll("\\s","");
    infixtopostfix obj = new infixtopostfix();
    obj.convert(infix);
 }
 public void convert(String infix) throws ParenthesisException, OperatorException, OperandException
 {
    Stack<String> stack = new Stack<String>();
    Queue<String> queue = new LinkedList<String>();
    boolean misMatched = false;
    boolean missingOperator = false;
    boolean missingOperand = false;
    infixtopostfix obj = new infixtopostfix();
    while(obj.count < infix.length())
    {
       String token= obj.gettoken(obj,infix);
      
       if(obj.isoperand(token))
       {
          queue.add(token);
       }
       else if(obj.isoperator(token))
       {
          if(stack.isEmpty())
          {
             stack.push(token);
          }
          else
          {
             while(!stack.isEmpty()&&(precedence((String)stack.peek())>=precedence(token)))
             {
                String mystring = (String) stack.peek();
                stack.pop();
                queue.add(mystring);
             }
             stack.push(token);
          }
       }
       
      
       else if(token.equals(")"))
       {
          if(stack.isEmpty())
          {
        	  throw new ParenthesisException("Mismatched parenthesis");
          }
    	   while((String)stack.peek()!="(")
           {
               String mystr =(String)stack.peek();
               stack.pop();
               queue.add(mystr);
           }
           if(!stack.empty())
           stack.pop();
        }
        else
        {
           stack.push(token);
        }
     }
     while(!stack.empty())
     {
        queue.add((String)stack.peek());
        stack.pop();
     }
     
     String full = "";
     Queue<String> queue1 = new LinkedList<String>(queue);
     System.out.println(full);
     while(!queue1.isEmpty())
     {
    	 full += queue1.remove();
     }
     
    
     if(!CheckParenthesis(infix))
     {
    	 throw new ParenthesisException("Mismatched parenthesis");
     }
     if(!checkOperator(infix))
     {
    	 throw new OperatorException("Not enough operators");
     }
     
     if(!checkOperand(full))
     {
    	 throw new OperandException("Not enough operands");
    }
  
     while(!queue.isEmpty())
     {
        System.out.print(queue.peek()+" ");
               queue.remove();
     }
    
 }
 public boolean CheckParenthesis(String str)
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
	public boolean checkOperator(String str)
	{
		if(isPostfix(str))
			return false;
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
		return true;
	}
	
	public  boolean checkOperand(String str)
	{
		if(isOperator(str.charAt(0)) )
			return false;
		if(isOperator(str.charAt(1)) )
			return false;
	
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
	if(signCount >= numCount)
		return false;
	else
		return true;
	}

	public static boolean isPostfix(String expr) {
		
		String[] stringArray = expr.split(" ");
		for(int i=0; i < stringArray.length; i++){
			try{
				Double.parseDouble(stringArray[i]);
					
			}
			
			catch(NumberFormatException e){
				if(stringArray[i].length() == 1){
					char op = stringArray[i].charAt(0);
					switch(op){
					case '*':
					case '/':
					case '+':
					case '-':
					case '^':
		
						break;
					default:
					
						return false;
					}
				}
			
				else{
					return false;
				}
			}
		}
		
		if(isPost(expr)){
			return true;
		}else{
			return false;
		}
	}
private static boolean isPost(String string){
		
		String[] stringArray = string.split(" ");
		int size = stringArray.length;
		int lastString = beginPost(stringArray,0,size-1);
	
		if(lastString == 0){
			return true;
		}else{
			return false;
		}
	}
private static int beginPost(String[] stringArray, int first, int last){
	
	if(last < 0 || first > last){
		return -1;
	}
	
	String next = stringArray[last];
	if( !(next.charAt(0) == '+' || next.charAt(0) == '-' || next.charAt(0) == '*' ||
			next.charAt(0) == '/' || next.charAt(0) == '^')){
		
		return last;
	}
	
	else{
		int firstEnd = beginPost(stringArray,first,last-1);
		if(firstEnd > -1){
			return beginPost(stringArray,first,firstEnd-1);
		}
		else{
			return -1;
		}
	}
}
 
 public int precedence(String token)
 {
    if(token=="%")
    {
       return 10;
    }
    if(token=="/")
    {
       return 9;
    }
    if(token=="*")
    {
       return 9;
    }
    if(token=="+")
    {
       return 7;
    }
    if(token=="-")
    {
       return 7;
    }
    
   
    return 0;
 }
 public boolean isOperand(char t)
 {
    int i=0;   
       if((t>=65 &&t<=90)||(t>=97 && t<=122)||t==':'||((t>=48)&&(t<=57)))
       {
          return true;
       }
       else
          return false;
 }
 public  boolean isoperator(String token)
 {
 if((token.equals("+"))||(token.equals("%"))||(token.equals(">"))||
 (token.equals("<"))||(token.equals("-"))||(token.equals("*"))||
 (token.equals("/"))||(token.equals("=="))||(token.equals(">="))||
 (token.equals("<="))||(token.equals("!="))||(token.equals("&&"))||
 (token.equals("||")))
   return true;
 else
   return false;
 }
 public  boolean isOperator(char c)
 {
 if(c =='+'||c =='-'||c =='/'|| c =='*')
   return true;
 else
   return false;
 }
 
 public String gettoken(infixtopostfix obj,String infix)
 {
    int i=obj.count;
    int j,k;
    char str[] = new char[100];
    str = infix.toCharArray();
    if((str[i]>=65 &&str[i]<=90)||(str[i]>=97&&str[i]<=122)||((str[i]>=48)&&(str[i]<=57)))
    {
       for(j=i;j<infix.length();j++)
       {
          if((str[j]>=65 &&str[j]<=90)||(str[j]>=97&&str[j]<=122)||str[j]==':'||((str[j]>=48)&&(str[j]<=57)))
          {
             continue;
          }
          else
          {
             break;
          } 
       }
       obj.count=j;
       char str1[] = new char[j-i];
       for(k=i;k<j;k++)
       {
          str1[k-i]=str[k];
       }
       String mystring = new String(str1);
       return mystring;
    }
    else if(str[i]=='(')
    {
       obj.count++;
       return "(";
    }
    else if(str[i]==')')
    {
       obj.count++;
       return ")";
    }
    else if(str[i]=='+')
    {
       obj.count++;
       return "+";
    }
    else if(str[i]=='-')
    {
       obj.count++;
       return "-";
    }
    else if(str[i]=='*')
    {
       obj.count = obj.count +1;
       return "*";
    }
    
    else if(str[i]=='/')
    {
       obj.count = obj.count +1;
       return "/";
    }
    else if(str[i]=='=' && str[i+1]=='=')
    {
       obj.count = obj.count +2;
       return "==";
    }
   
   
    return null;
  }
 public class ParenthesisException extends Exception
 {
	 private String message;
	 public ParenthesisException ( String message)
	 {
		 super(message);
		 this.message = message;
	 }
 }
 public boolean isoperand(String token)
 {
    int i=0;
    char t[] = new char[100];
    t = token.toCharArray();
    for(i=0;i<token.length();i++)
    {
       if((t[i]>=65 &&t[i]<=90)||(t[i]>=97&&t[i]<=122)||t[i]==':'||((t[i]>=48)&&(t[i]<=57)))
       {
          continue;
       }
       else
          return false;
    }
    return true;
 }

 public class OperatorException extends Exception
 {
	 private String message;
	 public OperatorException ( String message)
	 {
		 super(message);
		 this.message = message;
	 }
 }
 public class OperandException extends Exception
 {
	 private String message;
	 public OperandException ( String message)
	 {
		 super(message);
		 this.message = message;
	 }
 }
}