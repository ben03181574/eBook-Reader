package idv.pcku.gui.container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import idv.pcku.gui.component.EBookNextPageLabel;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookNextPanel extends JPanel {
	public static final int WIDTH = (int) (EBookReaderWindow.WIDTH * 0.05);
	public static final int HEIGHT = EBookReaderWindow.HEIGHT;
	
	private EBookNextPageLabel nextPageLabel;
			
	/**
	 * 
	 * @param eBookPanel
	 */
	public EBookNextPanel(EBookPanel eBookPanel) {	
		this.nextPageLabel = new EBookNextPageLabel(WIDTH, HEIGHT/3, getClass().getResource("/NextPage.png"), eBookPanel);
        	
		setLayout(new BoxLayout(EBookNextPanel.this,BoxLayout.PAGE_AXIS));
        
        add(Box.createVerticalGlue());
        add(nextPageLabel);
        add(Box.createVerticalGlue());
	}
}
