<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="documentationDetail.title"/></title>
    <meta name="menu" content="DocumentationMenu"/>
    <meta name="heading" content="<fmt:message key='documentationDetail.heading'/>"/>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap-multiselect.css'/>" type="text/css"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="documentationList.documentation"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="documentationDetail.heading"/></h2>
    <fmt:message key="documentationDetail.message"/>
</div>

<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="documentation" method="post" action="documentationform" cssClass="well" enctype="multipart/form-data"
               id="documentationForm" onsubmit="return validateDocumentation(this)">
        <form:hidden path="id"/>
        <spring:bind path="documentation.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="documentation.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
        </div>
        <div class="form-group">
            <appfuse:label key="documentation.docType" styleClass="control-label"/>:
            <form:select cssClass="form-control" path="docType.id" items="${docTypeList}" itemLabel="name" itemValue="id"/>
        </div>
        <spring:bind path="documentation.introduction">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="documentation.introduction" styleClass="control-label"/>
        <form:input cssClass="form-control" path="introduction" id="introduction" maxlength="255"/>
        <form:errors path="introduction" cssClass="help-block"/>
        </div>
        <%-- <spring:bind path="documentation.content">
         <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         </spring:bind>
             <appfuse:label key="documentation.content" styleClass="control-label"/>
             <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
             <form:errors path="content" cssClass="help-block"/>
         </div>--%>
        <div class="form-group">
            <appfuse:label key="uploadForm.file" styleClass="control-label"/>
            <input type="file" name="file" id="file"/>
        </div>

        <spring:bind path="documentation.viewLimit">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="documentation.viewLimit" styleClass="control-label"/>
        <form:checkbox path="viewLimit" id="viewLimit" cssClass="checkbox"/>
        <form:errors path="viewLimit" cssClass="help-block"/>
        </div>

        <div class="form-group" style="display:none" id="viewUserdiv">
            <appfuse:label key="documentation.viewUser" styleClass="control-label"/>:
            <form:select cssClass="form-control" path="viewUsers" items="${userList}" itemLabel="fullName"
                         itemValue="id"/>
        </div>
        <div class="form-group" style="display:none" id="viewGroupdiv">
            <appfuse:label key="documentation.viewGroups" styleClass="control-label"/>:
            <form:select cssClass="form-control" path="viewUserGroups" items="${userGroupList}" itemLabel="name"
                         itemValue="id"/>
        </div>
        <spring:bind path="documentation.comment">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="documentation.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment" maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty documentation.id}">
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

<v:javascript formName="documentation" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/multieselect/bootstrap-multiselect.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/editor/kindeditor.js'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['documentationForm']).focus();
        $("#viewUsers").multiselect();
        $("#viewUserGroups").multiselect();
        $("#viewLimit").click(function () {
            $("#viewUserdiv").toggle(this.checked);
            $("#viewGroupdiv").toggle(this.checked);
        })
        if ($("#viewLimit").attr('checked')) {
            $("#viewUserdiv").show();
            $("#viewGroupdiv").show();
        }
        else {
            $("#viewUserdiv").hide();
            $("#viewGroupdiv").hide();
        }
        KindEditor.ready(function (K) {
            window.editor = K.create('#introduction', {
                uploadJson: "<c:url value='/editeruploadattachement'/>",
                fileManagerJson: "<c:url value='/editeruploadattachement'/>",
                allowFileManager: true, filterMode: false
            });
        });
    });
</script>
