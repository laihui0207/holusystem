<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="subComponentListList.title"/></title>
    <meta name="menu" content="SubComponentListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="subComponentListList.heading"/></h2>

<form method="get" action="${ctx}/subComponentLists" id="searchForm" class="form-inline">
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

<p><fmt:message key="subComponentListList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/subComponentListform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="subComponentListList" class="table table-condensed table-striped table-hover" requestURI="" id="subComponentListList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="subComponentListform" media="html"
        paramId="id" paramProperty="id" titleKey="subComponentList.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="subComponentList.id"/>
    <display:column property="componentID" sortable="true" titleKey="subComponentList.componentID"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="subComponentList.createDate">
         <fmt:formatDate value="${subComponentListList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="subComponentID" sortable="true" titleKey="subComponentList.subComponentID"/>
    <display:column property="subComponentName" sortable="true" titleKey="subComponentList.subComponentName"/>
    <display:column property="subQuantity" sortable="true" titleKey="subComponentList.subQuantity"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="subComponentListList.subComponentList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="subComponentListList.subComponentLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="subComponentListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="subComponentListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="subComponentListList.title"/>.pdf</display:setProperty>
</display:table>
