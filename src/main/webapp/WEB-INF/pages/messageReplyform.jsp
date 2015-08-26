<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageReplyDetail.title"/></title>
    <meta name="menu" content="MessageReplyMenu"/>
    <meta name="heading" content="<fmt:message key='messageReplyDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="messageReplyList.messageReply"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="messageReplyDetail.heading"/></h2>
    <fmt:message key="messageReplyDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="messageReply" method="post" action="${pageContext.request.contextPath}/messageReplyform" cssClass="well"
           id="messageReplyForm" onsubmit="return validateMessageReply(this)">
<form:hidden path="id"/>
    <form:hidden path="message.id"/>
    <spring:bind path="messageReply.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="messageReply.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty messageReply.id}">
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

<v:javascript formName="messageReply" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['messageReplyForm']).focus();
    });
</script>
