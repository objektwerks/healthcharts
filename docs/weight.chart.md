Chart
-----
>Composed of 2 Line charts, with:
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Weight ( pounds )
3. Tooltip ( pounds )
4. Legend : Weight (Red)
5. Title : Weight : (y.m.d - y.m.d)

Model
-----
1. Weight: datetime, pounds (1)

Constraints
-----------
1. > 0.00 <= 500.00

Weight
------
```scala
final case class Weight(datetime: Minute, pounds: Double)
```

Weight CSV
----------
>See data/bloodpressure/blood-pressure.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. pounds - nnn.nn