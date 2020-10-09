Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Vitals ( temperature, respiration, pulse, oxygen, systolic, diastolic )
3. Tooltip ( vital, delta )
4. Legend : Temperature (Red), Respiration (Blue), Pulse (Yellow), Oxygen (Green), Systolic (Orange), Diastolic (Pink)
5. Title : Vitals : (y.m.d - y.m.d)

Model
-----
1. Vitals: datetime, temperature (1), respiration (2), pulse (3), oxygen (4), systolic (5), diastolic (6)

Constraints
-----------
1. temperature >= 95.00 && temperature <= 105.00
2. respiration >= 12 && respiration <= 25
3. pulse >= 40 && pulse <= 200
4. oxygen >= 50 && oxygen <= 100
5. systolic >= 120 && systolic <= 200
6. diastolic >= 80 && diastolic <= 120

Vitals
------
```scala
final case class Vitals(datetime: Minute,
                        temperature: Double,
                        respiration: Int,
                        pulse: Int,
                        oxygen: Int,
                        systolic: Int,
                        diastolic: Int)
```

Vitals CSV
----------
>See data/vitals/vitals.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. temperature - nnn.nn
3. respiration - nn
4. pulse - nnn
5. oxygen - nnn
6. systolic - nnn
7. diastolic - nnn