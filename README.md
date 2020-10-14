HealthCharts
------------
>Healthcharts is an open source project, governed by the Apache 2.0 license, with the
>singular objective of providing useful health charts sourced by simple csv/txt files.

Charts
------
- [Blood Pressure](./docs/blood.pressure.chart.md)
- [Calories Weight](docs/calories.weight.chart.md)
- [Glucose](docs/glucose.chart.md)
- [Glucose Med](docs/glucose.med.chart.md)
- [Med](docs/med.chart.md)
- [Pulse](./docs/pulse.chart.md)
- [Pulse Oxygen](./docs/pulse.oxygen.chart.md)
- [Respiration](./docs/respiration.chart.md)
- [Temperature](./docs/temperature.chart.md)
- [Vitals](./docs/vitals.chart.md)
- [Weight](./docs/weight.chart.md)

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
> Copyright (c) [2020] [Mike Funk]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.