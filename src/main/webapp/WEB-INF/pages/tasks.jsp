<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="taskList.title"/></title>
    <meta name="menu" content="TaskMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="taskList.heading"/></h2>

<form method="get" action="${ctx}/tasks" id="searchForm" class="form-inline">
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

<p><fmt:message key="taskList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/taskform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="taskList" class="table table-condensed table-striped table-hover" requestURI="" id="taskList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="taskform" media="html"
        paramId="id" paramProperty="id" titleKey="task.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="task.id"/>
    <display:column property="acceptPush" sortable="true" titleKey="task.acceptPush"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="task.createDate">
         <fmt:formatDate value="${taskList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="endCount" sortable="true" titleKey="task.endCount"/>
    <display:column sortProperty="endDate" sortable="true" titleKey="task.endDate">
         <fmt:formatDate value="${taskList.endDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="recordId" sortable="true" titleKey="task.recordId"/>
    <display:column property="startCount" sortable="true" titleKey="task.startCount"/>
    <display:column sortProperty="startDate" sortable="true" titleKey="task.startDate">
         <fmt:formatDate value="${taskList.startDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="taskId" sortable="true" titleKey="task.taskId"/>
    <display:column property="taskName" sortable="true" titleKey="task.taskName"/>
    <display:column property="taskStyle" sortable="true" titleKey="task.taskStyle"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="taskList.task"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="taskList.tasks"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="taskList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="taskList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="taskList.title"/>.pdf</display:setProperty>
</display:table>
