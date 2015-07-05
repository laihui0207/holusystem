<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageList.title"/></title>
    <meta name="menu" content="MessageMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="messageList.heading"/></h2>

<form method="get" action="${ctx}/messages" id="searchForm" class="form-inline">
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

<p><fmt:message key="messageList.messageInfo"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/messageform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="messageList" class="table table-condensed table-striped table-hover" requestURI="" id="messageList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="messageform" media="html"
        paramId="id" paramProperty="id" titleKey="message.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="message.id"/>
    <display:column property="title" sortable="true" titleKey="message.title"/>
<%--    <display:column property="content" sortable="true" titleKey="message.content"/>--%>
    <display:column property="createTime" sortable="true" titleKey="message.createTime"/>
    <display:column property="status" sortable="true" titleKey="message.status"/>
    <display:column property="owner.fullName" sortable="true" titleKey="message.owner"/>
    <display:column titleKey="list.action">
            <a href="messageform/${messageList.id}/Send"><fmt:message key="action.ReadAndSend"/> </a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="messageList.message"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="messageList.messages"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="messageList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="messageList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="messageList.title"/>.pdf</display:setProperty>
</display:table>
