import uuid
import time
import subprocess
from datetime import datetime
import argparse


parser = argparse.ArgumentParser(description='manual to this script')
parser.add_argument('-c','--count', type=int, default=20)
args = parser.parse_args()

data='The matter is speed.Only the speed make sense.But we cant just look for speed without quality.'
fileName="testInt.cql"
base =50000


print datetime.now()



maxCount=args.count
count = int(maxCount/base)

if count == 0:
    count=1

for j in range(count):
    fout = open(fileName,"w")
    for i in range(1,base+1):
        bigValue = int(time.time()*1000)*1000000+i
        cql = 'insert into test.monitor_event_group_int (globalId,currentTime,zoneId,groupId,data) values ({0},{1},1,\'{2}\',\'{3}\');'.format(uuid.uuid1(),bigValue,str(j),data)
        fout.write(cql+"\n");
        time.sleep(0.001)
    fout.close()
    subprocess.call("cqlsh -u {0} -p {1} -f {2}".format("scef","scef@ece",fileName),shell=True)



print datetime.now()

