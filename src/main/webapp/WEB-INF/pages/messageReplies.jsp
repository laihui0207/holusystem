<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageReplyList.title"/></title>
    <meta name="menu" content="MessageReplyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="messageReplyList.heading"/></h2>

<form method="get" action="${ctx}/messageReplies" id="searchForm" class="form-inline">
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

<p><fmt:message key="messageReplyList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/messageReplyform/${message.id}"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
<%--    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
</div>

<display:table name="messageReplyList" class="table table-condensed table-striped table-hover" requestURI="" id="messageReplyList" export="true" pagesize="25">
    <%--<display:column property="id" sortable="true" href="messageReplyform" media="html"
        paramId="id" paramProperty="id" titleKey="messageReply.id"/>--%>
    <display:column property="id" media="csv excel xml pdf" titleKey="messageReply.id"/>
    <display:column property="content" sortable="true" titleKey="messageReply.content"/>
<%--    <display:column property="message" sortable="true" titleKey="messageReply.message"/>--%>
    <display:column property="replyUser.username" sortable="true" titleKey="messageReply.replyUser"/>
    <display:column property="createTime" sortable="true" titleKey="messageReply.createTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="messageReplyList.messageReply"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="messageReplyList.messageReplies"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="messageReplyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="messageReplyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="messageReplyList.title"/>.pdf</display:setProperty>
</display:table>
