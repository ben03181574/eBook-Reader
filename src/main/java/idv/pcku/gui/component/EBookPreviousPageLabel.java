package idv.pcku.gui.component;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import idv.pcku.gui.container.EBookPanel;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookPreviousPageLabel extends JLabel {
	private Image previousPageImg;
	private ImageIcon previousPageImgIcon;
	private PreviousPageMouseHandler previousPageMouseHandler;
	
	private EBookPanel eBookPanel;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param imgFile
	 * @param eBookPanel
	 */
	public EBookPreviousPageLabel(int width, int height, URL imgFile, EBookPanel eBookPanel) {
		this.previousPageImg = new ImageIcon(imgFile).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		this.previousPageImgIcon = new ImageIcon(previousPageImg);
		this.previousPageMouseHandler = new PreviousPageMouseHandler();
		this.eBookPanel = eBookPanel;
		
		setIcon(previousPageImgIcon);
		
		addMouseListener(previousPageMouseHandler);
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class PreviousPageMouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(EBookPreviousPageLabel.this.eBookPanel.getEBookPageNum()-2 >= EBookPreviousPageLabel.this.eBookPanel.getEBookPageMinNum() && EBookPreviousPageLabel.this.eBookPanel.getEBookPageNum()-2 <= EBookPreviousPageLabel.this.eBookPanel.getEBookPageMaxNum()) {
				EBookPreviousPageLabel.this.eBookPanel.turnToPage(EBookPreviousPageLabel.this.eBookPanel.getEBookPageNum()-2);				
			}else {
				JOptionPane.showMessageDialog(getRootPane(),"已經是第一頁了。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
