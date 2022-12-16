# Алгоритмы и структуры данных

## Оглавление
* [Сортировки](#сортировки)
  * [Квадратичные сортировки](#квадратичные-сортировки)
    * [Выбором](#сортировка-выбором)
    * [Вставками](#сортировка-вставками)
    * [Пузырьком](#сортировка-пузырьком)
  * [Эффективные сортировки](#эффективные-сортировки)
    * [Слиянием ("Разделяй и властвуй")](#сортировка-слиянием))
    * [Подсчетом](#сортировка-подсчетом)
* [Поиск](#поиск)
    * [Бинарный](#бинарный-поиск)
    * [Бинарный по ответу](#бинарный-поиск-по-ответу)
    * [Вещественный бинпоиск](#вещественный-бинпоиск)
    * [Тернарный поиск](#тернарный-поиск)
* [Структуры данных](#структуры-данных)
  * [Линейные](#линейные-структуры-данных)
    * [Вектор](#вектор)
    * [Стек](#стек)
    * [Очередь](#очередь)
* [Задачи](#задачи)
* [Источники](#источники)
## Сортировки
### Квадратичные сортировки
* Асимптотика O(n<sup>2</sup>)
* Редко применяются на практике
#### Сортировка выбором
**Идея:** мы можем _n_ раз выбрать минимум и составить отсортированный массив
* Поиск минимума выполняется в зависимости от размера массива:
  * На первом шаге минимум надо выбрать из элементов и поставить его на позицию 1
  * На втором шаге из и поставить его на позицию 2
  * ...
  * На _n_-м шаге мы его сразу знаем
  * _n + (n -1) + ... + 1 = <sup>n(n+1)</sup>/<sub>2</sub> = O(n<sup>2</sup>)_
```
[5,6,2,3] -> [2,6,5,3] -> [2,3,5,6] -> [2,3,5,6] -> [2,3,5,6]
```
* Пример исполнения (Java): [sorting.quadratic.Selection](src/main/java/kas/anton/sorting/quadratic/Selection.java)
```
Пример исполнения (Python):
n = int ( input () )
a = list ( map (int , input () . split () ) )
k = n - 1
while k >= 1:
  i_max = 0
  for i in range (1 , k + 1) :
    if a[ i ] > a[ i_max ]:
      i_max = i
    a[ k ], a[ i_max ] = a [ i_max ], a[ k ]
    k -= 1
print (’ ’. join ( map (str , a)) )
```

#### Сортировка вставками
**Идея:** двигаем элемент влево, пока он не встанет на свое место
* Представим, что у нас слева есть отсортированный участок, пусть его длина _k_:
  * Возьмем _k + 1_ элемент
  * Будем двигать его, пока он не встанет на своё место
  * Получили отсортированный участок размера _k + 1_
  * _n + (n -1) + ... + 1 = <sup>n(n+1)</sup>/<sub>2</sub> = O(n<sup>2</sup>)_
```
[5,3,6,2] -> [3,5,6,2] -> [3,5,2,6] -> [3,2,5,6] -> [2,3,5,6]
```
* Пример исполнения (Java): [sorting.quadratic.Insertion](src/main/java/kas/anton/sorting/quadratic/Insertion.java)
```
Пример исполнения (Python):
n = int ( input () )
a = list ( map (int , input () . split () ) )
for i in range ( 1 , n ):
  k = i
  while k > 0 and a[ k - 1] > a[ k ]:
    a[ k ], a[k - 1] = a[k - 1] , a [ k ]
    k -= 1
print (’ ’. join ( map (str , a)) )
```

#### Сортировка пузырьком
**Идея:** Каждый раз будем смещать самый "тяжелый" элемент в конец
* Для всех _i_ от 1 до _n - 1_ , если _a<sub>i</sub> > a<sub>i + 1</sub>_ обмениваем их местами
  * Для одного элемента в худшем случае _n_ обменов
  * Для _n_ элементов _n<sup>2</sup>_ обменов
* Можно понять, что после _i_ шага последние _i_ элементов отсортированы
```
проход 1: [5,3,6,2] -> [3,5,6,2] -> [3,5,2,6]
проход 2: [3,5,2,6] -> [3,2,5,6]
проход 3: [3,2,5,6] -> [2,3,5,6]
```
* Пример исполнения (Java): [sorting.quadratic.Bubble](src/main/java/kas/anton/sorting/quadratic/Bubble.java)
```
Пример исполнения (Python):
n = int ( input () )
a = list ( map (int , input () . split () ) )
for i in range ( n):
  for j in range ( n - i - 1) :
    if a[ j] > a[ j + 1]:
      a[j ], a[j + 1] = a[j + 1] , a [j]
print (’ ’. join ( map (str , a)) )
```

### Эффективные сортировки

#### Сортировка слиянием 
* принцип "Разделяй и властвуй"
* Делим массив пополам на две части: левую и правую
* Допустим, мы их как-то отсортировали
* Можем объединить за _O(n)_ : идём и выбираем минимальный элемент из двух массивов
* Применяем эту идею рекурсивно, пока размер массива больше 1
* На каждом "уровне" делаем _O(n)_ операций
* Уровней рекурсии ⌈_log<sub>2</sub>n_⌉ , потому что каждый раз делим пополам
* Итого: _O(n log(n))_
* Пример исполнения (Java): [sorting.effective.Merge](src/main/java/kas/anton/sorting/effective/Merge.java)
```
Пример исполнения (Python):
def merge_sort (a , l , r) :
  if r - l < 2:
    return 0
  m = (r + l) // 2
  merge_sort (a , l , m)
  merge_sort (a , m , r)
  merge (a , l , m , r)

def merge (a , l , m , r):
  l1 , r1 = l , m
  l2 , r2 = m , r
  result = []
  while l1 < r1 or l2 < r2 :
    if l1 < r1 and l2 < r2 :
      if a[ l1 ] < a [ l2 ]:
        result . append ( a[ l1 ])
        l1 += 1
      else :
        result . append ( a[ l2 ])
        l2 += 1
    elif l1 >= r1 and l2 < r2 :
      result . append ( a[ l2 ])
      l2 += 1
    elif l2 >= r2 and l1 < r1 :
      result . append ( a[ l1 ])
      l1 += 1
  for i in range (l , r ):
    a[i ] = result [ i - l]
  
n = int ( input () )
x = list ( map (int , input () . split () ) )
merge_sort (x , 0, n)
print (’ ’. join ( map (str , x)) )
```

#### Сортировка подсчетом
* Пусть, все числа в массиве _a_ от 1 до _C_, где _C_ не очень большое
* Создадим массив _cnt<sub>i</sub>_ размера _C_: сколько чисел в массиве равны _i_
* Пройдем по _cnt_ и перезапишем _a_
* Асимптотика _O(n+C)_
* Можно использовать, когда _max-min_ мало
* Пример исполнения (Java): [sorting.effective.Counting](src/main/java/kas/anton/sorting/effective/Counting.java)
```
Пример исполнения (Python):
n = int ( input () )
x = list ( map (int , input () . split () ) )
min_e , max_e = min (x ) , max (x )
cnt = [0] * ( max_e - min_e + 1)
for v in x:
  cnt [v - min_e ] += 1
k = 0
for i in range ( len ( cnt ) ):
  for c in range ( cnt [ i ]) :
    x[k ] = i + min_e
    k += 1
print (’ ’. join ( map (str , x)) )
```


## Поиск

#### Бинарный поиск
* Пусть, элементы упорядочены по неубыванию (как возрастание, но бывают одинаковые)
* Идея: посмотрим на центральный элемент, сравним с искомым
* Если он равен, то мы его нашли
* Если он больше искомого, имеет смысл рассматривать только левую половину
* Иначе только правую
* Каждый раз сужаем область поиска в два раза
* на один запрос
* Тонкости реализации:
  * Хотим: нерекурсивно, не путаться с +1 и -1, будет легко обобщить на следующую тему
  * Пусть, _f(i)_ - функция, которая говорит, правда ли что _a<sub>i</sub> >= x_ (возвращает 0/1)
  * Тогда _f_ выглядит как 000...000111...111
  * Выберем _l_, _r_, такие что гарантированно _f(l) = 0_, _f(r) = 1_. Представим, что слева от массива идут - ∞, а справа + ∞
  * Тогда _l = -1_, _r = n_
  ```
  while r - l > 1:
    m = (l + r) // 2
    if f(m): r = m
    else: l = m
  ```
  * После выполнения _r_- первый подходящий, _l_- последний неподходящий
  * Заметим, что никогда не вызовемся от некорректных значений

#### Бинарный поиск по ответу
* Задача: даны _n_ палок длин _a<sub>i</sub>_, мы умеем их ломать
* Хотим получить _k_ палок одинаковой целочисленной длины, требуется максимимзировать эту длину
* Хочется просто перебрать все значения длины и для каждого проверить, является ли оно решением: _O(n*max a)_
* Идея: если можем получить длину _x_, то можем и длину _x-1_
* _f(x)_ - можем ли мы получить _k_ палок длины _x_,можем проверить за _O(n)_
* выглядит как 111...111000...000 => можем сделать бинпоиск по _x_ по аналогии
* _O(n*Log max a)_

#### Вещественный бинпоиск
* Дана возрастающая функция _f_, хотим найти _x: f(x) = 0_
* Делаем бинпоиск по _x_
* Хотим знать _x_ с какой-то точностью
* ***Нельзя писать _while r - l > eps_ : проблемы с погрешностью!***
* Вычисляем количество итераций, когда бинпоиск сойдется до нужной точности.

#### Тернарный поиск
* Дана выпуклая функция , хотим найти ее минимум.
* Берем _m<sub>1</sub> = <sup>2l + r</sup>/<sub>3</sub>_ и _m<sub>2</sub> = <sup>2r + l</sup>/<sub>3</sub>_
* Вычисляем _f(m<sub>1</sub>)_ и _f(m<sub>2</sub>)_
* Если _f(m<sub>1</sub>) > f(m<sub>2</sub>)_, то минимум не может быть между _l_ и _m<sub>1</sub>_ => переходим к отрезку [ _m<sub>1</sub>, r_ ]
* Иначе минимум не может быть между _m<sub>2</sub>_ и _r_ => переходим к отрезку [ _l, m<sub>2</sub>_ ]
* Снова выбираем число операций, чтобы точность была достаточной

## Структуры данных
### Линейные структуры данных

#### Вектор
* Динамический массив
* размер (_size_) и вместимость (_capacity_)
* Пока _size <= capacity_, просто добавляем
* Затем выделяем новый массив с _capacity' = C * capacity_, копируем туда содержимое старого
```
Пример:
C = 2
[1]       (size 1, capacity 1)
[1,2]     (size 2, capacity 2, перекладываем)
[1,2,3]   (size 3, capacity 4, перекладываем)
[1,2,3,4] (size 4, capacity 4)
[1,2,3]   (size 3, capacity 4)
```
* Пусть, итоговая _capacity_ вектора равна _n_. Тогда сумма _capacity_ всех массивов не превосходит:
 _n + <sup>n</sup>/<sub>2</sub> + <sup>n</sup>/<sub>4</sub> + ... + 1 <= 2n = O(n)_
* _n_ операций сделали за _O(n)_
* На практике используют разные _C_, там тоже получается сумма геометрической прогрессии, которая зависит только от _C_

#### Стек
* Добавить элемент на стек
* Узнать "верхний" элемент стека
* Удалить "верхний" элемент
* Можно реализовать используя обычный массив
```
-------
| 1, 2  <-> FILO Добавление в конец, удаление с конца
-------
```
* Пример исполнения (Java): [data_structure.linear.Stack](src/main/java/kas/anton/data_structure/linear/Stack.java)
```
Пример исполнения (Python):
x = []
c = input ()
while c != ’exit ’:
  cmd = c. split ()
  if cmd [0] == ’push ’:
    x = x + [ cmd [ -1]]
    print (’ok ’)
  if cmd [0] == ’pop ’:
    if len (x) > 0:
      print (x [ -1])
      x. pop ()
    else :
      print (’error ’)
  if cmd [0] == ’back ’:
    if len (x) > 0:
      print (x [ -1])
    else :
      print (’error ’)
  if cmd [0] == ’size ’:
    print ( len (x) )
  if cmd [0] == ’clear ’:
    x. clear ()
    print (’ok ’)
    c = input ()
print (’bye ’)
```
* Пример использования (Java): [Правильная скобочная последовательность](src/main/java/kas/anton/tasks/t001_bracket_sequence.java)

#### Очередь
* Добавить элемент в конец очереди
* Удалить элемент из начала очереди
* Узнавать первый/последний элементы
* Сделать таких операций за _O(n)_
* Можно реализовать, используя массив и указатель на начало очереди
  * лишняя память
* Можно реализовать, используя 2 стека, представить как два стакана, которые касаются донышками
  * При добавлении элемента кладем его в правый стек
  * При удалении удаляем из левого
  * Если в левом пусто, перекладываем туда по одному все элементы из правого. Они развернутся.
  * Каждый элемент переложится только 1 раз -> асимптотика _O(n)_
* Пример исполнения (Java): [data_structure.linear.Queue](src/main/java/kas/anton/data_structure/linear/Queue.java)
```
Пример исполнения (Python):
  b , f = [] , []
  c = input ()
  while c != ’exit ’:
    cmd = c. split ()
    if cmd [0] == ’push ’:
      b. append ( cmd [ -1])
      print (’ok ’)
    if cmd [0] == ’pop ’:
    if len (f) == 0:
      f = list ( reversed ( b))
      b = []
    print (f [ -1])
      f. pop ()
    if cmd [0] == ’front ’:
      if len (f) == 0:
        f = list ( reversed ( b))
        b = []
      print (f [ -1])
    if cmd [0] == ’size ’:
      print ( len (f) + len ( b))
    if cmd [0] == ’clear ’:
      f = []
      b = []
      print (’ok ’)
    c = input ()
  print (’bye ’)
 ```

## Задачи
<br>🟢 - Задача решена и оттестирована
<br>🟡 - Задача частично решена, проходит не все тесты
<br>🔴 - Задача не решена, либо решена неверно
### Лекции Тинькофф Образование Алгоритмы и структуры данных
* [🟢 Правильная скобочная последовательность](src/main/java/kas/anton/tasks/t001_bracket_sequence.java)

### Тинькофф. Вечный контекст
* [🟢 1. Cуммарные расходы на интернет](src/main/java/kas/anton/tasks/eternal_contest/T01.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T01Test.java)
* [🟢 2. Разрезать рулет на N равных частей](src/main/java/kas/anton/tasks/eternal_contest/T02.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T02Test.java)
* [🟢 3. Минимальное количество лестничных пролетов](src/main/java/kas/anton/tasks/eternal_contest/T03.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T03Test.java)
* [🟢 4. Числа на бумажке](src/main/java/kas/anton/tasks/eternal_contest/T04.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T04Test.java)
* [🟢 5. Число различных тестов](src/main/java/kas/anton/tasks/eternal_contest/T05.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T05Test.java)
* [🟡 6. Четные и нечетные](src/main/java/kas/anton/tasks/eternal_contest/T06.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T06Test.java)
* [🟡 7. Тайный санта](src/main/java/kas/anton/tasks/eternal_contest/T07.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T07Test.java)
* [🔴 8. План переговорки, поиск точки совпадения](src/main/java/kas/anton/tasks/eternal_contest/T08.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T08Test.java)
* [🟡 9. Минимизировать стоимость обедов](src/main/java/kas/anton/tasks/eternal_contest/T09.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T09Test.java)
* [🟢 10. Разделение многоугольника пополам](src/main/java/kas/anton/tasks/eternal_contest/T10.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T10Test.java)
* [🟡 11. Малая теорема Ферма](src/main/java/kas/anton/tasks/eternal_contest/T11.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T11Test.java)
* [🟡 12. Количество сумм монет в кошельке](src/main/java/kas/anton/tasks/eternal_contest/T12.java) / [TEST](src/test/java/kas/anton/tasks/eternal_contest/T12Test.java)

## Источники
* [Тинькофф Образование](https://edu.tinkoff.ru/)
* [VK Тинькофф Образование](https://vk.com/board123851409)