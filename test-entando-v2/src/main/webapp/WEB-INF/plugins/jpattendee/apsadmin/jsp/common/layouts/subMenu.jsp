<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wp" uri="/aps-core" %>

<wp:ifauthorized permission="superuser">
	<li><a href="<s:url namespace="/do/jpattendee/Attendee" action="list" />" ><s:text name="jpattendee.title.attendeeManagement" /></a></li>
</wp:ifauthorized>
