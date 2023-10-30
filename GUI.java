import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
   final int PLAYER_LIST_SIZE = 30;
   
   int currentPlayerCount = 0;                                                      //Counter for number of players
   int currentTurn = 0;                                                             //Counter for current turn
   int x = 0, y = 0, xVelocity = 1, yVelocity = 1, duration = 5000;                 //Integers for animation
   boolean phraseGuessed = true;                                                    //Boolean for phrase guessed
   boolean saveMessages = true;                                                     //Boolean for saving messages
   boolean animate = false;                                                         //Boolean for if animation should show
   Random rand = new Random();                                                      //Randomizer
   Turn turn = new Turn();                                                          //Turn object
   Hosts currentHost = null;                                                        //Host object
   Players[] currentPlayers = new Players[PLAYER_LIST_SIZE];                        //List of players
   Image square = new ImageIcon("images/square.png").getImage();                    //Square image for animation
   Image backdrop = new ImageIcon("images/Backdrop.png").getImage();                //Background for animation
   Timer timer = new Timer(30, this);                                               //Timer for square animation
   GridLayout layout = new GridLayout(0, 2);                                        //Layout for frame
   JPanel framePanel = new JPanel(layout);                                          //Panel for frame
   JMenuBar menuBar = new JMenuBar();                                               //Menu bar to hold menu items
   JMenu personMenu = new JMenu("Game");                                            //Menu for adding players and changing host
   JMenu aboutMenu = new JMenu("About");                                            //Menu with reason for chosen layout
   JLabel prizeImage = new JLabel();                                                //Image for physical prize
   JLabel playersText = new JLabel("Player List: None");                            //GUI List of player info
   JLabel hostText = new JLabel("Host: None");                                      //GUI Host name
   JLabel nextTurnText = new JLabel("No Players: Press the Add Players Button");    //GUI Next player to take turn
   JLabel playingPhraseText = new JLabel("Phrase: ");                               //GUI Playing phrase as '_'
   JLabel messagesText = new JLabel("");                                            //GUI Messages involving the game results
   JCheckBox saveMessagesCheckbox = new JCheckBox("Save Messages", true);           //GUI Checkbox for saving messages with a tooltip
   JMenuItem addPlayerMenuItem = new JMenuItem("Add Player");                       //Menu item for adding players
   JMenuItem changeHostMenuItem = new JMenuItem("Set Host");                        //Menu item to change host
   JMenuItem setPhraseMenuItem = new JMenuItem("Change Phrase");                    //Menu item for changing in-play phrase
   JMenuItem layoutMenuItem = new JMenuItem("Layout");                              //Menu item with reason for choosing GUI layout
   JMenuItem imagesMenuItem = new JMenuItem("Image Attributions");                  //Menu item for image attributions
   JMenuItem soundsMenuItem = new JMenuItem("Sound Attributions");                  //Menu item for sound attributions
   JButton takeTurnButton = new JButton("Take Turn");                               //GUI Button for next player to take turn
   
   /*
      Constructor used by main method
      Initializes GUI
      Adds actionListener's to buttons
   */
   public GUI(){
      super("Word Game");
      setSize(1500, 1000);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      personMenu.setMnemonic('G');
      aboutMenu.setMnemonic('A');
      
      layout.setHgap(10);
      layout.setVgap(10);
      add(framePanel);
      framePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
      
      takeTurnButton.setHorizontalAlignment(SwingConstants.LEFT);
      takeTurnButton.setBorder(new LineBorder(Color.BLUE));
      takeTurnButton.setBackground(Color.WHITE);
      takeTurnButton.setOpaque(true);
      takeTurnButton.setToolTipText("Begins the turn of the next player");
      saveMessagesCheckbox.setToolTipText("Will keep all messages involving guesses, wins, prizes, etc. until phrase is guessed");
      prizeImage.setIcon(new ImageIcon());
      
      setJMenuBar(menuBar);
      menuBar.add(personMenu);
      menuBar.add(aboutMenu);
      personMenu.add(addPlayerMenuItem);
      personMenu.add(changeHostMenuItem);
      personMenu.add(setPhraseMenuItem);
      aboutMenu.add(layoutMenuItem);
      aboutMenu.add(imagesMenuItem);
      aboutMenu.add(soundsMenuItem);
      framePanel.add(new JScrollPane(playersText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(new JScrollPane(hostText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(new JScrollPane(nextTurnText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(takeTurnButton);
      framePanel.add(new JScrollPane(playingPhraseText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(new JScrollPane(messagesText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(new JScrollPane(prizeImage, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      framePanel.add(saveMessagesCheckbox);
      
      addPlayerMenuItem.addActionListener(this);
      changeHostMenuItem.addActionListener(this);
      setPhraseMenuItem.addActionListener(this);
      layoutMenuItem.addActionListener(this);
      imagesMenuItem.addActionListener(this);
      soundsMenuItem.addActionListener(this);
      takeTurnButton.addActionListener(this);
      saveMessagesCheckbox.addActionListener(this);
   }
   
   public void paint(Graphics g){
      if (animate){
         Graphics2D g2D = (Graphics2D)g;
         g2D.drawImage(backdrop, 0, 0, null);
         g2D.drawImage(square, x, y, null);
      }
      else{
         super.paint(g);
      }
   }
   
   /*
      Recieves actions from buttons
      timer (): If animate == true will update the position of the square every 0.03 seconds
      addPlayerMenuItem (): Adds a player to currentPlayers array and increases currentPlayerCount
      changeHostMenuItem (): Sets a new host name and updates host text on GUI
      setPhraseMenuItem (): Calls Hosts.setPhrase() and sets phraseGuessed to false
      takeTurnButton (): Calls Turn.takeTurn() and if phrase is fully guessed asks if players want to play again
      saveMessagesCheckbox (): Inverts state of saveMessages boolean
      layoutMenuItem (): Opens JOptionPane with reasoning for choosing the layout for the GUI
      imagesMenuItem (): Opens JOptionPane with attribution to creators of images
      soundsMenuItem (): Opens JOptionPane with attribution to creators of sounds
      After every ActionEvent when applicable:
      • () Updates player list on GUI
      • () Sets currentTurn back to 0 if it reaches currentPlayerCount
      • () Shows the next player to take turn
      • () Updates phrase on GUI
   */
   public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      if ((animate) && (source == timer)){
         if ((x >= (framePanel.getWidth() - square.getWidth(null))) || (x < 0)){
            xVelocity *= -1;
         }
         if ((y >= (framePanel.getHeight() - square.getHeight(null))) || (y < 0)){
            yVelocity *= -1;
         }
         x += xVelocity;
         y += yVelocity;
         repaint();
         duration--;
         if (duration == 0){
            duration = 5000;
            timer.stop();
            animate = false;
            repaint();
         }
      }
      else if (source == addPlayerMenuItem){
         if (currentPlayerCount == PLAYER_LIST_SIZE){
            JOptionPane.showMessageDialog(null, "There is not enough space for more players.");
         }
         else{
            String newFirstName = JOptionPane.showInputDialog(null, "What is the new player's first name?");
            String newLastName = JOptionPane.showInputDialog(null, "What is the new player's last name?");
            currentPlayers[currentPlayerCount] = new Players(newFirstName, newLastName);
            currentPlayerCount++;
         }
      }
      else if (source == changeHostMenuItem){
         String hostFirstName = JOptionPane.showInputDialog(null, "What is the host's first name?");
         String hostLastName = JOptionPane.showInputDialog(null, "What is the host's last name?");
         if (currentHost == null){
            currentHost = new Hosts(hostFirstName, hostLastName);
         }
         else{
            currentHost.setFirstName(hostFirstName);
            currentHost.setLastName(hostLastName);
         }
         hostText.setText("Host: " + currentHost.getFirstName() + " " + currentHost.getLastName());
      }
      else if (source == setPhraseMenuItem){
         if (currentHost == null){
            JOptionPane.showMessageDialog(null, "A host needs to be set before a phrase can be added.");
         }
         else{
            currentHost.setPhrase();
            phraseGuessed = false;
            messagesText.setText("");
         }
      }
      else if (source == takeTurnButton){
         if (currentPlayerCount != 0 && !phraseGuessed){
            if (!saveMessages){
               messagesText.setText("");
            }
            phraseGuessed = turn.takeTurn(currentPlayers[currentTurn], currentHost, messagesText, prizeImage);
            currentTurn++;
            if (phraseGuessed){
               if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION)){
                  dispose();
               }
            }
            else if (rand.nextInt(5) == 0){
               x = rand.nextInt(500) + 1;
               y = rand.nextInt(500) + 1;
               timer.start();
               animate = true;
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "There needs to be at least one player & There has to be an unguessed phrase");
         }
      }
      else if (source == saveMessagesCheckbox){
         saveMessages = !saveMessages;
      }
      else if (source == layoutMenuItem){
         JOptionPane.showMessageDialog(null, "I chose to use a grid layout since it was able to place the JLabels and JButtons in a pleasant looking format involving various columns.");
      }
      else if (source == imagesMenuItem){
         JOptionPane.showMessageDialog(null, "Car image by \"Pexels\"; Game Console image by \"WikimediaImages\"; TV image by \"ADMC\"; House image by \"Pexels\"");
      }
      else if (source == soundsMenuItem){
         JOptionPane.showMessageDialog(null, "\"feedback-correct\" sound by \"StavSounds\"; \"Wrong Answer / Incorrect / Error\" sound by \"Beetlemuse\"");
      }
      if (currentPlayerCount != 0){
         playersText.setText("Player List:");
         for (int i = 0; i < currentPlayerCount; i++){
            playersText.setText(playersText.getText() + "\nPlayer " + (i+1) + " " + (currentPlayers[i].toString()) + ";");
         }
      }
      if (currentTurn == currentPlayerCount){
         currentTurn = 0;
      }
      if (currentPlayerCount != 0){
         nextTurnText.setText("Next Up: Player " + (currentTurn + 1) + " " + currentPlayers[currentTurn].getFirstName()
            + " " + currentPlayers[currentTurn].getLastName());
      }
      if (currentHost != null && currentHost.getPhrase() != null){
         playingPhraseText.setText("Phrase: " + currentHost.getPhrase().getPlayingPhrase());
      }
   }
   
   /*
      main method creates a frame object and makes it visible
   */
   public static void main(String[] args){
      GUI frame = new GUI();
      frame.setVisible(true);
   }
}