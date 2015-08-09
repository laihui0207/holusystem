<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="componentStyleList.title"/></title>
    <meta name="menu" content="ComponentStyleMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="componentStyleList.heading"/></h2>

<%--<form method="get" action="${ctx}/componentStyles" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>--%>

<p><fmt:message key="componentStyleList.message"/></p>

<%--<div id="actions" class="btn-group">
    <a href='<c:url value="/componentStyleform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>--%>

<display:table name="componentStyleList" class="table table-condensed table-striped table-hover" requestURI="" id="componentStyleList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="componentStyleform" media="html"
        paramId="id" paramProperty="id" titleKey="componentStyle.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="componentStyle.id"/>
    <display:column property="company.companyShortNameCN" sortable="true" titleKey="companyList.title"/>
    <c:if test="${language.equalsIgnoreCase('english')}">
        <display:column  sortable="true" titleKey="componentStyle.processName">
            ${componentStyleList.processDictionary.englishName}_${componentStyleList.processDictionary.processStyle}
        </display:column>
    </c:if>
    <c:if test="${!language.equalsIgnoreCase('english')}">
        <display:column  sortable="true" titleKey="componentStyle.processName">
            ${componentStyleList.processDictionary.chineseName}_${componentStyleList.processDictionary.processStyle}
        </display:column>
            </c:if>
    <display:column property="styleName" sortable="true" titleKey="componentStyle.styleName"/>
    <display:column property="confirmDate" sortable="true" titleKey="processMid.confirmDate"/>
    <display:column property="confirmer.username" sortable="true" titleKey="processMid.confirmer"/>
    <display:column title="">
    <c:if test="${componentStyleList.operationer}">
        <a href="<c:url value='/processMidform/Confirm?SPID=${componentStyleList.styleProcessID}&componentID=${componentID}&type=${componentType}&projectID=${component.project.projectID}'/> ">
            <fmt:message key="processMidList.processMid"/>
        </a>
    </c:if>
    </display:column>
    <display:setProperty name="paging.banner.item_name"><fmt:message key="componentStyleList.componentStyle"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="componentStyleList.componentStyles"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="componentStyleList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="componentStyleList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="componentStyleList.title"/>.pdf</display:setProperty>
</display:table>
