<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="newsTypeDetail.title"/></title>
    <meta name="menu" content="NewsTypeMenu"/>
    <meta name="heading" content="<fmt:message key='newsTypeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="newsTypeList.newsType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="newsTypeDetail.heading"/></h2>
    <fmt:message key="newsTypeDetail.message"/>
</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="newsType" method="post" action="newsTypeform" cssClass="well"
               id="newsTypeForm" onsubmit="return validateNewsType(this)">
        <form:hidden path="id"/>
        <spring:bind path="newsType.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="newsType.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
        </div>
        <spring:bind path="newsType.comment">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="newsType.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment" maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty newsType.id}">
                <button type="submit" class="btn btn-danger" id="delete" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<v:javascript formName="newsType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['newsTypeForm']).focus();
    });
</script>
