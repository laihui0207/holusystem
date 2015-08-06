<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="processMidDetail.title"/></title>
    <meta name="menu" content="ProcessMidMenu"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="heading" content="<fmt:message key='processMidDetail.heading'/>"/>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&libraries=convertor"></script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="processMidList.processMid"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="processMidDetail.heading"/></h2>
    <fmt:message key="processMidDetail.message"/>


</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="processMid" method="post" action="${pageContext.request.contextPath}/processMidform"
               cssClass="well"
               id="processMidForm" onsubmit="return validateProcessMid(this)">
        <form:hidden path="id"/>
        <form:hidden path="subComponentID" id="subComponentID" value="${componentID}"/>
        <form:hidden path="styleProcessID" id="styleProcessID" value="${componentStyle.styleProcessID}"/>
        <div class="row">
            <div class="col-sm-6 form-group">
                <appfuse:label key="processMid.project" styleClass="control-label"/>
                <label class="form-control">${project.projectName}</label>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label key="processMid.component" styleClass="control-label"/>
                <label class="form-control">${component.componentName}</label>
            </div>
        </div>
        <c:if test="${subComponent!=null}">
            <div class="form-group">
                <appfuse:label key="processMid.subComponent" styleClass="control-label"/>
                <label class="form-control">${subComponent.subComponentName}</label>
            </div>
        </c:if>
        <div class="row">
            <div class="col-sm-6 form-group">
                <appfuse:label key="processMid.styleName" styleClass="control-label"/>
                <label class="form-control">${componentStyle.styleName}</label>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label key="processMid.processName" styleClass="control-label"/>
                <label class="form-control">${componentStyle.processName}</label>
            </div>
        </div>

        <spring:bind path="processMid.processNote">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="processMid.processNote" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processNote" id="processNote" maxlength="255"/>
        <form:errors path="processNote" cssClass="help-block"/>
        </div>

        <div class="row">
            <spring:bind path="processMid.startDate">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="processMid.startDate" styleClass="control-label"/>
                <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="startDate" cssClass="help-block"/>
            </div>

            <spring:bind path="processMid.endDate">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label key="processMid.endDate" styleClass="control-label"/>
                <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date"
                            datepicker="true"/>
                <form:errors path="endDate" cssClass="help-block"/>
            </div>
        </div>


        <%--<spring:bind path="processMid.styleProcessID">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="processMid.styleProcessID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleProcessID" id="styleProcessID" maxlength="255"/>
        <form:errors path="styleProcessID" cssClass="help-block"/>
        </div>

        <spring:bind path="processMid.subComponentID">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="processMid.subComponentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="subComponentID" id="subComponentID" maxlength="255"/>
        <form:errors path="subComponentID" cssClass="help-block"/>
        </div>--%>
        <spring:bind path="processMid.positionGPS">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="processMid.positionGPS" styleClass="control-label"/>
        <form:input cssClass="form-control" path="positionGPS" id="positionGPS" maxlength="255" readonly="true"/>
        <p class="help-block" id="gpsblocked"></p>
        <form:errors path="positionGPS" cssClass="help-block"/>
        </div>

        <div style="width:515px;height:300px" class="form-group" id="container"></div>

        <div class="form-group">
            <c:if test="${!confirmed}">
                <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                    <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
                </button>
            </c:if>
                <%-- <c:if test="${not empty processMid.id}">
                     <button type="submit" class="btn btn-danger" id="delete" name="delete"
                             onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                         <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                     </button>
                 </c:if>--%>

            <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>

</div>
<%--<v:javascript formName="processMid" cdata="false" dynamicJavascript="true" staticJavascript="true"/>--%>
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
        $("input[type='text']:visible:enabled:first", document.forms['processMidForm']).focus();
        $('#startDate').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'
        });

        $('#endDate').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'
        });
        function showPosition(position, needConvertor) {
            var map = new qq.maps.Map(document.getElementById("container"), {
                center: new qq.maps.LatLng(position.coords.latitude, position.coords.longitude),
                zoom: 13
            });
            var markersArray = [];
            var info = new qq.maps.InfoWindow({map: map});
            var label = new qq.maps.Label({
                offset: new qq.maps.Size(15, 0)
            });

            qq.maps.event.addListener(map, "mousemove", function (e) {
                label.setPosition(e.latLng);
                geocoder = new qq.maps.Geocoder({
                    complete: function (result) {
                        label.setContent(result.detail.address);
                    }
                });
                geocoder.getAddress(e.latLng);

            });
            qq.maps.event.addListener(map, "click", function (e) {
                info.close();
                markMap(e.latLng);
            })
            qq.maps.event.addListener(map, "mouseover", function (e) {
                label.setMap(map);
            });
            qq.maps.event.addListener(map, "mouseout", function (e) {
                label.setMap(null);
            });
            qq.maps.convertor.translate(new qq.maps.LatLng(position.coords.latitude, position.coords.longitude), 1, function (res) {
                latlng = res[0];
                markMap(latlng);
            });
            function markMap(latlng) {
                console.log(latlng);
                if (markersArray) {
                    for (i in markersArray) {
                        markersArray[i].setMap(null);
                    }
                }
                var marker = new qq.maps.Marker({
                    map: map,
                    position: latlng
                });
                markersArray.push(marker)
                geocoder = new qq.maps.Geocoder({
                    complete: function (result) {
                        info.open();
                        info.setContent(result.detail.address)
                        <c:if test="${!confirmed}">
                        $("#positionGPS").val(result.detail.address)
                        </c:if>
                        info.setPosition(result.detail.location);
                    }
                });
                geocoder.getAddress(latlng);
            }

        }

        function getPosition(position) {
            showPosition(position, true);
        }

        function showError(error) {
            // if location fail, then use default position
            var defaultPosition = {};
            //position.coords.latitude, position.coords.longitude   31.821637,117.226739
            defaultPosition = {
                coords: {
                    latitude: '31.821637',
                    longitude: '117.226739'
                }
            }
            $("#gpsblocked").html("<fmt:message key='processMid.gpsBlocked'/>");
            showPosition(defaultPosition, false);

            /*switch(error.code) {
             case error.PERMISSION_DENIED:
             alert("location Refuse");
             break;
             case error.POSITION_UNAVAILABLE:
             alert("Location failed");
             break;
             case error.TIMEOUT:
             alert("Time OUt");
             break;
             case error.UNKNOWN_ERROR:
             alert("Location invalid");
             break;
             }*/

        }

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                    getPosition,
                    showError,
                    {
                        enableHighAcuracy: true,
                        timeout: 10000,
                        maximumAge: 0
                    });
        }


    });
</script>
