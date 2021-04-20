<%--
- form.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberPublicTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberPublicTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberPrivateTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberPrivateTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberFinishedTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberFinishedTask}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberNoFinishedTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberNoFinishedTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageWorkloadTask"/>
		</th>
		<td>
			<acme:print value="${averageWorkloadTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minWorkloadTask"/>
		</th>
		<td>
			<acme:print value="${minWorkloadTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxWorkloadTask"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationWorkloadTask"/>
		</th>
		<td>
			<acme:print value="${deviationWorkloadTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxExecutionPeriodsTask"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodsTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minExecutionPeriodsTask"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodsTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageExecutionPeriodsTask"/>
		</th>
		<td>
			<acme:print value="${averageExecutionPeriodsTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationExecutionPeriodsTask"/>
		</th>
		<td>
			<acme:print value="${deviationExecutionPeriodsTask}"/>
		</td>
	</tr>

</table>
