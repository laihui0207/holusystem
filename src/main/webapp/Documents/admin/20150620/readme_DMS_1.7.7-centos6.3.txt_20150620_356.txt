
This directory contains release of DMS1.7.8

RELEASE_VERSION
==============================================
1.7.8

LAST_RELEASE_VERSION
==============================================
1.7.8

OWNER
==============================================
Dan Fan (Dev): (408)566-7580 dan.fan@webex.com
Hut Wang (DMS): linghwan@cisco.com
Peter Sheu (COLD/WBXmgr):(408)234-9222 psheu@cisco.com

DATE
==============================================

Server type version identifier (STVI):
==============================================
admssvr-1.9
admdsvr-1.9
admisvr-1.9

RELEASE NUMBER
==============================================
DMS_1.7.8

MEMO NUMBER
==============================================
N/A

PATCH TYPE
==============================================
Full release

REQUIRED DISK SPACE
==============================================
200 MB

RELEASE NAME
==============================================
DMS1.7.8

BUILD_NUMBER
==============================================
1.7.8-3

RELEASE PURPOSE
==============================================
For DMS1.7.8

CONTENTS
==============================================


PACKAGE SOURCE
==============================================
Packages included:
WBXdms-<BUILD_NUMBER>-centos6.3_64.rpm
WBXdmi-<BUILD_NUMBER>-centos6.3_64.rpm
WBXdmd-<BUILD_NUMBER>-centos6.3_64.rpm
readme_DMS_1.7.8-centos6.3.txt

Packages location:
1) go to http://rmc.webex.com , login with your CEC account   
2) go to Release --> dms --> 1.7.8
3) in the right panel, click "Document List"  


INSTALLATION DB PATCH AND SOP
==============================================

N/A

PREPARATION FOR templates
==============================================
N/A
        

CONFIGURATION INSTRUCTION
==============================================

Does this release require SIS XML file changes?

YES.
	
Need to change SIS XML file. Template has changed, so the SIS change list as below:
 
1) Upgrade from DMS1.7.5 to DMS1.7.8, change items as below:
---------------------------------------------
   a.  [Original]:
   				<WIN Version='DMS 1.7.7'
       [New]:
       		<WIN Version='DMS 1.7.8' 


   b.  [Original]:
   				ConfigVersion="DMS1.7.7"> 
   		 [New]:
   		 		ConfigVersion="DMS1.7.8">
2) Add key manager configure in sis file
--------------------------------------------- 
add new attribute in ClusterInfo: 
CompatibleMode='false' Apptoken='disabled' SessionKey='disabled' ExternalKey='disabled' InternalKey='disabled' SMACKey='disabled'

Result like:
<ClusterInfo IsGSB='0' DataCenterName='SZ' BackupLogTime='10 8 * * *' CompatibleMode='false' Apptoken='disabled' SessionKey='disabled' ExternalKey='disabled' InternalKey='disabled' SMACKey='disabled'/>
				
3) If this is new SIS server, please create new SIS XML file.
--------------------------------------------- 
	
=============Example for DMS=============
<?xml version="1.0" encoding="UTF-8"?>
<WINCFG>
    <WIN Version="DMS 1.7.3.1" Comment="DCQF DMS COLD for DMS 1.7.3" ConfigVersion="DMD1.7.3.1">
<ClusterInfo IsGSB='0' DataCenterName='SZ' BackupLogTime='10 8 * * *' CompatibleMode='false' Apptoken='disabled' SessionKey='disabled' ExternalKey='disabled' InternalKey='disabled' SMACKey='disabled'/>
        <ZooKeeperInfo ListenPort="2181" TickTime="3000" DataDir="/var/zookeeperData" InitLimit="10" SyncLimit="10" CleanZooKeeperLogTime="0 22 * * *"/>
    <MemCacheInfo RunUserName='wbx-memcached' Memory='4096' Connection='1024' ListenPort='11211' EnableLog='false' AuthUserName='memuser' AuthPassword='W3b3x!cach3'/>
        <DatabaseServer Type="DMS" UserName="dms" Password="pass" DriveURL="jdbc:inetora:10.224.38.13:3004?service=qfdms.webex.com&amp;failover=true"/>
        <DependentServiceList>
            <DependentService Type="ServiceManager" FloatDNS="user-qf1-qz1.qa.webex.com" Protocol="http" ListenPort="80"/>
            <DependentService Type="WAPI1X" FloatDNS="dfwapi-qf1-qz1.qa.webex.com" Protocol="https" Listenport="8080" FailRetryTime="-1"/>
            <DependentService Type="WAPI2X" Protocol="https"/>
            <DependentService Type="LDSF-Search" FloatDNS="search-app-qf.qa.webex.com" ListenPort="8090" Protocol="http"/>
            <DependentService Type="LDSF-Index" FloatDNS="search-app-qf.qa.webex.com" ListenPort="8090" Protocol="http"/>
            <DependentService Type="CAS" URL="https://login-qf1-qz1.qa.webex.com/cas/auth.do"/>
            <DependentService Type="DMS" StaticDNS="dms-qf1-s.qa.webex.com" Protocol="http" StaticListenPort="8080" StaticListenPortForSPI="8090" StaticListenPortForDMD="8070"/>
            <DependentService Type="DMD" FloatDNS="dmd-qf1-qz1.qa.webex.com" FloatListenPort="443" Protocol="https" StaticDNS="dmd-qf1-s.qa.webex.com" StaticListenPort="8080"/>
            <DependentService Type="DMI" StaticDNS="dmi-qf1-s.qa.webex.com" StaticListenPort="80"/>
            <DependentService Type="GSB60" FloatDNS="hfqagsb6.qa.webex.com" Protocol="http" ListenPort="80"/>
            <DependentService Type="MessageQueueNode" FloatDNS="msgsvr-qf1-qz1.qa.webex.com" ListenPort="12320" Token="KCojQCRIREZJa2RoQCQpJkAjXnczaWhm" UseQD="true"/>
            <DependentService Type="GlobalPubSub" Use="true"/>
            <DependentService Type="Scribe" LocalListenPort="1463" RemoteDNS="scribe-qf1.qa.webex.com" RemoteListenPort="1499" RemoteListenPortForHealthCheck="12339" Enable="true"/>
        </DependentServiceList>
        <DMIInstanceList>
            <DMIInstance ListenPort="8080" ShutdownPort="8001" AJPPort="8041" MonitorPort="8444"/>
        </DMIInstanceList>
        <DMSInstanceList>
            <DMSInstance ListenPort="8080" ListenPortForSPI="8090" ListenPortForDMD="8070" ShutdownPort="8001" AJPPort="8041" MonitorPort="8444"/>
        </DMSInstanceList>
        <DMSServerList JavaOption="-D64 -Xms4096m -Xmx4096m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true -Djute.maxbuffer=4000000" Compatible="false">
            <DMSServer HostName="aqf1dms001" IP="10.224.83.70"/>
        </DMSServerList>
        <DMIServerList JavaOption="-D64 -Xms4096m -Xmx4096m -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true -Djute.maxbuffer=4000000">
            <DMIServer HostName="aqf1dmi001" IP="10.224.83.71"/>
            <DMIServer HostName="aqf1dmi002" IP="10.224.83.72"/>
        </DMIServerList>
        <DMDServerList ListenPort="8080" ListenPortForIDMAP="8181" AllowNetwork="10.224.0.0/16" EnableCheck="FALSE">
            <DMDServer HostName="aqf1dmd001" IP="10.224.83.69" WorkerProcesses="8"/>
        </DMDServerList>
        <DataCenterMapList>
            <DataCenterMap DataCenterName="QF" GSBDataCenterName="QF"/>
        </DataCenterMapList>
        <LogicalDataCenterMapList>
            <LogicalDataCenterMap LogicalDataCenterName="QF" PhysicalDataCenterName="QF"/>
        </LogicalDataCenterMapList>
        <StorageList>
            <Storage RemoteStorage="hfengnfs.qa.webex.com:/hfdnfs/dcqf/dcqf_dms_wbxdms" LocalMountPoint="/wbxdms" Comment="First DMS Storage mount point used for connect DMS"/>
            <Storage RemoteStorage="hfengnfs.qa.webex.com:/hfdnfs/dcqf/dcqf_dms_wbxdms2" LocalMountPoint="/wbxdms2" Comment="Second DMS Storage mount point used for connect DMS"/>
            <Storage RemoteStorage="hfengnfs.qa.webex.com:/hfdnfs/dcqf/dcqf_dms_ardms" LocalMountPoint="/ardms" Comment="First DMS Storage mount point used for Artemis DMS"/>
            <Storage RemoteStorage="hfengnfs.qa.webex.com:/hfdnfs/dcqf/dcqf_dms_cdn" LocalMountPoint="/cdn" Comment="For File CDN"/>
            <Storage RemoteStorage="hfengnfs.qa.webex.com:/hfdnfs/dcqf/dcqf_dms_migcontainers" LocalMountPoint="/migcontainers" Comment="For migcontainers"/>
        </StorageList>
        <AllowDomainList>
            <AllowDomain DomainName="*.qa.webex.com"/>
        </AllowDomainList>
        <ApplicationIPLimit AllowIPList="127.0.0.1|192.168.252.59|192.168.252.6[0-5]|192.168.253.27|192.168.251.21[8-9]|10.224.*.*|173.*.*.*" DenyIPList=""/>
    </WIN>
</WINCFG> 
=======End example for DMS=============



Note: All configure items are just same as DMS 1.7.4 centos6.3 except some change described in section 





INSTALLATION INSTRUCTIONS
=======================================================
DMS use "pool switch" to upgrade to CentOS6.3 and the installation of all software packages will be done by DA 1.0 when provision machine from PXE/RAPID

    1. Provision the new pool
       For an existing pool to be upgraded, obtain the same number of machines from PXE/RAPID. Those new machines will be in a VLAN and with temporary IP addresses and hostnames.
       please refre to the document (DMSID: WBX0447588) of PXE/RAPID

    2. Steps before shutdown
       1) Prepare the SIS XML file, please refer to the above "CONFIGURATION INSTRUCTIONS"

    3. Shutdown the old pool
       1) Fail over the old pool to Primary/GSB accordingly
       2) Shutdown all machines in the old pool

    4. Steps before switch
    	 N/A.

    5. Switch on the new pool
       Switch the new pool into the place of the old pool. please refre to the document (DMSID: WBX0447588) of PXE/RAPID

    6. Steps after switch
			1) Login DMI/DMS/DMD server as root
	    			#/etc/init.d/wbxd stop all
	    	2) #/etc/init.d/wbxd start all

POST INSTRUCTIONS
==============================================
	A. After start all DMI/DMS/DMD servers, please restart all DMI servers.
	Notes: If your have not follow the start squence, DMS and DMI can't work normally, you can restart all DMS servers and then restart all DMI servers. 


INSTALLATION VERFICATION
==============================================
Health Check:
DMS Check:
	http://${dms_server_ip}:$(dms_port)/wbxdms/detectService?cmd=selfcheck	
	sucess result: OKOKOK
DMD Check:
	http://${dmd_server_ip}:${dmd_port}/wbxdmd/detectService?cmd=selfcheck
	sucess result: OKOKOK
DMI check:	
	http://${dmi_server_ip}:${dmi_port}/wbxdmi/detectService?cmd=selfcheck
	sucess result: OKOKOK


DEPENDENCE VERIFY:
==============================================
Run following command on DMS Server check scribe status:
		#service wbxd  -v | grep scribe
		Make sure results look like:  Scribe  /opt/webex/scribe/bin/scribed    DA1_1_798

ROLLBACK INSTRUCTIONS
==============================================
	A. Switch back the new pool and turn on the old one, please refre to the document (DMSID: WBX0447588) of PXE/RAPID
	B. Reinstall DMS1.7.6


DEPENDENCIES
==============================================
DEPENDENCIES
==============================================
DMD server install by PXE/RAPID should include:
		[Dependency]				
				WBXmsend-1.0-14.x86_64
				WBXsysctlTuning-1.0.0-1.el6_3.x86_64
				WBXmgr.template.nodemanage-3.3-1.noarch
				WBXcdpr-2.4-0.x86_64
				WBXslimdm-5.401-31.x86_64
				WBXmgr-2.3.6-1.noarch
				WBXVASscripts-4.1.0-4.noarch
				WBXmgr.template.dms-1.7.8-10.noarch
				WBXntpcheck-1.2-4.noarch
				WBXmonplugin-1.1-2.noarch
				WBXnet-snmp-5.6.1-1.x86_64
				WBXLivechk-5.2.5-10.noarch
				WBXmonx-3.3-1.x86_64
				WBXdmd-1.7.8-3.x86_64


	
	pp

				pp-admdsvr-mid-1.0-227.noarch
				pp-admdsvr-app-1.8-XXXX.noarch
				pp-tools-mid-1.14-2699.noarch



DMS server install by PXE/RAPID should include:
		[Dependency]
		
				WBXmsend-1.0-14.x86_64
				WBXsysctlTuning-1.0.0-1.el6_3.x86_64
				WBXmgr-2.3.6-1.noarch
				WBXmgr.template.nodemanage-3.3-1.noarch
				WBXcdpr-2.4-0.x86_64
				WBXslimdm-5.401-31.x86_64
				WBXmemcached-1.4.13_02-6.x86_64
				WBXjce-1.7-1.noarch
				WBXtomcat-7.0.30-16.x86_64
				WBXmgr.template.dms-1.7.8-10.noarch
				WBXscribe-1.1-798.x86_64
				WBXntpcheck-1.2-4.noarch
				WBXmonplugin-1.1-2.noarch
				WBXnet-snmp-5.6.1-1.x86_64
				WBXmonx-3.3-1.x86_64
				WBXVASscripts-4.0.3-4.noarch
				WBXLivechk-5.2.5-7.noarch
				WBXjdk-1.7.0_09-2.el6.x86_64
				WBXapr-1.4.6-1.x86_64
				WBXdms-1.7.8-3.x86_64
				

    pp-
				pp-admssvr-mid-1.2-1312.noarch
				pp-tools-mid-1.14-2699.noarch
				pp-admssvr-app-1.7-2451.noarch



DMI server install by PXE/RAPID should include:
		[Dependency]
				WBXmsend-1.0-14.x86_64
				WBXsysctlTuning-1.0.0-1.el6_3.x86_64
				WBXmgr-2.3.6-1.noarch
				WBXmgr.template.nodemanage-3.3-1.noarch
				WBXcdpr-2.4-0.x86_64
				WBXslimdm-5.401-31.x86_64
				WBXjdk-1.7.0_09-2.el6.x86_64
				WBXjmagick-6.4.0-1.x86_64
				WBXghostscript-9.05-1.x86_64
				WBXlibreoffice-3.6.3-2.x86_64
				WBXapr-1.4.6-1.x86_64
				WBXdmi-1.7.8-3.x86_64
				WBXntpcheck-1.2-4.noarch
				WBXmonplugin-1.1-2.noarch
				WBXnet-snmp-5.6.1-1.x86_64
				WBXmonx-3.3-1x86_64
				WBXVASscripts-4.0.3-4.noarch
				WBXLivechk-5.2.5-7.noarch
				WBXimagemagick-6.8.0_7-1.x86_64
				WBXzookeeper-3.3.6-1.x86_64
				WBXghostscript-fonts-8.11-1.x86_64
				WBXjce-1.7-1.noarch
				WBXtomcat-7.0.30-16.x86_64
				WBXmgr.template.dms-1.7.6-10.noarch
				

pp-			

				pp-admisvr-mid-1.2-1312.noarch
				pp-admisvr-app-1.7-2451.noarch
				pp-tools-mid-1.14-2699.noarch


				
Other service dependency:
Component	Version	Release #
DA 	1.0 and above	DA-1.0	
GSB 	v6.4.2 and above	15097
QD1.2.7 	1.2.7 and above	QD1.2.7
LDSF 1.7	1.7 and above	LDSF_1.7.0


MISCELLANEOUS
==============================================
Service down time: (minutes) 10 (10 minutes down time for failover to GSB)
Server program restart? Y/N Yes
Server machine reboot? Y/N No
Customer impact? High/Medium/Low High

PRIMARY CONTACTS
===========================================================
Dan Fan (Dev): (408)566-7580 dan.fan@webex.com
Sam Liu (QA Lead):(408)235-2447 Sam.Liu@webex.com
Peter Sheu (COLD/WBXmgr):(408)234-9222 peter.sheu@webex.com
