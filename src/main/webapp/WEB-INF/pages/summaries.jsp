<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="summaryList.title"/></title>
    <meta name="menu" content="SummaryMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="summaryList.heading"/></h2>

<form method="get" action="${ctx}/summaries" id="searchForm" class="form-inline">
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

<p><fmt:message key="summaryList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/summaryform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="summaryList" class="table table-condensed table-striped table-hover" requestURI="" id="summaryList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="summaryform" media="html"
        paramId="id" paramProperty="id" titleKey="summary.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="summary.id"/>
    <display:column property="actualEnd" sortable="true" titleKey="summary.actualEnd"/>
    <display:column property="actualStart" sortable="true" titleKey="summary.actualStart"/>
    <display:column property="createDate" sortable="true" titleKey="summary.createDate"/>
    <display:column property="planEnd" sortable="true" titleKey="summary.planEnd"/>
    <display:column property="planStart" sortable="true" titleKey="summary.planStart"/>
    <display:column property="predictEnd" sortable="true" titleKey="summary.predictEnd"/>
    <display:column property="predictStart" sortable="true" titleKey="summary.predictStart"/>
    <display:column property="processId" sortable="true" titleKey="summary.processId"/>
    <display:column property="processName" sortable="true" titleKey="summary.processName"/>
    <display:column property="styleId" sortable="true" titleKey="summary.styleId"/>
    <display:column property="styleName" sortable="true" titleKey="summary.styleName"/>
    <display:column property="sumDate" sortable="true" titleKey="summary.sumDate"/>
    <display:column property="sumMonth" sortable="true" titleKey="summary.sumMonth"/>
    <display:column property="sumQuantity" sortable="true" titleKey="summary.sumQuantity"/>
    <display:column property="sumRight" sortable="true" titleKey="summary.sumRight"/>
    <display:column property="sumWeek" sortable="true" titleKey="summary.sumWeek"/>
    <display:column property="sumWeight" sortable="true" titleKey="summary.sumWeight"/>
    <display:column property="sumYear" sortable="true" titleKey="summary.sumYear"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="summaryList.summary"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="summaryList.summaries"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="summaryList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="summaryList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="summaryList.title"/>.pdf</display:setProperty>
</display:table>
