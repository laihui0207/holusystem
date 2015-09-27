<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="summaryTotalList.title"/></title>
    <meta name="menu" content="SummaryTotalMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="summaryTotalList.heading"/></h2>

<form method="get" action="${ctx}/summaryTotals" id="searchForm" class="form-inline">
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

<p><fmt:message key="summaryTotalList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/summaryTotalform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="summaryTotalList" class="table table-condensed table-striped table-hover" requestURI="" id="summaryTotalList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="summaryTotalform" media="html"
        paramId="id" paramProperty="id" titleKey="summaryTotal.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="summaryTotal.id"/>
    <display:column property="actualPlanPredict" sortable="true" titleKey="summaryTotal.actualPlanPredict"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="summaryTotal.createDate">
         <fmt:formatDate value="${summaryTotalList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createUserID" sortable="true" titleKey="summaryTotal.createUserID"/>
    <display:column sortProperty="curDate" sortable="true" titleKey="summaryTotal.curDate">
         <fmt:formatDate value="${summaryTotalList.curDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="departmentID" sortable="true" titleKey="summaryTotal.departmentID"/>
    <display:column property="departmentPathName" sortable="true" titleKey="summaryTotal.departmentPathName"/>
    <display:column property="departmentRootID" sortable="true" titleKey="summaryTotal.departmentRootID"/>
    <display:column property="itemID" sortable="true" titleKey="summaryTotal.itemID"/>
    <display:column property="itemName" sortable="true" titleKey="summaryTotal.itemName"/>
    <display:column property="itemStyle" sortable="true" titleKey="summaryTotal.itemStyle"/>
    <display:column property="processID" sortable="true" titleKey="summaryTotal.processID"/>
    <display:column property="processName" sortable="true" titleKey="summaryTotal.processName"/>
    <display:column property="itemID" sortable="true" titleKey="summaryTotal.itemID"/>
    <display:column property="projectLevel" sortable="true" titleKey="summaryTotal.projectLevel"/>
    <display:column property="itemName" sortable="true" titleKey="summaryTotal.itemName"/>
    <display:column property="projectRootID" sortable="true" titleKey="summaryTotal.projectRootID"/>
    <display:column property="startOrEnd" sortable="true" titleKey="summaryTotal.startOrEnd"/>
    <display:column property="sumCount" sortable="true" titleKey="summaryTotal.sumCount"/>
    <display:column property="sumDate" sortable="true" titleKey="summaryTotal.sumDate"/>
    <display:column property="sumMonth" sortable="true" titleKey="summaryTotal.sumMonth"/>
    <display:column property="sumRight" sortable="true" titleKey="summaryTotal.sumRight"/>
    <display:column property="sumWeek" sortable="true" titleKey="summaryTotal.sumWeek"/>
    <display:column property="sumWeight" sortable="true" titleKey="summaryTotal.sumWeight"/>
    <display:column property="sumYear" sortable="true" titleKey="summaryTotal.sumYear"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="summaryTotalList.summaryTotal"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="summaryTotalList.summaryTotals"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="summaryTotalList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="summaryTotalList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="summaryTotalList.title"/>.pdf</display:setProperty>
</display:table>
