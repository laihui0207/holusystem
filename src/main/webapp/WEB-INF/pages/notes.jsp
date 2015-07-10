<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="noteList.title"/></title>
    <meta name="menu" content="NoteMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="noteList.heading"/></h2>

<form method="get" action="${ctx}/notes" id="searchForm" class="form-inline">
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

<p><fmt:message key="noteList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/noteform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
   <%-- <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message
            key="button.done"/></a>--%>
</div>

<display:table name="noteList" class="table table-condensed table-striped table-hover" requestURI="" id="noteList"
               export="true" pagesize="25">
    <display:column property="id" sortable="true" href="noteform" media="html"
                    paramId="id" paramProperty="id" titleKey="note.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="note.id"/>
    <display:column sortable="true" titleKey="note.title">
        <a href="notes/view/${noteList.id}">${noteList.title}</a>
    </display:column>
    <display:column property="createTime" sortable="true" titleKey="note.createTime"/>
    <display:column property="receriveTime" sortable="true" titleKey="note.receriveTime"/>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <display:column property="creater.fullName" sortable="true" titleKey="note.creater"/>
        <display:column property="receiver.fullName" sortable="true" titleKey="note.receiver"/>
    </c:if>
    <display:column titleKey="list.action">
        <a href="notes/view/${noteList.id}" target="_blank"><fmt:message key="action.view"/></a>
        |
        <a href="noteform/${noteList.id}/Send"> <fmt:message key="action.send"/> </a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="noteList.note"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="noteList.notes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="noteList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="noteList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="noteList.title"/>.pdf</display:setProperty>
</display:table>
