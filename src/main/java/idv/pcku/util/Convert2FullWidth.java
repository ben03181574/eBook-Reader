package idv.pcku.util;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class Convert2FullWidth {
	/**
	 * 
	 * @param halfStr
	 * @return
	 */
	public static String convertFromString(String halfStr) {
		StringBuilder sb = new StringBuilder();
		char[] halfChrArr = halfStr.toCharArray();
	    for (int i = 0; i < halfChrArr.length; ++i) {
	        int halfChrValue = (int) halfChrArr[i];
	        if (halfChrValue >= 33 && halfChrValue <= 126) {
	        	//full width of Sign and English
	        	halfChrArr[i] = (char) (halfChrValue + 65248);
	        } else if (halfChrValue == 32) {
	        	//full width of Space
	        	halfChrArr[i] = (char) 12288;
	        }
	    }
	    for(int i=0;i<halfChrArr.length;i++){
	        sb.append(halfChrArr[i]);
	    }
	    return sb.toString();
	}
}
