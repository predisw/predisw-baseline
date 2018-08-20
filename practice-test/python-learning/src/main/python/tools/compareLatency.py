import time
from datetime import datetime
import subprocess



def compare_latency(exeCql):

    start1 = datetime.now()
    print start1

    isOk = subprocess.call(exeCql,shell=True)

    start2 = datetime.now()
    print start2

    print start2 - start1

exeCql1= "cqlsh -u scef -p scef@ece -e \"paging off;SELECT *  from test.monitor_event_group where currentTime > '2018-06-08 05:40:00.000 +0000' and currentTime< '2018-06-08 05:42:00.000 +0000' limit 10000  allow filtering;\""

exeCql2= "cqlsh -u scef -p scef@ece -e \"paging off;SELECT *  from test.monitor_event_group_set where globalId = 4;\""

compare_latency(exeCql1);

compare_latency(exeCql2)
