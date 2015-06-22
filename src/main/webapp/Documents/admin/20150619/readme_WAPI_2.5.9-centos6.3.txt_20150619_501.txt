OWNER
==============================================
Don Chen (Owner): dongchen@cisco.com
Peter Sheu (COLD/WBXmgr):(408)234-9222 psheu@cisco.com

DATE
==============================================
30/06/2015

RELEASE NUMBER
==============================================
WAPI2.5.8

MEMO NUMBER
==============================================
wiki page:  http://wikicentral.cisco.com/display/CSGACP/WAPI+2.5.9

PATCH TYPE
==============================================
SP release

REQUIRED DISK SPACE
==============================================
100 MB

RELEASE NAME
==============================================
WAPI 2.5.9

RELEASE PURPOSE
==============================================
Support WebEx11 v1.3.10

CONTENTS
==============================================
Server Type version identifier (STVI):
   awapisvr-1.15

Middle Tier Packages:
	WBXjdk-1.7.0_09-2.el6.x86_64
	WBXtomcat-7.0.30-16.x86_64
	WBXjdkcert-0.2-1.noarch	
	WBXapr-1.4.6-1.x86_64
Tools Packages:
	WBXntpcheck-1.2-4.noarch
	WBXmonplugin-1.1-2.noarch
	WBXLivechk-5.2.5-10.noarch		
	WBXslimdm-5.401-31.x86_64
	WBXmsend-1.0-14.x86_64
	WBXmgr-2.3.6-1.noarch
	WBXnet-snmp-5.6.1-1.x86_64
	WBXcdpr-2.4-0.x86_64
	WBXmonx-3.3-1.x86_64
	WBXjce-1.7.1.noarch
	WBXVASscripts-4.1.0-4.noarch

Release RPM packages:
	WBXconnect-2.5.9-X.x86_64
	WBXcsuper-1.3.15.0-1.x86_64
	WBXcwclient-2.5.0-53.x86_64
	WBXprobeconnect-2.5.0-53.x86_64
	WBXmgr.template.wapi-2.5.9-X.noarch
	WBXscribe-1.1-798.x86_64

Puppet manifest file names:
	pp-tools-mid-1.14-2699.noarch
	pp-awapisvr-app-1.15-XXXX.noarch
	pp-awapisvr-mid-1.3-910.noarch

	
PREINSTALL DB DATA
==============================================
Does this release require DB path or DB scripts?

NO


PREPARATION FOR Templates
==============================================
        
NO

CONFIGURATION INSTRUCTIONS
==============================================
Does this release require SIS XML file changes?

NO

1) Prepare the SIS XML file on SIS server for the WAPI server if you need create a brand new SIS XML file.
    
1.1 Create a wincfg_<PoolType><PoolName>.xml based on RMC Server wapi 2.5.8 Document :wincfg_ahz1.xml.
            
    The SIS XML file might look as below which includes all necessary configurations for WAPI servers. 
	

=============Example for WAPI=================
<?xml version="1.0" ?>
<WINCFG>
......
......
</WINCFG>
=============End example for WAPI=============
Note:
	a) Server Naming
	  Please make sure target server hostname can meet required hostname conversion, for server naming standard, please refer to http://dcoinfo.corp.webex.com/wiki/index.php/Server_Naming_Standards
	  For WAPI, it's hostname will be like "<PoolType><PoolName><ServerType><ServerNumber>", like "weqf1wapi001"

	b) Modify "${...}" to your real value.


1.2 Get the XML file reviewed and approved by the server dev owner. The XML file name shall use this format, wincfg_<PoolType><PoolName>.xml. For example, the XML file for ahz1lgn001 shall be named as wincfg_ahz1.xml

1.3 Place the approved XML file into the ${SIS_SVR}:/${Document_ROOT}/wapi/ directory on your SIS server.

         #cd ${Document_ROOT}
         #chown -R nobody:nobody wapi
         #chmod -R 755 wapi


INSTALLATION INSTRUCTIONS
=======================================================
Notes: Before we do below change, we need make sure this WAPI shard really failover stick to GSB. Without manually failback, it can't auto failback.


The installation of all software packages(OS, Middleware, App package) will be handled by DA 1.0 tool automcatically.
The detail building and setup steps as below:

    1. package change list compare to WAPI2.5.5-CentOS6.3:
	    WBXconnect-2.5.9-XX.86_64
		WBXmgr.template.wapi-2.5.9-XX.noarch
		WBXmgr-2.3.6-1.noarch
		WBXcsuper-1.3.15.0-XX.noarch


    2.  Install steps:

		1)install pp packages:
			yum install pp-awapisvr-app-1.15-XXX
			yum install pp-awapisvr-mid-1.3-910
			yum install pp-tools-mid-1.14-2699
		2)remove all existing app, mid and tools packages
			yum erase 'rpm -qa|grep WBX'
		3)clean yum
			yum clean all
		4)Install packages with DA1.0  -> the server type must be correct
			sudo puppet apply -e'include tools::mid::'1_14
			sudo puppet apply -e'include awapisvr::mid::'1_3
			sudo puppet apply -e'include awapisvr::app::'1_15
    
 	3. Start Wapi server
	      --Start service with command
          #service wbxd start
          --Check service with command
          #service wbxd status
       
        
	    We need GSB team help check the GSB failover/failback setup is correct or not in this new ENV. And manually test it before customer the Shard failback to primary.
        

VERIFICATION INSTRUCTIONS
==============================================
Health Check:
https://<wapiDNS>/wbxconnect/envcheck?cmd=selfcheck
Expected Result:
<status>OKOKOK</status>

https://<SuperadminDNS>/wbxcsuper/detectService?cmd=selfcheck
Expected Result:
<status>OKOKOK</status>

Version:
https://<wapiDNS>/wbxconnect/version.txt
Expected Result:
wapi-2.5.9-XX

https://<SuperadminDNS>/wbxcsuper/version.txt
Expected Result:
WBXcsuper-1.3.15.0-xxxx

Run following command on target Server:
service wbxd -v | grep scribe
Make sure results look like:
Scribe  /opt/webex/scribe/bin/scribed          DA1_1_798


ROLLBACK INSTRUCTIONS
==============================================
Switch back the new pool and turn on the old one
please refre to the document (PXE_RAPID_DOC_ID:WBX0447588) of PXE/RAPID


Pre-release-version:
==============================================
WAPI2.5.8_CentOS 6.3 


DEPENDENCIES
==============================================
RMC part:
Component              RMC Location
Memcached              /RPM File/systuning/centos6.3_64/WBXmemcached-1.4.13_02-6-centos6.3_64.rpm
Local Scribe           /RPM File/dataanalytics/centos6.3_64/WBXscribe-1.1-798-centos6.3_64.rpm 

Jdrive part:
Component              Version          Release #
GSB                    5.1              J:/14930


MISCELLANEOUS
==============================================
Service down time: (minutes) 30 (30 minutes down time for failover to GSB)
Server program restart? Y/N Yes
Server machine reboot? Y/N No
Customer impact? High/Medium/Low Medium

PACKAGE SOURCE
==============================================
RMC: Release-> wapi-> 2.5.9

PRIMARY CONTACTS
===========================================================
Don Chen (US EM): dongchen@cisco.com
Sam Liu (QA Lead): shengl@cisco.com
Peter Sheu (COLD/WBXmgr):(408)234-9222 psheu@cisco.com
