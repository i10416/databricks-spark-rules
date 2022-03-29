## Databricks-Spark-Rules 

Scalafix rules for Databricks Spark.

| pre-release                                                                                                                                                                                                                                | release                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/s01.oss.sonatype.org/dev.i10416/databricks-spark-rules_2.13.svg)](https://s01.oss.sonatype.org/content/repositories/snapshots/dev/i10416/databricks-spark-rules_2.13/) | [![Maven Central](https://img.shields.io/maven-central/v/dev.i10416/databricks-spark-rules_2.13.svg)](https://search.maven.org/artifact/dev.i10416/databricks-spark-rules_2.13) |



## Install

sbt
```scala
ThisBuild / scalafixDependencies  += "dev.i10416" %% "databricks-spark-rules" % "0.0.1"
```

Mill
```scala
def scalafixIvyDeps = Agg(ivy"dev.i10416::databricks-spark-rules:0.0.1")
```

## How to Use
| rule                    | description                                      |
| ----------------------- | ------------------------------------------------ |
| UnsafeSparkStopCall     | warn `SparkContext#stop` and `SparkSession#stop` |
| UnsafeSparkConstructors | warn `new SparkContext`                          |
| UnsafeSysExit           | warn `System.exit(0)`                            |


## How to contribute?

- Give it a star⭐
- Drop the feedback to the author @i10416
- Send a PR with fixes of typos/bugs/etc🐛

## License

Licensed under the Apache License, Version 2.0.
