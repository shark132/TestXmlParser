# TestXmlParser
Тестовое задание.
<hr>
######Разработчик Лопатин Илья
<hr>

Запуск происходит из командной строки.
Jar файл примает путь к xml файлу в качестве параметра. 
<hr>
Файл - схема, .XSD находится в самом проекте.
При несоответствии входного xml файла и схемы(.XSD) будет выдана ошибка с указанием места ошибки.
Программа может работать с большим файлами 1гб и больше.
<hr>
<hr>
Файл xml имеет следующую структуру (знаки тегов опущены):
<br>< shape > (корневой элемент) <br>
  < figureName >  назвние фигуры (треугольник, квадрат, прямоугольник или окружность)<br>
    < side > intNum < /side >  -  может быть несколько сторон<br> 
    < figureName > <br>
  < shape > <br>

