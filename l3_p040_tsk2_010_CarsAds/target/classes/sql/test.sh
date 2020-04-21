#!/bin/sh
#
# Rates upload file check.
#

if [ $# -lt 1 or $# -gt 2 ]; then
	echo "Usage: `basename $0` < RATE AREA > < RUNDATE (optional) > "
	echo "        RUNDATE with the format yyyymmdd "
	exit 1
fi

if [ $# -eq 1 ]; then
	REGION=$1
	RUN_DATE=`date '+%Y%m%d'`
elif [ $# -eq 2 ];  then
	REGION=$1
	RUN_DATE=$2
fi

PROGNAME=`basename $0`
LOGFILE="RatesUpload.log"

ECHO()
{
DATE=`date +'%a %m/%d/%y %R'`
echo "$DATE  $PROGNAME         $*" >> ${APPDIR}/apps/logs/${LOGFILE}
echo "$*"
}

ECHO_ERROR()
{
DATE=`date +'%a %m/%d/%y %R'`
echo "$DATE $PROGNAME  Error       $*" >> ${APPDIR}/apps/logs/RatesUpload.err
echo "$*"
}

sngl_select()
{
ret=`(
        cat <<%
set pagesize 0;
set linesize 1000;
set feed off;
$SQLSTR;
%
        ) | ${BSQLPLUS}`
}

if [ ! -x ${APPDIR}/apps/firebin/bsqlplus ]; then
        if [ ! -x ${APPDIR}/apps/bin/bsqlplus ]; then
                echo "ERROR Unable to execute bsqlplus"
                exit 2
        else
                BSQLPLUS=${APPDIR}/apps/bin/bsqlplus
        fi
else
        BSQLPLUS=${APPDIR}/apps/firebin/bsqlplus
fi

#Todays date in YYYYMMDD format
CURRENT_WAIT=0
FOUND_MMIN=0
FOUND_FU=0
FOUND_FX=0
FOUND_MM=0
FOUND_MMBBA=0
FOUND_MMBK=0


SOURCE_FILE_MMIN="MM_INDRATE_${RUN_DATE}.csv"
SOURCE_FILE_FU="FU_${REGION}_${RUN_DATE}.csv"
SOURCE_FILE_FX="FX_${REGION}_${RUN_DATE}.csv"
SOURCE_FILE_MM="MM_${REGION}_${RUN_DATE}.csv"
SOURCE_FILE_MMBBA="MM_${REGION}BBA_${RUN_DATE}.csv"
SOURCE_FILE_MMBK="MM_${REGION}BK_${RUN_DATE}.csv"

DATASCOPE_SOURCE_FILE_MMIN=$SOURCE_FILE_MMIN
DATASCOPE_SOURCE_FILE_FU=$SOURCE_FILE_FU
DATASCOPE_SOURCE_FILE_FX=$SOURCE_FILE_FX
DATASCOPE_SOURCE_FILE_MM=$SOURCE_FILE_MM
DATASCOPE_SOURCE_FILE_MMBBA=$SOURCE_FILE_MMBBA
DATASCOPE_SOURCE_FILE_MMBK=$SOURCE_FILE_MMBK


if [ "${REGION}" = "HKGREF" ] || [ "${REGION}" = "SKKREF" ] || [ "${REGION}" = "IRDREF" ] ; then
	DATASCOPE_SOURCE_FILE_FX="FX_SNGREF_${RUN_DATE}.csv"
	DATASCOPE_SOURCE_FILE_MM="MM_SNGREF_${RUN_DATE}.csv"
	DATASCOPE_SOURCE_FILE_MMBBA="MM_SNGREFBBA_${RUN_DATE}.csv"
	DATASCOPE_SOURCE_FILE_FU="FU_SNGREF_${RUN_DATE}.csv"
	ECHO "Inside the Region: $REGION"
fi


DEST_PATH="$APPDIR/apps/RatesUpload"
ECHO "Executing $PROGNAME for REGION: $REGION with RUN DATE: $RUN_DATE"

#Get server info
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_DATASCOPE_SERVER';"
SERVER=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
if [ "${SERVER}" = "no rows selected" ]; then
	ECHO "Aborting: Unable to get the SERVER from bns_system_param table."
	exit 1
fi

#Get src path
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_DATASCOPE_SERVER_SOURCEPATH';"
SOURCE_PATH=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
if [ "${SOURCE_PATH}" = "no rows selected" ]; then
	ECHO "Aborting: Unable to get the SOURCE PATH from bns_system_param table."
	exit 1
fi

#Get to email
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_TO_EMAIL'"
#EMAIL_TO=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
sngl_select
EMAIL_TO=$ret
if [ "${EMAIL_TO}" = "no rows selected" ]; then
	ECHO "Aborting: Unable to get the To Email from bns_system_param table."
	exit 1
fi

#Get from email
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_FROM_EMAIL';"
EMAIL_FROM=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
if [ "${EMAIL_FROM}" = "no rows selected" ]; then
	ECHO "Aborting: Unable to get the From Email from bns_system_param table."
	exit 1
fi

#Get Wait time
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_WAITTIME';"
WAIT_TIME=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
echo $WAIT_TIME | grep [0-9] > /dev/null 2>&1
if [ $? -ne 0 ]; then
	ECHO "Aborting: Unable to get the WAIT TIME from bns_system_param table."
	exit 1
fi
ECHO "WAIT TIME: $WAIT_TIME"

#Get Wait interval
SQLSTR="select configvalue from bns_system_param where configname ='RATES_UPLOAD_WAIT_INTERVAL';"
WAIT_INTERVAL=`echo $SQLSTR | ${BSQLPLUS} | tail -2`
if [ "${WAIT_INTERVAL}" = "no rows selected" ]; then
	ECHO "Aborting: Unable to get the wait interval from bns_system_param table."
	exit 1
fi
ECHO "WAIT INTERVAL: $WAIT_INTERVAL"

	while [ $CURRENT_WAIT -lt $WAIT_TIME ]
	do
		if [ "${FOUND_FX}" -ne "0" and "${FOUND_MM}" -ne "0" and "${FOUND_MMBBA}" -ne "0" and "${FOUND_FU}" -ne "0" ]; then
			break
		fi
		if [ "${FOUND_MMIN}" -ne "0" ]; then
			break
		fi
		if [ "${FOUND_FX}" -eq "0" ]; then
			if [ -f $DEST_PATH/$SOURCE_FILE_FX ]; then
				if [ -s $DEST_PATH/$SOURCE_FILE_FX ]; then
					ECHO "Found FILE: ${SOURCE_FILE_FX}."
					FOUND_FX=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} FX"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE FX
				else
					ECHO "FILE: ${SOURCE_FILE_FX} is empty."
					FOUND_FX=2
				fi
			else
				ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_FX $SOURCE_PATH $DATASCOPE_SOURCE_FILE_FX GET"
				scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_FX $SOURCE_PATH $DATASCOPE_SOURCE_FILE_FX GET
			fi
		fi

		if [ "${FOUND_MM}" -eq "0" ]; then
			if [ -f $DEST_PATH/$SOURCE_FILE_MM ]; then
				if [ -s $DEST_PATH/$SOURCE_FILE_MM ]; then
                                	FOUND_MM=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} MM"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE MM
				else
					ECHO "FILE: ${SOURCE_FILE_MM} is empty."
					FOUND_MM=2
				fi
                        else
                                ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MM $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MM GET"
                                scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MM $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MM GET
                        fi
		fi

		if [ "${FOUND_MMBBA}" -eq "0" ]; then
			if [ -f $DEST_PATH/$SOURCE_FILE_MMBBA ]; then
				if [ -s $DEST_PATH/$SOURCE_FILE_MMBBA ]; then
                                	FOUND_MMBBA=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} MMBA"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE MMBA
				else
					ECHO "FILE: ${SOURCE_FILE_MMBBA} is empty."
					FOUND_MMBBA=2
				fi
                        else
                                ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMBBA $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMBBA GET"
                                scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMBBA $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMBBA GET
                        fi
		fi


		if [ "${FOUND_MMBK}" -eq "0" ]; then
 			if [ -f $DEST_PATH/$SOURCE_FILE_MMBK ]; then
 				if [ -s $DEST_PATH/$SOURCE_FILE_MMBK ]; then
					FOUND_MMBK=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} MM"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE MMBK
 				else
 					ECHO "FILE: ${SOURCE_FILE_MMBK} is empty."
 					FOUND_MMBK=2
 				fi
 			else
 			ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMBK $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMBK GET"
 			scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMBK $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMBK GET
 			fi
		fi


		if [ "${FOUND_FU}" -eq "0" ]; then
			if [ -f $DEST_PATH/$SOURCE_FILE_FU ]; then
				if [ -s $DEST_PATH/$SOURCE_FILE_FU ]; then
                                	ECHO "Found FILE: ${SOURCE_FILE_FU}."
                                	FOUND_FU=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} FU"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE FU
				else
					ECHO "FILE: ${SOURCE_FILE_FU} is empty."
					FOUND_FU=2
				fi
                        else
                                ECHO "Getting FILE: ${SOURCE_FILE_FU}."
                                ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_FU $SOURCE_PATH $DATASCOPE_SOURCE_FILE_FU GET"
                                scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_FU $SOURCE_PATH $DATASCOPE_SOURCE_FILE_FU GET
			fi
		fi

		if [ "${REGION}" = "IRDREF" and "${FOUND_MMIN}" -ne "0" ]; then
			if [ -f $DEST_PATH/$SOURCE_FILE_MMIN ]; then
				if [ -s $DEST_PATH/$SOURCE_FILE_MMIN ]; then
					ECHO "Found FILE: ${SOURCE_FILE_MMIN}."
					FOUND_FX=1
					ECHO "run_Ratesupload ${REGION} ${RUN_DATE} MMIN"
					$APPDIR/apps/scripts/startup/run_Ratesupload $REGION $RUN_DATE MMIN
				else
					ECHO "FILE: ${SOURCE_FILE_MMIN} is empty."
					FOUND_FX=2
				fi
			else
				ECHO "Getting FILE: ${SOURCE_FILE_MMIN}."
				ECHO "scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMIN $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMIN GET"
				scp_file.sh $SERVER $DEST_PATH $SOURCE_FILE_MMIN $SOURCE_PATH $DATASCOPE_SOURCE_FILE_MMIN GET
			fi
		fi
                ECHO "Searching for files... Sleeping for $WAIT_INTERVAL seconds."
		sleep $WAIT_INTERVAL
		CURRENT_WAIT=`expr ${CURRENT_WAIT} + ${WAIT_INTERVAL}`
	done

	ERROR_MSG="Failed to retrieve files: "
	if [ "${FOUND_FX}" -eq "0" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_FX} not found "
	elif [ "${FOUND_FX}" -eq "2" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_FX} is empty"
	fi
	if [ "${FOUND_MM}" -eq "0" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MM} not found "
	elif [ "${FOUND_MM}" -eq "2" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MM} is empty"
	fi
	if [ "${FOUND_MMBBA}" -eq "0" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MMBBA} not found "
	elif [ "${FOUND_MMBBA}" -eq "2" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MMBBA} is empty"
	fi
	if [ "${FOUND_FU}" -eq "0" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_FU} not found "
	elif [ "${FOUND_FU}" -eq "2" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_FU} is empty"
	fi
	if [ "${REGION}" = "IRDREF" and "${FOUND_MMIN}" -eq "0" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MMIN} not found "
	elif [ "${FOUND_MMIN}" -eq "2" ]; then
		ERROR_MSG="${ERROR_MSG} \n${SOURCE_FILE_MMIN} is empty"
	fi
	if [ "${ERROR_MSG}" = "Failed to retrieve files: " ]; then
		ECHO "All files processed. RatesUpload completed."
	else
		## Email notification to BA
		echo $ERROR_MSG > /tmp/rate_file_message.txt
		mpack -s "$ENV_CODE:: Rate File not found" -r ${EMAIL_FROM} -d /tmp/rate_file_message.txt /tmp/rate_file_message.txt "${EMAIL_TO}"
	fi

ECHO "Finished executing $PROGNAME"
exit 0
