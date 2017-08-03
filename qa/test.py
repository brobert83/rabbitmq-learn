#!/usr/bin/python
import subprocess
import sys
import time
import urllib2

def wait_for_container_to_be_up(name,count):

    command = "docker ps -qf \"name=%s\"" % name

    while len(subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().split("\n"))!=count+1:
        print "Checking for a pulse with: %s" % command
        time.sleep(1)

    print "%s is up" % name
    print "Count is %s" % count

wait_for_container_to_be_up("da-rabbitmq-server",1)
wait_for_container_to_be_up("sender",1)
wait_for_container_to_be_up("worker*",3)

print "Wait for sprint to boot (30 secs)"
time.sleep(30)

print "Send the message"
req = urllib2.urlopen('http://localhost:1250/?message=test')
print req.read()

print "Wait 10 seconds before checking"
time.sleep(10)

# Check workers
received_messages = {'worker_1':0,'worker_2':0,'worker_3':0}
tail_log_command_template = "docker logs $(docker ps -qf \"name=worker%s\") --tail 10"

for i in [1, 2, 3]:
    command = tail_log_command_template % i
    print "Getting container logs with: %s" % command
    worker_log = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().split("\n")
    # print worker_log
    received = filter(lambda line: line.startswith('Received'), worker_log)
    print received
    messages_count=len(received)
    received_messages['worker_%s' %i] = messages_count

print "Received messages count: %s" % received_messages

workers_used_count = len(filter(lambda worker: worker[1]!=0, received_messages.iteritems()))

print "Workers used %s" % workers_used_count
if workers_used_count<=1:
    sys.exit(1)
