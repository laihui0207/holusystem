<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="rGroupList.title"/></title>
    <meta name="menu" content="RGroupMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="rGroupList.heading"/></h2>

<form method="get" action="${ctx}/rGroups" id="searchForm" class="form-inline">
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

<p><fmt:message key="rGroupList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/rGroupform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="rGroupList" class="table table-condensed table-striped table-hover" requestURI="" id="rGroupList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="rGroupform" media="html"
        paramId="id" paramProperty="id" titleKey="rGroup.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="rGroup.id"/>
    <display:column property="comment" sortable="true" titleKey="rGroup.comment"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="rGroup.createDate">
         <fmt:formatDate value="${rGroupList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="groupID" sortable="true" titleKey="rGroup.groupID"/>
    <display:column property="name" sortable="true" titleKey="rGroup.name"/>
    <display:column property="groupID" sortable="true" titleKey="rGroup.groupID"/>
    <display:column property="groupID" sortable="true" titleKey="rGroup.groupID"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="rGroupList.rGroup"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="rGroupList.rGroups"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="rGroupList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="rGroupList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="rGroupList.title"/>.pdf</display:setProperty>
</display:table>
