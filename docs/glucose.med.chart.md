Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Glucose Level / Med Dosage ( n )
3. Glucose Tooltip ( Glucose: ( dayHourMinute, level, delta ) )
4. Med Tooltip ( Meds: ( dayHourMinute, dosage, med, delta ) )
5. Legend : Glucose (Red), Med (Blue)
6. Title : Glucose-Med : (y.m.d - y.m.d)

Model
-----
1. Glucose: datetime, level (1)
2. Med: datetime, medtype (2), dosage (3)

Constraints
-----------
1. nnn >= 0 && nnn <= 300 [ 70 - 100 is normal ]
2. 1 -> insulin, 2 -> steroids
3. nnn >= 1 && nnn <= 100

GlucoseMed
----------
```scala
final case class GlucoseMed(number: Int, datetime: Minute, level: Int, medtype: MedType.Value, dosage: Int)
```

GlucoseMed CSV
--------------
>See data/glucosemed/glucosemed.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. level - nnn
3. medtype - n
4. dosage - nnn