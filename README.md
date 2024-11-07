

В devTools перейти в консоль и ввесли локтор через$ или $$. Можно увидеть один или несколько найдется

```$$("ul.list-style-none")```

```$$("ul.list-style-none li")```

``` $("#repository-container-header nav ul li a#wiki-tab")```

![Screenshot from 2024-10-16 19-32-47.png](src/test/resources/Screenshot%20from%202024-10-16%2019-32-47.png)
![Screenshot from 2024-10-17 20-32-02.png](src/test/resources/Screenshot%20from%202024-10-17%2020-32-02.png)

Запуск тестов
gradle test
https://habr.com/ru/companies/rostelecom/articles/748444/

```
tasks.register('test-by-tag', Test) {
    useJUnitPlatform {
        if (project.hasProperty("includedTags")) {
            includeTags(project.properties.get("includedTags").toString())
        }
        if (project.hasProperty("excludedTags")) {
            excludeTags(project.properties.get("excludedTags").toString())
        }
    }
}
```

gradle test-by-tag -PincludedTags=SMOKE