Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Blood Pressure ( systolic )
3. Tooltip ( systolic / diastolic, deltaSystolic / deltaDiastolic )
4. Legend : Blood Pressure
5. Title : Blood Pressure : (y.m.d - y.m.d)

Model
-----
1. Blood Pressure: datetime, systolic (1), diastolic (2)

Constraints
-----------
1. systolic >= 120 && systolic <= 200
2. diastolic >= 80 && diastolic <= 120

Blood Pressure
--------------
```scala
final case class BloodPressure(datetime: Minute, systolic: Int, diastolic: Int)
```

Blood Pressure CSV
------------------
>See data/bloodpressure/blood-pressure.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. systolic - nnn
3. diastolic - nnn