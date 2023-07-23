### MULTITHREADING, DOCKER

[![Java CI](https://github.com/serjM123/orderedthreads/actions/workflows/build.yml/badge.svg)](https://github.com/serjM123/orderedthreads/actions/workflows/build.yml)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

#### Multithreading

Задание: написать код, позволяющий добавлять любое количество потоков,
выполняющихся по схеме 1,2,3,1,2,3,...

Вывод в консоль:

```
First
Second
Third
Forth
First
Second
и т.д.
```

***
Код выполняется в DOCKER контейнере на github.

***

### Запуск кода

```sh
$ docker build -t my-java-app .
$ docker run my-java-app
```

***
Контакты<br>
Email: my@gmail.com