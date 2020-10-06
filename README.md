HealthCharts
------------
>Healthcharts is an open source project, governed by the GPL.V3 license, with the
>singular objective of providing useful health charts sourced by simple csv/txt files.

Charts
------
- [x] [Blood Pressure](./docs/blood.pressure.chart.md)
- [x] [Glucose](docs/glucose.chart.md)
- [x] [Glucose Med](docs/glucose.med.chart.md)
- [x] [Med](docs/med.chart.md)
- [x] [Pulse](./docs/pulse.chart.md)
- [x] [Pulse Oxygen](./docs/pulse.oxygen.chart.md)
- [x] [Respiration](./docs/respiration.chart.md)
- [x] [Temperature](./docs/temperature.chart.md)
- [x] [Vitals](./docs/vitals.chart.md)
- [x] [Weight](./docs/weight.chart.md)

Test
----
1. sbt clean test

Run
---
1. sbt run
2. click Charts menu
3. click Chart menu item
4. via the Chart Dialog, select *.csv / *.txt file
5. click 'Select' button
6. view tabbed chart ( mouseover line data points to view details )
7. close app via top-left 'x' icon
>Tabs are typed, numbered and closeable.

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020, Mike Funk, Published under GPL.V3