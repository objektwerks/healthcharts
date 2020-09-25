MedCharts
---------
>Medcharts is an open source project governed by the GPL.V3 license. The project objective is
>to build useful medical charts initially sourced by simple csv files.

Todo
----
1. Build composite chart panel: JPanel <>---> JTabbedPane <>---> ChartPanel | EntitiesPanel | InvalidLinesPanel
   * ChartAction -> buildChartPanel | buildEntitiesPanel | buildInvalidLinesPanel
   * ChartAction -> buildCompositeChartPanel
   * ChartAction -> compositeChartPanel -> Frame -> TabbedPane

Charts
------
- [x] [Blood Pressure](./docs/blood.pressure.chart.md)
- [x] [Glucose Meds](./docs/glucose.meds.chart.md)
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
4. via the Chart Dialog, select *.csv / *.txt file(s)
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