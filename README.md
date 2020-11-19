
# Feed Property API

- [Getting Started](#getting-started)
	- [Prerequisities](#prerequisities)
	- [Installing](#installing)
- [Versioning](#versioning)
- [Authors](#authors)

## Getting Started
Repositorio de ejemplos de aprendizaje de uso de Apache Flink
[Apache Flink](https://flink.apache.org)
### Prerequisities
Para ejecutar Flink tienes que tener Java 8 u 11 instalado.
Actualmente la version mas reciente es la 1.11.2. La versión a descargar tiene que ser la de scala

### Installing
Paso 1: Descargar
[https://flink.apache.org/downloads.html](https://flink.apache.org/downloads.html)

```bash
$ wget https://apache.mediamirrors.org/flink/flink-1.11.2/flink-1.11.2-bin-scala_2.11.tgz
$ tar -xzf flink-1.11.2-bin-scala_2.11.tgz
$ cd flink-1.11.2-bin-scala_2.11
```

Paso 2: Ejecutar un Cluster
```bash
$ ./bin/start-cluster.sh
Starting cluster.
Starting standalonesession daemon on host.
Starting taskexecutor daemon on host.
```

Una vez arrancado puedes acceder a la UI de Flink desde:
[http://localhost:8081](http://localhost:8081)

Paso 3: Enviar un Job
La release de Flink viene con un par de ejemplos de Jobs. Podemos enviar uno de estos a cluster que tenemos ejecutandose
```bash
$ ./bin/flink run examples/streaming/WordCount.jar
$ tail log/flink-*-taskexecutor-*.out
  (to,1)
  (be,1)
  (or,1)
  (not,1)
  (to,2)
  (be,2)
```

Paso 4: Parar el Cluster
```bash
$ ./bin/stop-cluster.sh
```

## Versioning

Current major version:

* xxxx (1.X.X)

If you want to find more details check our [CHANGELOG.MD](CHANGELOG.md)

## Authors

* **[Israel Lozano](https://www.linkedin.com/in/israel-lozano-6469bb39)**