<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userTrackList.title"/></title>
    <meta name="menu" content="UserTrackMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="userTrackList.heading"/></h2>

<form method="get" action="${ctx}/userTracks" id="searchForm" class="form-inline">
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

<p><fmt:message key="userTrackList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/userTrackform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="userTrackList" class="table table-condensed table-striped table-hover" requestURI="" id="userTrackList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="userTrackform" media="html"
        paramId="id" paramProperty="id" titleKey="userTrack.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="userTrack.id"/>
    <display:column property="inOrOut" sortable="true" titleKey="userTrack.inOrOut"/>
    <display:column property="ip" sortable="true" titleKey="userTrack.ip"/>
    <display:column sortProperty="loginDate" sortable="true" titleKey="userTrack.loginDate">
         <fmt:formatDate value="${userTrackList.loginDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="optionTable" sortable="true" titleKey="userTrack.optionTable"/>
    <display:column property="userId" sortable="true" titleKey="userTrack.userId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="userTrackList.userTrack"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="userTrackList.userTracks"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="userTrackList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="userTrackList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="userTrackList.title"/>.pdf</display:setProperty>
</display:table>
