<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.task.form.label.beginning" path="beginning"/>
	<acme:form-textbox code="authenticated.task.form.label.ending" path="ending"/>
	<acme:form-textbox code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="authenticated.task.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.task.form.label.link" path="link"/>
	<acme:form-textbox code="authenticated.task.form.label.privacy" path="privacy"/>

	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>