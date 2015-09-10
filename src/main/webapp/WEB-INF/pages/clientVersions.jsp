<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="clientVersionList.title"/></title>
    <meta name="menu" content="ClientVersionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="clientVersionList.heading"/></h2>

<form method="get" action="${ctx}/clientVersions" id="searchForm" class="form-inline">
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

<p><fmt:message key="clientVersionList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/clientVersionform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="clientVersionList" class="table table-condensed table-striped table-hover" requestURI="" id="clientVersionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="clientVersionform" media="html"
        paramId="id" paramProperty="id" titleKey="clientVersion.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="clientVersion.id"/>
    <display:column property="QRCode" sortable="true" titleKey="clientVersion.QRCode"/>
    <display:column property="clientType" sortable="true" titleKey="clientVersion.clientType"/>
    <display:column property="clientUrl" sortable="true" titleKey="clientVersion.clientUrl"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="clientVersion.createTime">
         <fmt:formatDate value="${clientVersionList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="releaseNote" sortable="true" titleKey="clientVersion.releaseNote"/>
    <display:column property="storePath" sortable="true" titleKey="clientVersion.storePath"/>
    <display:column property="version" sortable="true" titleKey="clientVersion.version"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="clientVersionList.clientVersion"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="clientVersionList.clientVersions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="clientVersionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="clientVersionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="clientVersionList.title"/>.pdf</display:setProperty>
</display:table>
