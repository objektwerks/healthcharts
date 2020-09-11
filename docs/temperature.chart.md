Chart
-----
>Composed of 2 Line charts, with:
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Temperature ( degrees )
3. Tooltip ( degrees )
4. Legend : Temperature (Red)
5. Title : Temperature : (y.m.d - y.m.d)

Model
-----
1. Temperature: datetime, degrees (1)

Constraints
-----------
1. >= 95.0 <= 105.0

Temperature
-----------
```scala
final case class Temperature(datetime: Minute, degrees: Int)
```

Temperature CSV
---------------
>See data/temperature/temperature.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. degrees - nnn.nn