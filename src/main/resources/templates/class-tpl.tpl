[# th:if="!${#strings.isEmpty(config.packagePrefix)}"]package  [(${config.packagePrefix})];[/]
[# th:each="importClass : ${entity.needImportClass}"]import [(${importClass})];
[/]
public class [# th:if="!${#strings.isEmpty(entity.name)}"][(${entity.name})][/]
[# th:if="!${#strings.isEmpty(entity.parentClass)}"] extends [(${entity.parentClass})][/]
{
    [# th:each="field : ${entity.fields}"]
    /**
     * [(${field.fieldComment})]
     */
    [(${field.packageAccessor})] [(${field.fieldType})] [(${field.fieldName})];
    [/]
}