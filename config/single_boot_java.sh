#!/bin/bash

# 线上配置
# project_name="cloudclass-core-api"
# project_jar_name="cloudclass-core-api-0.0.1.jar"
# java_home_path="/var/www/dream/jdk"
# java_ops="-Xmx8192M -Xms8192M -Xss256k -Xloggc:/var/www/dream/webapp/cloudclass-core-api/logs/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/www/dream/webapp/cloudclass-core-api/logs -XX:ErrorFile=/var/www/dream/webapp/cloudclass-core-api/logs/jvm_err.log -XX:ConcGCThreads=1 -XX:ParallelGCThreads=1 -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -verbose:gc -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:MaxMetaspaceSize=256M -XX:MetaspaceSize=256M -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+ParallelRefProcEnabled -javaagent:/var/www/dream/webapp/apache-skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=class-core-api-$host -Dskywalking.collector.backend_service=trace.csslcloud.net:11800"
# start_path="/var/www/dream/webapp"
#rm -f host_file
#echo $host|sed -e 's/,/\n/g' >> host_file
#ansible -i host_file all -m shell -a "mkdir -p $start_path/$project_name &&  mkdir -p $start_path/$project_name/config &&  mkdir -p $start_path/$project_name/logs" 2>/dev/null
#ansible -i host_file all -m copy -a "src=/var/www/dream/jenkins/config/single_boot_java.sh dest=$start_path/$project_name/config" 2>/dev/null
#ansible -i host_file all -m shell -a "cd $start_path/$project_name/config && chmod a+x single_boot_java.sh && cd $start_path/$project_name && ./config/single_boot_java.sh stop $project_jar_name" 2>/dev/null
#ansible -i host_file all -m shell -a "cd $start_path/$project_name && rm -rf ./$project_jar_name" 2>/dev/null
#ansible -i host_file all -m copy -a "src=target/$project_jar_name dest=$start_path/$project_name" 2>/dev/null
#ansible -i host_file all -m shell -a "cd $start_path/$project_name/config && chmod a+x single_boot_java.sh && cd $start_path/$project_name && ./config/single_boot_java.sh start $project_jar_name $project_name $java_home_path $active $java_ops" 2>/dev/null
#


#操作动作
shell_action=$1
#项目jar包名称
SpringBoot=$2
#项目名称
project_name=$3
#JAVA_HOME路径
java_home_path=$4
#环境
env=$5
#启动参数
run_args=`echo ${@:6}`

#日志路径
log_dir="/var/www/dream/webapp/$project_name/logs"
#默认启动参数
JAVA_OPTS="-Xmx256M -Xms64M -Xss256k -Xloggc:$log_dir/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$log_dir -XX:ErrorFile=$log_dir/jvm_err.log -XX:ConcGCThreads=1 -XX:ParallelGCThreads=1 -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -verbose:gc -XX:+PrintHeapAtGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:MaxMetaspaceSize=256M -XX:MetaspaceSize=256M -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+ParallelRefProcEnabled "
export JAVA_HOME=$java_home_path
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


if [ "$shell_action" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$SpringBoot" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi

function start()
{
    echo echo "开始启动服务..."
    if [ "$run_args" = "" ]; then
	JAVA_OPTS="-DLOG_PATH=$log_dir $JAVA_OPTS"
    else
	JAVA_OPTS="-DLOG_PATH=$log_dir $run_args"
    fi

	#tpid=`ps -ef|grep $SpringBoot |grep -v grep|grep -v kill|awk '{print $2}'`
	#count=`ps -ef |grep java|grep $SpringBoot|grep Xms|grep -v grep|grep -v kill|wc -l`
    #if [ $count != 0 ];then
        #echo "$SpringBoot is running..."
    #else
        echo "Start $SpringBoot success..."
        #nohup java $JAVA_OPTS -jar -Dspring.profiles.active=$env $SpringBoot & >/dev/null 2>&1 &
	nohup java $JAVA_OPTS -jar -Dspring.profiles.active=$env $SpringBoot >$log_dir/console.log 2>&1 &
    #fi
}

function stop()
{
    echo "Stop $SpringBoot"
    boot_id=`ps -ef |grep java|grep $SpringBoot|grep Xms|grep -v grep|awk '{print $2}'`
    count=`ps -ef |grep java|grep $SpringBoot|grep Xms|grep -v grep|wc -l`

    if [ $count != 0 ];then
        echo '开始停止服务...'
        kill $boot_id
        sleep 5
        #count=`ps -ef |grep java|grep $SpringBoot|grep -v grep|wc -l`
        boot_id=`ps -ef |grep java|grep $SpringBoot|grep Xms|grep -v grep|awk '{print $2}'`
        kill -9 $boot_id
        echo '服务停止完成'
    fi
}

function restart()
{
    stop
    sleep 10
    echo '开始启动'
    start
}

function status()
{
    count=`ps -ef |grep java|grep $SpringBoot|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$SpringBoot is running..."
    else
        echo "$SpringBoot is not running..."
    fi
}

case $shell_action in
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    *)

    echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  {SpringBootJarName} \033[0m\033[0;31m Example: \033[0m\033[0;33m sh  $0  start esmart-test.jar \033[0m"
esac