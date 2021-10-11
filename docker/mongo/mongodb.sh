#!/usr/bin/env bash

echo 'Creating application user and db'

mongo single-registry-service \
        --host localhost:27018 \
        --authenticationDatabase admin \
        --eval "db.createUser({user: 'mall-service', pwd: 'mall-serviee', roles:[{role:'dbOwner', db: 'mall-service'}]});"