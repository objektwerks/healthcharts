Chart
-----
>Composed of 2 Line charts, with:
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Glucose Level / Med Dosage ( n )
3. Glucose Tooltip ( Glucose: ( dayHourMinute, level, delta ) )
4. Meds Tooltip ( Meds: ( dayHourMinute, dosage, med, delta ) )
5. Legend : Glucose (Red), Meds (Blue)
6. Title : Glucose-Med : (y.m.d - y.m.d)

Model
-----
1. Glucose: datetime, level (1)
2. Med: datetime, medtype (2), dosage (3)

Constraints
-----------
1. 0 - 300, 70 - 100 is normal
2. 1 -> insulin, 2 -> steroids
3. 1 - 100 

Glucose
-------
```scala
final case class Glucose(datetime: Minute, level: Int)
```

Glucose CSV
-----------
>See data/glucose/glucose.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. level - nnn ( 0-300 )

Med
---
```scala
final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)
```

Med CSV
-------
>See data/meds/meds.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. medtype - 1 ( insulin ), 2 ( steroid )
3. dosage - nnn ( 1-100)