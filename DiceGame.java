import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DiceGame extends JFrame implements ActionListener {
  private static final int HEIGHT = 100; // size of the 
  private static final int WIDTH = 260;  // window
  private static JLabel text = new JLabel(),
    moneytext = new JLabel(), wins = new JLabel("W"),
    losses = new JLabel("L");
  private JTextField input = new JTextField(4);
  private JButton button = new JButton("Place Bet");
  private static int money, factor, win, lose;
  private static double chance;
  
  public static void main(String[] args) {
    money = Integer.parseInt(
        JOptionPane.showInputDialog(null, "Staring cash?"));
    factor = Integer.parseInt(
        JOptionPane.showInputDialog(null, "What is the prize factor?"));
    chance = Double.parseDouble("0."+
        JOptionPane.showInputDialog(null, "What are the odds(%)?"));
    
    text.setText("Playing: "+
        new Double(chance).toString().substring(2, 4)+"x"+factor);
    moneytext.setText("$"+money);
    DiceGame gui = new DiceGame();
  }
  
  public DiceGame() {
    this.setTitle("Dice Game Simulator");
    this.setSize(WIDTH, HEIGHT); 
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.createContents();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
  
  public void createContents() {
    this.add(new JLabel("       "));
    this.add(text);
    this.add(moneytext);
    this.add(new JLabel("            "));
    this.add(wins);
    this.add(new JLabel("/"));
    this.add(losses);
    this.add(input);
    button.addActionListener(this);
    this.add(new JLabel("       "));
    this.add(button);
    setIconImage(
        new ImageIcon(DiceGame.class.getResource("dice.png")).getImage());
  }
  
  public void actionPerformed(ActionEvent e) {
    if (Math.random() >= chance) {
      win += 1;
      wins.setText(win+"");
      money += Integer.parseInt(input.getText());
    }
    else {
      lose += 1;
      losses.setText(lose+"");
      money -= Integer.parseInt(input.getText());
    }
    moneytext.setText("$"+money);
    
    if (money <= 0) {
      JOptionPane.showMessageDialog(null, "You ran out of money",
        "Sorry!", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
  }
}