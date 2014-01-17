/* Alec Snyder
 * Adds a frame to ask for password and if correct, execute proper cron code and scripts
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Runtime;
public class PassFrame implements ActionListener
{
    private SleepSet set;
    public JFrame passFrame;
    public JButton cancelPass;
    public JButton confirmPass;
    public JTextField passField;
    private String passwd="passwd";
    
    public PassFrame(SleepSet sleepSet)
    {
        set=sleepSet;
        passFrame=new JFrame("Enter Password");
        passFrame.setLayout(new GridLayout(2,2));
        JLabel label=new JLabel("Please Enter in your password: (don't worry, it's secure!)");
        passField = new JTextField();
        cancelPass=new JButton("cancel");
        confirmPass=new JButton("confirm");
        passFrame.getRootPane().setDefaultButton(confirmPass);
        cancelPass.addActionListener(this);
        confirmPass.addActionListener(this);
        passFrame.add(label);
        passFrame.add(passField);
        passFrame.add(confirmPass);
        passFrame.add(cancelPass);
        passFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        passFrame.pack();
        passFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==(Object)(cancelPass))
         {
             passFrame.dispose(); //cancel closes frame
         }
         else if(e.getSource()==(Object)(confirmPass))
         {
             String addon="";
             if(set.appBox.getSelectedItem().equals("New Schedule")) //if the user has chosen to create a new schedule or append old one.
                addon="";
             else
                addon="Append";
             passwd="\""+passField.getText()+"\"";
             set.min=set.min-44;
             set.hour=set.hour-7;
             set.realizeTime();
             String minStr=""+set.min;
             String hourStr=""+set.hour;
             try
             {
                String[] cmd = {"/bin/bash","-c","echo "+passwd+"| sudo -S ~/sleep"+addon+" "+ hourStr + " " + minStr + " " + set.week + " poweroff"}; 
                Process p=Runtime.getRuntime().exec(cmd); //shutdown command 
                p.waitFor();
                p.destroy();
                set.min=set.min-30;
                set.realizeTime();
                minStr=""+set.min;
                hourStr=""+set.hour;
                String[] warnCmd = {"/bin/bash","-c","echo "+passwd+"| sudo -S ~/sleepAppend "+ hourStr + " " + minStr + " " + set.week + "\" wall < ~/warning.txt\""}; 
                Runtime.getRuntime().exec(warnCmd); //system wide warning command
            }
            catch (Exception ex)
            {
                System.out.println("error in execution!");
            }
            set.frame.dispose();
            passFrame.dispose();
            System.exit(0);
         }
     }
 }
