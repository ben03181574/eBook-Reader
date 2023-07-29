package idv.pcku.util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Test;

public class Convert2ShortLineTest {

	@Test
	public void testConvertFromList() throws IOException {
		ArrayList<String> convertedStrArr = Convert2TraditionChinese.convertFromFile("全知讀者視角.txt");
		ArrayList<String> convertedShortStrArr = Convert2ShortLine.convertFromList(convertedStrArr, 20);
		
		FileInputStream fis = new FileInputStream("全知讀者視角.txt");
		InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
	    BufferedReader br = new BufferedReader(isr);
	    
	    int charCount = 0;
	    String data;        
	    while((data = br.readLine()) != null) {
	    	charCount += data.length();                        
	    }   
	    int charCount2 = 0;
	    for(String convertedShortStr : convertedShortStrArr) {
	    	charCount2 += convertedShortStr.length();
	    }
	    
	    if(fis!=null)fis.close();
        
	    assertEquals(charCount,charCount2);
	}

}
