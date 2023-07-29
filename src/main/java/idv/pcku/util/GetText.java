package idv.pcku.util;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class GetText {
	
	/**
	 * 
	 * @param strArr
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getFromLineNumber(List<String> strArr, int start, int end){
        List<String> selectStrArr = new ArrayList<>();
        
        for(int i=start-1;i<end && i<strArr.size();i++) {
        	selectStrArr.add(strArr.get(i));
        }
		return selectStrArr;
	}
	
	/**
	 * 
	 * @param rowStrArr
	 * @param page
	 * @param perPage
	 * @return
	 */
	public static List<String> getFromPage(List<String> rowStrArr, int page, int perPage){
        
        return GetText.getFromLineNumber(rowStrArr, perPage*page+1, perPage*page+perPage);
	}
	/**
	 * 將textArr內容以及符號由row major轉換成column major，並以Array形式回傳。
	 * @param textArr
	 * @return
	 */
	public static String[] getFromRow(List<String> rTextArr) {
		//get max length of string of textArr
		int maxLen = 0;
		for(String line : rTextArr) {
			if(line.length() > maxLen) {
				maxLen = line.length();
			}
		}
		//transform row major to column major
		String[] cTextArr = new String[maxLen];
		for(int i=0;i<maxLen;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=rTextArr.size()-1;j>=0;j--) {
				char chr = (rTextArr.get(j).length()>i)?rTextArr.get(j).charAt(i):'　';
				//transform 'row sign' to 'column sign'
				int chrValue = (int) chr;
			    if(chrValue == 8220) {
		        	//transform '“' to full width of '﹁'
			    	chr = (char) 65089;
		        } else if(chrValue == 8221) {
		        	//transform '”' to full width of '﹂'
		        	chr = (char) 65090;
		        } else if(chrValue == 8216) {
		        	//transform '‘' to full width of '﹁'
			    	chr = (char) 65089;
		        } else if(chrValue == 8217) {
		        	//transform '’' to full width of '﹂'
		        	chr = (char) 65090;
		        } else if(chrValue == 65288) {
		        	//transform '（' to full width of '︵'
		        	chr = (char) 65077;
		        } else if(chrValue == 65289) {
		        	//transform '）' to full width of '︶'
		        	chr = (char) 65078;
		        } else if(chrValue == 65339) {
		        	//transform '［' to full width of '︹'
		        	chr = (char) 65081;
		        } else if(chrValue == 65341) {
		        	//transform '］' to full width of '︺'
		        	chr = (char) 65082;
		        } else if(chrValue == 12304) {
		        	//transform '【' to full width of '︻'
		        	chr = (char) 65083;
		        } else if(chrValue == 12305) {
		        	//transform '】' to full width of '︼'
		        	chr = (char) 65084;
		        } else if(chrValue == 12298) {
		        	//transform '《' to full width of '︽'
		        	chr = (char) 65085;
				} else if(chrValue == 12299) {
		        	//transform '《' to full width of '︽'
		        	chr = (char) 65086;
				} else if(chrValue == 65293) {
		        	//transform '－' to full width of '｜'
		        	chr = (char) 65372;
		        } else if(chrValue == 65309) {
		        	//transform '＝' to full width of '：'
		        	chr = (char) 65306;
		        } else if(chrValue == 183 || chrValue == 8230) {
		        	//transform '…' to full width of '．'
		        	chr = (char) 65294;
		        } else if (chrValue == 9632) {
		        	//transform '' to full width of '＊'
		        	chr = (char) 65290;
		        }
				sb.append(chr);
			}
			cTextArr[i] = sb.toString();
		}
		return cTextArr;
	}
}
