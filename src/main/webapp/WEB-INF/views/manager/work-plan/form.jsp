<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:form>
	<acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
	<acme:form-moment code="manager.workplan.form.label.beginning" path="beginning"/>
	<jstl:if test="${suggestedBeginning != beginning && command != 'create' && suggestedBeginning != null}">
		<acme:message code="manager.workplan.form.label.suggestedBeginning"/>
		<acme:print value="${suggestedBeginning}"/>
		<br>
	</jstl:if>
	<acme:form-moment code="manager.workplan.form.label.ending" path="ending"/>
	<jstl:if test="${suggestedEnding != ending && command != 'create' && suggestedEnding != null}">
		<acme:message code="manager.workplan.form.label.suggestedEnding"/>
		<acme:print value="${suggestedEnding}"/>
		<br>
	</jstl:if>
	<acme:form-textbox code="manager.workplan.form.label.workload" path="workload" readonly="true"/>
	<acme:form-select code="manager.workplan.form.label.privacy" path="privacy">
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${privacy == 'PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${privacy == 'PRIVATE'}"/>
	</acme:form-select>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" 
		action="/management/workplan/create"/>
	<acme:form-submit test="${command == 'show' || command == 'update' || command == 'add_task_workplan'}" code="manager.workplan.form.button.update" 
		action="/management/workplan/update"/>
	<acme:form-submit test="${command == 'show' || command == 'update' || command == 'add_task_workplan'}" code="manager.workplan.form.button.delete" 
		action="/management/workplan/delete"/>
	<acme:form-return code="manager.workplan.form.button.return"/>
	<jstl:if test="${command != 'create'}">
		<h2>
			<acme:message code="manager.workplan.form.tasks"/>
		</h2>
		<jstl:if test="${assignedTasks.size() == 0}">
			<acme:message code="manager.workplan.noTasks"/>
		</jstl:if>
		<table aria-hidden="true" class="table table-sm">
			<jstl:forEach items="${assignedTasks}" var="assignedTask">
				<tr>
					<th id="th">
						<a href="management/task/show?id=${assignedTask.id}"><acme:print value="${assignedTask.title}"></acme:print></a>
					</th>
					<td>
						<acme:form-submit code="manager.workplan.form.button.deleteTask" action="/management/workplan/delete_task_workplan?workplanId=${workplanId}&taskId=${assignedTask.id}"/>
					</td>
				</tr>
			</jstl:forEach>
		</table>
	</jstl:if>


	</acme:form>
	<jstl:if test="${command != 'create' && nonAssignedTasks.size() > 0}">
	<br>
	<br>
	<acme:form>
		<acme:form-select code="manager.workplan.form.addTask" path="task">
			<jstl:forEach items="${nonAssignedTasks}" var="nonAssignedTask">
				<acme:form-option code="${nonAssignedTask.title} (${nonAssignedTask.privacy})" value="${nonAssignedTask.id}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-submit code="manager.workplan.form.addTask" action="/management/workplan/add_task_workplan"/>
	</acme:form>
</jstl:if>