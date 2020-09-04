MedCharts
---------
>Medcharts is an open source project governed by the GPL.V3 license. The project objective is
>to build useful medical charts initially sourced by simple csv files.

Ideas
-----
1. Rest service that accepts csv files for a target chart.

Charts
------
1. [Glucose-Meds Chart](glucose.meds.chart.md)

Test
----
1. sbt clean test

Run
---
1. sbt run
2. Click Charts Menu
3. Click Glucose-Meds
4. Select Glucose and Meds Csv file paths in Dialog
5. Click enabled Select Button
6. View chart!

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020 Mike Funk

>Published under GPL.V3