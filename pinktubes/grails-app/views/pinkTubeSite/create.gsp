

<%@ page import="pinktubes.PinkTubeSite" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pinkTubeSite.label', default: 'PinkTubeSite')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${pinkTubeSiteInstance}">
            <div class="errors">
                <g:renderErrors bean="${pinkTubeSiteInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="alexaRank"><g:message code="pinkTubeSite.alexaRank.label" default="Alexa Rank" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pinkTubeSiteInstance, field: 'alexaRank', 'errors')}">
                                    <g:textField name="alexaRank" value="${fieldValue(bean: pinkTubeSiteInstance, field: 'alexaRank')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="alexaUSRank"><g:message code="pinkTubeSite.alexaUSRank.label" default="Alexa USR ank" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pinkTubeSiteInstance, field: 'alexaUSRank', 'errors')}">
                                    <g:textField name="alexaUSRank" value="${fieldValue(bean: pinkTubeSiteInstance, field: 'alexaUSRank')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="domain"><g:message code="pinkTubeSite.domain.label" default="Domain" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pinkTubeSiteInstance, field: 'domain', 'errors')}">
                                    <g:textField name="domain" value="${pinkTubeSiteInstance?.domain}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="pinkTubeSite.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pinkTubeSiteInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${pinkTubeSiteInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url"><g:message code="pinkTubeSite.url.label" default="Url" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pinkTubeSiteInstance, field: 'url', 'errors')}">
                                    <g:textField name="url" value="${pinkTubeSiteInstance?.url}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
