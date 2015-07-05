<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postBarList.title"/></title>
    <meta name="menu" content="PostBarMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="postBarList.heading"/></h2>

<form method="get" action="${ctx}/postBars" id="searchForm" class="form-inline">
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

<p><fmt:message key="postBarList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/postBarform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="postBarList" class="table table-condensed table-striped table-hover" requestURI="" id="postBarList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="postBarform" media="html"
        paramId="id" paramProperty="id" titleKey="postBar.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="postBar.id"/>
    <display:column property="title" sortable="true" titleKey="postBar.title"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="postBar.createTime">
         <fmt:formatDate value="${postBarList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="ifAccessAllReply" sortable="true" titleKey="postBar.ifAccessAllReply">
        <input type="checkbox" disabled="disabled" <c:if test="${postBarList.ifAccessAllReply}">checked="checked"</c:if>/>
    </display:column>
    <display:column sortProperty="ifAccessAllView" sortable="true" titleKey="postBar.ifAccessAllView">
        <input type="checkbox" disabled="disabled" <c:if test="${postBarList.ifAccessAllView}">checked="checked"</c:if>/>
    </display:column>
    <display:column property="lastReplyTime" sortable="true" titleKey="postBar.lastReplyTime"/>
    <display:column sortProperty="updateTime" sortable="true" titleKey="postBar.updateTime">
         <fmt:formatDate value="${postBarList.updateTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column  sortable="false" titleKey="list.action">
        <a href="postBars/${postBarList.id}/replies"><fmt:message key="action.replies"/> </a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="postBarList.postBar"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="postBarList.postBars"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="postBarList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="postBarList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="postBarList.title"/>.pdf</display:setProperty>
</display:table>
