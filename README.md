HealthCharts
------------
>Healthchart app providing 11 health charts sourced by **csv** text files, using Swing, Flatlaf, MigLayout, JFreeChart, Ox and Scala 3.

Charts
------
>Technical details on each chart type:
1. [Blood Pressure](docs/blood.pressure.chart.md)
2. [Calories Weight](docs/calories.weight.chart.md)
3. [Glucose](docs/glucose.chart.md)
4. [Glucose Med](docs/glucose.med.chart.md)
5. [Med](docs/med.chart.md)
6. [Pulse](docs/pulse.chart.md)
7. [Pulse Oxygen](docs/pulse.oxygen.chart.md)
8. [Respiration](docs/respiration.chart.md)
9. [Temperature](docs/temperature.chart.md)
10. [Vitals](docs/vitals.chart.md)
11. [Weight](docs/weight.chart.md)

Data
----
>Sample data for each chart type:
1. [Blood Pressure](data/blood-pressure/blood-pressure.txt)
2. [Calories Weight](data/calories-weight/calories-weight.txt)
3. [Glucose](data/glucose.chart.md)
4. [Glucose Med](data/glucose.med.chart.md)
5. [Med](data/med.chart.md)
6. [Pulse](data/pulse.chart.md)
7. [Pulse Oxygen](data/pulse.oxygen.chart.md)
8. [Respiration](data/respiration.chart.md)
9. [Temperature](data/temperature.chart.md)
10. [Vitals](data/vitals.chart.md)
11. [Weight](data/weight.chart.md)

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