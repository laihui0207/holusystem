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

<%--<form method="get" action="${ctx}/projects" id="searchForm" class="form-inline">
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

<p><fmt:message key="projectList.message"/></p>

<%--<div id="actions" class="btn-group">
    <a href='<c:url value="/projectform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
&lt;%&ndash;    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>&ndash;%&gt;
</div>--%>

<display:table name="projectList" class="table table-condensed table-striped table-hover" requestURI="" id="projectList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="projectform" media="html"
        paramId="id" paramProperty="id" titleKey="project.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="project.id"/>
    <display:column property="itemID" sortable="true" titleKey="project.itemID"/>
    <display:column property="projectName" sortable="true" titleKey="project.projectName"/>
    <display:column property="fullName" sortable="true" titleKey="project.fullName"/>
    <display:column property="projectLevel" sortable="true" titleKey="project.projectLevel"/>
    <display:column property="brokerName" sortable="true" titleKey="project.brokerName"/>
    <display:column sortProperty="startDate" sortable="true" titleKey="project.startDate">
        <fmt:formatDate value="${projectList.startDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="endDate" sortable="true" titleKey="project.endDate">
         <fmt:formatDate value="${projectList.endDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="note" sortable="true" titleKey="project.note"/>
    <display:column property="ownerName" sortable="true" titleKey="project.ownerName"/>
    <display:column property="totalCost" sortable="true" titleKey="project.totalCost"/>
    <display:column property="totalWeight" sortable="true" titleKey="project.totalWeight"/>
    <display:column title="">
    <c:if test="${projectList.childProjects.size() > 0 }">
            <a href='<c:url value="/projects?parentID=${projectList.itemID}"/>'><fmt:message key="project.childProject"></fmt:message></a>
    </c:if>
    <c:if test="${projectList.childProjects.size() == 0 }">
            <a href='<c:url value="/components/${projectList.itemID}/Component"/>'><fmt:message key="componentList.components"/></a>
    </c:if>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="projectList.project"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="projectList.projects"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="projectList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="projectList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="projectList.title"/>.pdf</display:setProperty>
</display:table>
