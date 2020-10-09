Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Weight ( pounds )
3. Tooltip ( pounds, delta )
4. Legend : Weight
5. Title : Weight : (y.m.d - y.m.d)

Model
-----
1. Weight: datetime, pounds (1)

Constraints
-----------
1. pounds > 0.00 && pounds <= 500.00

Weight
------
```scala
final case class Weight(datetime: Minute, pounds: Double)
```

Weight CSV
----------
>See data/weight/weight.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. pounds - nnn.nn