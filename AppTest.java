

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test 
  	public void testSimpleAddition() {
        PolishParser polishParser = new PolishParser();
      	assertEquals(7, polishParser.compute(new String[] {"+", "3", "4"}));
    }
    
    @Test 
  	public void testSimpleSubtract() {
        PolishParser polishParser = new PolishParser();
      	assertEquals(100, polishParser.compute(new String[] {"-", "200", "100"}));
    }
    
    @Test 
  	public void testSimpleMultiply() {
        PolishParser polishParser = new PolishParser();
      	assertEquals(144, polishParser.compute(new String[] {"x", "12", "12"}));
    }
    
    @Test 
  	public void testAllOperations() {
        PolishParser polishParser = new PolishParser();
      	assertEquals(50, polishParser.compute(new String[] {"+", "-", "x", "-", "100", "150", "50", "2", "350" }));
    }
}
