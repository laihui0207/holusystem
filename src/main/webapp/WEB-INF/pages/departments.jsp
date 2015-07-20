<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="departmentList.title"/></title>
    <meta name="menu" content="DepartmentMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="departmentList.heading"/></h2>

<form method="get" action="${ctx}/departments" id="searchForm" class="form-inline">
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

<p><fmt:message key="departmentList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/departmentform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="departmentList" class="table table-condensed table-striped table-hover" requestURI="" id="departmentList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="departmentform" media="html"
        paramId="id" paramProperty="id" titleKey="department.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="department.id"/>
    <display:column property="comment" sortable="true" titleKey="department.comment"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="department.createDate">
         <fmt:formatDate value="${departmentList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="departmentID" sortable="true" titleKey="department.departmentID"/>
    <display:column property="level" sortable="true" titleKey="department.level"/>
    <display:column property="name" sortable="true" titleKey="department.name"/>
    <display:column property="positionGPS" sortable="true" titleKey="department.positionGPS"/>
    <display:column property="departmentID" sortable="true" titleKey="department.departmentID"/>
    <display:column property="departmentID" sortable="true" titleKey="department.departmentID"/>
    <display:column property="departmentID" sortable="true" titleKey="department.departmentID"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="departmentList.department"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="departmentList.departments"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="departmentList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="departmentList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="departmentList.title"/>.pdf</display:setProperty>
</display:table>
