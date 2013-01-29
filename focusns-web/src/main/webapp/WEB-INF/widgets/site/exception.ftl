<div class="exception">
    <ul>
        <#list Request.exception.allErrors as error>
        <li>
            ${error.defaultMessage}
        </li>
        </#list>
    </ul>
</div>