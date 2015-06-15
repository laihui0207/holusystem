<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postSubjectDetail.title"/></title>
    <meta name="menu" content="PostSubjectMenu"/>
    <meta name="heading" content="<fmt:message key='postSubjectDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postSubjectList.postSubject"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="postSubjectDetail.heading"/></h2>
    <fmt:message key="postSubjectDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="postSubject" method="post" action="postSubjectform" cssClass="well"
           id="postSubjectForm" onsubmit="return validatePostSubject(this)">
<form:hidden path="id"/>
    <spring:bind path="postSubject.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>
    <spring:bind path="postSubject.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.createTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
        <form:errors path="createTime" cssClass="help-block"/>
    </div>
    <spring:bind path="postSubject.creater">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.creater" styleClass="control-label"/>
        <form:input cssClass="form-control" path="creater" id="creater"  maxlength="255"/>
        <form:errors path="creater" cssClass="help-block"/>
    </div>
    <spring:bind path="postSubject.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="postSubject.updateTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.updateTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updateTime" id="updateTime" size="11" title="date" datepicker="true"/>
        <form:errors path="updateTime" cssClass="help-block"/>
    </div>
    <spring:bind path="postSubject.updater">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postSubject.updater" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updater" id="updater"  maxlength="255"/>
        <form:errors path="updater" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty postSubject.id}">
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

<v:javascript formName="postSubject" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['postSubjectForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
