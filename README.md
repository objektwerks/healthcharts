Medical Chart
-------------
>Medical chart.

Model
-----
* Glucose: datetime, level
* Medication: datetime, type **, dosage

** insulin, steroids

Chart
-----
1. Period: 72 hours ( 14.4 * 5-hour splits as 'ha' )
2. X-Axis: Date Range bounded by data model min to max datetimes.
3. Y-Axis: Line (Glucose), Scatter(Medication)

Test
----
sbt clean test

Run
---
sbt clean compile run