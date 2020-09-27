Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Pulse Oxygen ( beatsPerMinute )
3. Tooltip ( beatsPerMinute, bloodOxygenPercentage, beatsPerMinuteDelta, bloodOxygenPercentageDelta )
4. Legend : Pulse Oxygen
5. Title : Pulse Oxygen : (y.m.d - y.m.d)

Model
-----
1. Pulse Oxygen: datetime, breathesPerMinute (1), bloodOxygenPercentage (2)

Constraints
-----------
1. >= 12 <= 25
2. >= 50 <= 100

PulseOxygen
-----------
```scala
final case class PulseOxygen(datetime: Minute, breathesPerMinute: Int, bloodOxygenPercentage: Int)
```

PulseOxygen CSV
---------------
>See data/pulseoxygen/pulse-oxygen.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. breathesPerMinute - nn
3. bloodOxygenPercentage - nnn