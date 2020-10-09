Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Level ( n )
3. Glucose Tooltip ( Glucose: ( dayHourMinute, level, delta ) )
5. Legend : Glucose
6. Title : Glucose : (y.m.d - y.m.d)

Model
-----
1. Glucose: datetime, level (1)

Constraints
-----------
1. 0 - 300, 70 - 100 is normal

Glucose
-------
```scala
final case class Glucose(datetime: Minute, level: Int)
```

Glucose CSV
-----------
>See data/glucose/glucose.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. level - nnn