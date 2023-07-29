package idv.pcku.gui.container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import idv.pcku.gui.component.EBookPreviousPageLabel;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookPreviousPanel extends JPanel {
	public static final int WIDTH = (int) (EBookReaderWindow.WIDTH * 0.05);
	public static final int HEIGHT = EBookReaderWindow.HEIGHT;
	
	private EBookPreviousPageLabel previousPageLabel;
	
	/**
	 * 
	 * @param eBookPanel
	 */
	public EBookPreviousPanel(EBookPanel eBookPanel) {
		this.previousPageLabel = new EBookPreviousPageLabel(WIDTH, HEIGHT/3, getClass().getResource("/PreviousPage.png"), eBookPanel);
		
		setLayout(new BoxLayout(EBookPreviousPanel.this,BoxLayout.PAGE_AXIS));
        
        add(Box.createVerticalGlue());
        add(previousPageLabel);
        add(Box.createVerticalGlue());
	}
}
