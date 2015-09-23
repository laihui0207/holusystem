<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="settingList.title"/></title>
    <meta name="menu" content="SettingMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="settingList.heading"/></h2>

<form method="get" action="${ctx}/settings" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>

<p><fmt:message key="settingList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/settingform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="settingList" class="table table-condensed table-striped table-hover" requestURI="" id="settingList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="settingform" media="html"
        paramId="id" paramProperty="id" titleKey="setting.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="setting.id"/>
    <display:column property="companyID" sortable="true" titleKey="setting.companyID"/>
    <display:column property="keyArea" sortable="true" titleKey="setting.keyArea"/>
    <display:column property="keyName" sortable="true" titleKey="setting.keyName"/>
    <display:column property="keyValue" sortable="true" titleKey="setting.keyValue"/>
    <display:column property="keyValue1" sortable="true" titleKey="setting.keyValue1"/>
    <display:column property="keyValue2" sortable="true" titleKey="setting.keyValue2"/>
    <display:column property="keyValue3" sortable="true" titleKey="setting.keyValue3"/>
    <display:column property="userID" sortable="true" titleKey="setting.userID"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="settingList.setting"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="settingList.settings"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="settingList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="settingList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="settingList.title"/>.pdf</display:setProperty>
</display:table>
