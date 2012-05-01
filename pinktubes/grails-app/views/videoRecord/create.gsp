

<%@ page import="pinktubes.VideoRecord" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'videoRecord.label', default: 'VideoRecord')}" />
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
            <g:hasErrors bean="${videoRecordInstance}">
            <div class="errors">
                <g:renderErrors bean="${videoRecordInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="duration"><g:message code="videoRecord.duration.label" default="Duration" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'duration', 'errors')}">
                                    <g:textField name="duration" value="${videoRecordInstance?.duration}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imgURL"><g:message code="videoRecord.imgURL.label" default="Img URL" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'imgURL', 'errors')}">
                                    <g:textField name="imgURL" value="${videoRecordInstance?.imgURL}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="queryString"><g:message code="videoRecord.queryString.label" default="Query String" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'queryString', 'errors')}">
                                    <g:textField name="queryString" value="${videoRecordInstance?.queryString}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title"><g:message code="videoRecord.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${videoRecordInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="videoURL"><g:message code="videoRecord.videoURL.label" default="Video URL" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'videoURL', 'errors')}">
                                    <g:textField name="videoURL" value="${videoRecordInstance?.videoURL}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="viewCount"><g:message code="videoRecord.viewCount.label" default="View Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: videoRecordInstance, field: 'viewCount', 'errors')}">
                                    <g:textField name="viewCount" value="${fieldValue(bean: videoRecordInstance, field: 'viewCount')}" />
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
