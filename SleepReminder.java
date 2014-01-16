import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Runtime;
public class SleepReminder implements ActionListener
{
  public JFrame frame;
  
  public SleepReminder()
  {
    frame=new JFrame("Shutdown Reminder!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(2,1));
    JLabel label=new JLabel("Your Computer will shutdown automatically in thirty minutes, please save your work");
    JButton confirm=new JButton("confirm");
    confirm.addActionListener(this);
    frame.add(label);
    frame.add(confirm);
    frame.pack();
    frame.setSize(new Dimension(800,200));
    frame.setVisible(true);
    //frame.show(); //deprecated
  }
  public static void main(String[] args)
  {
    new SleepReminder();
  }
  public void actionPerformed(ActionEvent e)
  {
    try
    {
        Process p=new ProcessBuilder("/home/alecsnyder/Documents/git/cs162/sleepTime/testScript", "Terti\\*4@p.30").start();
        p.waitFor();
        System.out.println("Success");
    }
    catch (Exception ex)
    {
        System.out.println("error in execution!");
        //return 1;
    }
    frame.dispose();
    System.exit(0);
  }
}
