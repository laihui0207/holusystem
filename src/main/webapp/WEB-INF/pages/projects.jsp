<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="projectList.title"/></title>
    <meta name="menu" content="ProjectMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="projectList.heading"/></h2>

<form method="get" action="${ctx}/projects" id="searchForm" class="form-inline">
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

<p><fmt:message key="projectList.message"/></p>

<%--<div id="actions" class="btn-group">
    <a href='<c:url value="/projectform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>--%>

<display:table name="projectList" class="table table-condensed table-striped table-hover" requestURI="" id="projectList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="projectform" media="html"
        paramId="id" paramProperty="id" titleKey="project.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="project.id"/>
    <display:column property="projectID" sortable="true" titleKey="project.projectID"/>
    <display:column property="projectShortName" sortable="true" titleKey="project.projectShortName"/>
    <display:column property="projectFullName" sortable="true" titleKey="project.projectFullName"/>
    <display:column property="batchFullName" sortable="true" titleKey="project.batchFullName"/>
    <display:column property="batchShortName" sortable="true" titleKey="project.batchShortName"/>
    <display:column property="unitFullName" sortable="true" titleKey="project.unitFullName"/>
    <display:column property="unitShortName" sortable="true" titleKey="project.unitShortName"/>
    <display:column property="company.companyShortNameCN" sortable="true" titleKey="company.title"/>
    <display:column titleKey="list.action">
        <a href="components/${projectList.id}/Component"><fmt:message key="action.components"/></a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="projectList.project"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="projectList.projects"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="projectList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="projectList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="projectList.title"/>.pdf</display:setProperty>
</display:table>
