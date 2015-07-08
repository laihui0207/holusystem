<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="postBarDetail.title"/></title>
    <meta name="menu" content="PostBarMenu"/>
    <meta name="heading" content="<fmt:message key='postBarDetail.heading'/>"/>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap-multiselect.css'/>" type="text/css"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postBarList.postBar"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<%--<div class="col-sm-3">
    <h2><fmt:message key="postBarDetail.heading"/></h2>
    <fmt:message key="postBarDetail.message"/>
</div>--%>

<div class="col-sm-12">
    <h2><fmt:message key="postBarDetail.heading"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="postBar" method="post" action="postBarform" cssClass="well"
               id="postBarForm" onsubmit="return validatePostBar(this)">
        <form:hidden path="id"/>
        <spring:bind path="postBar.title">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postBar.title" styleClass="control-label"/>
        <form:input cssClass="form-control" path="title" id="title" maxlength="255"/>
        <form:errors path="title" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <appfuse:label key="postBar.subject" styleClass="control-label"/>
            <form:select cssClass="form-control" path="postSubject" items="${postSubjectList}" itemLabel="name"
                         itemValue="id"/>
        </div>
        <spring:bind path="postBar.content">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postBar.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="contentinput" maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
        </div>
        <spring:bind path="postBar.ifAccessAllReply">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postBar.ifAccessAllReply" styleClass="control-label"/>
        <form:checkbox path="ifAccessAllReply" id="ifAccessAllReply" cssClass="checkbox"/>
        <form:errors path="ifAccessAllReply" cssClass="help-block"/>
        </div>

        <div class="row" id="replyDiv" style="display: none;">
            <div class="form-group col-sm-6" id="replyuserdiv">
                <appfuse:label key="postBar.replyUsers" styleClass="control-label"/>:<br>
                <form:select cssClass="form-control" path="replyUsers" items="${userList}" itemLabel="fullName"
                             itemValue="id"/>
            </div>
            <div class="form-group col-sm-6" id="replygroupdiv">
                <appfuse:label key="postBar.replyUserGroups" styleClass="control-label"/>:<br>
                <form:select cssClass="form-control" path="replyGroups" items="${userGroupList}" itemLabel="name"
                             itemValue="id"/>
            </div>
        </div>

        <spring:bind path="postBar.ifAccessAllView">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postBar.ifAccessAllView" styleClass="control-label"/>
        <form:checkbox path="ifAccessAllView" id="ifAccessAllView" cssClass="checkbox"/>
        <form:errors path="ifAccessAllView" cssClass="help-block"/>
        </div>

        <div class="row" style="display:none" id="viewDiv">
            <div class="form-group col-sm-6"  id="viewUserdiv">
                <appfuse:label key="postBar.viewUsers" styleClass="control-label"/>:
                <form:select cssClass="form-control" path="viewUsers" items="${userList}" itemLabel="fullName"
                             itemValue="id"/>
            </div>
            <div class="form-group col-sm-6" id="viewGroupdiv">
                <appfuse:label key="postBar.viewUserGroups" styleClass="control-label"/>:
                <form:select cssClass="form-control" path="viewGroups" items="${userGroupList}" itemLabel="name"
                             itemValue="id"/>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty postBar.id}">
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

<v:javascript formName="postBar" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/editor/kindeditor.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript" src="<c:url value='/scripts/multieselect/bootstrap-multiselect.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['postBarForm']).focus();
        KindEditor.ready(function (K) {
            window.editor = K.create('#contentinput', {
                uploadJson: "<c:url value='/editeruploadattachement'/>",
                fileManagerJson: "<c:url value='/editeruploadattachement'/>",
                allowFileManager: true, filterMode: false
            });
        });
        $('.text-right.date').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'
        });
        $("#replyUsers").multiselect();
        $("#replyGroups").multiselect();
        $("#ifAccessAllReply").click(function () {
            $("#replyDiv").toggle(!this.checked);
        })
        if ($("#ifAccessAllReply").attr('checked')) {
            $("#replyDiv").hide();
        }
        else {
            $("#replyDiv").show();
        }
        $("#viewUsers").multiselect();
        $("#viewGroups").multiselect();
        $("#ifAccessAllView").click(function () {
            $("#viewDiv").toggle(!this.checked);
        })
        if ($("#ifAccessAllView").attr('checked')) {
            $("#viewDiv").hide();
        }
        else {
            $("#viewDiv").show();
        }
    });
</script>
