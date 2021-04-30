<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="manager.workplan.form.label.beginning" path="beginning"/>
	<acme:form-moment code="manager.workplan.form.label.ending" path="ending"/>
	<acme:form-textbox code="manager.workplan.form.label.workload" path="workload" readonly="true"/>
	<acme:form-select code="manager.workplan.form.label.privacy" path="privacy">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${privacy == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${privacy == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-submit test="${command == 'show'}" code="manager.workplan.form.button.tasks" method="get" action="/manager/task/list-task-workplan?workplanId=${workplanId}"/>
	<br>
	<br>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" 
		action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.workplan.form.button.update" 
		action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.workplan.form.button.delete" 
		action="/manager/workplan/delete"/>
	<acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>