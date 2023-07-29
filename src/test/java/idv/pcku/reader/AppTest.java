package idv.pcku.reader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
	App app = new App();
    
    @Test
    public void testClassName()
    {
        assertEquals("idv.pcku.reader.App",app.getClass().getName());
    }
}
