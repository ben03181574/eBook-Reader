package idv.pcku.gui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import idv.pcku.util.GetText;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookLeftTextArea extends JTextArea {
	private Font eBookLeftFont;
	private TitledBorder eBookLeftTitleBorder;
	private Insets insets;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public EBookLeftTextArea(int width, int height, String title) {
		this.eBookLeftFont = new Font("標楷體", Font.PLAIN, 16);
		this.eBookLeftTitleBorder = BorderFactory.createTitledBorder("");
		this.eBookLeftTitleBorder.setTitle(title);
		this.eBookLeftTitleBorder.setTitleJustification(TitledBorder.LEFT);
		this.insets = eBookLeftTitleBorder.getBorderInsets(this);

        setFont(eBookLeftFont);
        setBorder(eBookLeftTitleBorder);
        setEditable(false);
		setMaximumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setMinimumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setPreferredSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void setEBookLeftSize(int width, int height) {
		setMaximumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setMinimumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setPreferredSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setEBookLeftTitle(String title) {
		this.eBookLeftTitleBorder.setTitle(title);
	}
	
	/**
	 * 
	 * @param rowStrArr
	 * @param page
	 * @param perPage
	 */
	public void setEBookLeftText(List<String> rowStrArr, int page, int perPage) {
		setText("");
		String[] eBookLeftText = GetText.getFromRow(GetText.getFromPage(rowStrArr, page, perPage));
		for(int i=0;i<eBookLeftText.length;i++) {
			for(int j=0;j<eBookLeftText[i].length();j++) {
				append(String.format(" %s ",eBookLeftText[i].charAt(j)));
			}
			append("\r\n");
		}
	}
	
	/**
	 * 
	 * @param fontSize
	 */
	public void setEBookLeftTextFontSize(int fontSize) {
		this.eBookLeftFont = new Font(eBookLeftFont.getFamily(), eBookLeftFont.getStyle(), fontSize);
		setFont(eBookLeftFont);
	}
	
	/**
	 * 
	 * @param fontStyle
	 */
	public void setEBookLeftTextFontStyle(int fontStyle) {
		this.eBookLeftFont = new Font(eBookLeftFont.getFamily(), fontStyle, eBookLeftFont.getSize());
		setFont(eBookLeftFont);
	}
	
	/**
	 * 
	 * @param fontStyle
	 */
	public void setEBookLeftTextFontMode(String fontMode) {
		if("日間模式".equals(fontMode)) {
	        this.eBookLeftTitleBorder.setTitleColor(Color.BLACK);
	        setBackground(Color.WHITE);
	        setForeground(Color.BLACK);
		}else if("夜間模式".equals(fontMode)){
	        this.eBookLeftTitleBorder.setTitleColor(Color.LIGHT_GRAY);
	        setBackground(Color.DARK_GRAY);
	        setForeground(Color.WHITE);
		}
	}
}
