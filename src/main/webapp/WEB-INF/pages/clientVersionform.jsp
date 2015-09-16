<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="clientVersionDetail.title"/></title>
    <meta name="menu" content="ClientVersionMenu"/>
    <meta name="heading" content="<fmt:message key='clientVersionDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="clientVersionList.clientVersion"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="clientVersionDetail.heading"/></h2>
    <fmt:message key="clientVersionDetail.message"/>
</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="clientVersion" method="post" action="clientVersionform" cssClass="well" enctype="multipart/form-data"
               id="clientVersionForm" onsubmit="return validateClientVersion(this)">
        <form:hidden path="id"/>
        <spring:bind path="clientVersion.clientType">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="clientVersion.clientType" styleClass="control-label"/>
        <form:select cssClass="form-control" path="clientType" items="${clientTypes}" itemLabel="label"
                     itemValue="value"/>
        </div>
        <spring:bind path="clientVersion.version">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="clientVersion.version" styleClass="control-label"/>
        <form:input cssClass="form-control" path="version" id="version" maxlength="255"/>
        <form:errors path="version" cssClass="help-block"/>
        </div>
        <spring:bind path="clientVersion.releaseNote">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="clientVersion.releaseNote" styleClass="control-label"/>
        <form:input cssClass="form-control" path="releaseNote" id="releaseNote" maxlength="255"/>
        <form:errors path="releaseNote" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <appfuse:label key="uploadForm.file" styleClass="control-label"/>
            <input type="file" name="file" id="file"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty clientVersion.id}">
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

<v:javascript formName="clientVersion" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['clientVersionForm']).focus();
        $('.text-right.date').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'
        });
    });
</script>
