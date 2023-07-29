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
public class EBookRightTextArea extends JTextArea {
	private Font eBookRightFont;
	private TitledBorder eBookRightTitleBorder;
	private Insets insets;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public EBookRightTextArea(int width, int height, String title) {
		this.eBookRightFont = new Font("標楷體", Font.PLAIN, 16);
		this.eBookRightTitleBorder = BorderFactory.createTitledBorder("");
		this.eBookRightTitleBorder.setTitle(title);
		this.eBookRightTitleBorder.setTitleJustification(TitledBorder.RIGHT);
		this.insets = eBookRightTitleBorder.getBorderInsets(this);

        setFont(eBookRightFont);
        setBorder(eBookRightTitleBorder);
        setEditable(false);
		setPreferredSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setMaximumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setMinimumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void setEBookRightSize(int width, int height) {
		setMaximumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setMinimumSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
		setPreferredSize(new Dimension(width-insets.left-insets.right, height-insets.top-insets.bottom));
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setEBookRightTitle(String title) {
		this.eBookRightTitleBorder.setTitle(title);
	}

	/**
	 * 
	 * @param rowStrArr
	 * @param page
	 * @param per
	 */
	public void setEBookRightText(List<String> rowStrArr, int page, int per) {
		setText("");
		String[] eBookRightText = GetText.getFromRow(GetText.getFromPage(rowStrArr, page, per));
		for(int i=0;i<eBookRightText.length;i++) {
			for(int j=0;j<eBookRightText[i].length();j++) {
				append(String.format(" %s ",eBookRightText[i].charAt(j)));
			}
			append("\r\n");
		}
	}
	
	/**
	 * 
	 * @param fontSize
	 */
	public void setEBookRightTextFontSize(int fontSize) {
		this.eBookRightFont = new Font(eBookRightFont.getFamily(), eBookRightFont.getStyle(), fontSize);
		setFont(eBookRightFont);
	}
	
	/**
	 * 
	 * @param fontStyle
	 */
	public void setEBookRightTextFontStyle(int fontStyle) {
		this.eBookRightFont = new Font(eBookRightFont.getFamily(), fontStyle, eBookRightFont.getSize());
		setFont(eBookRightFont);
	}
	
	/**
	 * 
	 * @param fontMode
	 */
	public void setEBookRightTextFontMode(String fontMode) {
		if("日間模式".equals(fontMode)) {
	        this.eBookRightTitleBorder.setTitleColor(Color.BLACK);
	        setBackground(Color.WHITE);
	        setForeground(Color.BLACK);
		}else if("夜間模式".equals(fontMode)){
			this.eBookRightTitleBorder.setTitleColor(Color.LIGHT_GRAY);
	        setBackground(Color.DARK_GRAY);
	        setForeground(Color.WHITE);
		}
	}
}
