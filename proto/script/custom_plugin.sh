#!/bin/sh
echo 'custom_plugin ------------- start'
exec java -jar ./script/protoEnumOpt-1.0-SNAPSHOT.jar path:ss "$@"