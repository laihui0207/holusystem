<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageReceiverList.title"/></title>
    <meta name="menu" content="MessageReceiverMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="messageReceiverList.heading"/></h2>

<form method="get" action="${ctx}/messageReceivers" id="searchForm" class="form-inline">
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

<p><fmt:message key="messageReceiverList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/messageReceiverform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="messageReceiverList" class="table table-condensed table-striped table-hover" requestURI="" id="messageReceiverList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="messageReceiverform" media="html"
        paramId="id" paramProperty="id" titleKey="messageReceiver.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="messageReceiver.id"/>
    <display:column property="createTime" sortable="true" titleKey="messageReceiver.createTime"/>
    <display:column property="status" sortable="true" titleKey="messageReceiver.status"/>
    <display:column property="updateTime" sortable="true" titleKey="messageReceiver.updateTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="messageReceiverList.messageReceiver"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="messageReceiverList.messageReceivers"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="messageReceiverList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="messageReceiverList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="messageReceiverList.title"/>.pdf</display:setProperty>
</display:table>
