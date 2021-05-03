<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manager.workplan.list.label.title" path="title" width="40%"/>
	<acme:list-column code="manager.workplan.list.label.beginning" path="beginning" width="15%"/>
	<acme:list-column code="manager.workplan.list.label.ending" path="ending" width="15%"/>
	<acme:list-column code="manager.workplan.list.label.workload" path="workload" width="10%"/>
	<acme:list-column code="manager.workplan.list.label.privacy" path="privacy" width="20%"/>
</acme:list>