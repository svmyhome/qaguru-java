11. Jenkins. Создаем первую задачу
12.
13.
14.
15. REST API. Пишем автотесты с REST Assured
16. REST API. Модели. Спецификации
17. 17-18 REST API. Декомпозируем UI-тесты  https://school.qa.guru/pl/teach/control/lesson/view?id=334644399

В devTools перейти в консоль и ввесли локтор через$ или $$. Можно увидеть один или несколько найдется

```$$("ul.list-style-none")```

```$$("ul.list-style-none li")```

``` $("#repository-container-header nav ul li a#wiki-tab")```

![Screenshot from 2024-10-16 19-32-47.png](src/test/resources/Screenshot%20from%202024-10-16%2019-32-47.png)
![Screenshot from 2024-10-17 20-32-02.png](src/test/resources/Screenshot%20from%202024-10-17%2020-32-02.png)

Запуск тестов
gradle test
gradle dependencies - покажет список зависимостей на проекте
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

## ALLURE

```
plugins {
    id 'io.qameta.allure' version '2.12.0'
}
allure {
    report { // секция репора
        version.set("2.29.0")
    }
    adapter {  // отвечает за появление папки build/allure-results
        aspectjWeaver.set(true) //  обработка аннотации @step
        frameworks {
            junit5 { //название фреймворка
                adapterVersion.set("2.29.0") // версия интеграции фреимворка и Aallure
            }
        }
    }

}


repositories {
    mavenCentral()
}

compileTestJava {
    options.encoding = 'UTF-8'
}

dependencies {

    testImplementation 'io.qameta.allure:allure-selenide:2.29.0'
}
```

build/allure-result файлы с настройками
например ....
"testCaseName": "SelenideTest()",
"fullName": "allure.SelenideAllureTest.SelenideTest",
"
"name": "SelenideTest()",
"status": "passed",
"stage": "finished",
"description": "",
"steps": [],
"attachments": [],
"parameters": [],
"start": 1731601321612,
"stop": 1731601329281

### GitHUB Actions

https://habr.com/ru/companies/flant/articles/803251/

### Посмотреть куки и сессии в браузере

открыть вкладку console
document.cookie
localStorage
sessionStorage

AWAITILITY ожидание для RESTASSURED