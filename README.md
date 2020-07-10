MedCharts
---------
>Medical charts:
1. Glucose-Meds

Model
-----
1. Med: datetime, typeof (1), dosage (2)
2. Glucose: datetime, level (3)

Notes
-----
1. insulin, steroids
2. 1 - 100 
3. 0 - 300, 70 - 100 is normal

Med CSV
-------
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04s:00 )
2. typeof - 1 (Insultion), 2 (Steroid)
3. dosage - nnn (1-100)

Glucose CSV
-----------
1. datetime - yyyy-MM-ddThh:mm:ss ( 2020-07-04T10:04s:00 )
2. level - nnn (0-300)

Chart
-----
1. X-Axis : DateTime - 72-hour range with 5-hour splits, bounded by min/max datetimes, displayed in 'd,hh:mm a' format.
2. Y-Axis : Measurement - Compound or Overlaid Line (Glucose) and Scatter(Med) charts.

Test
----
sbt clean test

Run
---
sbt run

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020 Mike Funk

>Published under GPL.v3