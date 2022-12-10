# Алгоритмы и структуры данных
source: [Тинькофф Образование](https://edu.tinkoff.ru/)
## Сортировки
### Квадратичные сортировки
* Асимптотика O(n<sup>2</sup>)
* Редко применяются на практике
#### 1. Сортировка выбором
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
* Пример исполнения (Java): [sorting.quadratic.Selection](src/main/java/komrachkov/anton/sorting/quadratic/Selection.java)
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

#### 2. Сортировка вставками
**Идея:** двигаем элемент влево, пока он не встанет на свое место
* Представим, что у нас слева есть отсортированный участок, пусть его длина _k_:
  * Возьмем _k + 1_ элемент
  * Будем двигать его, пока он не встанет на своё место
  * Получили отсортированный участок размера _k + 1_
  * _n + (n -1) + ... + 1 = <sup>n(n+1)</sup>/<sub>2</sub> = O(n<sup>2</sup>)_
```
[5,3,6,2] -> [3,5,6,2] -> [3,5,2,6] -> [3,2,5,6] -> [2,3,5,6]
```
* Пример исполнения (Java): [sorting.quadratic.Insertion](src/main/java/komrachkov/anton/sorting/quadratic/Insertion.java)
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

#### 3. Сортировка пузырьком
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
* Пример исполнения (Java): [sorting.quadratic.Bubble](src/main/java/komrachkov/anton/sorting/quadratic/Bubble.java)
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

## Структуры данных
### Линейные структуры данных

#### 1. Вектор
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

#### 2. Стек
* Добавить элемент на стек
* Узнать "верхний" элемент стека
* Удалить "верхний" элемент
* Можно реализовать используя обычный массив
```
-------
| 1, 2  <-> FILO Добавление в конец, удаление с конца
-------
```
* Пример исполнения (Java): [data_structure.linear.Stack](src/main/java/komrachkov/anton/data_structure/linear/Stack.java)
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
* Пример использования (Java): [Правильная скобочная последовательность](src/main/java/komrachkov/anton/tasks/t001_bracket_sequence.java)

#### 3. Очередь
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
* Пример исполнения (Java): [data_structure.linear.Queue](src/main/java/komrachkov/anton/data_structure/linear/Queue.java)
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
#### 1. [Правильная скобочная последовательность](src/main/java/komrachkov/anton/tasks/t001_bracket_sequence.java)
