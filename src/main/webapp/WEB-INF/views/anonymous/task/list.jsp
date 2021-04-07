<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="15%"/>
	<acme:list-column code="anonymous.task.list.label.beginning" path="beginning" width="15%"/>
	<acme:list-column code="anonymous.task.list.label.ending" path="ending" width="15%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="5%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="60%"/>	
</acme:list>