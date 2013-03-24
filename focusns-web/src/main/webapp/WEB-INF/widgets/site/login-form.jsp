<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="login">
    <form action='<c:url value="/site/login.action"/>' method="post">
        <div>
            <label>用户名</label>
            <input name="username" value="" />
        </div>
        <div>
            <label>密码</label>
            <input type="password" name="password" />
        </div>
        <div class="submit">
            <c:set value="${param.redirectpath}" var="redirectTo"/>
            <c:forEach items="${param}" var="paramKV" varStatus="status">
                <c:if test="${paramKV.key!='path' and paramKV.key!='redirectpath'}">
                    <c:choose>
                        <c:when test="${not fn:contains(redirectTo, '?')}">
                            <c:set value="${redirectTo}?" var="redirectTo" />
                        </c:when>
                        <c:otherwise>
                            <c:set value="${redirectTo}&" var="redirectTo" />
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${fn:startsWith(paramKV.key, 'redirect')}">
                        <c:set value="${redirectTo}${fn:substringAfter(paramKV.key, 'redirect')}=${paramKV.value}" var="redirectTo" />
                    </c:if>
                </c:if>
            </c:forEach>
            <button type="submit">登录</button>
        </div>
    </form>
</div>