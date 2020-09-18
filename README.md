MedCharts
---------
>Medcharts is an open source project governed by the GPL.V3 license. The project objective is
>to build useful medical charts initially sourced by simple csv files.

Todo
----
1. Charts { entities-x, validators-x, transformers-x, dialogs-x, charts, actions, menus, data-x, tests-x, md-x }

Charts
------
- [ ] [Blood Pressure](./docs/blood.pressure.chart.md)
- [x] [Glucose Meds](./docs/glucose.meds.chart.md)
- [ ] [Pulse](./docs/pulse.chart.md)
- [ ] [Pulse Oxygen](./docs/pulse.oxygen.chart.md)
- [ ] [Respiration](./docs/respiration.chart.md)
- [x] [Temperature](./docs/temperature.chart.md)
- [x] [Weight](./docs/weight.chart.md)

Test
----
1. sbt clean test

Run
---
1. sbt run
2. click Charts menu
3. click Chart menu item
4. via the Chart Dialog, select *.csv / *.txt as requested
5. click 'Select' button
6. view tabbed chart ( mouseover line data points to view data details )
7. close app via top-left 'x' icon
>Tabs are typed, numbered and closeable.

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020, Mike Funk, Published under GPL.V3