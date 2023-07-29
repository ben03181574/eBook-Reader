package idv.pcku.ebook;

import java.util.List;

import org.apache.commons.io.FilenameUtils;

import idv.pcku.util.Convert2ShortLine;
import idv.pcku.util.Convert2TraditionChinese;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookInfo {
	private String eBookBaseName;
	private List<String> eBookTraditionalChineseStrArr;
	private List<String> eBookShortTraditionalChineseStrArr;
	private int eBookTotalLineNum;
	private int eBookEachLineLen;
	private boolean isInit;
	
	/**
	 * 
	 * @param eBookPath
	 */
	public void initEBook(String eBookPath) {	
		this.eBookBaseName = FilenameUtils.getBaseName(eBookPath);
		this.eBookTraditionalChineseStrArr = Convert2TraditionChinese.convertFromFile(eBookPath);
		this.eBookEachLineLen = 20;
		this.eBookShortTraditionalChineseStrArr = Convert2ShortLine.convertFromList(this.eBookTraditionalChineseStrArr, this.eBookEachLineLen);
		this.eBookTotalLineNum = this.eBookShortTraditionalChineseStrArr.size();	
		this.isInit = true;		
	}
	
	/**
	 * 
	 */
	public void resetEBookInfo() {
		this.eBookBaseName = null;
		this.eBookTraditionalChineseStrArr = null;
		this.eBookShortTraditionalChineseStrArr = null;
		this.eBookTotalLineNum = 0;
		this.eBookEachLineLen = 0;
		this.isInit = false;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEBookBaseName() {
		return this.eBookBaseName;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getEBookShortTraditionalChineseStrArr() {
		return this.eBookShortTraditionalChineseStrArr;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookTotalLineNum() {
		return this.eBookTotalLineNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookEachLineLen() {
		return this.eBookEachLineLen;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsInit() {
		return this.isInit;
	}
}
