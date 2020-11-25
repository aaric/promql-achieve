# promql-achieve

[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![spring](https://img.shields.io/badge/spring-2.3.2-brightgreen.svg?style=flat&logo=spring)](https://docs.spring.io/spring-boot/docs/2.3.x-SNAPSHOT/reference/htmlsingle)
[![gradle](https://img.shields.io/badge/gradle-6.7-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/6.7/userguide/installation.html)
[![build](https://github.com/aaric/grpc-achieve/workflows/build/badge.svg)](https://github.com/aaric/promql-achieve/actions)
[![release](https://img.shields.io/badge/release-0.5.0-blue.svg)](https://github.com/aaric/promql-achieve/releases)

> [HTTP API | Prometheus](https://prometheus.io/docs/prometheus/2.21/querying/api/)  
> Simple chart sample with PromQL.

## Java SDK

|No.|Name|Version|Remark|
|:-:|:--:|:-----:|------|
|1|[`client_java`](https://github.com/prometheus/client_java)|`0.9.0`|*Official, poor compatibility*|
|2|[`micrometer-registry-prometheus`](https://micrometer.io/docs/registry/prometheus)|`1.6.1`|*Support for spring boot 2.x*|

## Prometheus Configuration

> [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

### Static

```bash
# su - root
sh> tee -a /tmp/prometheus.yml <<-'EOF'
  - job_name: 'springboot'
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
    - targets: ['localhost:8080']
EOF
sh> docker restart prometheus

# test
promql> http_server_requests_seconds_count
```

### Dynamic

```bash
# su - root
sh> tee -a /tmp/prometheus.yml <<-'EOF'
  - job_name: 'springboot'
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    file_sd_configs:
    - files: ['/tmp/prometheus/groups/*.json']
EOF
sh> mkdir -p /tmp/prometheus/groups
sh> tee /tmp/prometheus/groups/springboot-prometheus.json <<-'EOF'
[
    {
        "targets": [
            "localhost:8080"
        ],
        "labels": {
            "instance": "springboot-prometheus",
            "service": "springboot-prometheus-service"
        }
    }
]
EOF
sh> docker restart prometheus

# test
promql> http_counter_total
```
