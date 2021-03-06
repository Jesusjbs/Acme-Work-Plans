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
	<acme:message code="administrator.dashboard.form.title.task-indicators"/>
</h2>

<table aria-hidden="true" class="table table-sm">
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

<br>
<br>

<h2>
	<acme:message code="administrator.dashboard.form.title.workplan-indicators"/>
</h2>

<table aria-hidden="true" class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberPublicWorkplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberPublicWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberPrivateWorkplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberPrivateWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberFinishedWorkplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberFinishedWorkplan}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNumberNoFinishedWorkplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberNoFinishedWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageWorkloadWorkplan"/>
		</th>
		<td>
			<acme:print value="${averageWorkloadWorkplan}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minWorkloadWorkplan"/>
		</th>
		<td>
			<acme:print value="${minWorkloadWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxWorkloadWorkplan"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationWorkloadWorkplan"/>
		</th>
		<td>
			<acme:print value="${deviationWorkloadWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxExecutionPeriodsWorkplan"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodsWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minExecutionPeriodsWorkplan"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodsWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageExecutionPeriodsWorkplan"/>
		</th>
		<td>
			<acme:print value="${averageExecutionPeriodsWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationExecutionPeriodsWorkplan"/>
		</th>
		<td>
			<acme:print value="${deviationExecutionPeriodsWorkplan}"/>
		</td>
	</tr>

</table>


<h2>
	<acme:message code="administrator.dashboard.form.title.chart"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<br>
<br>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"TOTAL", "PUBLISHED", "NOT PUBLISHED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberPublicWorkplan} + ${totalNumberPrivateWorkplan}"/>, 
						<jstl:out value="${totalNumberPublicWorkplan}"/>, 
						<jstl:out value="${totalNumberPrivateWorkplan}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0,
							suggestedMax : 1
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
