import subprocess
import sys

received_messages = {'worker_1':0,'worker_2':0,'worker_3':0}
tail_log_command_template = "docker logs $(docker ps -qf \"name=worker%s\") --tail 10"

for i in [1, 2, 3]:
    command = tail_log_command_template % i
    print "Getting container logs with: %s" % command
    worker_log = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().split("\n")
    messages_count=len(filter(lambda line: line.startswith('Received'), worker_log))
    received_messages['worker_%s' %i] = messages_count

print "Received messages count: %s" % received_messages

workers_used_count = len(filter(lambda worker: worker[0]!=0, received_messages.iteritems()))

print "Workers used %s" % workers_used_count

if workers_used_count==1:
    sys.exit(1)
