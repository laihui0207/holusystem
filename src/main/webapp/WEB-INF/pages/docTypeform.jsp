<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="docTypeDetail.title"/></title>
    <meta name="menu" content="DocTypeMenu"/>
    <meta name="heading" content="<fmt:message key='docTypeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="docTypeList.docType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="docTypeDetail.heading"/></h2>
    <fmt:message key="docTypeDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="docType" method="post" action="docTypeform" cssClass="well"
           id="docTypeForm" onsubmit="return validateDocType(this)">
<form:hidden path="id"/>
    <spring:bind path="docType.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="docType.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
    <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="docType.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="docType.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty docType.id}">
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

<v:javascript formName="docType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['docTypeForm']).focus();
    });
</script>
