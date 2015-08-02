<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="processMidList.title"/></title>
    <meta name="menu" content="ProcessMidMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="processMidList.heading"/></h2>

<%--<form method="get" action="${ctx}/processMids" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>--%>

<p><fmt:message key="processMidList.message"/></p>

<%--<div id="actions" class="btn-group">
    <a href='<c:url value="/processMidform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>--%>

<display:table name="processMidList" class="table table-condensed table-striped table-hover" requestURI="" id="processMidList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="processMidform" media="html"
        paramId="id" paramProperty="id" titleKey="processMid.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="processMid.id"/>
    <display:column property="styleProcessID" sortable="true" titleKey="processMid.styleProcessID"/>
    <display:column property="subComponentID" sortable="true" titleKey="processMid.subComponentID"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="processMid.createDate">
         <fmt:formatDate value="${processMidList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="endDate" sortable="true" titleKey="processMid.endDate">
         <fmt:formatDate value="${processMidList.endDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="positionGPS" sortable="true" titleKey="processMid.positionGPS"/>
    <display:column property="processNote" sortable="true" titleKey="processMid.processNote"/>
    <display:column sortProperty="startDate" sortable="true" titleKey="processMid.startDate">
         <fmt:formatDate value="${processMidList.startDate}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="processMidList.processMid"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="processMidList.processMids"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="processMidList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="processMidList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="processMidList.title"/>.pdf</display:setProperty>
</display:table>
