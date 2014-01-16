import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Runtime;
import java.lang.Integer;
public class SleepSet implements ActionListener
{
    public JFrame frame;
    public int hour=0;
    public int min=0;
    public String week="1";
    public String AP="AM";
    public JButton cancel, confirm;
    public JComboBox minsWake, hoursWake, AM, day;
    
    public SleepSet()
    {
        frame=new JFrame("set Your wakeup hour!");
        frame.setLayout(new GridLayout(2,9));
        JLabel instructions=new JLabel("Please input your wakeup time!  ");
        confirm=new JButton("Confirm");
        cancel=new JButton("Cancel");
        String[] hours={"1","2","3","4","5","6", "7", "8", "9", "10", "11", "12"};
        hoursWake=new JComboBox(hours);
        String[] mins=getMins();
        minsWake = new JComboBox(mins);
        String[] AMPM={"AM", "PM"};
        AM=new JComboBox(AMPM);
        AM.setSelectedIndex(0);
        String[] dayWeek={"Mon","Tues","Wed","Thurs","Fri","Sat", "Sun", "Weekdays", "Weekends", "Mon/Wed/Fri", "Tues/Thurs", "Everyday"};
        day=new JComboBox(dayWeek);
        minsWake.addActionListener(this);
        hoursWake.addActionListener(this);
        cancel.addActionListener(this);
        confirm.addActionListener(this);
        AM.addActionListener(this);
        day.addActionListener(this);
        JLabel label1=new JLabel("   Hour: ");
        JLabel label2=new JLabel("   Minutes: ");
        JLabel label3=new JLabel("   AMPM: ");
        JLabel label4=new JLabel("   Day of the Week: ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(instructions);
        frame.add(label1);
        frame.add(hoursWake);
        frame.add(label2);
        frame.add(minsWake);
        frame.add(label3);
        frame.add(AM);
        frame.add(label4);
        frame.add(day);
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(new JLabel("")); //garbage
        frame.add(confirm);
        frame.add(cancel);
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==(Object)(cancel))
        {
            frame.dispose();
            System.exit(0);
         }
         else if(e.getSource()==(Object)(minsWake))
         {
             JComboBox box=(JComboBox)(e.getSource());
             min=Integer.parseInt((String)(box.getSelectedItem()));
             //System.out.println(min+4);
         }
         else if(e.getSource()==(Object)(hoursWake))
         {
             JComboBox box=(JComboBox)(e.getSource());
             hour=Integer.parseInt((String)(box.getSelectedItem()));
             //System.out.println(hour+4);
         }
         else if(e.getSource()==(Object)(day))
         {
             JComboBox box=(JComboBox)(e.getSource());
             String dayOfWeek=(String)(box.getSelectedItem());
             switch (dayOfWeek) {
               case "Mon":  week="1";
                            break;
               case "Tues":  week="2";
                             break;
               case "Wed":  week="3";
                            break;
               case "Thurs":  week="4";
                              break;
               case "Fri":  week="5";
                            break;
               case "Sat":  week="6";
                             break;                            
               case "Sun":  week="7";
                            break;
               case "Weekdays":  week="1-5";
                                 break;
               case "Weekends":  week="6-7";
                                 break;
               case "Mon/Wed/Fri":  week="1,3,5";
                                    break;
                             
               case "Tues/Thurs":  week="2,4";
                                   break;
               case "Everyday":  week="1-7";
                                 break;
             
             }
         }
         else if(e.getSource()==(Object)(AM))
         {
             JComboBox box=(JComboBox)(e.getSource());
             AP=(String)(box.getSelectedItem());
         }
         else if(e.getSource()==(Object)(confirm))
         {
            min=min-44;
            hour=hour-7;
            realizeTime();
            String minStr=""+min;
            String hourStr=""+hour;
            try
            {
                
                Process p=new ProcessBuilder("/home/alecsnyder/Documents/git/cs162/sleepTime/testScript", "\"Terti*4@p.30\"", hourStr, minStr, week).start();
                //p.waitFor();
                System.out.println("Success "+min+" "+hour);
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
     public String[] getMins()
     {
         String[] nums=new String[60];
         for(int i=0; i<=59; i++)
         {
             nums[i]=""+(i);
         }
         return nums;
     }
     public void realizeTime()
     {
         if(min<0)
         {
             min=min+60;
             hour=hour-1;
         }
         if(hour<0)
         {
             hour=hour+24;
         }
         if(AP.equals("PM"))
         {
             hour=hour+12;
         }
     }
     public static void main(String[] args)
     {
         new SleepSet();
     }
 }   
    
