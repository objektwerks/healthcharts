Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Temperature ( degrees )
3. Tooltip ( degrees, delta )
4. Legend : Temperature
5. Title : Temperature : (y.m.d - y.m.d)

Model
-----
1. Temperature: datetime, degrees (1)

Constraints
-----------
1. nn >= 95.0 <= 105.0

Temperature
-----------
```scala
final case class Temperature(datetime: Minute, degrees: Int)
```

Temperature CSV
---------------
>See data/temperature/temperature.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. degrees - nnn.nn