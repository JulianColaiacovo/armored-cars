pkill java
while ps axg | grep -vw grep | grep -w java > /dev/null; do sleep 1; done