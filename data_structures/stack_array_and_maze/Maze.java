import java.util.*;
import java.io.*;

public class Maze
{
    
    private String[][] map;
    private int x = 1;
    private int y = 1;
    IStack<String> undoStack = new StackArray<String>();
    IStack<String> redoStack = new StackArray<String>();
    
    public static void main(String[] args)
    {
        Maze obj = new Maze();
        obj.go();
    }
    
    private void go()
    {
        createMap();
        
        while(true)
        {
            Scanner in = new Scanner(System.in);
            displayMap();
            System.out.println("What do you want to do?");
            String input = in.nextLine();

        //Optional
        //  clearConsole();
            
            move(input, false, false);
        }
    }

// Create Maze Map    
    private void createMap()
    {
        Random random = new Random();
        int die = random.nextInt(5);
        
        if (die == 0)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        if (die == 1)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", "|", " ", " ", " ", "|", " ", " ", "|"},
                {"|", " ", "|", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|", " ", " ", "|"},
                {"|", " ", "|", "-", "-", " ", "-", "-", "-", "|"},
                {"|", " ", "|", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", "|", "-", "-", " ", "-", "-", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|", " ", " ", "|"},
                {"|", " ", "|", " ", " ", " ", "|", " ", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        if (die == 2)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", "|", " ", "|", " ", "|", " ", "|"},
                {"|", " ", " ", "|", " ", "|", "-", "|", " ", "|"},
                {"|", " ", " ", "|", " ", "|", " ", " ", " ", "|"},
                {"|", "-", " ", "|", " ", "|", " ", "-", "-", "|"},
                {"|", " ", " ", "|", " ", "|", " ", "|", " ", "|"},
                {"|", " ", " ", "|", " ", "|", " ", "|", " ", "|"},
                {"|", " ", " ", "|", " ", " ", " ", "|", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        if (die == 3)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", "|", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", "|", " ", " ", "|", "-", "-", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", "-", "|", "-", "-", "|", " ", " ", " ", "|"},
                {"|", " ", "|", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", "|", " ", " ", "|", "-", "-", " ", "|"},
                {"|", " ", "|", " ", " ", "|", " ", " ", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        if (die == 4)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", " ", " ", " ", "|", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", "|", "-", "|"},
                {"|", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", "|", "-", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", "|", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        if (die == 5)
        {
            map = new String[][]{
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"},
                {"|", "*", " ", "|", " ", " ", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", "|", " ", " ", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", "|", " ", " ", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", " ", " ", "|", " ", " ", " ", "|", " ", "|"},
                {"|", " ", " ", " ", " ", "|", " ", " ", " ", "|"},
                {"|", "-", "-", "-", "-", "-", "-", "-", "-", "|"}
            };
        }
        
    }
// Handle input to movement    
    private void move(String input, Boolean isUndo, Boolean isRedo)
    {
        if(input.equals("w"))
        {
            if(!isUndo && canMove(x - 1, y))
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
            moveUp();
        }
        else if(input.equals("a"))
        {
            if(!isUndo && canMove(x, y - 1))
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
            moveLeft();
        }
        else if(input.equals("s"))
        {
            if(!isUndo && canMove(x + 1, y))
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
            moveDown();
        }
        else if(input.equals("d"))
        {
            if(!isUndo && canMove(x, y + 1))
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
            moveRight();
        }
        else if(input.equals("g"))
        {
            grog();
            if(!isUndo)
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
        }
        else if(input.equals("v"))
        {
            vomit();
            if(!isUndo)
            {
                undoStack.push(input);
                if(!isRedo)
                    clearRedoStack();
            }
        }
        else if(input.equals("q"))
            undo();
        else if(input.equals("e"))
            redo();
        else
            System.out.println("That is not an action you can do.");
    }
// Movement Methods
    private void moveUp()
    {
        if(canMove(x - 1, y))
        {
            map[x][y] = " ";
            map[x - 1][y] = "*";
            x--;
        }
        else
            System.out.println("You walk head first into a wall. Maybe you've had a bit too much grog...");
    }
    private void moveDown()
    {
        if(canMove(x + 1, y))
        {
            map[x][y] = " ";
            map[x + 1][y] = "*";
            x++;
        }
        else
            System.out.println("You walk head first into a wall. Maybe you've had a bit too much grog...");
    
    }
    private void moveLeft()
    {
        if(canMove(x, y - 1))
        {
            map[x][y] = " ";
            map[x][y - 1] = "*";
            y--;
        }
        else
            System.out.println("You walk head first into a wall. Maybe you've had a bit too much grog...");
    }
    private void moveRight()
    {
        if(canMove(x, y + 1))
        {
            map[x][y] = " ";
            map[x][y + 1] = "*";
            y++;
        }
        else
            System.out.println("You walk head first into a wall. Maybe you've had a bit too much grog...");
    }
    private void grog()
    {
        System.out.println("You take a hearty swig of grog and feel invigorated!");
    }
    private void vomit()
    {
        System.out.println("You vomit whatever was in your stomach back out onto the floor.");
        System.out.println("Strange... The grog looks the same coming out as it did going in!");
    }
// Undo/Redo
    private void undo()
    {
        if (!undoStack.isEmpty())
        {
            System.out.println("Time Warps...");
            String temp = undoStack.pop();
            move(opposite(temp), true, false);
            redoStack.push(temp);
        }
        else
            System.out.println("Time cannot warp any farther from here.");
    }
    private void redo()
    {
        if (!redoStack.isEmpty())
        {
            System.out.println("Time Warps...");
            move(redoStack.pop(), false, true);
        }
        else
            System.out.println("Time cannot warp any farther from here.");
    }
//Utilities    
	private void clearRedoStack()
	{
	    while(!redoStack.isEmpty())
	    {
	        redoStack.pop();
	    }
	}
    private Boolean canMove(int a, int b)
    {
        if (map[a][b].equals("|") || map[a][b].equals("-"))
        {
            return false;
        }
        else
            return true;
    }
    private String opposite(String movement)
    {
        if(movement.equals("w"))
            return "s";
        else if(movement.equals("s"))
            return "w";
        else if(movement.equals("a"))
            return "d";
        else if(movement.equals("d"))
            return "a";
        else if(movement.equals("g"))
            return "v";
        else if(movement.equals("v"))
            return "g";
        else
            return "";
    }
    private void displayMap()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
                System.out.print(map[i][j] + " ");
            System.out.println("");
        }
    }
    
    //"Clears" the console. Makes the program look better as it runs.
    private void clearConsole()
    {
        for(int i = 0; i < 50; i++)
        System.out.println("");
    }
}