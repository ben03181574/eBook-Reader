package idv.pcku.gui.container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import idv.pcku.ebook.EBookInfo;
import idv.pcku.ebook.EBookMark;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookReaderWindow extends JFrame{
	public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.7);
	public static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.7);
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem fileChooseItem;
	private JMenuItem closeFileItem;
	private JMenuItem fullScreenItem;
	private JMenuItem exitFullScreenItem;
	private JMenuItem aboutItem;
	private JMenuItem exitItem;

	private JMenu styleMenu;
	
	private JMenu fontSizeMenu;
	private ButtonGroup fontSizeButtonGroup;
	private JRadioButtonMenuItem[] fontSizeItems;
	private int[] fontSizes = {12, 14, 16, 20, 22, 24, 28, 32, 36, 40};
	
	private JMenu fontStyleMenu;
	private ButtonGroup fontStyleButtonGroup;
	private JRadioButtonMenuItem[] fontStyleItems;
	private String[] fontStyles = {"預設", "粗體", "斜體"};
	
	private JMenu fontModeMenu;
	private ButtonGroup fontModeButtonGroup;
	private JRadioButtonMenuItem[] fontModeItems;
	private String[] fontModes = {"日間模式", "夜間模式"};
	
	private JMenu bookMarkMenu;
	private JMenuItem addMarkItem;
	private JMenuItem manageMarkItem;

	private JMenu toolMenu;
	private JMenuItem jumpTopItem;
	private JMenuItem jumpItem;
	private JMenuItem jumpBottomItem;
	
	private EBookNextPanel eBookNextPanel;
	private EBookPanel eBookPanel;
	private EBookPreviousPanel eBookPreviousPanel;
	
	private EBookInfo eBookInfo;
	private EBookMark eBookMark;
	
	private MenuBarHandler menuBarHandler;
	private PageKeyHandler pageKeyHandler;
	private ExitWindowHandler exitWindowHandler;
	
	/**
	 * 
	 * @param title
	 */
	public EBookReaderWindow(String title) {
		super(title);
		this.eBookInfo = new EBookInfo();
		this.eBookMark = new EBookMark();
		this.exitWindowHandler = new ExitWindowHandler();
		
		/**********MENU**********/
		this.menuBar = new JMenuBar();
		this.menuBarHandler = new MenuBarHandler();
		
		this.fileMenu = new JMenu("檔案");
		this.fileMenu.setMnemonic('F');
		this.fileChooseItem = new JMenuItem("打開檔案　　　　　　（Alt+O）");
		this.fileChooseItem.setMnemonic('O');
		this.fileChooseItem.addActionListener(menuBarHandler);
		this.closeFileItem = new JMenuItem("關閉檔案　　　　　　（Alt+C）");
		this.closeFileItem.setMnemonic('C');
		this.closeFileItem.addActionListener(menuBarHandler);
		this.fullScreenItem = new JMenuItem("全螢幕模式　　　　　   （F11）");
		this.fullScreenItem.addActionListener(menuBarHandler);
		this.exitFullScreenItem = new JMenuItem("退出全螢幕　　　　　   （Esc）");
		this.exitFullScreenItem.addActionListener(menuBarHandler);
		this.aboutItem = new JMenuItem("關於　　　　　　　　（Alt+A）");
		this.aboutItem.setMnemonic('A');
		this.aboutItem.addActionListener(menuBarHandler);
		this.exitItem = new JMenuItem("離開　　　　　　　　（Alt+E）");
		this.exitItem.setMnemonic('E');
		this.exitItem.addActionListener(menuBarHandler);
		this.fileMenu.add(fileChooseItem);
		this.fileMenu.add(closeFileItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(fullScreenItem);
		this.fileMenu.add(exitFullScreenItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(aboutItem);
		this.fileMenu.add(exitItem);
		
		this.styleMenu = new JMenu("樣式");
		this.styleMenu.setMnemonic('S');
		
		this.fontSizeMenu = new JMenu("字體大小　");
		this.fontSizeButtonGroup = new ButtonGroup();
		this.fontSizeItems = new JRadioButtonMenuItem[fontSizes.length];
		for(int i=0;i<fontSizes.length;i++) {
			this.fontSizeItems[i] = new JRadioButtonMenuItem(String.valueOf(fontSizes[i]));
			this.fontSizeItems[i].addActionListener(menuBarHandler);
			this.fontSizeMenu.add(fontSizeItems[i]);
			this.fontSizeButtonGroup.add(fontSizeItems[i]);
		}
		this.fontSizeItems[1].setSelected(true);
		
		this.fontStyleMenu = new JMenu("字體樣式　");
		this.fontStyleButtonGroup = new ButtonGroup();
		this.fontStyleItems = new JRadioButtonMenuItem[fontStyles.length];
		for(int i=0;i<fontStyles.length;i++) {
			this.fontStyleItems[i] = new JRadioButtonMenuItem(fontStyles[i]);
			this.fontStyleItems[i].addActionListener(menuBarHandler);
			this.fontStyleMenu.add(fontStyleItems[i]);
			this.fontStyleButtonGroup.add(fontStyleItems[i]);
		}
		this.fontStyleItems[0].setSelected(true);
		
		this.fontModeMenu = new JMenu("字體模式　");
		this.fontModeButtonGroup = new ButtonGroup();
		this.fontModeItems = new JRadioButtonMenuItem[fontModes.length];
		for(int i=0;i<fontModes.length;i++) {
			this.fontModeItems[i] = new JRadioButtonMenuItem(fontModes[i]);
			this.fontModeItems[i].addActionListener(menuBarHandler);
			this.fontModeMenu.add(fontModeItems[i]);
			this.fontModeButtonGroup.add(fontModeItems[i]);
		}
		this.fontModeItems[0].setSelected(true);
		
		this.styleMenu.add(fontSizeMenu);
		this.styleMenu.add(fontStyleMenu);
		this.styleMenu.add(fontModeMenu);
		
		this.toolMenu = new JMenu("工具");
		this.toolMenu.setMnemonic('T');
		this.jumpTopItem = new JMenuItem("跳至首頁　");
		this.jumpTopItem.addActionListener(menuBarHandler);
		this.jumpItem = new JMenuItem("跳至…頁　");
		this.jumpItem.addActionListener(menuBarHandler);
		this.jumpBottomItem = new JMenuItem("跳至末頁　");
		this.jumpBottomItem.addActionListener(menuBarHandler);
		this.toolMenu.add(jumpTopItem);
		this.toolMenu.add(jumpItem);
		this.toolMenu.add(jumpBottomItem);

		this.bookMarkMenu = new JMenu("書籤");
		this.bookMarkMenu.setMnemonic('M');
		this.addMarkItem = new JMenuItem("加入書籤");
		this.addMarkItem.addActionListener(menuBarHandler);
		this.manageMarkItem = new JMenuItem("管理書籤");
		this.manageMarkItem.addActionListener(menuBarHandler);
		this.bookMarkMenu.add(addMarkItem);
		this.bookMarkMenu.add(manageMarkItem);
		
		this.menuBar.add(fileMenu);
		this.menuBar.add(styleMenu);
		this.menuBar.add(toolMenu);
		this.menuBar.add(bookMarkMenu);
		setJMenuBar(menuBar);
		
		this.resetMenuBar();
		/************************/

		addWindowListener(exitWindowHandler);
		
		setResizable(false);
		setFocusable(true);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
		setMaximumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
		setMinimumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	 * 
	 */
	private void resetMenuBar() {
		if(!this.eBookInfo.getIsInit()) {
			this.fullScreenItem.setEnabled(false);
			this.exitFullScreenItem.setEnabled(false);
			this.fontSizeMenu.setEnabled(false);
			this.fontStyleMenu.setEnabled(false);
			this.fontModeMenu.setEnabled(false);
			this.jumpTopItem.setEnabled(false);
			this.jumpItem.setEnabled(false);
			this.jumpBottomItem.setEnabled(false);
			this.fileChooseItem.setEnabled(true);
			this.closeFileItem.setEnabled(false);
			this.addMarkItem.setEnabled(false);
			this.manageMarkItem.setEnabled(false);
		}else {
			this.fullScreenItem.setEnabled(true);
			this.exitFullScreenItem.setEnabled(true);
			this.fontSizeMenu.setEnabled(true);
			this.fontSizeItems[1].setSelected(true);
			this.fontStyleMenu.setEnabled(true);
			this.fontStyleItems[0].setSelected(true);
			this.fontModeMenu.setEnabled(true);
			this.fontModeItems[0].setSelected(true);
			this.jumpTopItem.setEnabled(true);
			this.jumpItem.setEnabled(true);
			this.jumpBottomItem.setEnabled(true);
			this.fileChooseItem.setEnabled(false);
			this.closeFileItem.setEnabled(true);
			this.addMarkItem.setEnabled(true);
			this.manageMarkItem.setEnabled(true);			
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class ExitConfirmWindow extends JDialog implements ActionListener {
		public final int width = (int) (EBookReaderWindow.WIDTH * 0.3);
		public final int height = (int) (EBookReaderWindow.HEIGHT * 0.25);
		
		private JLabel exitConfirmLabel;
		private JPanel exitConfirmButtonPanel;
		private JButton exitButton;
		private JButton cancelButton;
		
		/**
		 * 
		 * @param parentContainer
		 */
		public ExitConfirmWindow(JFrame parentContainer) {
			super(parentContainer);
			
			exitConfirmLabel = new JLabel("<html><font size=\"5\" face=\"標楷體\" text-align=center;>您確定要離開嗎？</font><html>",SwingConstants.CENTER);
			exitConfirmButtonPanel = new JPanel();
			exitConfirmButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
			exitButton = new JButton("離開");
			exitButton.addActionListener(this);
			cancelButton = new JButton("返回");
			cancelButton.addActionListener(this);
			exitConfirmButtonPanel.add(cancelButton, BorderLayout.EAST);
			exitConfirmButtonPanel.add(exitButton, BorderLayout.WEST);
			
			setTitle("即將離開");
			setLayout(new BorderLayout());
			setResizable(false);
			setPreferredSize(new Dimension(width, height));
			setMaximumSize(new Dimension(width, height));
			setMinimumSize(new Dimension(width, height));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			add(exitConfirmLabel, BorderLayout.CENTER);
			add(exitConfirmButtonPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "離開":
					System.exit(0);
					break;
				case "返回":
					dispose();
					break;
			}
			
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class JumpToPageWindow extends JDialog implements ActionListener, KeyListener {
		public final int width = (int) (EBookReaderWindow.WIDTH * 0.5);
		public final int height = (int) (EBookReaderWindow.HEIGHT * 0.2);
		
		private JPanel jumpToPagePanel;
		private JTextField jumpToPageTextField;
		private JButton jumpToPageButton;
		
		private String jumpToPageNumStr;
		private int jumpToPageNum;
		
		/**
		 * 
		 * @param parentContainer
		 */
		public JumpToPageWindow(JFrame parentContainer) {
			super(parentContainer,true);
			
			this.jumpToPagePanel = new JPanel();
			this.jumpToPagePanel.setLayout(new BoxLayout(jumpToPagePanel,BoxLayout.LINE_AXIS));
			this.jumpToPageTextField = new JTextField();
			this.jumpToPageTextField.addKeyListener(this);
			this.jumpToPageTextField.setPreferredSize(new Dimension((int) (width*0.4), (int) (height*0.3)));
			this.jumpToPageTextField.setMaximumSize(new Dimension((int) (width*0.4), (int) (height*0.3)));
			this.jumpToPageTextField.setMinimumSize(new Dimension((int) (width*0.4), (int) (height*0.3)));
			this.jumpToPageButton = new JButton("跳至…");
			this.jumpToPageButton.setPreferredSize(new Dimension((int) (width*0.2), (int) (height*0.3)));
			this.jumpToPageButton.setMaximumSize(new Dimension((int) (width*0.2), (int) (height*0.3)));
			this.jumpToPageButton.setMinimumSize(new Dimension((int) (width*0.2), (int) (height*0.3)));
			this.jumpToPageButton.addActionListener(this);

			this.jumpToPagePanel.add(Box.createHorizontalGlue());
			this.jumpToPagePanel.add(jumpToPageTextField);
			this.jumpToPagePanel.add(Box.createHorizontalGlue());
			this.jumpToPagePanel.add(new JSeparator(SwingConstants.VERTICAL));
			this.jumpToPagePanel.add(jumpToPageButton);
			this.jumpToPagePanel.add(Box.createHorizontalGlue());

			setTitle("請輸入頁數");
			setResizable(false);
			setPreferredSize(new Dimension(width, height));
			setMaximumSize(new Dimension(width, height));
			setMinimumSize(new Dimension(width, height));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			add(jumpToPagePanel);		
		}
		
		/**
		 * 
		 */
		public void jumpToPage() {
			this.jumpToPageNumStr = this.jumpToPageTextField.getText();
			try {
				this.jumpToPageNum = Integer.parseInt(this.jumpToPageNumStr);		
				if(this.jumpToPageNum >= EBookReaderWindow.this.eBookPanel.getEBookPageMinNum() && this.jumpToPageNum <= EBookReaderWindow.this.eBookPanel.getEBookPageMaxNum()) {
					EBookReaderWindow.this.eBookPanel.turnToPage(this.jumpToPageNum);
					JumpToPageWindow.this.dispose();				
				}else {
					JOptionPane.showMessageDialog(JumpToPageWindow.this,"超出電子書頁數範圍，請重新輸入。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
					this.jumpToPageTextField.setText("");
				}			
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(JumpToPageWindow.this,"請輸入正確數字。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
				this.jumpToPageTextField.setText("");
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jumpToPage();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(KeyEvent.VK_ENTER == e.getKeyCode()) {
				jumpToPage();
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// do nothing
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// do nothing
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class ManageMarkWindow extends JDialog implements ActionListener {
		public final int width = (int) (EBookReaderWindow.WIDTH * 0.3);
		public final int height = (int) (EBookReaderWindow.HEIGHT * 0.85);
		
		private JPanel[] manageMarkUpPanel;
		private JPanel[] manageMarkDownPanel;
		private JLabel[] manageMarkLabel;
		private JButton[] deleteMarkButton;
		private JButton[] moveMarkButton;
		
		/**
		 * 
		 * @param parentContainer
		 */
		public ManageMarkWindow(JFrame parentContainer) {
			super(parentContainer,true);
			this.manageMarkUpPanel = new JPanel[EBookReaderWindow.this.eBookMark.getEBookMark().size()];
			this.manageMarkDownPanel = new JPanel[EBookReaderWindow.this.eBookMark.getEBookMark().size()];
			this.manageMarkLabel = new JLabel[EBookReaderWindow.this.eBookMark.getEBookMark().size()];
			this.deleteMarkButton = new JButton[EBookReaderWindow.this.eBookMark.getEBookMark().size()];
			this.moveMarkButton = new JButton[EBookReaderWindow.this.eBookMark.getEBookMark().size()];
			
			Iterator<Integer> iterator = EBookReaderWindow.this.eBookMark.getEBookMark().iterator();
			for(int i=0;i<EBookReaderWindow.this.eBookMark.getEBookMark().size();i++) {
				String pageNumStr = iterator.next().toString();
				this.manageMarkUpPanel[i] = new JPanel();
				this.manageMarkDownPanel[i] = new JPanel();
				this.manageMarkLabel[i] = new JLabel("書籤 "+(i+1)+" (頁數 : "+pageNumStr+")");
				this.deleteMarkButton[i] = new JButton("刪除書籤");
				this.moveMarkButton[i] = new JButton("移至書籤");
				
				this.deleteMarkButton[i].addActionListener(this);
				this.deleteMarkButton[i].setActionCommand("delete/"+pageNumStr);
				this.moveMarkButton[i].addActionListener(this);
				this.moveMarkButton[i].setActionCommand("move/"+pageNumStr);
				
				this.manageMarkUpPanel[i].add(this.manageMarkLabel[i]);
				this.manageMarkDownPanel[i].add(this.deleteMarkButton[i]);
				this.manageMarkDownPanel[i].add(this.moveMarkButton[i]);

				add(this.manageMarkUpPanel[i]);
				add(this.manageMarkDownPanel[i]);
			}
			
			setTitle("管理書籤");
			setResizable(false);
			setLayout(new FlowLayout());
			setPreferredSize(new Dimension(width, height));
			setMaximumSize(new Dimension(width, height));
			setMinimumSize(new Dimension(width, height));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		}
		
		/**
		 * 
		 */
		public void resetManageMarkWindow() {
			for(int i=0;i<manageMarkUpPanel.length;i++) {
				remove(manageMarkUpPanel[i]);
			}
			for(int i=0;i<manageMarkDownPanel.length;i++) {
				remove(manageMarkDownPanel[i]);
			}
			
			Iterator<Integer> iterator = EBookReaderWindow.this.eBookMark.getEBookMark().iterator();
			for(int i=0;i<EBookReaderWindow.this.eBookMark.getEBookMark().size();i++) {
				String pageNumStr = iterator.next().toString();
				this.manageMarkUpPanel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
				this.manageMarkDownPanel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
				this.manageMarkLabel[i] = new JLabel("書籤 "+(i+1)+" (頁數 : "+pageNumStr+")");
				this.deleteMarkButton[i] = new JButton("刪除書籤");
				this.moveMarkButton[i] = new JButton("移至書籤");
				
				this.deleteMarkButton[i].addActionListener(this);
				this.deleteMarkButton[i].setActionCommand("delete/"+pageNumStr);
				this.moveMarkButton[i].addActionListener(this);
				this.moveMarkButton[i].setActionCommand("move/"+pageNumStr);
				
				this.manageMarkUpPanel[i].add(this.manageMarkLabel[i]);
				this.manageMarkDownPanel[i].add(this.deleteMarkButton[i]);
				this.manageMarkDownPanel[i].add(this.moveMarkButton[i]);

				add(this.manageMarkUpPanel[i]);
				add(this.manageMarkDownPanel[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String type = e.getActionCommand().split("/")[0];
			String pageNumStr = e.getActionCommand().split("/")[1];
			int pageNum = Integer.parseInt(pageNumStr);
			
			switch(type) {
				case "delete":
					EBookReaderWindow.this.eBookMark.deleteEBookMark(pageNum);
					if(EBookReaderWindow.this.eBookMark.getEBookMark().size()>0) {
						resetManageMarkWindow();
						repaint();
						revalidate();
					}else {
						dispose();
					}
					break;
				case "move":
					EBookReaderWindow.this.eBookPanel.turnToPage(pageNum);
					dispose();
					break;
			}
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class ExitWindowHandler extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			ExitConfirmWindow exitConfirmWindow = new ExitConfirmWindow(EBookReaderWindow.this);
			exitConfirmWindow.setLocationRelativeTo(getContentPane());
			exitConfirmWindow.setVisible(true);
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class MenuBarHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "打開檔案　　　　　　（Alt+O）":
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter restrict = new FileNameExtensionFilter("txt files (*.txt)", "txt");
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.addChoosableFileFilter(restrict);
					if (fileChooser.showOpenDialog(EBookReaderWindow.this) == JFileChooser.APPROVE_OPTION){
						EBookReaderWindow.this.eBookInfo.initEBook(fileChooser.getSelectedFile().getAbsolutePath());
						EBookReaderWindow.this.eBookPanel = new EBookPanel(EBookReaderWindow.this.eBookInfo);
						EBookReaderWindow.this.eBookNextPanel = new EBookNextPanel(EBookReaderWindow.this.eBookPanel);
						EBookReaderWindow.this.eBookPreviousPanel = new EBookPreviousPanel(EBookReaderWindow.this.eBookPanel);
						EBookReaderWindow.this.pageKeyHandler = new PageKeyHandler();
						
						EBookReaderWindow.this.add(eBookNextPanel,BorderLayout.WEST);
						EBookReaderWindow.this.add(eBookPanel,BorderLayout.CENTER);
						EBookReaderWindow.this.add(eBookPreviousPanel,BorderLayout.EAST);
						EBookReaderWindow.this.addKeyListener(pageKeyHandler);
						
						if(EBookReaderWindow.this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
							EBookReaderWindow.this.eBookPanel.setEBookSize(Toolkit.getDefaultToolkit().getScreenSize().width - EBookNextPanel.WIDTH - EBookPreviousPanel.WIDTH, Toolkit.getDefaultToolkit().getScreenSize().height);
						}

						EBookReaderWindow.this.resetMenuBar();
						EBookReaderWindow.this.repaint();
						EBookReaderWindow.this.revalidate();
					} 
					break;
				case "關閉檔案　　　　　　（Alt+C）":
					EBookReaderWindow.this.eBookInfo.resetEBookInfo();
					EBookReaderWindow.this.eBookMark.resetEBookMark();
					EBookReaderWindow.this.remove(EBookReaderWindow.this.eBookPanel);
					EBookReaderWindow.this.remove(EBookReaderWindow.this.eBookNextPanel);
					EBookReaderWindow.this.remove(EBookReaderWindow.this.eBookPreviousPanel);
					EBookReaderWindow.this.removeKeyListener(pageKeyHandler);

					EBookReaderWindow.this.resetMenuBar();	
					EBookReaderWindow.this.repaint();
					EBookReaderWindow.this.revalidate();					
					break;
				case "全螢幕模式　　　　　   （F11）":
					EBookReaderWindow.this.dispose();
					EBookReaderWindow.this.setUndecorated(true);
					EBookReaderWindow.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
					EBookReaderWindow.this.eBookPanel.setEBookSize(Toolkit.getDefaultToolkit().getScreenSize().width - EBookNextPanel.WIDTH - EBookPreviousPanel.WIDTH, Toolkit.getDefaultToolkit().getScreenSize().height);
					EBookReaderWindow.this.setVisible(true);
					break;
				case "退出全螢幕　　　　　   （Esc）":
					EBookReaderWindow.this.dispose();
					EBookReaderWindow.this.setUndecorated(false);
					EBookReaderWindow.this.setExtendedState(JFrame.NORMAL);
					EBookReaderWindow.this.setPreferredSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.setMaximumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.setMinimumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.eBookPanel.setEBookSize(EBookReaderWindow.WIDTH - EBookPreviousPanel.WIDTH - EBookNextPanel.WIDTH, EBookReaderWindow.HEIGHT);
					EBookReaderWindow.this.setVisible(true);
					EBookReaderWindow.this.pack();
					break;
				case "關於　　　　　　　　（Alt+A）":
					JEditorPane jEditorPane =new JEditorPane("text/html","<html><body>Contributed by Kuo, Po-Cheng.<br/>"
							+ "If you want to see more personal project, please visit my "
			                + "<a href=\"https://github.com/ben03181574\">Websit</a><br/><br/>"
			                + "Also, If you find any problem, please feel free to contact me.</body></html>");
					jEditorPane.addHyperlinkListener(new HyperlinkListener() {
						@Override
						public void hyperlinkUpdate(HyperlinkEvent e) {
							if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
								try {
									Desktop.getDesktop().browse(new URI(e.getURL().toString()));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (URISyntaxException e1) {
									// TODO Auto-generated catch block
								}
							}
						}
					});
					jEditorPane.setEditable(false);
					jEditorPane.setBackground(null);
					JOptionPane.showMessageDialog(EBookReaderWindow.this,jEditorPane,"關於",JOptionPane.INFORMATION_MESSAGE);
					break;
				case "離開　　　　　　　　（Alt+E）":
					System.exit(0);
					break;
				case "跳至首頁　":
					EBookReaderWindow.this.eBookPanel.turnToPage(0);
					break;
				case "跳至…頁　":
					JumpToPageWindow jumpToPageWindow = new JumpToPageWindow(EBookReaderWindow.this);
					jumpToPageWindow.setLocationRelativeTo(getContentPane());
					jumpToPageWindow.setVisible(true);
					break;
				case "跳至末頁　":
					EBookReaderWindow.this.eBookPanel.turnToPage(EBookReaderWindow.this.eBookPanel.getEBookPageMaxNum());
					break;
				case "加入書籤":
					if(EBookReaderWindow.this.eBookMark.getEBookMark().size() < 5) {
						EBookReaderWindow.this.eBookMark.appendEBookMark(EBookReaderWindow.this.eBookPanel.getEBookPageNum());
						JOptionPane.showMessageDialog(EBookReaderWindow.this,"加入成功！","通知訊息",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(EBookReaderWindow.this,"加入失敗！超過書籤數量上限","通知訊息",JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "管理書籤":
					ManageMarkWindow manageMarkWindow = new ManageMarkWindow(EBookReaderWindow.this);
					manageMarkWindow.setLocationRelativeTo(getContentPane());
					manageMarkWindow.setVisible(true);
					break;
				default:
					for(int i=0;i<fontSizeItems.length;i++) {
						if(fontSizeItems[i].isSelected()) {
							EBookReaderWindow.this.eBookPanel.setEBookTextFontSize(EBookReaderWindow.this.fontSizes[i]);
						}
					}
					for(int i=0;i<fontStyleItems.length;i++) {
						if(fontStyleItems[i].isSelected()) {
							EBookReaderWindow.this.eBookPanel.setEBookTextFontStyle(i);
						}
					}
					for(int i=0;i<fontModeItems.length;i++) {
						if(fontModeItems[i].isSelected()) {
							EBookReaderWindow.this.eBookPanel.setEBookTextFontMode(EBookReaderWindow.this.fontModes[i]);
							if(i==0) {
								EBookReaderWindow.this.eBookPanel.setBackground(null);
								EBookReaderWindow.this.eBookNextPanel.setBackground(null);
								EBookReaderWindow.this.eBookPreviousPanel.setBackground(null);								
							}else if(i==1) {
								EBookReaderWindow.this.eBookPanel.setBackground(Color.DARK_GRAY);
								EBookReaderWindow.this.eBookNextPanel.setBackground(Color.DARK_GRAY);
								EBookReaderWindow.this.eBookPreviousPanel.setBackground(Color.DARK_GRAY);
							}
						}
					}
			}
		}
	}
	
	/**
	 * 
	 * @author KuoPoCheng
	 *
	 */
	private class PageKeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_RIGHT:
					if(EBookReaderWindow.this.eBookPanel.getEBookPageNum()-2 >= EBookReaderWindow.this.eBookPanel.getEBookPageMinNum() && EBookReaderWindow.this.eBookPanel.getEBookPageNum()-2 <= EBookReaderWindow.this.eBookPanel.getEBookPageMaxNum()) {
						EBookReaderWindow.this.eBookPanel.turnToPage(EBookReaderWindow.this.eBookPanel.getEBookPageNum()-2);				
					}else {
						JOptionPane.showMessageDialog(EBookReaderWindow.this,"已經是第一頁了。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
					}
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_LEFT:
					if(EBookReaderWindow.this.eBookPanel.getEBookPageNum()+2 >= EBookReaderWindow.this.eBookPanel.getEBookPageMinNum() && EBookReaderWindow.this.eBookPanel.getEBookPageNum()+2 <= EBookReaderWindow.this.eBookPanel.getEBookPageMaxNum()) {
						EBookReaderWindow.this.eBookPanel.turnToPage(EBookReaderWindow.this.eBookPanel.getEBookPageNum()+2);				
					}else {
						JOptionPane.showMessageDialog(EBookReaderWindow.this,"已經是最後一頁了。","錯誤訊息",JOptionPane.ERROR_MESSAGE);
					}
					break;
				case KeyEvent.VK_F11:
					if(EBookReaderWindow.this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
						EBookReaderWindow.this.dispose();
						EBookReaderWindow.this.setUndecorated(false);
						EBookReaderWindow.this.setExtendedState(JFrame.NORMAL);
						EBookReaderWindow.this.setPreferredSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
						EBookReaderWindow.this.setMaximumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
						EBookReaderWindow.this.setMinimumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
						EBookReaderWindow.this.eBookPanel.setEBookSize(EBookReaderWindow.WIDTH - EBookPreviousPanel.WIDTH - EBookNextPanel.WIDTH, EBookReaderWindow.HEIGHT);
						EBookReaderWindow.this.setVisible(true);
						EBookReaderWindow.this.pack();
						break;					
					}else {
						EBookReaderWindow.this.dispose();
						EBookReaderWindow.this.setUndecorated(true);
						EBookReaderWindow.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
						EBookReaderWindow.this.eBookPanel.setEBookSize(Toolkit.getDefaultToolkit().getScreenSize().width - EBookNextPanel.WIDTH - EBookPreviousPanel.WIDTH, Toolkit.getDefaultToolkit().getScreenSize().height);
						EBookReaderWindow.this.setVisible(true);
					}
					break;
				case KeyEvent.VK_ESCAPE:
					EBookReaderWindow.this.dispose();
					EBookReaderWindow.this.setUndecorated(false);
					EBookReaderWindow.this.setExtendedState(JFrame.NORMAL);
					EBookReaderWindow.this.setPreferredSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.setMaximumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.setMinimumSize(new Dimension(EBookReaderWindow.WIDTH, EBookReaderWindow.HEIGHT));
					EBookReaderWindow.this.eBookPanel.setEBookSize(EBookReaderWindow.WIDTH - EBookPreviousPanel.WIDTH - EBookNextPanel.WIDTH, EBookReaderWindow.HEIGHT);
					EBookReaderWindow.this.setVisible(true);
					EBookReaderWindow.this.pack();
					break;			
			}
		}
	}
}
