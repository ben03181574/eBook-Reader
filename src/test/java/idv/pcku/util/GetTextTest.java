package idv.pcku.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GetTextTest {
	private ArrayList<String> convertedStrArr = Convert2TraditionChinese.convertFromFile("全知讀者視角.txt");
	private ArrayList<String> convertedShortStrArr = Convert2ShortLine.convertFromList(convertedStrArr, 20);
	
	@Test
	public void testGetFromLineNumber() {
		int count = GetText.getFromLineNumber(convertedShortStrArr, 11, 15).size();
		
		assertEquals(5,count);
	}
	
	@Test
	public void testGetFromPage() {
		int count = GetText.getFromPage(convertedShortStrArr, 1, 10).size();
		
		assertEquals(10,count);
	}
	
	@Test
	public void testGetFromRow() {
		String[] textArr = GetText.getFromRow(GetText.getFromPage(convertedShortStrArr, 0, 10));
		for(int i=0;i<textArr.length;i++) {
			for(int j=0;j<textArr[i].length();j++) {
				System.out.printf("%2s",textArr[i].charAt(j));
			}
			System.out.println();
		}
	}

}
