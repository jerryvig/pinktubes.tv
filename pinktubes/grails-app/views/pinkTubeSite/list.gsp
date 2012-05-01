
<%@ page import="pinktubes.PinkTubeSite" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pinkTubeSite.label', default: 'PinkTubeSite')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'pinkTubeSite.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="alexaRank" title="${message(code: 'pinkTubeSite.alexaRank.label', default: 'Alexa Rank')}" />
                        
                            <g:sortableColumn property="alexaUSRank" title="${message(code: 'pinkTubeSite.alexaUSRank.label', default: 'Alexa USR ank')}" />
                        
                            <g:sortableColumn property="domain" title="${message(code: 'pinkTubeSite.domain.label', default: 'Domain')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'pinkTubeSite.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="url" title="${message(code: 'pinkTubeSite.url.label', default: 'Url')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${pinkTubeSiteInstanceList}" status="i" var="pinkTubeSiteInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${pinkTubeSiteInstance.id}">${fieldValue(bean: pinkTubeSiteInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: pinkTubeSiteInstance, field: "alexaRank")}</td>
                        
                            <td>${fieldValue(bean: pinkTubeSiteInstance, field: "alexaUSRank")}</td>
                        
                            <td>${fieldValue(bean: pinkTubeSiteInstance, field: "domain")}</td>
                        
                            <td>${fieldValue(bean: pinkTubeSiteInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: pinkTubeSiteInstance, field: "url")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${pinkTubeSiteInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
