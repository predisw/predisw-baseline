#!/usr/bin/python
import argparse
import re
import subprocess

parser = argparse.ArgumentParser(description='manual to this script')
parser.add_argument('--count', type=int, default=20)
parser.add_argument('--as_ip', type=str, default="10.175.162.141")
parser.add_argument('--file', type=str, default="monite_config_pre.cql")
parser.add_argument('--user', type=str, default="scef")
parser.add_argument('--password', type=str, default="scef@ece")
args = parser.parse_args()

cql_script= "INSERT INTO scef.monitor_event_config (scefRefId,externalid,maxnumberofreports,monitoringdestaddr,monitoringduration,monitortype,scsasid,ttri,tltri,reportinformations) VALUES (30003,'100000001@ericsson.com',1000000000,'http://127.0.0.1:2222/event_report','1569897116000',1,'MonitoringEvent_app1@MonitoringEvent_sp1','ttri1','0',{'100000001@ericsson.com':{currentNumberOfReport:0,latestNotificationTime:1438015512000}});"

#without maxnumberofreports
#cql_script= "INSERT INTO scef.monitor_event_config (scefRefId,externalid,monitoringdestaddr,monitoringduration,monitortype,scsasid,ttri,tltri,reportinformations) VALUES (30003,'100000001@ericsson.com','http://127.0.0.1:2222/event_report','1569897116000',1,'MonitoringEvent_app1@MonitoringEvent_sp1','ttri1','0',{'100000001@ericsson.com':{currentNumberOfReport:0,latestNotificationTime:1438015512000}})"

sc = cql_script.replace("http://127.0.0.1","http://{0}".format(args.as_ip))

fout = open(args.file,"w");

for i in range(1,args.count+1):
    cmd=re.subn("\([0-9]+","("+str(i), sc)[0]
    fout.write(cmd+"\n");

fout.close()

isSuccess = subprocess.call("cqlsh -u {0} -p {1} -f {2}".format(args.user,args.password,args.file),shell=True)
if isSuccess == 0:
    print "success"
else:
    print "return code is "+isSuccess


