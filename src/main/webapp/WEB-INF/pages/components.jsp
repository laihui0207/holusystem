<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="componentList.title"/></title>
    <meta name="menu" content="ComponentMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="componentList.heading"/></h2>

<form method="get" action="${ctx}/components" id="searchForm" class="form-inline">
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

<p><fmt:message key="componentList.message"/></p>

<%--<div id="actions" class="btn-group">
    <a href='<c:url value="/componentform/${project.id}"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>--%>

<display:table name="componentList" class="table table-condensed table-striped table-hover" requestURI="" id="componentList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="/componentform/${project.id}" media="html"
        paramId="id" paramProperty="id" titleKey="component.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="component.id"/>
    <display:column property="componentID" sortable="true" titleKey="component.componentID"/>
    <display:column property="componentName" sortable="true" titleKey="component.componentName"/>
    <display:column property="createDate" sortable="true" titleKey="component.createDate"/>
    <display:column property="length" sortable="true" titleKey="component.length"/>
    <display:column property="material" sortable="true" titleKey="component.material"/>
    <display:column property="pieceList" sortable="true" titleKey="component.pieceList"/>
    <display:column property="price" sortable="true" titleKey="component.price"/>
    <display:column property="quantity" sortable="true" titleKey="component.quantity"/>
    <display:column property="size" sortable="true" titleKey="component.size"/>
    <display:column property="styleName" sortable="true" titleKey="component.styleName"/>
    <display:column property="creater.fullName" sortable="true" titleKey="component.user"/>
    <display:column property="weight" sortable="true" titleKey="component.weight"/>
    <display:column titleKey="list.action">
        <%--<spring:url value="componentStyles/processlist" var="url">
            <spring:param name="styleName" value="${componentList.styleName}"></spring:param>
            <spring:param name="companyId" value="${componentList.project.company.id}"></spring:param>
        </spring:url>--%>
        <%--<c:url value="/componentStyles/processlist" var="url">
            <c:param name="styleName"><c:out value="${componentList.styleName}"></c:out></c:param>
            <c:param name="companyId">${componentList.project.company.id}</c:param>
        </c:url>--%>
        <a href="${pageContext.request.contextPath}/componentStyles/processlist?styleName=${componentList.styleName}&companyId=${componentList.project.company.id}"><fmt:message key="action.processList"/></a>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="componentList.component"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="componentList.components"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="componentList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="componentList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="componentList.title"/>.pdf</display:setProperty>
</display:table>
