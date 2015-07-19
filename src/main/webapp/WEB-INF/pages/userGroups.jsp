<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userGroupList.title"/></title>
    <meta name="menu" content="UserGroupMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="userGroupList.heading"/></h2>

<form method="get" action="${ctx}/userGroups" id="searchForm" class="form-inline">
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

<p><fmt:message key="userGroupList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/userGroupform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
<%--    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
</div>

<display:table name="userGroupList" class="table table-condensed table-striped table-hover" requestURI="" id="userGroupList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="userGroupform" media="html"
        paramId="id" paramProperty="id" titleKey="userGroup.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="userGroup.id"/>
    <display:column property="name" sortable="true" titleKey="userGroup.name"/>
    <display:column property="comment" sortable="true" titleKey="userGroup.comment"/>
    <display:column property="creater.fullName" sortable="true" titleKey="userGroup.creater"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="userGroup.createTime">
         <fmt:formatDate value="${userGroupList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="updateTime" sortable="true" titleKey="userGroup.updateTime">
         <fmt:formatDate value="${userGroupList.updateTime}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="userGroupList.userGroup"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="userGroupList.userGroups"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="userGroupList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="userGroupList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="userGroupList.title"/>.pdf</display:setProperty>
</display:table>
