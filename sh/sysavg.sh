todo(){
d=$(uptime)
t=$(echo $d |grep -P -o '([0-9]{2}:){2}[0-9]{2}')
s=$(echo $d |grep -P -o '[0-9]\.[0-9]{2}')
echo $t $s>>/root/sysLoadAvg/sysavg
}
for ((i=0;i<12;i++))
do
	todo
	sleep 5
done
