#!/bin/bash
warn=$(($2-1))
echo $warn $1 "* * * wall < /home/alecsnyder/Documents/git/cs162/sleepTime/warning.txt" > /home/alecsnyder/Documents/cs162/sleep.cron
#echo $2 $1 "* * * poweroff" >> /home/alecsnyder/Documents/cs162/sleep.cron
sudo crontab /home/alecsnyder/Documents/cs162/sleep.cron
exit 0
