Medical Chart
-------------
>Medical chart.

Model
-----
* Glucose: datetime, level *
* Med: datetime, type **, dosage ***

* 0 - 300, 70 - 100 is normal
** insulin, steroids
*** 1 - 100 

Chart
-----
1. Period: 72 hours ( 14.4 * 5-hour splits as 'ha' )
2. X-Axis: Date Range bounded by Period min and max datetimes.
3. Y-Axis: Line (Glucose), Scatter(Med)

Test
----
sbt clean test

Run
---
sbt run