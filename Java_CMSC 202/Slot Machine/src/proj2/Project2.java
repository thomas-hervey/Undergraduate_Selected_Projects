package proj2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Project2 
{
	private static final String GAME_NAME = "Lucky Seven's";
	static {
		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", GAME_NAME);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// rely on defaults if not available
		}
	}
	
	private JFrame mainFrame;
	private JLabel creditLabel;
	private JLabel resetLabel;
	private JLabel betMaxLabel;
	private JLabel betOneLabel;
	private JLabel betAmountLabel;
	private JLabel playLabel;
	
	private SlotMachine slotMachine;
	
	public Project2(){
		 slotMachine = new SlotMachine();
		 reels = new ReelAnimation[3];
		 reels[0] = new ReelAnimation(100, slotMachine.getReel(0).getSymbols());
		 reels[1] = new ReelAnimation(200, slotMachine.getReel(1).getSymbols());
		 reels[2] = new ReelAnimation(300, slotMachine.getReel(2).getSymbols());
		 
		// create the mainFrame
		createMainFrame();
		createMainMenu();
		createReelPanel();
		createSidebarPanel();
		
				
		// create and show the gui
		mainFrame.setMinimumSize(new Dimension(860,250));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private static final Border UNSELECTED_BORDER = new EmptyBorder(5, 5, 5, 5);
	private static final Color LABEL_COLOR = new Color(255, 255, 0, 191);
	private static final Font MED_LABEL_FONT = new Font("Sans Serif", Font.BOLD, 14);
	private static final Font LG_LABEL_FONT = new Font("Sans Serif", Font.BOLD, 32);
	private static final Color CARD_BG_COLOR = new Color(35, 150, 70);
	private static final Border SELECTED_BORDER = new CompoundBorder(
			new LineBorder(new Color(255, 255, 0, 191), 3),
			new EmptyBorder(2, 2, 2, 2)
	);
	
	private void createSidebarPanel(){
		// panel to wrap everything on the left side pane
		JPanel sidePanel = new JPanel(new BorderLayout());
		sidePanel.setBorder(new EmptyBorder(10, 10, 10, 64));
		sidePanel.setOpaque(false);
		mainFrame.add(sidePanel, BorderLayout.WEST);
				
		// the bet one
		betOneLabel = new JLabel();
		betOneLabel.setIcon(new ImageIcon(getClass().getResource("images/one.jpg")));
		betOneLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				increaseBet(1);
			}
			public void mouseExited(MouseEvent e) {
				betOneLabel.setBorder(UNSELECTED_BORDER);
			}
			public void mouseEntered(MouseEvent e) {
				betOneLabel.setBorder(SELECTED_BORDER);
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		
		betOneLabel.setBackground(CARD_BG_COLOR);
		betOneLabel.setOpaque(true);
		betOneLabel.setBorder(UNSELECTED_BORDER);
		// wrap the bet one button and add to the top of the side panel
		JPanel betLabelPanel = new JPanel();
		betLabelPanel.setOpaque(false);
		betLabelPanel.setBorder(new EmptyBorder(0, 12, 0, 12));
		betLabelPanel.add(this.betOneLabel, BorderLayout.CENTER);
		sidePanel.add(betLabelPanel, BorderLayout.NORTH);
		
		// the bet max
		betMaxLabel = new JLabel();
		betMaxLabel.setIcon(new ImageIcon(getClass().getResource("images/max.jpg")));
		betMaxLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				increaseBet(slotMachine.MAX_BET);
			}
			public void mouseExited(MouseEvent e) {
				betMaxLabel.setBorder(UNSELECTED_BORDER);
			}
			public void mouseEntered(MouseEvent e) {
				betMaxLabel.setBorder(SELECTED_BORDER);
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		betMaxLabel.setBackground(CARD_BG_COLOR);
		betMaxLabel.setOpaque(true);
		betMaxLabel.setBorder(UNSELECTED_BORDER);
		// wrap the bet one button and add to the top of the side panel
		JPanel betMaxLabelPanel = new JPanel();
		betMaxLabelPanel.setOpaque(false);
		betMaxLabelPanel.setBorder(new EmptyBorder(0, 12, 0, 12));
		betLabelPanel.add(this.betMaxLabel, BorderLayout.CENTER);
		
		// the bet reset
		resetLabel = new JLabel();
		resetLabel.setIcon(new ImageIcon(getClass().getResource("images/reset.jpg")));
		resetLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				resetBet();
			}
			public void mouseExited(MouseEvent e) {
				resetLabel.setBorder(UNSELECTED_BORDER);
			}
			public void mouseEntered(MouseEvent e) {
				resetLabel.setBorder(SELECTED_BORDER);
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		resetLabel.setBackground(CARD_BG_COLOR);
		resetLabel.setOpaque(true);
		resetLabel.setBorder(UNSELECTED_BORDER);
		// wrap the bet one button and add to the top of the side panel
		betLabelPanel.add(this.resetLabel, BorderLayout.CENTER);
		
		// the play
		playLabel = new JLabel();
		playLabel.setIcon(new ImageIcon(getClass().getResource("images/spin.jpg")));
		playLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				winner.setText("");
				spin();
			}
			public void mouseExited(MouseEvent e) {
				playLabel.setBorder(UNSELECTED_BORDER);
			}
			public void mouseEntered(MouseEvent e) {
				playLabel.setBorder(SELECTED_BORDER);
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		playLabel.setBackground(CARD_BG_COLOR);
		playLabel.setOpaque(true);
		playLabel.setBorder(UNSELECTED_BORDER);
		betLabelPanel.add(this.playLabel, BorderLayout.CENTER);
		
		
		
		// title wrapped panel for the number of credits remaining
		TitledBorder cardsLeftTitle = new TitledBorder(new LineBorder(LABEL_COLOR, 2), "Credits");
		cardsLeftTitle.setTitleFont(MED_LABEL_FONT);
		cardsLeftTitle.setTitleColor(LABEL_COLOR);
		cardsLeftTitle.setTitleJustification(TitledBorder.CENTER);
		this.creditLabel = new JLabel();
		this.creditLabel.setText(""+slotMachine.getCredits());
		this.creditLabel.setFont(LG_LABEL_FONT);
		this.creditLabel.setForeground(LABEL_COLOR);
		JPanel creditPanel = new JPanel();
		creditPanel.setBorder(cardsLeftTitle);
		creditPanel.setOpaque(false);
		creditPanel.add(this.creditLabel);
		
		// title wrapped panel for the bet
		TitledBorder scoreTitle = new TitledBorder(new LineBorder(LABEL_COLOR, 2), "Bet");
		scoreTitle.setTitleFont(MED_LABEL_FONT);
		scoreTitle.setTitleColor(LABEL_COLOR);
		scoreTitle.setTitleJustification(TitledBorder.CENTER);
		this.betAmountLabel = new JLabel();
		this.betAmountLabel.setText(""+slotMachine.getBetAmount());
		this.betAmountLabel.setFont(LG_LABEL_FONT);
		this.betAmountLabel.setForeground(LABEL_COLOR);
		JPanel betAmountPanel = new JPanel();
		betAmountPanel.setBorder(scoreTitle);
		betAmountPanel.setOpaque(false);
		betAmountPanel.add(this.betAmountLabel);
		
		// wrap cards left & score panels and add to side pane
		JPanel bottomWrapperPanel = new JPanel(new BorderLayout());
		bottomWrapperPanel.setOpaque(false);
		bottomWrapperPanel.add(creditPanel, BorderLayout.NORTH);
		bottomWrapperPanel.add(betAmountPanel, BorderLayout.SOUTH);
		sidePanel.add(bottomWrapperPanel, BorderLayout.SOUTH);

		// winner bar
		winner = new JLabel("Enter Bet and Spin!");
		winner.setOpaque(true);
		sidePanel.add(winner, BorderLayout.CENTER);
	}
	
	private JLabel winner;
	
	private void createMainMenu(){
		// detect if we're running on Mac so we can make Java behave better
		boolean isMac = System.getProperty("os.name").contains("Mac");
		int mask = isMac ? InputEvent.META_MASK : InputEvent.CTRL_MASK;
		
		// main menu bar
		JMenuBar mainMenu = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu adminMenu = new JMenu("Admin");
		JMenu helpMenu = new JMenu("Help");
		
		// menu item, listener, accelerator for new random game
		JMenuItem addCreditItem = new JMenuItem("Add Credits");
		addCreditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String s = (String) JOptionPane.showInputDialog(
						Project2.this.mainFrame, 
						"Enter the amount of credits to add",
						"Add Credits", 
						JOptionPane.PLAIN_MESSAGE);
				try {
					addCredits(Integer.parseInt(s));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(
							mainFrame,
							"Unrecognizable number!", 
							"Error",
							JOptionPane.PLAIN_MESSAGE
					);
				}
			}
		});
		addCreditItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, mask));
		gameMenu.add(addCreditItem);		
		
		// menu item, listener, accelerator for help
		JMenuItem helpItem = new JMenuItem(GAME_NAME + " Help");
		helpItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JTextArea helpTextArea = new JTextArea();
				helpTextArea.setText(slotMachine.getHelpText());
				helpTextArea.setEditable(false);
				helpTextArea.setPreferredSize(new Dimension(480, 320));
				JOptionPane.showMessageDialog(
					mainFrame,
					new JScrollPane(helpTextArea),
					GAME_NAME + " Help",
					JOptionPane.PLAIN_MESSAGE
				);
			}
			
		});
		helpMenu.add(helpItem);
		
		// menu item, listener, accelerator for admin
		JMenuItem statsItem = new JMenuItem(GAME_NAME + " Statistics");
		statsItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JTextArea helpTextArea = new JTextArea();
				helpTextArea.setText(slotMachine.getMachineStatistics());
				helpTextArea.setEditable(false);
				helpTextArea.setPreferredSize(new Dimension(480, 320));
				JOptionPane.showMessageDialog(
					mainFrame,
					new JScrollPane(helpTextArea),
					GAME_NAME + " Machine Statistics",
					JOptionPane.PLAIN_MESSAGE
				);
			}
			
		});
		adminMenu.add(statsItem);
		
		// add each sub-menu to top level menu & add to frame
		mainMenu.add(gameMenu);
		mainMenu.add(adminMenu);
		mainMenu.add(helpMenu);
		mainFrame.setJMenuBar(mainMenu);
	}

	
	private void updateLabels(){
		betAmountLabel.setText(""+slotMachine.getBetAmount());
		creditLabel.setText(""+slotMachine.getCredits());
	}

	private void resetBet(){
		slotMachine.resetBet();
		updateLabels();
	}
	
	private void spin(){
		synchronized(reels){
			for(ReelAnimation reel: reels){
				reel.stop();
				reel.go();
			}
		}
		Project2.this.spin = true;
		updateLabels();
	}
	
	private void increaseBet(int amt){
		slotMachine.increaseBet(amt);
		updateLabels();
	}
	
	private void addCredits(int numCredits){
		slotMachine.addCredits(numCredits);
		updateLabels();
	}
	
	private void createReelPanel(){
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(1,3));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(reels[0]);
		panel.add(reels[1]);
		panel.add(reels[2]);
		
		mainFrame.add(panel, BorderLayout.CENTER);
	}
	
	//done
	private void createMainFrame() {
		mainFrame = new JFrame(GAME_NAME);
		mainFrame.setIconImage(new ImageIcon(getClass().getResource("images/seven.jpg")).getImage());
		mainFrame.setContentPane(new MachinePanel(new Color(22, 135, 28)));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
	}

	// done
	public static void main(String[] args) {
		new Project2();
	}

	private ReelAnimation[] reels;
	
	private boolean spin = false;
	
	private class ReelAnimation extends JPanel
	implements MouseListener
	{
		private static final long serialVersionUID = 1L;
		private final int DELAY;
		private Insets insets;
		private Symbols showing;
		private Symbols symbols[];
		private int i;
		private boolean stopped = true;
		
		public ReelAnimation(int microSeconds, Symbols[] s){
			super();
			setBorder(new EmptyBorder(0, 12, 0, 12));
			this.setBackground(Color.BLACK);
			this.setMinimumSize(new Dimension(100,100));
			addMouseListener(this);
			symbols = s;
			DELAY = microSeconds;
		}		
		public void paint(Graphics graphics) {
			super.paint(graphics);
			if (insets == null) {
				insets = getInsets();
			}
			int steps=symbols.length;
		    int x = insets.left;
		    int y = insets.top;
		    int width = getWidth() - insets.left - insets.right;
		    int height = getHeight() - insets.top - insets.bottom;
		  
		    synchronized (symbols) {
		    	for (i = 0; i < steps; i++) {
		    		graphics.drawImage(symbols[i].getImage(), x, y, width,	height, null);
		    		showing = symbols[i];
		    	}	
		    }
		}
		private Timer timer;
		private TimerTask timerTask;
		// starts the thread
		public void go() {
			stopped = false;
			timerTask = new TimerTask() {
				public void run() {
					Symbols symbol = symbols[0];
					synchronized (symbols) {
						System.arraycopy(symbols,1,symbols,0,symbols.length-1);
						symbols[symbols.length-1] = symbol;
					}
					repaint();
				}
			};
			timer = new Timer();
			timer.schedule(timerTask, 1, DELAY);
		}
		
		// stop the thread
		public void stop(){
			if(timerTask != null)
				timerTask.cancel();
			stopped = true;
		}
		public void mouseReleased(MouseEvent e) {
			stop();
			ReelAnimation[] allReels = Project2.this.reels;
			for(ReelAnimation r: allReels){
				if(!r.stopped)
					return;
			}
			if(Project2.this.spin == true)
			if(Project2.this.slotMachine.isWinner(allReels[0].showing, allReels[1].showing, allReels[2].showing)){
				// show winner panel
				winner.setText("WINNER");
				Project2.this.updateLabels();
			}
			else{
				winner.setText("LOSER");
				Project2.this.updateLabels();
			}
		}
		public void mouseExited(MouseEvent e) {
			setBorder(UNSELECTED_BORDER);
		}
		public void mouseEntered(MouseEvent e) {
			setBorder(SELECTED_BORDER);
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseClicked(MouseEvent arg0) {
		}
	}
	
	// handles background
	private class MachinePanel extends JPanel
	{	
		
		private static final long serialVersionUID = 1L;
		private BufferedImage texture;
		public MachinePanel(Color color) {
			texture = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
			for (int x = 0, xMax = texture.getWidth(); x < xMax; x++) {
				for (int y = 0, yMax = texture.getWidth(); y < yMax; y++) {
					int red = (int) (color.getRed() * (Math.random() * .1 + .9));
					int green = (int) (color.getGreen() * (Math.random() * .1 + .9));
					int blue = (int) (color.getBlue() * (Math.random() * .1 + .9));
					texture.setRGB(x, y, (red << 16) + (green << 8) + blue);
				}
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(new TexturePaint(texture, new Rectangle(0, 0, 128, 128)));
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
}
