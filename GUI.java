import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
   final int PLAYER_LIST_SIZE = 20;
   
   int currentPlayerCount = 0;                                                      //Counter for number of players
   int currentTurn = 0;                                                             //Counter for current turn
   boolean phraseGuessed = true;                                                    //Boolean for phrase guessed
   Turn turn = new Turn();                                                          //Turn object
   Hosts currentHost = null;                                                        //Host object
   Players[] currentPlayers = new Players[PLAYER_LIST_SIZE];                        //List of players
   JLabel playersText = new JLabel("Player List: None");                            //GUI List of player info
   JLabel hostText = new JLabel("Host: None");                                      //GUI Host name
   JLabel nextTurnText = new JLabel("No Players: Press the Add Players Button");    //GUI Next player to take turn
   JLabel playingPhraseText = new JLabel("Phrase: ");                               //GUI Playing phrase as '_'
   JButton addPlayerButton = new JButton("Add Player");                             //GUI Button to add player
   JButton changeHostButton = new JButton("Set Host & Change Phrase");              //GUI Button to change host & phrase
   JButton takeTurnButton = new JButton("Take Turn");                               //GUI Button for next player to take turn
   
   /*
      Constructor used by main method
      Initializes GUI
      Adds actionListener's to buttons
   */
   public GUI(){
      super("Word Game");
      setSize(1500, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new FlowLayout());
      add(playersText);
      add(addPlayerButton);
      add(hostText);
      add(changeHostButton);
      add(nextTurnText);
      add(takeTurnButton);
      add(playingPhraseText);
      
      addPlayerButton.addActionListener(this);
      changeHostButton.addActionListener(this);
      takeTurnButton.addActionListener(this);
   }
   
   /*
      Recieves actions from buttons
      addPlayerButton (60): Adds a player to currentPlayers array and increases currentPlayerCount
      changeHostButton (71): Sets a new host name, calls Hosts.setPhrase(), and sets phraseGuessed to false
      takeTurnButton (79): Calls Turn.takeTurn() and if phrase is fully guessed asks if players want to play again
      After every ActionEvent when applicable:
      • (93) Updates player list on GUI
      • (99) Sets currentTurn back to 0 if it reaches currentPlayerCount
      • (102) Shows the next player to take turn
      • (106) Updates phrase on GUI
   */
   public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      if (source == addPlayerButton){
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
      else if (source == changeHostButton){
         String hostFirstName = JOptionPane.showInputDialog(null, "What is the host's first name?");
         String hostLastName = JOptionPane.showInputDialog(null, "What is the host's last name?");
         currentHost = new Hosts(hostFirstName, hostLastName);
         currentHost.setPhrase();
         hostText.setText("Host: " + currentHost.getFirstName() + " " + currentHost.getLastName());
         phraseGuessed = false;
      }
      else if (source == takeTurnButton){
         if (currentPlayerCount != 0 && !phraseGuessed){
            phraseGuessed = turn.takeTurn(currentPlayers[currentTurn], currentHost);
            currentTurn++;
            if (phraseGuessed){
               if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION)){
                  dispose();
               }
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "There needs to be at least one player & There has to be an unguessed phrase");
         }
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
      if (currentHost != null){
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