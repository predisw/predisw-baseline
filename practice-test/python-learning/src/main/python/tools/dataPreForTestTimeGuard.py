import uuid
import time
import subprocess
from datetime import datetime
import argparse


parser = argparse.ArgumentParser(description='manual to this script')
parser.add_argument('-c','--count', type=int, default=20)
args = parser.parse_args()

data='The matter is speed.Only the speed make sense.But we cant just look for speed without quality.'
fileName="testTimeGuard.cql"
fileBase =50000
groupBase=300000


print datetime.now()



maxCount=args.count
groupCount = int(maxCount/groupBase)

if groupCount == 0:
    groupCount=1


for groupId in range(6,11):
    for cycle in range(groupBase/fileBase):
        first_map = {'globalId':'','currentTime':'','zoneId':1,'groupId':'0','data':''}
        first_map['globalId'] = str(uuid.uuid1())
        first_map['currentTime'] = int(time.time()*1000)
        first_map['groupId'] = '\''+str(0)+'\''
        first_map['data'] = '\''+data +'\''

        first_obj = str(first_map).replace("\'","").replace("\"","\'")
        next_obj=""
        fout = open(fileName,"w")
        for i in range(1,fileBase):

            next_map = {'globalId':'','currentTime':'','zoneId':1,'groupId':'0','data':''}
            next_map['globalId'] = str(uuid.uuid1())
            next_map['currentTime'] = int(time.time()*1000)
            next_map['groupId'] = '\''+str(i)+'\''
            next_map['data'] ='\''+data +'\''

            next_obj = next_obj+","

            next_obj=next_obj+str(next_map).replace("\'","").replace("\"","\'")
            time.sleep(0.001)

        total_obj = "{"+first_obj+next_obj+"}"

        cql = "insert into test.monitor_event_group_timeForGuard (groupId,zoneId,timeForGuard,globalId,data) values (\'{0}\','zone1',{1},{2},{3});".format(groupId,cycle*5,uuid.uuid1(),total_obj)
        fout.write(cql+"\n")
        fout.close()
        subprocess.call("cqlsh -u {0} -p {1} -f {2}".format("scef","scef@ece",fileName),shell=True)



print datetime.now()

