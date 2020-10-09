Chart
-----
1. X-Axis : Day,Hour ( d,H )
2. Y-Axis : Calories Int/Out ( nnnn ) / Weight ( nnn )
3. Calories In Tooltip ( Calories: ( dayHourMinute, in, delta ) )
4. Calories Out Tooltip ( Calories: ( dayHourMinute, out, delta ) )
4. Weight Tooltip ( Meds: ( dayHourMinute, weight, delta ) )
5. Legend : Calories In, Calories Out, Weight
6. Title : Calories-Weight : (y.m.d - y.m.d)

Model
-----
1. Calories: datetime, in (1), out (2), weight (3)

Constraints
-----------
1. 0 - 9999
2. 0 - 9999
1. > 0.00 <= 500.00

GlucoseMed
----------
```scala
final case class CaloriesWeight(number: Int, datetime: Minute, in: Int, out: Int, weight: Double)
```

GlucoseMed CSV
--------------
>See data/caloriesweight/calorieweight.txt
1. datetime - yyyy-MM-ddThh:mm:ss
2. in - nnnn
3. out - nnnn
4. weight - nnn