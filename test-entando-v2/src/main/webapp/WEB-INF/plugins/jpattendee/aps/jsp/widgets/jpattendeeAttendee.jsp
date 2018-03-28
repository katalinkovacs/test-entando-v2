<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wp" uri="/aps-core"%>
<%@ taglib prefix="jpattendee" uri="/jpattendee-core"%>

<jpattendee:attendee var="attendee" />
<article>
<c:choose>
	<c:when test="${not empty attendee}">
	<h1><wp:i18n key="jpattendee_ATTENDEE_ID" />: <c:out value="${attendee.id}" /></h1>
	<ul>
		<li>
			<wp:i18n key="jpattendee_ATTENDEE_NAME" />: <c:out value="${attendee.name}" /><br />
			<wp:i18n key="jpattendee_ATTENDEE_LNAME" />: <c:out value="${attendee.lname}" /><br />
			<wp:i18n key="jpattendee_ATTENDEE_AGE" />: <c:out value="${attendee.age}" /><br />
			<wp:i18n key="jpattendee_ATTENDEE_REMARKS" />: <c:out value="${attendee.remarks}" /><br />
		</li>
	</ul>
	</c:when>
	<c:otherwise>
	<div class="alert alert-error">
		<p><wp:i18n key="jpattendee_ATTENDEE_NOT_FOUND" /></p>
	</div>
	</c:otherwise>
</c:choose>
</article>