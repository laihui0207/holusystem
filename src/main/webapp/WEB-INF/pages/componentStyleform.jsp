<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="componentStyleDetail.title"/></title>
    <meta name="menu" content="ComponentStyleMenu"/>
    <meta name="heading" content="<fmt:message key='componentStyleDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="componentStyleList.componentStyle"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="componentStyleDetail.heading"/></h2>
    <fmt:message key="componentStyleDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="componentStyle" method="post" action="componentStyleform" cssClass="well"
           id="componentStyleForm" onsubmit="return validateComponentStyle(this)">
<form:hidden path="id"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="company" items="companyList" itemLabel="label" itemValue="value"/>
    <spring:bind path="componentStyle.processName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="componentStyle.processName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processName" id="processName"  maxlength="255"/>
        <form:errors path="processName" cssClass="help-block"/>
    </div>
    <spring:bind path="componentStyle.processOrder">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="componentStyle.processOrder" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processOrder" id="processOrder"  maxlength="255"/>
        <form:errors path="processOrder" cssClass="help-block"/>
    </div>
    <spring:bind path="componentStyle.styleName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="componentStyle.styleName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleName" id="styleName"  maxlength="255"/>
        <form:errors path="styleName" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty componentStyle.id}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="componentStyle" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['componentStyleForm']).focus();
    });
</script>
