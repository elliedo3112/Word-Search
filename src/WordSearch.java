//Name: Ellie Do
//Assignment 2: Word Search

import java.io.File;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The WordSearch class is able to search for words in a 2-D grid.
 * Words can go in any direction:  left-to-right, right-to-left,
 * top-to-bottom, bottom-to-top, or any of the 4 diagonal directions.
 */
class WordSearch {
    // The 2D array of characters containing the hidden words.
    char[][] grid;

    /**
     * Reads a WordSearch grid from a file and initializes the grid.
     * The first line of the file contains a number.  That number 
     * indicates how many rows are in the grid.  Following that, 
     * there will be a line for each row of the grid.  The characters 
     * in the grid will be separated by spaces in the file.  
     * 
     * @param filename the name of the file containing the grid.
     */
    public WordSearch(String filename) {
        try {
            Scanner in = new Scanner (new File (filename));
            
            // Read in # of rows in the grid
            int numRows = in.nextInt();
            in.nextLine();
            
            // Read in the first row
            char[] firstRow = readRow(in);
            
            // Now that we know the number of rows and the number of 
            // characters in each row, we can create the grid.
            grid = new char[numRows][firstRow.length];
            grid[0]= firstRow;
            
            // Read in the rest of the grid.
            for (int i = 1; i < numRows; i++) {
                grid[i] = readRow(in);
            }
            in.close();
            
        } catch (InputMismatchException e) {
            System.out.println("The file should start with the number of rows.");
        } catch (FileNotFoundException e) {
            System.out.println ("WordSearch file " + filename + " was not found.");
        }
    }

    /**
     * Read one row from the file
     * @param in the scanner that is reading the file
     * @return a character array containing the letters in the row read.
     */
    private char[] readRow(Scanner in) {
        String gridLine = in.nextLine();
        String[] row = gridLine.split(" ");
        char[] charRow = new char[row.length];
        for (int i = 0; i < row.length; i++) {
            charRow[i] = row[i].charAt(0);
            //System.out.println(row[i].charAt(0));
        }
        
        return charRow;
    }
    
   

    /**
     * Search for a word in the grid in all directions
     * 
     * @param nextWord the word to search for
     * @return an array with 3 values. The first value is the row where the
     *         first letter of the word appears. The second value is the column
     *         where the first letter in the word appears. The third value
     *         indicates the direction: 
     *         0 - left-to-right [r+0][c+1]
     *         1 - right-to-left [r+0][c-1]
     *         2 - top-to-bottom [r+1][c]
     *         3 - bottom-to-top [r-1][c]
     *         4 - diagonal-down-left-to-right [r+1][c+1]
     *         5 - diagonal-down-right-to-left [r+1][c-1]
     *         6 - diagonal-up-left-to-right [r-1][c+1]
     *         7 - diagonal-up-right-to-left [r-1][c-1]
     *         If the word cannot be found, the array that is returned should 
     *         contain 3 values that are all -1.
     */
    public int[] findWord(String nextWord) {
        // You need to write this.
    	char[]wordArray= nextWord.toCharArray();
    	int [] wordFound = {-1,-1,-1};
    	for(int r=0;r<grid.length;r++) {
    		for(int c=0;c<grid[r].length;c++) {
    			if (grid[r][c]==wordArray[0]) {	
    				if (checkWord(r,c,0,1,wordArray)) {//left to right
    					wordFound[0]=r;
        				wordFound[1]=c;
        				wordFound[2]=0;
    				}
    				else if (checkWord(r,c,0,-1,wordArray)) { //right to left
    					wordFound[0]=r;
        				wordFound[1]=c;
        				wordFound[2]=1;
    				}
    				else if(checkWord(r,c,1,0,wordArray)) { // top to bottom
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=2;
    				}else if(checkWord(r,c,-1,0,wordArray)) { // bottom to top
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=3;
    				}else if(checkWord(r,c,1,1,wordArray)) { // dig down left to right
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=4;
    				}else if(checkWord(r,c,1,-1,wordArray)) { // dig down right to left
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=5;
    				}else if(checkWord(r,c,-1,1,wordArray)) { // dig up left to right
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=6;
    				}else if(checkWord(r,c,-1,-1,wordArray)) { // dig up right to left
    					wordFound[0]=r;
        				wordFound[1]=c;
    					wordFound[2]=7;
    				}
    				
    			}
    		}
    		
    }
    				
        return wordFound;
    	
    
    }
    
    public boolean checkWord (int row, int col, int rowUpdate, int colUpdate, char[] wordArray) {
    	//check if the word is out of bound and if the word match in a specific direction
    	//@param row and column of the first letter, the update value of row and column (direction) and the word char array 
    	//return true if the word is found in this specific direction
    	//if the word is out of bound or not found, return false
    	
    	int size=wordArray.length;
    	int rowSize=grid.length;
    	int colSize=grid[row].length;
    	boolean result=false;
    	boolean search=false;
    	if (rowUpdate==0) {
    		if (colUpdate==1) {
    			//check out of bound for left to right
    			if (col+size <= colSize) {
    				search=true;
    			}
    		} else if (colUpdate==-1) {
    			
    			if(col-size>=-1) {		
    				search=true;
    		}
    			}
    			//check out of bound for right to left
    			
    	}else if (rowUpdate==1) {
    		if (row+size<=rowSize) {
    			if (colUpdate==-1) {
    				//check out of bound for dig down right to left
    				if (col-size>=-1) {
    					search=true;
    				}
    			}else if (colUpdate==0) {
    				//check out of bound for top to bottom
    					search=true;
    			}else if (colUpdate==1) {
    				//check out of bound for dig down left to right
    				if (col+ size <= colSize) {
    					search=true;
    				}
    			}
    		}
    	}else if (rowUpdate==-1) {
    		if (row-size>=-1) {
    			if (colUpdate==0) {
    				//check out of bound bottom to top
    				search=true;
    			}else if (colUpdate==-1) {
    				//check out of bound dig up right to left
    				if (col-size>=-1) {
    					search=true;
    				}
    			}else if (colUpdate==1) {
    				//check out of bound dig up left to right
    				if (col+size<=colSize) {
    					search=true;
    				}
    			}
    		}
    	}
    	
  
    	if (search==true) {
    		//check if the word matches
			 for (int i=1;i<wordArray.length;i++) {
    			row=row+rowUpdate;
    			col=col+colUpdate;
    					
    			if (grid[row][col]!=wordArray[i]) {
    				return false;
    			} else {
    				result=true;
    				}	
    				
    			}
    	}
		else {
    				return false;
    			}
    	
    	return result;
    	
    }
    
    public static void main (String [] args) {
    	WordSearch test1=new WordSearch("test1.txt");
    	int[] result=test1.findWord("RED");
    	for(int i=0;i<result.length;i++) {
    		System.out.print(result[i]);
    	}
    
}
}
