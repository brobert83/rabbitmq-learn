#!/usr/bin/env bash

cat << "EOF"
______      _               _   _       _____        __ _                            _     _      _
| ___ \    | |             | | ( )     /  ___|      / _| |                          | |   | |    | |
| |_/ /___ | |__   ___ _ __| |_|/ ___  \ `--.  ___ | |_| |___      ____ _ _ __ ___  | |   | |_ __| |
|    // _ \| '_ \ / _ \ '__| __| / __|  `--. \/ _ \|  _| __\ \ /\ / / _` | '__/ _ \ | |   | __/ _` |
| |\ \ (_) | |_) |  __/ |  | |_  \__ \ /\__/ / (_) | | | |_ \ V  V / (_| | | |  __/ | |___| || (_| |
\_| \_\___/|_.__/ \___|_|   \__| |___/ \____/ \___/|_|  \__| \_/\_/ \__,_|_|  \___| \_____/\__\__,_|
EOF

echoStep(){
   echo -e "\n\033[35m $1 ------------------------------------------------------>\033[0m\n"
}

echoStep "BUILD"

mvn package
docker build -t rabbitmq-sender rabbitmq-sender
docker build -t rabbitmq-worker rabbitmq-worker

echoStep "RUN"
docker-compose up -d

echoStep "TEST"
python qa/test.py
qa_status=$?

if [[ $qa_status == 0 ]]; then
    echo -e '\033[32m WORKS!!! \033[0m'
else
    echo -e '\033[31m ERRORS -> go fix \033[0m'
fi

echoStep "STOP"
docker-compose stop -t 0