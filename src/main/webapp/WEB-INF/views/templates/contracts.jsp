<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
	<c:forEach items="${contracts}" var="contract">
		<li><c:out value="${contract.id}" />: <c:out value="${contract.page}" /></li>
	</c:forEach>
</ul>
