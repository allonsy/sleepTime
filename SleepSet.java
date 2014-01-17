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
    public boolean append=false;   
    public JButton cancel, confirm;
    public JComboBox minsWake, hoursWake, AM, day, appBox;
    
    public SleepSet()
    {
        frame=new JFrame("set Your wakeup hour!");
        frame.setLayout(new GridLayout(2,5));
        JLabel instructions=new JLabel("Please input your wakeup time!  ");
        confirm=new JButton("Confirm");
        cancel=new JButton("Cancel");
        String[] hours={"Hour","1","2","3","4","5","6", "7", "8", "9", "10", "11", "12"};
        hoursWake=new JComboBox(hours);
        hoursWake.setSelectedIndex(0);
        String[] mins=getMins();
        minsWake = new JComboBox(mins);
        String[] AMPM={"AM/PM","AM", "PM"};
        AM=new JComboBox(AMPM);
        AM.setSelectedIndex(0);
        String[] app={"New Schedule", "Add to old schedule"};
        appBox=new JComboBox(app);
        appBox.setSelectedIndex(0);
        String[] dayWeek={"Day of the Week","Mon","Tues","Wed","Thurs","Fri","Sat", "Sun", "Weekdays", "Weekends", "Mon/Wed/Fri", "Tues/Thurs", "Everyday"};
        day=new JComboBox(dayWeek);
        day.setSelectedIndex(0);
        minsWake.addActionListener(this);
        hoursWake.addActionListener(this);
        cancel.addActionListener(this);
        confirm.addActionListener(this);
        AM.addActionListener(this);
        day.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(confirm);
        frame.add(instructions);
        frame.add(hoursWake);
        frame.add(minsWake);
        frame.add(AM);
        frame.add(day);
        frame.add(appBox);
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
              if(box.getSelectedItem().equals("minute"))
                min=0;
             else
                min=Integer.parseInt((String)(box.getSelectedItem()));
             //System.out.println(min+4);
         }
         else if(e.getSource()==(Object)(hoursWake))
         {
             JComboBox box=(JComboBox)(e.getSource());
             if(box.getSelectedItem().equals("hour"))
                hour=8;
             else
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
               default:         week="1";
                                break;
             
             }
         }
         else if(e.getSource()==(Object)(AM))
         {
             JComboBox box=(JComboBox)(e.getSource());
             AP=(String)(box.getSelectedItem());
             if(AP.equals("AM/PM"))
                AP="AM";
         }
         else if(e.getSource()==(Object)(appBox))
         {
             JComboBox box=(JComboBox)(e.getSource());
             if(((String)(box.getSelectedItem())).equals("New Schedule"))
                append=false;
             else
                append=true;
         }
         else if(e.getSource()==(Object)(confirm))
         {
            new PassFrame(this);
         }
     }
     public String[] getMins()
     {
         String[] nums=new String[61];
         nums[0]="minute";
         for(int i=1; i<=60; i++)
         {
             nums[i]=""+(i-1);
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
            if(week.equals("1-7"))
                week=week;
            else if(week.equals("1,3,5"))
                week="7,2,4";
            else if(week.equals("2,4"))
                week="1,3";
            else if(week.equals("1-5"))
                week="1,2,3,4,7";
            else if(week.equals("6-7"))
                week="5,6";
            else
            {
                Integer weekInt=(Integer.parseInt(week)-1);
                week=weekInt.toString();
            }
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
    
