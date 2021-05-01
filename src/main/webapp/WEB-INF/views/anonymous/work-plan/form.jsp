<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.workPlan.list.label.beginning" path="beginning"/>
	<acme:form-textbox code="anonymous.workPlan.list.label.ending" path="ending"/>
	<acme:form-textbox code="anonymous.workPlan.list.label.workload" path="workload"/>
	<acme:form-textbox code="anonymous.workPlan.list.label.privacy" path="privacy"/>
	<acme:form-hidden path="workplanId"/>
	
	<acme:form-submit test="${command == 'show'}" code="anonymous.workplan.form.button.tasks" method="get" action="/anonymous/task/list-task-workplan?workplanId=${workplanId}"/>
	<acme:form-return code="anonymous.workplan.form.button.return"/>
</acme:form>



