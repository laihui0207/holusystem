<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postSubjectList.title"/></title>
    <meta name="menu" content="PostSubjectMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="postSubjectList.heading"/></h2>

<form method="get" action="${ctx}/postSubjects" id="searchForm" class="form-inline">
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

<p><fmt:message key="postSubjectList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/postSubjectform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
<%--    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
</div>

<display:table name="postSubjectList" class="table table-condensed table-striped table-hover" requestURI="" id="postSubjectList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="postSubjectform" media="html"
        paramId="id" paramProperty="id" titleKey="postSubject.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="postSubject.id"/>
    <display:column property="name" sortable="true" titleKey="postSubject.name"/>
    <display:column property="comment" sortable="true" titleKey="postSubject.comment"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="postSubject.createTime">
         <fmt:formatDate value="${postSubjectList.createTime}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="postSubjectList.postSubject"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="postSubjectList.postSubjects"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="postSubjectList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="postSubjectList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="postSubjectList.title"/>.pdf</display:setProperty>
</display:table>
