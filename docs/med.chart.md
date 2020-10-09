Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Dosage ( n )
4. Meds Tooltip ( Meds: ( dayHourMinute, dmed, delta ) )
5. Legend : Med
6. Title : Med : (y.m.d - y.m.d)

Model
-----
1. Med: datetime, medtype (1), dosage (2)

Constraints
-----------
1. 1 -> insulin, 2 -> steroids
2. dosage >= 1 && dosage <= 100

Med
---
```scala
final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)
```

Med CSV
-------
>See data/med/med.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. medtype - n
3. dosage - nnn