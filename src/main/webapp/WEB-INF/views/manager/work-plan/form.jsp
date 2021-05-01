<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<h2>
	<acme:message code="manager.workplan.title"/>
</h2>
<acme:form>
	<acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
	<acme:form-moment code="manager.workplan.form.label.beginning" path="beginning"/>
	<acme:form-moment code="manager.workplan.form.label.ending" path="ending"/>
	<acme:form-textbox code="manager.workplan.form.label.workload" path="workload" readonly="true"/>
	<acme:form-select code="manager.workplan.form.label.privacy" path="privacy">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${privacy == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${privacy == 'PRIVATE'}"/>
	</acme:form-select>
<%--  	<acme:form-select code="manager.workplan.form.addTask" path="tasks">
		<jstl:forEach items="${nonAssignedTasks}" var="nonAssignedTask">
			<acme:form-option code="${nonAssignedTask.title}" value="${nonAssignedTask.id}"/>
		</jstl:forEach>
	</acme:form-select> --%>
	<jstl:if test="${command != 'create'}">
		<h2>
			<acme:message code="manager.workplan.form.tasks"/>
		</h2>
		<jstl:if test="${assignedTasks.size() == 0}">
			<acme:message code="manager.workplan.noTasks"/>
		</jstl:if>
		<table class="table table-sm">
			<jstl:forEach items="${assignedTasks}" var="assignedTask">
				<tr>
					<th>
						<a href="/Acme-Planner/manager/task/show?id=${assignedTask.id}"><acme:print value="${assignedTask.title}"></acme:print></a>
					</th>
					<td>
						<acme:form-submit code="manager.workplan.form.button.delete" action="/manager/workplan/delete_task_workplan?workplanId=${workplanId}&taskId=${assignedTask.id}"/>
					</td>
				</tr>
			</jstl:forEach>
		</table>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" 
		action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.workplan.form.button.update" 
		action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="manager.workplan.form.button.delete" 
		action="/manager/workplan/delete"/>
	<acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>