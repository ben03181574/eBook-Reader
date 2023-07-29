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
public class EBookNextPageLabel extends JLabel {
	private Image nextPageImg;
	private ImageIcon nextPageImgIcon;
	private NextPageMouseHandler nextPageMouseHandler;
	
	private EBookPanel eBookPanel;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param imgFile
	 * @param eBookPanel
	 */
	public EBookNextPageLabel(int width, int height, URL imgFile, EBookPanel eBookPanel) {
		this.nextPageImg = new ImageIcon(imgFile).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		this.nextPageImgIcon = new ImageIcon(nextPageImg);
		this.nextPageMouseHandler = new NextPageMouseHandler();
		this.eBookPanel = eBookPanel;
		
		setIcon(nextPageImgIcon);
		
		addMouseListener(nextPageMouseHandler);
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class NextPageMouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(EBookNextPageLabel.this.eBookPanel.getEBookPageNum()+2 >= EBookNextPageLabel.this.eBookPanel.getEBookPageMinNum() && EBookNextPageLabel.this.eBookPanel.getEBookPageNum()+2 <= EBookNextPageLabel.this.eBookPanel.getEBookPageMaxNum()) {
				EBookNextPageLabel.this.eBookPanel.turnToPage(EBookNextPageLabel.this.eBookPanel.getEBookPageNum()+2);				
			}else {
				JOptionPane.showMessageDialog(getRootPane(),"已經是最後一頁了。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
