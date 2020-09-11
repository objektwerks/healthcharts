Chart
-----
>Composed of 2 Line charts, with:
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Pulse ( beatsPerMinute )
3. Tooltip ( beatsPerMinute )
4. Legend : Pulse (Red)
5. Title : Pulse : (y.m.d - y.m.d)

Model
-----
1. Pulse: datetime, beatsPerMinute (1)

Constraints
-----------
1. >= 40 <= 200

Pulse
-----
```scala
final case class Pulse(datetime: Minute, beatsPerMinute: Int)
```

Pulse CSV
---------
>See data/pulse/pulse.txt
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04:00 )
2. beatsPerMinute - nnn