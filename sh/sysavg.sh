todo(){
d=$(date +'%Z %Y%m%d %H:%M:%S')
upt=$(uptime)
usr=$(echo $upt|grep -Poe '[0-9]+ user'|cut -d ' ' -f 1)
avg=$(echo $upt|grep -Poe '[0-9]\.[0-9]{2}')
echo $d ${usr}u $avg>>sysavg.log
}

for((i=0;i<${2};i++))
do
	todo
	sleep $1
done
