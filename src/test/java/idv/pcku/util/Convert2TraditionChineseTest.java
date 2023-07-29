package idv.pcku.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.junit.Test;

public class Convert2TraditionChineseTest {

	@Test
	public void testConvertFromString() {
		String line = Convert2TraditionChinese.convertFromString("没与乐乔祯祷祸禀谧谨谩谪谫谬谭");
		
		assertEquals("沒與樂喬禎禱禍稟謐謹謾謫譾謬譚",line);
	}

	@Test
	public void testConvertFromFile() throws FileNotFoundException, IOException {
		ArrayList<String> convertedStrArr = Convert2TraditionChinese.convertFromFile("全知讀者視角.txt");
		
		FileInputStream fis = new FileInputStream("全知讀者視角.txt");
		InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
	    BufferedReader br = new BufferedReader(isr);
	    
	    int charCount = 0;
	    String data;        
	    while((data = br.readLine()) != null) {
	    	charCount += data.length();                        
	    }   
	    int charCount2 = 0;
	    for(String convertedStr : convertedStrArr) {
	    	charCount2 += convertedStr.length();
	    }
	    
	    if(fis!=null)fis.close();
        
	    assertEquals(charCount,charCount2);
	}

}

