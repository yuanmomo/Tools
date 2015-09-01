#!/bin/bash
# Program:
#
#
# Histroy:
#       2014/02/26 11:38:42  Author:Yuanmomo  Version:1.0
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

# 检查参数个数, 生成的文件名为 izq+userId+8888.apk
if [ $# != 3 ] ; then
	echo " USAGE: source destination userId "	>> ${LOG_FILE}
	echo " e.g.: 0 sourjce path of APK file shenbian.apk"	>> ${LOG_FILE}
	echo " e.g.: 1 inviter's id, 123456. The new generated APK file will be ./generated/izq + 123456 + 8888.apk" >> ${LOG_FILE}
	exit 1
fi

# 当前download目录
APK_PATH=$1
GENERATED_PATH=${APK_PATH}/generated

cd ${APK_PATH}

# 日志文件路径
LOG_FILE="${APK_PATH}/generated.log"

# 新apk文件路径和名称
NEW_GENERATED_FILE="${GENERATED_PATH}/$3/izq$38888.apk"

# 检查文件是否存在备份
if [ -e "${NEW_GENERATED_FILE}" ] ; then
	echo "Apk file already generated, ${NEW_GENERATED_FILE}" >> ${LOG_FILE}
	exit 0
fi


# 拷贝文件到目的路径
install -D "${APK_PATH}/$2" "${NEW_GENERATED_FILE}"
if [ $? != 0 ] ; then 
	echo "Copy source apk file to destination failed. Source: ${APK_PATH}/$2; Des: ${NEW_GENERATED_FILE}" >> ${LOG_FILE}
fi

# 生成邀请用户id的文件
userIdFile="META-INF/izq.$3"
touch ${userIdFile}

# 插入用户id文件到apk包,并记录日志
zip -u "${NEW_GENERATED_FILE}" ${userIdFile}  >> ${LOG_FILE}

