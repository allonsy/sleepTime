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
             passFrame.dispose();
         }
         else if(e.getSource()==(Object)(confirmPass))
         {
             String addon="";
             if(set.appBox.getSelectedItem().equals("New Schedule"))
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
                Process p=Runtime.getRuntime().exec(cmd);
                p.waitFor();
                p.destroy();
                set.min=set.min-30;
                set.realizeTime();
                minStr=""+set.min;
                hourStr=""+set.hour;
                String[] warnCmd = {"/bin/bash","-c","echo "+passwd+"| sudo -S ~/sleepAppend "+ hourStr + " " + minStr + " " + set.week + "\" wall < ~/warning.txt\""}; 
                Runtime.getRuntime().exec(warnCmd);
                //Process p=new ProcessBuilder("/home/alecsnyder/Documents/git/cs162/sleepTime/testScript", "\"Terti*4@p.30\"", hourStr, minStr, week).start();
                //p.waitFor(); */
                System.out.println("Success "+set.min+" "+set.hour);
            }
            catch (Exception ex)
            {
                System.out.println("error in execution!");
                //return 1;
            }
            set.frame.dispose();
            passFrame.dispose();
            System.exit(0);
         }
     }
 }
