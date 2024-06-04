#!/bin/sh
echo 'custom_plugin ------------- start'
exec java -jar ./script/protoCusEnumCompiler-1.0-SNAPSHOT.jar path:custom "$@"