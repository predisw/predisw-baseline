import uuid
import time
import subprocess
from datetime import datetime
import argparse


parser = argparse.ArgumentParser(description='manual to this script')
parser.add_argument('-c','--count', type=int, default=20)
args = parser.parse_args()

data='The matter is speed.Only the speed make sense.But we cant just look for speed without quality.'
fileName="testUeid.cql"

fileBase =10000
groupBase =1000000

print datetime.now()



maxCount=args.count
groupCount = int(maxCount/groupBase)

if groupCount == 0:
    groupCount=1

j=0

for groupId in range(1,groupCount+1):

    for cycle in range(int(groupBase/fileBase)):
        fout = open(fileName,"w")
        for i in range(1,fileBase+1):
            j= j+1
            cql = 'insert into test.monitor_event_group_ueid (groupId,ueId,data) values (\'{0}\',\'{1}\',\'{2}\');'.format(str(groupId),str(j),data)
            fout.write(cql+"\n");
        fout.close()
        subprocess.call("cqlsh -u {0} -p {1} -f {2}".format("scef","scef@ece",fileName),shell=True)



print datetime.now()

