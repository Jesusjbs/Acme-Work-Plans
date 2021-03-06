<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="manager.task.form.label.title" path="title" />
	<acme:form-moment code="manager.task.form.label.beginning" path="beginning"/>
	<acme:form-moment code="manager.task.form.label.ending" path="ending"/>
	<acme:form-textbox code="manager.task.form.label.workload" path="workload"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description"/>
	<acme:form-url code="manager.task.form.label.link" path="link"/>
	<acme:form-select code="manager.task.form.label.privacy" path="privacy">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${privacy == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${privacy == 'PRIVATE'}"/>
	</acme:form-select>
	
	<jstl:if test="${command != 'create'}">
		<h4>
			<acme:message code="manager.task.form.label.workplans"/>
		</h4>
	</jstl:if>	
	
	<jstl:forEach items="${workplans}" var="workplan">
		<a href="management/workplan/show?id=${workplan.id}"><acme:print value="${workplan.title} (${workplan.privacy})"></acme:print></a>
		<br>
	</jstl:forEach>
	
	<jstl:if test="${workplans.size() == 0}">
		<acme:message code="manager.task.form.label.noWorkplans"/>
		<br>
	</jstl:if>
	
	<br>
	
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" 
		action="/management/task/create"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.task.form.button.update" 
		action="/management/task/update"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.task.form.button.delete" 
		action="/management/task/delete"/>
	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>