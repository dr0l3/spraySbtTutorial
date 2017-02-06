#!/usr/bin/env bash
docker rm -f testsbt
docker run --name testsbt -d -p 8081:8080 dr0l3/testsbt:latest