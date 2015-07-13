<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="companyList.title"/></title>
    <meta name="menu" content="CompanyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="companyList.heading"/></h2>

<form method="get" action="${ctx}/companies" id="searchForm" class="form-inline">
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

<p><fmt:message key="companyList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/companyform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
<%--    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>--%>
</div>

<display:table name="companyList" class="table table-condensed table-striped table-hover" requestURI="" id="companyList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="companyform" media="html"
        paramId="id" paramProperty="id" titleKey="company.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="company.id"/>
    <display:column property="companyAddress" sortable="true" titleKey="company.companyAddress"/>
    <display:column property="companyCode" sortable="true" titleKey="company.companyCode"/>
    <display:column property="companyFax" sortable="true" titleKey="company.companyFax"/>
    <display:column property="companyFullName" sortable="true" titleKey="company.companyFullName"/>
    <display:column property="companyId" sortable="true" titleKey="company.companyId"/>
    <display:column property="companyMaster" sortable="true" titleKey="company.companyMaster"/>
    <display:column property="companyNature" sortable="true" titleKey="company.companyNature"/>
    <display:column property="companyNote" sortable="true" titleKey="company.companyNote"/>
    <display:column property="companyPositionGPS" sortable="true" titleKey="company.companyPositionGPS"/>
    <display:column property="companyShortNameCN" sortable="true" titleKey="company.companyShortNameCN"/>
    <display:column property="companyShortNameEN" sortable="true" titleKey="company.companyShortNameEN"/>
    <display:column property="companyTel" sortable="true" titleKey="company.companyTel"/>
    <display:column property="companyWebSite" sortable="true" titleKey="company.companyWebSite"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="companyList.company"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="companyList.companies"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="companyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="companyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="companyList.title"/>.pdf</display:setProperty>
</display:table>
