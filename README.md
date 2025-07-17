Healthcharts
------------
>Health charts app providing 11 health charts sourced by **csv** text files, using Swing, Flatlaf, MigLayout, JFreeChart, Ox, jDeploy and Scala 3.

Install
-------
1. Select [Healthcharts](https://www.jdeploy.com/~healthcharts)
2. Select a platform to download a compressed app installer.
3. Decompress app installer.
4. Install app by double-clicking app installer.
5. Select app icon to launch app.
>This install has been tested on macOS.

Charts
------
>Technical specifications for each chart type:
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
3. [Glucose](data/glucose/glucose.txt)
4. [Glucose Med](data/glucose-med/glucose-med.txt)
5. [Med](data/med/med.txt)
6. [Pulse](data/pulse/pulse.txt)
7. [Pulse Oxygen](data/pulse-oxygen/pulse-oxygen.txt)
8. [Respiration](data/respiration/respiration.txt)
9. [Temperature](data/temperature/temperature.txt)
10. [Vitals](data/vitals/vitals.txt)
11. [Weight](data/weight/weight.txt)

Build
-----
1. ```sbt clean compile```

Test
----
1. ```sbt clean test```

Run
---
1. ```sbt run```

Usage
-----
1. click Charts menu
2. click Chart menu item
3. via the Chart Dialog, select *.csv / *.txt file
4. click 'Select' button
5. view tabbed chart ( mouseover line data points to view details )
6. close app via top-left 'x' icon
>Tabs are typed, numbered and closeable.

Assembly
--------
1. ```sbt clean test assembly copyAssemblyJar```

Execute
-------
1. ```java -jar .assembly/healthcharts-$version.jar```

Deploy
------
1. edit build.sbt ( jarVersion + version )
2. edit package.json ( version + jdeploy / jar )
3. edit app.conf ( about > alert > contentText )
4. sbt clean test assembly copyAssemblyJar
5. perform github release ( from https://github.com/objektwerks/healthcharts )
6. npm login
7. jdeploy publish ( to https://www.jdeploy.com/~healthcharts )
8. check email for npm message
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

jDeploy Install
---------------
1. Setup npm account at npmjs.com
2. Install node, which installs npm, which bundles npx.
3. Install jdeploy via npm - *npm install -g jdeploy*
4. Add icon.png ( 256x256 or 512x512 ) to project root and resources/images.
5. Edit jDeploy *package.json* as required.
6. Add *jdeploy* and *jdeploy-bundle* to .gitignore
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

jDeploy Issues
--------------
1. ***jDeploy publish*** fails due to npm *2fa* one-time password error.
    1. See: [Github Solution](https://github.com/shannah/jdeploy/issues/74)
2. ***macOS app icon*** not rendered correctly in Dock and Launchpad.
    1. Ensure app icon ( ./icon.png + ./src/main/resources/image/icon.png ) is at least 256x256. 512x512 is recommended.
    2. See objektwerks.App stage.icons, Taskbar and Toolkit code for details.

NPM Versioning
--------------
>The ```build.sbt``` **must** contain a ```semver 3-digit``` **version** number. See: [Npmjs Semver](https://docs.npmjs.com/about-semantic-versioning)

NPM Registry
------------
>Healthcharts is deployed to: https://www.npmjs.com/package/healthcharts

License
-------
>Copyright (c) [2022 - 2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.