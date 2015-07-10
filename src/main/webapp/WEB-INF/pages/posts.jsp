<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postList.title"/></title>
    <meta name="menu" content="PostMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="postList.heading"/></h2>

<form method="get" action="${ctx}/posts" id="searchForm" class="form-inline">
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

<p><fmt:message key="postList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/postform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
<%--    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
</div>

<display:table name="postList" class="table table-condensed table-striped table-hover" requestURI="" id="postList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="postform" media="html"
        paramId="id" paramProperty="id" titleKey="post.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="post.id"/>
    <display:column property="company.companyShortNameCN" sortable="true" titleKey="post.company"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="post.createDate">
         <fmt:formatDate value="${postList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="postID" sortable="true" titleKey="post.postID"/>
    <display:column property="postName" sortable="true" titleKey="post.postName"/>
    <display:column property="postNote" sortable="true" titleKey="post.postNote"/>
    <display:column property="processName" sortable="true" titleKey="post.processName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="postList.post"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="postList.posts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="postList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="postList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="postList.title"/>.pdf</display:setProperty>
</display:table>
