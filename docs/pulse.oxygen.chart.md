Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Pulse Oxygen ( beatsPerMinute )
3. Tooltip ( beatsPerMinute, bloodOxygenPercentage, beatsPerMinuteDelta, bloodOxygenPercentageDelta )
4. Legend : Pulse Oxygen
5. Title : Pulse Oxygen : (y.m.d - y.m.d)

Model
-----
1. Pulse Oxygen: datetime, beatsPerMinute (1), bloodOxygenPercentage (2)

Constraints
-----------
1. beatsPerMinute >= 40 && beatsPerMinute <= 200
2. bloodOxygenPercentage >= 50 && bloodOxygenPercentage <= 100

PulseOxygen
-----------
```scala
final case class PulseOxygen(datetime: Minute, beatsPerMinute: Int, bloodOxygenPercentage: Int)
```

PulseOxygen CSV
---------------
>See data/pulseoxygen/pulse-oxygen.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. breatsPerMinute - nnn
3. bloodOxygenPercentage - nnn