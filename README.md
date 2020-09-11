MedCharts
---------
>Medcharts is an open source project governed by the GPL.V3 license. The project objective is
>to build useful medical charts initially sourced by simple csv files.

Todo
----
1. Unbuilt charts { entities-x, validators-x, transformers-x, charts, dialogs, actions, menus, data-x, tests-x, md }

Charts
------
- [x] [Glucose-Meds Chart](./docs/glucose.meds.chart.md)
- [ ] Weight
- [ ] Pulse
- [ ] Pulse Oxygen
- [ ] Respiration
- [ ] Temperature
- [ ] Blood Pressure

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
6. view tabbed chart! ( mouseover line data points to view details )
>Note that tabs are closeable.

Package
-------
1. sbt clean test universal:packageBin

License
-------
>Copyright (c) 2020 Mike Funk

>Published under GPL.V3