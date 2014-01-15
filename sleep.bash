#!/bin/bash
warn=$(($2-1))
echo $warn $1 "* * * env DISPLAY=:0 java -jar /home/alecsnyder/Documents/Java/shutdown.jar " > /home/alecsnyder/Documents/cs162/sleep.cron
#echo $2 $1 "* * * poweroff" >> /home/alecsnyder/Documents/cs162/sleep.cron
sudo crontab /home/alecsnyder/Documents/cs162/sleep.cron
exit 0
