HealthCharts
------------
>Healthcharts app provides useful health charts sourced by csv/txt files, using Swing, Flatlaf, MigLayout, JFreeChart, Ox and Scala 3.

Charts
------
- [Blood Pressure](docs/blood.pressure.chart.md)
- [Calories Weight](docs/calories.weight.chart.md)
- [Glucose](docs/glucose.chart.md)
- [Glucose Med](docs/glucose.med.chart.md)
- [Med](docs/med.chart.md)
- [Pulse](docs/pulse.chart.md)
- [Pulse Oxygen](docs/pulse.oxygen.chart.md)
- [Respiration](docs/respiration.chart.md)
- [Temperature](docs/temperature.chart.md)
- [Vitals](docs/vitals.chart.md)
- [Weight](docs/weight.chart.md)

Build
-----
1. sbt clean compile

Test
----
1. sbt clean test

Run
---
1. sbt run

Usage
-----
1. click Charts menu
2. click Chart menu item
3. via the Chart Dialog, select *.csv / *.txt file
4. click 'Select' button
5. view tabbed chart ( mouseover line data points to view details )
6. close app via top-left 'x' icon
>Tabs are typed, numbered and closeable.

Package
-------
1. sbt clean test universal:packageBin
2. verify ./target/universal/healthcharts-${version}.zip

Install
-------
1. unzip ./target/universal/healthcharts-${version}.zip
2. copy unzipped healthcharts-${version} directory to ${healthcharts.directory}
3. set executable permissions for ${healthcharts.directory}/healthcharts-${version}/bin/healthcharts

Execute
-------
1. execute ${healthcharts-directory}/healthcharts-${version}/bin/healthcharts

License
-------
>Copyright (c) [2022, 2023, 2024, 2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
