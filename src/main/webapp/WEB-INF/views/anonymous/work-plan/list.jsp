<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.workPlan.list.label.title" path="title" width="35%" sortable="false"/>
	<acme:list-column code="anonymous.workPlan.list.label.beginning" path="beginning" width="15%"/>
	<acme:list-column code="anonymous.workPlan.list.label.ending" path="ending" width="15%"/>
	<acme:list-column code="anonymous.workPlan.list.label.workload" path="workload" width="5%"/>
 	<acme:list-column code="anonymous.workPlan.list.label.privacy" path="privacy" width="30%" sortable="false"/>

</acme:list>