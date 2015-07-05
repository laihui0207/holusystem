<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="documentationList.title"/></title>
    <meta name="menu" content="DocumentationMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="documentationList.heading"/></h2>

<form method="get" action="${ctx}/documentations" id="searchForm" class="form-inline">
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

<p><fmt:message key="documentationList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/documentationform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="documentationList" class="table table-condensed table-striped table-hover" requestURI="" id="documentationList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="documentationform" media="html"
        paramId="id" paramProperty="id" titleKey="documentation.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="documentation.id"/>
    <display:column property="name" sortable="true" titleKey="documentation.name"/>
    <display:column property="introduction" sortable="true" titleKey="documentation.introduction"/>
    <display:column property="comment" sortable="true" titleKey="documentation.comment"/>
    <display:column property="fileName" sortable="true" titleKey="documentation.fileName"/>
    <display:column property="createTime" sortable="true" titleKey="documentation.createTime"/>
    <display:column property="docSize" sortable="true" titleKey="documentation.docSize"/>
    <display:column sortProperty="viewLimit" sortable="true" titleKey="documentation.viewLimit">
        <input type="checkbox" disabled="disabled" <c:if test="${documentationList.viewLimit}">checked="checked"</c:if>/>
    </display:column>
    <display:column titleKey="list.action">
        <a href="documentations/${documentationList.id}/Download"><fmt:message key="action.download"/></a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="documentationList.documentation"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="documentationList.documentations"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="documentationList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="documentationList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="documentationList.title"/>.pdf</display:setProperty>
</display:table>
