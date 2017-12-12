#!/usr/bin/python

from optparse import OptionParser
import sys, os
import commands
#import rda_base.cluster

checkmemver = '0.1'

######################################################################################################
# JDK7(different from JDK8) jstat -gc column meaning is as follow:
#   S0C: Current survivor space 0 capacity (KB).
#   S1C: Current survivor space 1 capacity (KB).
#   S0U: Survivor space 0 utilization (KB).
#   S1U: Survivor space 1 utilization (KB).
#   EC:  Current eden space capacity (KB).
#   EU:  Eden space utilization (KB).
#   OC:  Current old space capacity (KB).
#   OU:  Old space utilization (KB).
#   PC:  Current permanent space capacity (KB).
#   PU:  Permanent space utilization (KB).
#   YGC: Number of young generation GC Events.
#   YGCT: Young generation garbage collection time(seconds).
#   FGC:  Number of full GC events.
#   FGCT: Full garbage collection time(seconds).
#   GCT:  Total garbage collection time(seconds).
######################################################################################################
#jvm_stat_var_array = ('S0C','S1C','S0U','S1U','EC','EU','OC','OU','PC','PU','YGC','YGCT','FGC','FGCT','GCT')
jvm_stat_var_array = ('S0C','S1C','S0U','S1U','EC','EU','OC','OU','MC','MU','CCSC','CCSU','YGC','YGCT','FGC','FGCT','GCT')

# Parse commandline options:
parser = OptionParser(usage="%prog [ -h ]",version="%prog " + checkmemver)
parser.add_option("-n", "--name",
                  action="store", type="string", dest="dp_name", help="dp name")
parser.add_option("-m", "--metrics",
                  action="store", type="string", dest="metrics", help="metrics name:[total-heap][used-heap][free-heap][used-per][yound-gc][young-gc-time][full-gc][full-gc-time][total-gc-time]")
(options, args) = parser.parse_args()


def getMetricsValue(metrice):
    values = {
        'total-heap' : totalHeap,
        'used-heap' : usedHeap,
        'free-heap' : freeHeap,
        'used-per' : usedPer,
        'yound-gc' : final_stat.jvm_stat_dict['YGC'],
        'young-gc-time' : youngGCT,
        'full-gc' : final_stat.jvm_stat_dict['FGC'],
        'full-gc-time' : fullGCT,
        'total-gc-time' : totalGCT
    }
    return values.get(metrice, 0)

class CollectStat:
    """Object to Collect JVM Statistic Data"""
    jvm_stat_dict = {}
    total = 0
    def __init__(self):
        pwd = os.path.split(os.path.realpath(__file__))[0]

        # get userid and pid of the process
        get_userid_cmd = 'ps -ef | grep -v "grep" | grep -e ".*Ddp\.name={DP}.*" | awk \'{{print $1}}\' | sort -u'.format(DP=options.dp_name)

        userid = commands.getoutput(get_userid_cmd)
        get_processid_cmd = 'ps -ef | grep -v "grep" | grep -e ".*Ddp\.name={DP}.*" | grep -v JAVA | awk \'{{print $2}}\''.format(DP=options.dp_name)
        processid = commands.getoutput(get_processid_cmd)
        if (userid == ''):
            return

        jstat_cmd = "su - {userid} -c \'/usr/lib/java/latest/bin/jstat -gc {processid}\'".format(userid=userid,processid=processid);
        gc_rc = commands.getoutput(jstat_cmd)
        rc_array = gc_rc.split('\n')
        value_line = rc_array[1].strip()
        jvm_stat = value_line.split()
        jvm_stat=map(float, jvm_stat)     # Convert the Array to float
        for i in range(len(jvm_stat)):
            self.jvm_stat_dict[jvm_stat_var_array[i]] = jvm_stat[i]



#nodename=novalocal
final_stat = CollectStat()

if final_stat.jvm_stat_dict:
    #totalHeap = round(float(final_stat.jvm_stat_dict['S0C']+final_stat.jvm_stat_dict['S1C']+final_stat.jvm_stat_dict['EC']+final_stat.jvm_stat_dict['OC']+final_stat.jvm_stat_dict['PC'])/1024,2)
    totalHeap = round(float(final_stat.jvm_stat_dict['S0C']+final_stat.jvm_stat_dict['S1C']+final_stat.jvm_stat_dict['EC']+final_stat.jvm_stat_dict['OC'])/1024,2)
    usedHeap = round(float(final_stat.jvm_stat_dict['S0U']+final_stat.jvm_stat_dict['S1U']+final_stat.jvm_stat_dict['EU']+final_stat.jvm_stat_dict['OU'])/1024,2)
    #usedHeap = round(float(final_stat.jvm_stat_dict['S0U']+final_stat.jvm_stat_dict['S1U']+final_stat.jvm_stat_dict['EU']+final_stat.jvm_stat_dict['OU']+final_stat.jvm_stat_dict['PU'])/1024,2)
    freeHeap = round(float(totalHeap-usedHeap),2)
    usedPer = round(((usedHeap/totalHeap)*100),2)
    youngGCT = round(final_stat.jvm_stat_dict['YGCT'],2)
    fullGCT = round(final_stat.jvm_stat_dict['FGCT'],2)
    totalGCT = round(final_stat.jvm_stat_dict['GCT'],2)
    print getMetricsValue(options.metrics)
