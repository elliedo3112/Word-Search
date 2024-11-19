
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class WordSearchTest {
    WordSearch test1;

    @BeforeEach
    void setUp() throws Exception {
        test1 = new WordSearch("test1.txt");
    }

    @Test
    void testWordSearch() {
        assertEquals(10, test1.grid.length);
        assertEquals(11, test1.grid[0].length);
        assertEquals('F', test1.grid[0][0]);
        assertEquals('H', test1.grid[9][10]);
    }

    @Test
    void testFindLefToRightWord() {
        int[] result = test1.findWord("PEGASUS");
        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
        assertEquals(0, result[2]);
    }
    
    @Test
    void testFindRightToLeftWord() {
        int[] result = test1.findWord("GRIFFIN");
        assertEquals(8, result[0]);
        assertEquals(6, result[1]);
        assertEquals(1, result[2]);

        result = test1.findWord("RED");
        assertEquals(1, result[0]);
        assertEquals(3, result[1]);
        assertEquals(1, result[2]);
    }
    
    @Test
    void testFindDownWord() {
        int[] result = test1.findWord("SPHINX");
        assertEquals(3, result[0]);
        assertEquals(8, result[1]);
        assertEquals(2, result[2]);
    }
    
    @Test
    void testFindUpWord() {
        int[] result = test1.findWord("BLUE");
        assertEquals(7, result[0]);
        assertEquals(10, result[1]);
        assertEquals(3, result[2]);
    }
    
    @Test
    void testFindDownRightWord() {
        int[] result = test1.findWord("PHOENIX");
        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
        assertEquals(4, result[2]);
    }
    
    @Test
    void testFindDownLeftWord(){
        int[] result = test1.findWord("BRR");
        assertEquals(7, result[0]);
        assertEquals(10, result[1]);
        assertEquals(5, result[2]);
    }
    
    @Test
    void testFindUpRightWord() {
        int[] result = test1.findWord("LION");
        assertEquals(9, result[0]);
        assertEquals(3, result[1]);
        assertEquals(6, result[2]);
    }
    
    @Test
    void testFindUpLeftWord() {
        int[] result = test1.findWord("JXS");
        assertEquals(2, result[0]);
        assertEquals(10, result[1]);
        assertEquals(7, result[2]);
    }
    
    @Test
    void testWordNotFoun() {
        int[] result = test1.findWord("ABCDE");
        assertEquals(-1, result[0]);
        assertEquals(-1, result[1]);
        assertEquals(-1, result[2]);
    }
    
    @Test
    void testWordTooLong() {
        int[] result = test1.findWord("ABCDEABCDEABCDE");
        assertEquals(-1, result[0]);
        assertEquals(-1, result[1]);
        assertEquals(-1, result[2]);
    }
}
