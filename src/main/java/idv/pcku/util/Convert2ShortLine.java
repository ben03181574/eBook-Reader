package idv.pcku.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class Convert2ShortLine {
	
	/**
	 * 
	 * @param strArr
	 * @param rst
	 * @return
	 */
	public static ArrayList<String> convertFromList(List<String> strArr, int rst) {
		ArrayList<String> convertedStrArr = new ArrayList<>();
            
        for(String str : strArr) {
        	if(str.length() <= rst) {
        		convertedStrArr.add(str);
    		}else {
    			StringBuilder sb = new StringBuilder(str);
    			while(sb.length() > rst) {           
    				convertedStrArr.add(sb.substring(0, rst));
    				sb.delete(0, rst);
    			}
    			convertedStrArr.add(sb.toString());
    		}
        }
        
        return convertedStrArr;
	}
}
