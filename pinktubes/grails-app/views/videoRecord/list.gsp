
<%@ page import="pinktubes.VideoRecord" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'videoRecord.label', default: 'VideoRecord')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'videoRecord.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="duration" title="${message(code: 'videoRecord.duration.label', default: 'Duration')}" />
                        
                            <g:sortableColumn property="imgURL" title="${message(code: 'videoRecord.imgURL.label', default: 'Img URL')}" />
                        
                            <g:sortableColumn property="queryString" title="${message(code: 'videoRecord.queryString.label', default: 'Query String')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'videoRecord.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="videoURL" title="${message(code: 'videoRecord.videoURL.label', default: 'Video URL')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${videoRecordInstanceList}" status="i" var="videoRecordInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${videoRecordInstance.id}">${fieldValue(bean: videoRecordInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: videoRecordInstance, field: "duration")}</td>
                        
                            <td>${fieldValue(bean: videoRecordInstance, field: "imgURL")}</td>
                        
                            <td>${fieldValue(bean: videoRecordInstance, field: "queryString")}</td>
                        
                            <td>${fieldValue(bean: videoRecordInstance, field: "title")}</td>
                        
                            <td>${fieldValue(bean: videoRecordInstance, field: "videoURL")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${videoRecordInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
