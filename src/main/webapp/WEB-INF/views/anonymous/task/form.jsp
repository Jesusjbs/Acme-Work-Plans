<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.title" path="title" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.beginning" path="beginning" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.ending" path="ending" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.workload" path="workload" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.description" path="description" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.link" path="link" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.privacy" path="privacy" readonly="true"/>

	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>