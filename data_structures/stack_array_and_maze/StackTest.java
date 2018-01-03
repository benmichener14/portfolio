

public class StackTest 
{
	public static void main(String[] args) 
	{
		StackTest tester = new StackTest();
	
		IStack<Integer> stack = new StackArray<Integer>();
		tester.test(stack);

		// uncomment this code to test your stack...
		//stack = new StackAry<Integer>();
		//tester.test(stack);
	}
	
	public void test(IStack<Integer> stack)
	{
		System.out.println("Start tests...");
		
		// test push, pop, push, pop...
		check(stack.toString().equals("top->"));
		check(stack.isEmpty());
		check(stack.getSize() == 0);

		System.out.println(stack);
		
		stack.push(10);
		check(stack.top() == 10);
		check(stack.toString().equals("top->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 1);

		System.out.println(stack);
		
		int i = stack.pop();
		check(i == 10);
		check(stack.toString().equals("top->"));
		check(stack.isEmpty());
		check(stack.getSize() == 0);

		System.out.println(stack);
		
		stack.push(10);
		check(stack.top() == 10);
		check(stack.toString().equals("top->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 1);

		System.out.println(stack);
		
		i = stack.pop();
		check(i == 10);
		check(stack.toString().equals("top->"));
		check(stack.isEmpty());
		check(stack.getSize() == 0);
		
		System.out.println(stack);
		
		// test push, push, push, pop, pop, pop...
		check(stack.toString().equals("top->"));
		check(stack.isEmpty());
		check(stack.getSize() == 0);
		
		System.out.println(stack);
		
		stack.push(10);
		check(stack.top() == 10);
		check(stack.toString().equals("top->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 1);

		System.out.println(stack);
		
		stack.push(20);
		check(stack.top() == 20);
		check(stack.toString().equals("top->20->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 2);

		System.out.println(stack);
		
		stack.push(30);
		check(stack.top() == 30);
		check(stack.toString().equals("top->30->20->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 3);

		System.out.println(stack);
		
		i = stack.pop();
		check(i == 30);
		check(stack.toString().equals("top->20->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 2);

		System.out.println(stack);
		
		i = stack.pop();
		check(i == 20);
		check(stack.toString().equals("top->10"));
		check(!stack.isEmpty());
		check(stack.getSize() == 1);

		System.out.println(stack);
		
		i = stack.pop();
		check(i == 10);
		check(stack.toString().equals("top->"));
		check(stack.isEmpty());
		check(stack.getSize() == 0);
		
		System.out.println(stack);
		
		// check for pop or top on emtpy stack...
		boolean ok = false;
		try
		{
			stack.pop();
		}
		catch (IllegalStateException e)
		{
			ok = true;
		}
		check(ok);
		
		try
		{
			stack.top();
		}
		catch (IllegalStateException e)
		{
			ok = true;
		}
		check(ok);
		
		System.out.println("Success!");
	}
	
	private static void check(boolean b)
	{
		if (!b)
			throw new AssertionError("Test Failed!");
	}}