package idv.pcku.gui.container;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import idv.pcku.ebook.EBookInfo;
import idv.pcku.gui.component.EBookLeftTextArea;
import idv.pcku.gui.component.EBookRightTextArea;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookPanel extends JPanel {
	public static final int BORDERTHICKNESS = 2;
	public static final int WIDTH = EBookReaderWindow.WIDTH - EBookPreviousPanel.WIDTH - EBookNextPanel.WIDTH;
	public static final int HEIGHT = EBookReaderWindow.HEIGHT;

	private int eBookPageNum;
	private int eBookPageMinNum;
	private int eBookPageMaxNum;
	private int eBookPagePerColNum;
	private int eBookPagePerRowNum;
	private EBookInfo eBookInfo;
	private EBookLeftTextArea eBookLeftTextArea;
	private EBookRightTextArea eBookRightTextArea;
	
	/**
	 * 
	 * @param eBookInfo
	 */
	public EBookPanel(EBookInfo eBookInfo) {
		this.eBookInfo = eBookInfo;
		this.eBookPageNum = 0;
		this.eBookPageMinNum = 0;
		this.eBookPagePerColNum = 11;
		this.eBookPagePerRowNum = eBookInfo.getEBookEachLineLen();
		this.eBookPageMaxNum = (eBookInfo.getEBookTotalLineNum()%this.eBookPagePerColNum == 0)?eBookInfo.getEBookTotalLineNum()/this.eBookPagePerColNum:(eBookInfo.getEBookTotalLineNum()/this.eBookPagePerColNum)+1;
		
		this.eBookRightTextArea = new EBookRightTextArea(WIDTH/2-BORDERTHICKNESS, HEIGHT-BORDERTHICKNESS*2, eBookInfo.getEBookBaseName()+"_"+this.getEBookPageNum());
		this.eBookRightTextArea.setEBookRightText(eBookInfo.getEBookShortTraditionalChineseStrArr(), this.eBookPageNum, this.eBookPagePerColNum);
		this.eBookLeftTextArea = new EBookLeftTextArea(WIDTH/2-BORDERTHICKNESS, HEIGHT-BORDERTHICKNESS*2, eBookInfo.getEBookBaseName()+"_"+(this.getEBookPageNum()+1));
		this.eBookLeftTextArea.setEBookLeftText(eBookInfo.getEBookShortTraditionalChineseStrArr(), this.eBookPageNum+1, this.eBookPagePerColNum);

		setLayout(new BoxLayout(EBookPanel.this,BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, BORDERTHICKNESS));
		
        add(this.eBookLeftTextArea);
        add(Box.createHorizontalGlue());
        add(new JSeparator(SwingConstants.VERTICAL));
        add(this.eBookRightTextArea);
	}
	
	/**
	 * 
	 * @param pageNum
	 */
	public void turnToPage(int pageNum) {
		if(pageNum >= this.eBookPageMinNum && pageNum <= this.eBookPageMaxNum) {
			this.eBookPageNum = (pageNum%2 == 0)?pageNum:pageNum-1;
			this.eBookRightTextArea.setEBookRightTitle(this.eBookInfo.getEBookBaseName()+"_"+this.eBookPageNum);
			this.eBookRightTextArea.setEBookRightText(eBookInfo.getEBookShortTraditionalChineseStrArr(), this.eBookPageNum, this.eBookPagePerColNum);
			this.eBookLeftTextArea.setEBookLeftTitle(this.eBookInfo.getEBookBaseName()+"_"+(this.eBookPageNum+1));
			this.eBookLeftTextArea.setEBookLeftText(eBookInfo.getEBookShortTraditionalChineseStrArr(), this.eBookPageNum+1, this.eBookPagePerColNum);
		}
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void setEBookSize(int width, int height){
		this.eBookLeftTextArea.setEBookLeftSize(width/2-BORDERTHICKNESS, height-BORDERTHICKNESS*2);
		this.eBookRightTextArea.setEBookRightSize(width/2-BORDERTHICKNESS, height-BORDERTHICKNESS*2);
	}
	
	/**
	 * 
	 * @param fontSize
	 */
	public void setEBookTextFontSize(int fontSize){
		this.eBookLeftTextArea.setEBookLeftTextFontSize(fontSize);
		this.eBookRightTextArea.setEBookRightTextFontSize(fontSize);
	}
	
	/**
	 * 
	 * @param fontStyle
	 */
	public void setEBookTextFontStyle(int fontStyle){
		this.eBookLeftTextArea.setEBookLeftTextFontStyle(fontStyle);
		this.eBookRightTextArea.setEBookRightTextFontStyle(fontStyle);
	}
	
	/**
	 * 
	 * @param fontMode
	 */
	public void setEBookTextFontMode(String fontMode){
		this.eBookLeftTextArea.setEBookLeftTextFontMode(fontMode);
		this.eBookRightTextArea.setEBookRightTextFontMode(fontMode);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookPageNum() {
		return this.eBookPageNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookPagePerColNum() {
		return this.eBookPagePerColNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookPagePerRowNum() {
		return this.eBookPagePerRowNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookPageMinNum() {
		return this.eBookPageMinNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEBookPageMaxNum() {
		return this.eBookPageMaxNum;
	}
}
