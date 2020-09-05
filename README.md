MedCharts
---------
>Medcharts is an open source project governed by the GPL.V3 license. The project objective is
>to build useful medical charts initially sourced by simple csv files.

Ideas
-----
1. Rest service that accepts csv files for a target chart.

Todo
----
1. Close open chart.
2. Open multiple charts.

Charts
------
1. [Glucose-Meds Chart](glucose.meds.chart.md)

Test
----
1. sbt clean test

Run
---
1. sbt run
2. click Charts menu
3. click Glucose-Meds menu item
4. select 1) glucose and 2) meds csv paths in Glucose-Meds dialog
5. click freshly enabled select button
6. view chart! ( mousover line data points to view details )

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020 Mike Funk

>Published under GPL.V3