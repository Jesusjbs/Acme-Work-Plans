<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<h2>
	<acme:message code="manager.workplan.title"/>
</h2>
<acme:form>
	<acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
	<acme:form-moment code="manager.workplan.form.label.beginning" path="beginning"/>
	<jstl:if test="${suggestedBeginning != beginning && command != 'create'}">
		<acme:message code="manager.workplan.form.label.suggestedBeginning"/>
		<acme:print value="${suggestedBeginning}"/>
	</jstl:if>
	<acme:form-moment code="manager.workplan.form.label.ending" path="ending"/>
	<jstl:if test="${suggestedEnding != ending && command != 'create'}">
		<acme:message code="manager.workplan.form.label.suggestedEnding"/>
		<acme:print value="${suggestedEnding}"/>
	</jstl:if>
	<acme:form-textbox code="manager.workplan.form.label.workload" path="workload" readonly="true"/>
	<acme:form-select code="manager.workplan.form.label.privacy" path="privacy">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${privacy == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${privacy == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" 
		action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'show' || command == 'update' || command == 'add_task_workplan'}" code="manager.workplan.form.button.update" 
		action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' || command == 'update' || command == 'add_task_workplan'}" code="manager.workplan.form.button.delete" 
		action="/manager/workplan/delete"/>
	<acme:form-return code="manager.workplan.form.button.return"/>
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


	</acme:form>
	<jstl:if test="${command != 'create'}">
	<br>
	<br>
	<acme:form>
		<acme:form-select code="manager.workplan.form.addTask" path="task">
			<jstl:forEach items="${nonAssignedTasks}" var="nonAssignedTask">
				<acme:form-option code="${nonAssignedTask.title} (${nonAssignedTask.privacy})" value="${nonAssignedTask.id}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-submit code="manager.workplan.form.addTask" action="/manager/workplan/add_task_workplan"/>
	</acme:form>
</jstl:if>