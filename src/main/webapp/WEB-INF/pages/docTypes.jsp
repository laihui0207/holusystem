<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="docTypeList.title"/></title>
    <meta name="menu" content="DocTypeMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="docTypeList.heading"/></h2>

<form method="get" action="${ctx}/docTypes" id="searchForm" class="form-inline">
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

<p><fmt:message key="docTypeList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/docTypeform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="docTypeList" class="table table-condensed table-striped table-hover" requestURI="" id="docTypeList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="docTypeform" media="html"
        paramId="id" paramProperty="id" titleKey="docType.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="docType.id"/>
    <display:column property="name" sortable="true" titleKey="docType.name"/>
    <display:column property="comment" sortable="true" titleKey="docType.comment"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="docTypeList.docType"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="docTypeList.docTypes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="docTypeList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="docTypeList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="docTypeList.title"/>.pdf</display:setProperty>
</display:table>
