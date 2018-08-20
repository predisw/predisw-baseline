import uuid
import time
import subprocess
from datetime import datetime
import argparse


parser = argparse.ArgumentParser(description='manual to this script')
parser.add_argument('-c','--count', type=int, default=20)
args = parser.parse_args()

data='The matter is speed.Only the speed make sense.But we cant just look for speed without quality.'
fileName="testTimeConGlobal.cql"

fileBase =10000
groupBase =10000

print datetime.now()



maxCount=args.count
groupCount = int(maxCount/groupBase)

if groupCount == 0:
    groupCount=1



for groupId in range(groupCount):
    for cycle in range(int(groupBase/fileBase)):
        fout = open(fileName,"w")
        for i in range(1,fileBase+1):
            cql = 'insert into test.monitor_event_group_timeConGlobal (groupId,zoneId,currentTime,globalId,data) values (\'{0}\',\'zone1\',{1},{2},\'{3}\');'.format(str(groupId),int(time.time()*1000),i,data)
            fout.write(cql+"\n");
            time.sleep(0.001)
        fout.close()
        subprocess.call("cqlsh -u {0} -p {1} -f {2}".format("scef","scef@ece",fileName),shell=True)



print datetime.now()

