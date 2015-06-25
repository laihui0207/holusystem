<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="componentDetail.title"/></title>
    <meta name="menu" content="ComponentMenu"/>
    <meta name="heading" content="<fmt:message key='componentDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="componentList.component"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="componentDetail.heading"/></h2>
    <fmt:message key="componentDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="component" method="post" action="/componentform/${project.id}" cssClass="well"
           id="componentForm" onsubmit="return validateComponent(this)">
<form:hidden path="id"/>
    <spring:bind path="component.componentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.componentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="componentID" id="componentID"  maxlength="255"/>
        <form:errors path="componentID" cssClass="help-block"/>
    </div>
    <spring:bind path="component.componentName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.componentName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="componentName" id="componentName"  maxlength="255"/>
        <form:errors path="componentName" cssClass="help-block"/>
    </div>
    <spring:bind path="component.length">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.length" styleClass="control-label"/>
        <form:input cssClass="form-control" path="length" id="length"  maxlength="255"/>
        <form:errors path="length" cssClass="help-block"/>
    </div>
    <spring:bind path="component.material">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.material" styleClass="control-label"/>
        <form:input cssClass="form-control" path="material" id="material"  maxlength="255"/>
        <form:errors path="material" cssClass="help-block"/>
    </div>
    <spring:bind path="component.pieceList">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.pieceList" styleClass="control-label"/>
        <form:input cssClass="form-control" path="pieceList" id="pieceList"  maxlength="255"/>
        <form:errors path="pieceList" cssClass="help-block"/>
    </div>
    <spring:bind path="component.price">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.price" styleClass="control-label"/>
        <form:input cssClass="form-control" path="price" id="price"  maxlength="255"/>
        <form:errors path="price" cssClass="help-block"/>
    </div>
    <div class="form-group">
        <appfuse:label key="projectList.title" styleClass="control-label"/>
        <form:select cssClass="form-control" path="project.id" items="${projectList}" itemLabel="projectFullName" itemValue="id" disabled="true"/>
    </div>
    <spring:bind path="component.quantity">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.quantity" styleClass="control-label"/>
        <form:input cssClass="form-control" path="quantity" id="quantity"  maxlength="255"/>
        <form:errors path="quantity" cssClass="help-block"/>
    </div>
    <spring:bind path="component.size">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.size" styleClass="control-label"/>
        <form:input cssClass="form-control" path="size" id="size"  maxlength="255"/>
        <form:errors path="size" cssClass="help-block"/>
    </div>
    <div class="form-group">
        <appfuse:label key="component.styleName" styleClass="control-label"/>
        <form:select cssClass="form-control" path="styleName" items="${componentStyleList}" itemLabel="styleName" itemValue="styleName"/>
    </div>
   <%-- <spring:bind path="component.styleName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.styleName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleName" id="styleName"  maxlength="255"/>
        <form:errors path="styleName" cssClass="help-block"/>
    </div>--%>
    <spring:bind path="component.weight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="component.weight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="weight" id="weight"  maxlength="255"/>
        <form:errors path="weight" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty component.id}">
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

<v:javascript formName="component" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['componentForm']).focus();
    });
</script>
