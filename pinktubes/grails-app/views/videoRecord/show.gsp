
<%@ page import="pinktubes.VideoRecord" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'videoRecord.label', default: 'VideoRecord')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.duration.label" default="Duration" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "duration")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.imgURL.label" default="Img URL" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "imgURL")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.queryString.label" default="Query String" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "queryString")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.title.label" default="Title" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "title")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.videoURL.label" default="Video URL" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "videoURL")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="videoRecord.viewCount.label" default="View Count" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: videoRecordInstance, field: "viewCount")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${videoRecordInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
