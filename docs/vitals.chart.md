Chart
-----
>Composed of 6 Line charts, with:
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
1. >= 95.0 <= 105.0
2. >= 12 <= 25
3. >= 40 <= 200
4. >= 50 <= 100
5. >= 120 <= 200
6. >= 80 <= 120

Temperature
-----------
```scala
final case class Vitals(datetime: Minute,
                        temperature: Double,
                        respiration: Int,
                        pulse: Int,
                        oxygen: Int,
                        systolic: Int,
                        diastolic: Int)
```

Temperature CSV
---------------
>See data/temperature/temperature.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. temperature - nnn.nn
3. respiration - nn
4. pulse - nnn
5. oxygen - nnn
6. systolic - nnn
7. diastolic - nnn