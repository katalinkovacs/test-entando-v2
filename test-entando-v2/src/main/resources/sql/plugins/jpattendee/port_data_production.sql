INSERT INTO widgetcatalog (code, titles, parameters, plugincode, parenttypecode, defaultconfig, locked, maingroup) VALUES ('jpattendeeAttendee', '<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Publish Attendee</property>
<property key="it">Pubblica Attendee</property>
</properties>', '<config>
	<parameter name="id">id</parameter>
	<action name="jpattendeeAttendeeConfig"/>
</config>','jpattendee', NULL, NULL, 1, 'free');

INSERT INTO widgetcatalog (code, titles, parameters, plugincode, parenttypecode, defaultconfig, locked, maingroup) VALUES ('jpattendeeAttendee_list_form', '<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Attendee List and Form</property>
<property key="it">Lista e Form Attendee</property>
</properties>', NULL, 'jpattendee', 'formAction', '<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/FrontEnd/jpattendee/Attendee/list.action</property>
</properties>', 1, 'free');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_ID', 'en', 'id');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_ID', 'it', 'id');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NAME', 'en', 'name');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NAME', 'it', 'name');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_LNAME', 'en', 'lname');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_LNAME', 'it', 'lname');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_AGE', 'en', 'age');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_AGE', 'it', 'age');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_REMARKS', 'en', 'remarks');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_REMARKS', 'it', 'remarks');


INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_ACTIONS', 'it', 'Actions');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_ACTIONS', 'en', 'Actions');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NEW', 'it', 'Attendee New');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NEW', 'en', 'Attendee New');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_EDIT', 'it', 'Attendee Edit');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_EDIT', 'en', 'Attendee Edit');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_TRASH', 'it', 'Attendee Trash');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_TRASH', 'en', 'Attendee Trash');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_TRASH_CONFIRM', 'it', 'Attendee Trash confirm');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_TRASH_CONFIRM', 'en', 'Attendee Trash confirm');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_SEARCH_ATTENDEE', 'it', 'Attendee search');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_SEARCH_ATTENDEE', 'en', 'Attendee search');

INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NOT_FOUND', 'it', 'Attendee not found');
INSERT INTO localstrings ( keycode, langcode, stringvalue ) VALUES ('jpattendee_ATTENDEE_NOT_FOUND', 'en', 'Attendee not found');


INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('jpattendee_is_front_Attendee_entry', 'jpattendeeAttendee_list_form', 'jpattendee', NULL, '<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#assign s=JspTaglibs["/struts-tags"]>
<#assign wp=JspTaglibs["/aps-core"]>
<#assign wpsf=JspTaglibs["/apsadmin-form"]>

<@wp.info key="currentLang" var="currentLangVar" />

<#assign js_for_datepicker="jQuery(function($){
$.datepicker.regional[''it''] = {
closeText: ''Chiudi'',
prevText: ''&#x3c;Prec'',
nextText: ''Succ&#x3e;'',
currentText: ''Oggi'',
monthNames: [''Gennaio'',''Febbraio'',''Marzo'',''Aprile'',''Maggio'',''Giugno'',
''Luglio'',''Agosto'',''Settembre'',''Ottobre'',''Novembre'',''Dicembre''],
monthNamesShort:  [''Gen'',''Feb'',''Mar'',''Apr'',''Mag'',''Giu'',
''Lug'',''Ago'',''Set'',''Ott'',''Nov'',''Dic''],
dayNames: [''Domenica'',''Luned&#236'',''Marted&#236'',''Mercoled&#236'',''Gioved&#236'',''Venerd&#236'',''Sabato''],
dayNamesShort: [''Dom'',''Lun'',''Mar'',''Mer'',''Gio'',''Ven'',''Sab''],
dayNamesMin: [''Do'',''Lu'',''Ma'',''Me'',''Gi'',''Ve'',''Sa''],
weekHeader: ''Sm'',
dateFormat: ''dd/mm/yy'',
firstDay: 1,
isRTL: false,
showMonthAfterYear: false,
yearSuffix: ''''};
});

jQuery(function($) {
 if (Modernizr.touch && Modernizr.inputtypes.date) {
  $.each( $(\"input[data-isdate=true]\"), function(index, item) {
   item.type = ''date'';
  });
 } else {
  $.datepicker.setDefaults( $.datepicker.regional[ \"${currentLangVar}\" ] );
  $(\"input[data-isdate=true]\").datepicker({
	changeMonth: true,
	changeYear: true,
	dateFormat: \"dd/mm/yy\"
   });
 }
});
">
<@wp.headInfo type="JS" info="entando-misc-html5-essentials/modernizr-2.5.3-full.js" />
<@wp.headInfo type="JS_EXT" info="http://code.jquery.com/ui/1.10.1/jquery-ui.js" />
<@wp.headInfo type="CSS_EXT" info="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<@wp.headInfo type="JS_RAW" info="${js_for_datepicker}" />
<section>
<@s.if test="strutsAction==1">
	<h1><@wp.i18n key="jpattendee_ATTENDEE_NEW" /></h1>
</@s.if>
<@s.elseif test="strutsAction==2">
	<h1><@wp.i18n key="jpattendee_ATTENDEE_EDIT" /></h1>
</@s.elseif>
<form action="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/save.action" />" method="post">
	<@s.if test="hasFieldErrors()">
		<div class="alert alert-error">
			<h2><@s.text name="message.title.FieldErrors" /></h2>
			<ul>
				<@s.iterator value="fieldErrors">
					<@s.iterator value="value">
				<li><@s.property/></li>
					</@s.iterator>
				</@s.iterator>
			</ul>
		</div>
	</@s.if>
	<@s.if test="hasActionErrors()">
		<div class="alert alert-error">
			<h2><@s.text name="message.title.ActionErrors" /></h2>
			<ul>
				<@s.iterator value="actionErrors">
				<li><@s.property/></li>
				</@s.iterator>
			</ul>
		</div>
	</@s.if>
	<p class="sr-only">
		<@wpsf.hidden name="strutsAction" />
		<@wpsf.hidden name="id" />
	</p>
	<fieldset>
		<label for="attendee_name"><@wp.i18n key="jpattendee_ATTENDEE_NAME" /></label>
		<input type="text" name="name" id="attendee_name" value="<@s.property value="name" />" />
		<label for="attendee_lname"><@wp.i18n key="jpattendee_ATTENDEE_LNAME" /></label>
		<input type="text" name="lname" id="attendee_lname" value="<@s.property value="lname" />" />
		<label for="attendee_age"><@wp.i18n key="jpattendee_ATTENDEE_AGE" /></label>
		<input type="text" name="age" id="attendee_age" value="<@s.property value="age" />" />
		<label for="attendee_remarks"><@wp.i18n key="jpattendee_ATTENDEE_REMARKS" /></label>
		<input type="text" name="remarks" id="attendee_remarks" value="<@s.property value="remarks" />" />
	</fieldset>
	<@wpsf.submit type="button" cssClass="btn btn-primary">
		<@wp.i18n key="SAVE" />
	</@wpsf.submit>
</form>
</section>', 1);
INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('jpattendee_is_front_Attendee_error', 'jpattendeeAttendee_list_form', 'jpattendee', NULL, '<pre>
   __   _ __   _ __   ___   _ __  
 /''__`\/\`''__\/\`''__\/ __`\/\`''__\
/\  __/\ \ \/ \ \ \//\ \L\ \ \ \/ 
\ \____\\ \_\  \ \_\\ \____/\ \_\ 
 \/____/ \/_/   \/_/ \/___/  \/_/ 
                                  
</pre>', 1);




INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('jpattendee_is_front_Attendee_list', 'jpattendeeAttendee_list_form', 'jpattendee', NULL, '<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#assign s=JspTaglibs["/struts-tags"]>
<#assign wp=JspTaglibs["/aps-core"]>
<#assign wpsa=JspTaglibs["/apsadmin-core"]>

<@wp.info key="currentLang" var="currentLangVar" />

<#assign js_for_datepicker="jQuery(function($){
$.datepicker.regional[''it''] = {
closeText: ''Chiudi'',
prevText: ''&#x3c;Prec'',
nextText: ''Succ&#x3e;'',
currentText: ''Oggi'',
monthNames: [''Gennaio'',''Febbraio'',''Marzo'',''Aprile'',''Maggio'',''Giugno'',
''Luglio'',''Agosto'',''Settembre'',''Ottobre'',''Novembre'',''Dicembre''],
monthNamesShort:  [''Gen'',''Feb'',''Mar'',''Apr'',''Mag'',''Giu'',
''Lug'',''Ago'',''Set'',''Ott'',''Nov'',''Dic''],
dayNames: [''Domenica'',''Luned&#236'',''Marted&#236'',''Mercoled&#236'',''Gioved&#236'',''Venerd&#236'',''Sabato''],
dayNamesShort: [''Dom'',''Lun'',''Mar'',''Mer'',''Gio'',''Ven'',''Sab''],
dayNamesMin: [''Do'',''Lu'',''Ma'',''Me'',''Gi'',''Ve'',''Sa''],
weekHeader: ''Sm'',
dateFormat: ''dd/mm/yy'',
firstDay: 1,
isRTL: false,
showMonthAfterYear: false,
yearSuffix: ''''};
});

jQuery(function($) {
 if (Modernizr.touch && Modernizr.inputtypes.date) {
  $.each( $(\"input[data-isdate=true]\"), function(index, item) {
   item.type = ''date'';
  });
 } else {
  $.datepicker.setDefaults( $.datepicker.regional[ \"${currentLangVar}\" ] );
  $(\"input[data-isdate=true]\").datepicker({
	changeMonth: true,
	changeYear: true,
	dateFormat: \"dd/mm/yy\"
   });
 }
});
">
<@wp.headInfo type="JS" info="entando-misc-html5-essentials/modernizr-2.5.3-full.js" />
<@wp.headInfo type="JS_EXT" info="http://code.jquery.com/ui/1.10.1/jquery-ui.js" />
<@wp.headInfo type="CSS_EXT" info="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<@wp.headInfo type="JS_RAW" info="${js_for_datepicker}" />

<section class="attendee_list">

<h1><@wp.i18n key="jpattendee_ATTENDEE_SEARCH_ATTENDEE" /></h1>

<form action="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/search.action" />" method="post" >

  <fieldset>
		<label for="attendee_id"><@wp.i18n key="jpattendee_ATTENDEE_ID" /></label>
		<input type="text" name="id" id="attendee_id" value="<@s.property value="id" />" />
		<label for="attendee_name"><@wp.i18n key="jpattendee_ATTENDEE_NAME" /></label>
		<input type="text" name="name" id="attendee_name" value="<@s.property value="name" />" />
		<label for="attendee_lname"><@wp.i18n key="jpattendee_ATTENDEE_LNAME" /></label>
		<input type="text" name="lname" id="attendee_lname" value="<@s.property value="lname" />" />
		<label for="attendee_age"><@wp.i18n key="jpattendee_ATTENDEE_AGE" /></label>
		<input type="text" name="age" id="attendee_age" value="<@s.property value="age" />" />
		<label for="attendee_remarks"><@wp.i18n key="jpattendee_ATTENDEE_REMARKS" /></label>
		<input type="text" name="remarks" id="attendee_remarks" value="<@s.property value="remarks" />" />
  </fieldset>

  <button type="submit" class="btn btn-primary">
    <@wp.i18n key="SEARCH" />
  </button>

<@wpsa.subset source="attendeesId" count=10 objectName="groupAttendee" advanced=true offset=5>
<@s.set name="group" value="#groupAttendee" />
<@wp.freemarkerTemplateParameter var="group" valueName="groupAttendee" removeOnEndTag=true >
<div class="margin-medium-vertical text-center">
	<@s.include value="/WEB-INF/apsadmin/jsp/common/inc/pagerInfo.jsp" />
	<@s.include value="/WEB-INF/apsadmin/jsp/common/inc/pager_formBlock.jsp" />
	<#--
	<@wp.fragment code="default_pagerInfo_is" escapeXml=false />
	<@wp.fragment code="default_pagerFormBlock_is" escapeXml=false />
	-->
</div>
<p>
  <a href="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/new.action"></@wp.action>" title="<@wp.i18n key="NEW" />" class="btn btn-info">
    <span class="icon-plus-sign icon-white"></span>&#32;
    <@wp.i18n key="NEW" />
  </a>
</p>
<table class="table table-bordered table-condensed table-striped">
<thead>
<tr>
  <th class="text-right">
    <@wp.i18n key="jpattendee_ATTENDEE_ID" />
  </th>
	<th
                 class="text-left"><@wp.i18n key="jpattendee_ATTENDEE_NAME" /></th>
	<th
                 class="text-left"><@wp.i18n key="jpattendee_ATTENDEE_LNAME" /></th>
	<th
         class="text-right"        ><@wp.i18n key="jpattendee_ATTENDEE_AGE" /></th>
	<th
                 class="text-left"><@wp.i18n key="jpattendee_ATTENDEE_REMARKS" /></th>
	<th>
    <@wp.i18n key="jpattendee_ATTENDEE_ACTIONS" />
  </th>
</tr>
</thead>
<tbody>
<@s.iterator var="attendeeId">
<tr>
	<@s.set var="attendee" value="%{getAttendee(#attendeeId)}" />
	<td class="text-right">
    <a
      href="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/edit.action"><@wp.parameter name="id"><@s.property value="#attendee.id" /></@wp.parameter></@wp.action>"
      title="<@wp.i18n key="EDIT" />: <@s.property value="#attendee.id" />"
      class="label label-info display-block">
    <@s.property value="#attendee.id" />&#32;
    <span class="icon-edit icon-white"></span>
    </a>
  </td>
	<td
            >
    <@s.property value="#attendee.name" />  </td>
	<td
            >
    <@s.property value="#attendee.lname" />  </td>
	<td
         class="text-right"    >
    <@s.property value="#attendee.age" />  </td>
	<td
            >
    <@s.property value="#attendee.remarks" />  </td>
	<td class="text-center">
    <a
      href="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/trash.action"><@wp.parameter name="id"><@s.property value="#attendee.id" /></@wp.parameter></@wp.action>"
      title="<@wp.i18n key="TRASH" />: <@s.property value="#attendee.id" />"
      class="btn btn-warning btn-small">
      <span class="icon-trash icon-white"></span>&#32;
      <@wp.i18n key="TRASH" />
    </a>
  </td>
</tr>
</@s.iterator>
</tbody>
</table>
<div class="margin-medium-vertical text-center">
	<@s.include value="/WEB-INF/apsadmin/jsp/common/inc/pager_formBlock.jsp" />
	<#--
	<@wp.fragment code="default_pagerFormBlock_is" escapeXml=false />
	-->
</div>
</@wp.freemarkerTemplateParameter>
</@wpsa.subset>
</form>
</section>', 1);

INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('jpattendee_is_front_Attendee_trash', 'jpattendeeAttendee_list_form', 'jpattendee', NULL, '<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#assign s=JspTaglibs["/struts-tags"]>
<#assign wp=JspTaglibs["/aps-core"]>
<#assign wpsf=JspTaglibs["/apsadmin-form"]>

<section>
	<h1><wp:i18n key="jpattendee_ATTENDEE_TRASH" /></h1>
	<form action="<@wp.action path="/ExtStr2/do/FrontEnd/jpattendee/Attendee/delete.action" />" method="post">
		<@s.if test="hasFieldErrors()">
			<div class="alert alert-error">
				<h2><@s.text name="message.title.FieldErrors" /></h2>
				<ul>
					<@s.iterator value="fieldErrors">
						<@s.iterator value="value">
						<li><@s.property /></li>
						</@s.iterator>
					</@s.iterator>
				</ul>
			</div>
		</@s.if>
		<@s.if test="hasActionErrors()">
			<div class="alert alert-error">
				<h2><@s.text name="message.title.ActionErrors" /></h2>
				<ul>
					<@s.iterator value="actionErrors">
					<li><@s.property /></li>
					</@s.iterator>
				</ul>
			</div>
		</@s.if>
		<p class="sr-only">
			<@wpsf.hidden name="strutsAction" />
			<@wpsf.hidden name="id" />
		</p>
		<div class="alert alert-warning">
			<p>
				<@wp.i18n key="jpattendee_ATTENDEE_TRASH_CONFIRM" />&#32;<@wp.i18n key="jpattendee_ATTENDEE_ID" />&#32;<@s.property value="id" />?
			</p>
			<p>
				<@wpsf.submit type="button" cssClass="btn btn-warning">
					<span class="icon-trash icon-white"></span>&#32;
					<@wp.i18n key="TRASH" />
				</@wpsf.submit>
			</p>
		</div>
	</form>
</section>', 1);

